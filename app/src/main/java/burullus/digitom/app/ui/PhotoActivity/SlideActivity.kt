package burullus.digitom.app.ui.PhotoActivity

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.recyclerview.widget.GridLayoutManager
import burullus.digitom.app.R
import burullus.digitom.app.ui.base.BaseActivity
import burullus.digitom.app.ui.mainActivity.MainActivity.Companion.currentPosition
import burullus.digitom.app.ui.mainActivity.MainActivity.Companion.imagesArray
import burullus.digitom.app.ui.ocrscreen.OcrCaptureActivity.Companion.head
import kotlinx.android.synthetic.main.activity_slide.*
import javax.microedition.khronos.egl.EGL10
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.egl.EGLContext
import javax.microedition.khronos.egl.EGLDisplay


//import com.digitom.app.MainActivity.Companion.img
//import com.squareup.picasso.Picasso

/**
 *
 */
class SlideActivity : BaseActivity(), SlideMvpView {
    private var adapter: SlideAdapter? = null

    /**
     *
     */
    lateinit var presenter: SlideMvpPresenter

    /**
     *
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slide)
        presenter = SlidePresenter(this)
        backk.setOnClickListener {
            presenter.backpressed()
        }
        nextimg.setOnClickListener {
            presenter.nextimg()
        }
        previmg.setOnClickListener {
            presenter.perviousimg()
        }
        initUI()

    }

    private fun initUI() {
        imagehead.text = head
        adapter = SlideAdapter(arrayListOf(), nextimg, previmg)
        rev_img.adapter = adapter
        rev_img.layoutManager = GridLayoutManager(this, 1, GridLayoutManager.HORIZONTAL, false)
        rev_img.setItemViewCacheSize(0)
        rev_img.scrollToPosition(currentPosition)
        presenter.loaddata()
    }

    /**
     *
     */
    override fun back() {
        super.onBackPressed()
    }

    /**
     *
     */
    override fun nextimag() {
        rev_img.scrollToPosition(currentPosition + 1)
        if (currentPosition < ((adapter?.itemCount ?: return) - 1)) {
            nextimg.visibility = View.VISIBLE
            rev_img.scrollToPosition(currentPosition + 1)
            if (currentPosition + 1 == ((adapter?.itemCount ?: return) - 1)) nextimg.visibility =
                View.INVISIBLE
        }
    }

    /**
     *
     */
    override fun perviousimg() {

        if (currentPosition > 0) {
            previmg.visibility = View.VISIBLE
            rev_img.scrollToPosition(currentPosition - 1)
            if (currentPosition - 1 == 0) previmg.visibility = View.INVISIBLE
        }
    }

    /**
     *
     */
    override fun getdata() {
        adapter?.updateAllTask(imagesArray)
    }

    companion object {
        /**
         *
         */
        fun getMaximumTextureSize(): Int {
            val egl: EGL10 = EGLContext.getEGL() as EGL10
            val display: EGLDisplay = egl.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY)

            // Initialise
            val version = IntArray(2)
            egl.eglInitialize(display, version)

            // Query total number of configurations
            val totalConfigurations = IntArray(1)
            egl.eglGetConfigs(display, null, 0, totalConfigurations)

            // Query actual list configurations
            val configurationsList: Array<EGLConfig?> =
                arrayOfNulls(totalConfigurations[0])
            egl.eglGetConfigs(
                display,
                configurationsList,
                totalConfigurations[0],
                totalConfigurations
            )
            val textureSize = IntArray(1)
            var maximumTextureSize = 0

            for (i in 0 until totalConfigurations[0]) {
                egl.eglGetConfigAttrib(
                    display,
                    configurationsList[i],
                    EGL10.EGL_MAX_PBUFFER_WIDTH,
                    textureSize
                )

                // Keep track of the maximum texture size
                if (maximumTextureSize < textureSize[0]) {
                    maximumTextureSize = textureSize[0]
                }
            }

            egl.eglTerminate(display)

            return maximumTextureSize
        }

    }


}







