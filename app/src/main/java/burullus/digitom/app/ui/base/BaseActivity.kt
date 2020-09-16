package burullus.digitom.app.ui.base

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Rect
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import burullus.digitom.app.ui.splash.SplashActivity.Companion.onetime
import com.squareup.picasso.LruCache
import com.squareup.picasso.Picasso

/**
 *
 */
abstract class BaseActivity : AppCompatActivity() {
    /**
     *
     */
    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val v = currentFocus
            if (v is EditText) {
                val outRect = Rect()
                v.getGlobalVisibleRect(outRect)
                if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                    v.clearFocus()
                    val imm =
                        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0)
                }
            }
        }
        return super.dispatchTouchEvent(event)
    }
    /**
     *
     */
    fun setuppicasso() {
        if (!onetime) {
            onetime = true
            val options = BitmapFactory.Options()
            options.inSampleSize = 25
            options.inPreferredConfig = Bitmap.Config.RGB_565
            val picasso = Picasso.Builder(this)
                .memoryCache(LruCache(1024 * 100 * 1024))
                .build()
            Picasso.setSingletonInstance(picasso)
        }
    }
    /**
     *

    override fun onWindowFocusChanged(hasFocus: Boolean) {
    super.onWindowFocusChanged(hasFocus)
    if (hasFocus) hideSystemUI()
    }

    private fun hideSystemUI() {
    // Enables regular immersive mode.
    // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
    // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
    window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
    // Set the content to appear under the system bars so that the
    // content doesn't resize when the system bars hide and show.
    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
    // Hide the nav bar and status bar
    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
    or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }

    // Shows the system bars by removing all the flags
    // except for the ones that make the content appear under the system bars.
    private fun showSystemUI() {
    window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
    }  */

}
