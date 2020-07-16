package burullus.digitom.app.ui.ocrscreen.dialog

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Canvas
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import burullus.digitom.app.APPContext
import burullus.digitom.app.R
import burullus.digitom.app.ui.mainActivity.MainActivity
import burullus.digitom.app.ui.ocrscreen.OcrCaptureActivity
import burullus.digitom.app.ui.ocrscreen.OcrCaptureActivity.Companion.getkks
import burullus.digitom.app.ui.ocrscreen.OcrCaptureActivity.Companion.head
import java.util.*

/**
 *
 */
class DiAlog(activity: OcrCaptureActivity) : Dialog(activity),
    DialogView {
    /**
     *
     */
    lateinit var presenter: DialogPresenter
    private var text: EditText? = null
    private val thisDiAlog: DiAlog = this
    private var mcontext: Context? = null

    /**
     *
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog)
        val search = findViewById<Button>(R.id.dsearch)
        mcontext = APPContext.applicationContext()
        presenter = DialogPresenter(this)
        search.setOnClickListener {
            presenter.send(text?.text.toString().toUpperCase(Locale.ENGLISH), head)
        }
        initalize()
        val canvas = Canvas()
        canvas.maximumBitmapHeight
    }

    private fun initalize() {
        text = findViewById(R.id.mkks2)

    }

    /**
     *
     */
    override fun onsucess(KKS: String) {

        (getkks() ?: return).text = text?.text.toString()
        val intent = Intent(mcontext, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)

        mcontext?.startActivity(intent)

    }

    /**
     *
     */
    override fun onerror(message: String) {
        Toast.makeText(mcontext, message, Toast.LENGTH_SHORT).show()
    }

    /**
     *
     */
    override fun close() {
        thisDiAlog.dismiss()
    }

}