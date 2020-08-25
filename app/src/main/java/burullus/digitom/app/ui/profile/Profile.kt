package burullus.digitom.app.ui.profile

import android.os.Bundle
import android.view.WindowManager
import burullus.digitom.app.R
import burullus.digitom.app.ui.base.BaseActivity

class Profile : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_page)

    }
}