@file:Suppress("DEPRECATION")

package burullus.digitom.app.ui.ocrscreen

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.hardware.Camera
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import burullus.digitom.app.APPContext
import burullus.digitom.app.R
import burullus.digitom.app.ui.base.BaseActivity
import burullus.digitom.app.ui.home.Home
import burullus.digitom.app.ui.mainActivity.MainActivity
import burullus.digitom.app.ui.ocrscreen.dialog.DiAlog
import burullus.digitom.app.ui.ocrscreen.ocrutils.CameraSourcePreview
import burullus.digitom.app.ui.ocrscreen.ocrutils.GraphicOverlay
import burullus.digitom.app.ui.ocrscreen.ocrutils.OcrDetectorProcessor
import burullus.digitom.app.ui.ocrscreen.ocrutils.OcrGraphic
import burullus.digitom.app.utils.CameraSource
import com.google.android.gms.vision.text.TextBlock
import com.google.android.gms.vision.text.TextRecognizer
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.ocr_capture.*
import java.io.IOException
import java.util.*


/**
 * Activity for the Ocr Detecting app.  This app detects text and displays the value with the
 * rear facing camera. During detection overlay graphics are drawn to indicate the position,
 * size, and contents of each TextBlock.
 */
@SuppressLint("StaticFieldLeak")
@Suppress("DEPRECATION")
class OcrCaptureActivity : BaseActivity(), OcrMvpView {
    private var flash: Boolean = false
    private var activity: OcrCaptureActivity? = null
    lateinit var presenter: OcrMvpPresenter
    private var cameraSource: CameraSource? = null
    private var preview: CameraSourcePreview? = null
    private var graphicOverlay: GraphicOverlay<OcrGraphic>? = null
    private var scaleGestureDetector: ScaleGestureDetector? = null
    private var gestureDetector: GestureDetector? = null
    private var tts: TextToSpeech? = null
    private val focusTag = "AutoFocus"
    private val cameraRc = 2
    private val permissions = arrayOf(Manifest.permission.CAMERA)
    public override fun onCreate(bundle: Bundle?) {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        super.onCreate(bundle)
        setContentView(R.layout.ocr_capture)
        head = intent.extras?.getString("header").toString()
        preview = findViewById(R.id.preview)
        graphicOverlay = findViewById(R.id.graphicOverlay)
        kksview = findViewById<EditText>(R.id.kkstext)
        presenter = OcrPresenter(this)
        val flasher = findViewById<ImageView>(R.id.manual)
        gestureDetector = GestureDetector(this, CaptureGestureListener())
        scaleGestureDetector = ScaleGestureDetector(this, ScaleListener())
        headtextocr.text = head
        activity = this
        flasher.setOnClickListener {
            presenter.flashPressed()
        }
        kksview?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                presenter.kksdetected(getkks()?.text.toString(), head)
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }


        })
        oback.setOnClickListener {
            presenter.backpressed()
        }
        val mkks = findViewById<ImageView>(R.id.mkks)
        kkstext.visibility = View.VISIBLE
        mkks.setOnClickListener {
            presenter.searchpressed()
        }

        initUI()
    }

    private fun initUI() {
        val rc = ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
        if (rc == PackageManager.PERMISSION_GRANTED) {
            createCameraSource()
        } else {
            requestCameraPermission()
        }
        Snackbar.make(
            graphicOverlay!!, "Tap to Speak. Pinch/Stretch to zoom", Snackbar.LENGTH_LONG
        ).show()
        val listener = TextToSpeech.OnInitListener { status ->
            if (status == TextToSpeech.SUCCESS) {
                tts!!.language = Locale.US
            }
        }
        tts = TextToSpeech(this.applicationContext, listener)
    }

    private fun requestCameraPermission() {
        if (!ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.CAMERA
            )
        ) {
            ActivityCompat.requestPermissions(this, permissions, cameraRc)
            return
        }
        val listener = View.OnClickListener {
            ActivityCompat.requestPermissions(this, permissions, cameraRc)
        }

        Snackbar.make(
            graphicOverlay!!,
            R.string.permission_camera_rationale,
            Snackbar.LENGTH_INDEFINITE
        )
            .setAction(R.string.ok, listener)
            .show()
    }

    override fun onTouchEvent(e: MotionEvent): Boolean {
        val b = scaleGestureDetector!!.onTouchEvent(e)

        val c = gestureDetector!!.onTouchEvent(e)
        return b || c || super.onTouchEvent(e)
    }

    private fun createCameraSource() {
        val context = applicationContext
        val textRecognizer = TextRecognizer.Builder(context).build()
        textRecognizer.setProcessor(graphicOverlay?.let {
            OcrDetectorProcessor(it)
        })

        if (!textRecognizer.isOperational) {
            val lowstorageFilter = IntentFilter(Intent.ACTION_DEVICE_STORAGE_LOW)
            val hasLowStorage = registerReceiver(null, lowstorageFilter) != null
            if (hasLowStorage) {
                Toast.makeText(
                    this,
                    R.string.low_storage_error, Toast.LENGTH_LONG
                ).show()
            }
        }
        cameraSource = CameraSource.Builder(applicationContext, textRecognizer)
            .setFacing(CameraSource.CAMERA_FACING_BACK)
            .setRequestedPreviewSize(1280, 1024)
            .setRequestedFps(120.0f)
            .setFlashMode(if (flash) Camera.Parameters.FLASH_MODE_TORCH else null)
            .setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO)
            .build()
    }

    override fun onResume() {
        super.onResume()
        presenter.activityresume()
    }

    override fun onPause() {
        super.onPause()
        presenter.activityPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.activityDestroy()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode != cameraRc) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            return
        }
        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            intent.getBooleanExtra(focusTag, true)
            createCameraSource()
            return
        }
        val listener = DialogInterface.OnClickListener { _, _ -> finish() }
        val builder = AlertDialog.Builder(this)
        builder.setTitle("digitom")
            .setMessage(R.string.no_camera_permission)
            .setPositiveButton(R.string.ok, listener)
            .show()
    }

    @Throws(SecurityException::class)
    override fun startCameraSource() {
        if (cameraSource != null) {
            try {
                preview!!.start(cameraSource, graphicOverlay)
            } catch (e: IOException) {
                cameraSource!!.release()
                cameraSource = null
            }

        }
    }

    override fun onsucess(KKS: String) {
        val intent = Intent(this@OcrCaptureActivity, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onerror(messsage: String) {
        Toast.makeText(this, messsage, Toast.LENGTH_SHORT).show()
    }


    override fun stopview() {
        if (preview != null) {
            preview!!.stop()
        }
    }

    private fun onTap(rawX: Float, rawY: Float): Boolean {
        val graphic = graphicOverlay!!.getGraphicAtLocation(rawX, rawY)
        var text: TextBlock? = null
        if (graphic != null) {
            text = graphic.textBlock
            if (text.value != null) {
                // Speak the string.
                tts!!.speak(text.value, TextToSpeech.QUEUE_ADD, null, "DEFAULT")
            }
        }
        return text != null
    }

    private inner class CaptureGestureListener : GestureDetector.SimpleOnGestureListener() {

        override fun onSingleTapConfirmed(e: MotionEvent): Boolean {
            return onTap(e.rawX, e.rawY) || super.onSingleTapConfirmed(e)
        }
    }

    private inner class ScaleListener : ScaleGestureDetector.OnScaleGestureListener {

        override fun onScale(detector: ScaleGestureDetector): Boolean {
            return false
        }

        override fun onScaleBegin(detector: ScaleGestureDetector): Boolean {
            return true
        }

        override fun onScaleEnd(detector: ScaleGestureDetector) {
            if (cameraSource != null) {
                cameraSource!!.doZoom(detector.scaleFactor)
            }
        }
    }

    companion object {
        var head: String = ""
        var kksview: TextView? = null
        var kks = ""
        fun getkks(): TextView? {
            return kksview
        }

        fun getStartIntent(context: Context?): Intent? {
            return Intent(context, OcrCaptureActivity::class.java)
        }
    }

    override fun backActivity() {
        val intent = Intent(this@OcrCaptureActivity, Home::class.java)
        // finish()
        startActivity(intent)
    }

    override fun flasher() {
        if (APPContext.applicationContext().packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {
            if (!flash) {
                flash = true
                cameraSource?.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH)
            } else {
                flash = false
                cameraSource?.setFlashMode(Camera.Parameters.FLASH_MODE_OFF)
            }
        } else {
            manual.visibility = View.INVISIBLE
        }
    }

    override fun dailog() {
        val yourDialog = DiAlog(activity!!)
        yourDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        yourDialog.show()
    }
}
