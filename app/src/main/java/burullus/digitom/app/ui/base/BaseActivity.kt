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
    fun hideKeyboard() {}

    /**
     *
     */
    override fun onPointerCaptureChanged(hasCapture: Boolean) {}

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
}