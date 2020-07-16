@file:Suppress("UNCHECKED_CAST")

package burullus.digitom.app.ui.home
//import com.google.android.gms.location.places.ui.PlacePicker

import android.Manifest
import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.app.ActivityCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import burullus.digitom.app.R
import burullus.digitom.app.data.network.api.NewsUrl
import burullus.digitom.app.data.network.model.ArticleData
import burullus.digitom.app.ui.base.BaseActivity
import burullus.digitom.app.ui.login.Login
import burullus.digitom.app.ui.ocrscreen.OcrCaptureActivity
import com.google.android.material.navigation.NavigationView
import com.leinardi.android.speeddial.SpeedDialView
import kotlinx.android.synthetic.main.activity_home.*


/**
 *
 */
/**
 *
 */
/**
 *
 */
@Suppress("DEPRECATED_IDENTITY_EQUALS")
class Home : BaseActivity(), HomeMvpView {

    /**
     *
     */
    /**
     *
     */
    /**
     *
     */
    lateinit var presenter: HomeMvpPresenter
    private lateinit var adapter: NewsAdapter
    private var drawerLayout: DrawerLayout? = null
    private var navigationView: NavigationView? = null
    private var mDrawerToggle: ActionBarDrawerToggle? = null
    private var speedDialView: SpeedDialView? = null
    private var requestCallPhone = 101
    /**
     *
     */
    /**
     *
     */
    /**
     *
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            , WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        setContentView(R.layout.activity_home)
        navigationView = findViewById(R.id.nav_view)
        drawerLayout = findViewById(R.id.drawer_layout)
        val menuViewItem = navigationView?.menu?.findItem(R.id.menn)
        val signout = menuViewItem?.actionView?.findViewById<Button>(R.id.signout)

        speedDialView = findViewById(R.id.speedDial)

        signout?.setOnClickListener {
            presenter.signOutPressed()
        }
        smanuu.setOnClickListener {
            presenter.menupressed()
        }
        presenter = HomePresenter(this)
        initUI()
        initDrawer()
    }

    private fun initUI() {
        adapter = NewsAdapter(mutableListOf(), this)
        my_news.adapter = adapter
        my_news.layoutManager = LinearLayoutManager(this)
        getnews()
        mDrawerToggle = object : ActionBarDrawerToggle(
            this,
            drawerLayout,
            R.string.openNavDrawer,
            R.string.closeNavDrawer
        ) {
            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
                hideKeyboard()
            }


        }
        //val mDrawerToggle: ActionBarDrawerToggle? = null
        mDrawerToggle?.let { (drawerLayout ?: return@let).addDrawerListener(it) }
        mDrawerToggle?.syncState()
        setupNavMenu()
        setupSpeedDail()
        presenter.onNavMenuCreated()
        presenter.onViewInitialized()

    }

    private fun setupSpeedDail() {
        speedDialView?.inflate(R.menu.menu_speed)
        speedDialView?.setOnActionSelectedListener(
            SpeedDialView.OnActionSelectedListener
            { actionItem ->
                when (actionItem.id) {
                    R.id.contact1 -> {
                        presenter.contactpressed(1)
                        return@OnActionSelectedListener true // false will close it without animation
                    }
                    R.id.contact2 -> {
                        presenter.contactpressed(2)
                        return@OnActionSelectedListener true // false will close it without animation
                    }
                    R.id.contact3 -> {
                        presenter.contactpressed(3)
                        return@OnActionSelectedListener true // false will close it without animation
                    }
                    R.id.contact4 -> {
                        presenter.contactpressed(4)
                        return@OnActionSelectedListener true // false will close it without animation
                    }
                }
                false
            })
    }

    private fun setupNavMenu() {
        navigationView?.setNavigationItemSelectedListener { item ->
            drawerLayout!!.closeDrawer(GravityCompat.START)
            when (item.itemId) {
                R.id.operscr -> {
                    presenter.drawerOperationPressed()
                    true
                }
                R.id.mechscr -> {
                    presenter.drawerMechanicalPressed()
                    true
                }
                R.id.icscr -> {
                    presenter.drawerICPressed()
                    true
                }
                R.id.elecscr -> {
                    presenter.drawerElectricalPressed()
                    true
                }
                else -> false
            }
        }
    }

    /**
     *
     */
    /**
     *
     */
    /**
     *
     */
    override fun getnews() {
        presenter.getnews(NewsUrl)

    }

    /**
     *
     */
    /**
     *
     */
    /**
     *
     */
    override fun onsuccess(news: List<ArticleData>) {
        adapter.updateAllTask(news.toMutableList())
    }

    /**
     *
     */
    /**
     *
     */
    /**
     *
     */
    override fun onerror(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun initDrawer() {
        if (drawerLayout != null) {
            (drawerLayout ?: return).closeDrawer(GravityCompat.START)
        }
    }

    /**
     *
     */
    /**
     *
     */
    /**
     *
     */
    override fun lockDrawer() {
        if (drawerLayout != null) (drawerLayout
            ?: return).setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }

    /**
     *
     */
    /**
     *
     */
    /**
     *
     */
    override fun unlockDrawer() {
        if (drawerLayout != null) (drawerLayout
            ?: return).setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
    }

    /**
     *
     */
    /**
     *
     */
    /**
     *
     */
    override fun opendrawer() {
        if (drawerLayout != null) {
            drawerLayout?.run { openDrawer(GravityCompat.START) }
        }
    }

    /**
     *
     */
    /**
     *
     */
    /**
     *
     */
    override fun singout(message: String) {

        val intent = Login.getStartIntent(this@Home as Context)
        this@Home.startActivity(intent)
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    /**
     *
     */
    /**
     *
     */
    /**
     *
     */
    override fun openOperation() {
        val intent = OcrCaptureActivity.getStartIntent(this@Home as Context)
        intent?.putExtra("header", "Operations ")
        this@Home.startActivity(intent)
    }

    /**
     *
     */
    /**
     *
     */
    /**
     *
     */
    override fun openMechanical() {
        val intent = OcrCaptureActivity.getStartIntent(this@Home as Context)
        intent?.putExtra("header", "Mechanical ")
        this@Home.startActivity(intent)
    }

    /**
     *
     */
    /**
     *
     */
    /**
     *
     */
    override fun openIC() {
        val intent = OcrCaptureActivity.getStartIntent(this@Home as Context)
        intent?.putExtra("header", "I&C ")
        this@Home.startActivity(intent)
    }

    /**
     *
     */
    /**
     *
     */
    /**
     *
     */
    override fun openElectrical() {
        val intent = OcrCaptureActivity.getStartIntent(this@Home as Context)
        intent?.putExtra("header", "Electrical ")
        this@Home.startActivity(intent)
    }

    /**
     *
     */
    /**
     *
     */
    /**
     *
     */
    @SuppressLint("LogNotTimber")
    override fun callNumber(number: String) {

        try {
            val callIntent = Intent(Intent.ACTION_CALL)
            callIntent.data = Uri.parse("tel:$number")
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) !==
                PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this, arrayOf(Manifest.permission.CALL_PHONE),
                    requestCallPhone
                )
                return
            }
            startActivity(callIntent)
        } catch (e: ActivityNotFoundException) {
            Log.e("digitom", "Call failed", e)
        }

    }

    /**
     *
     */
    /**
     *
     */
    /**
     *
     */
    override fun closeSpeedDial() {
        speedDialView?.close()
    }

    companion object {
        /**
         *
         */
        /**
         *
         */
        /**
         *
         */
        fun getStartIntent(context: Context?): Intent? {
            return Intent(context, Home::class.java)

        }
    }

    /**
     *
     */
    /**
     *
     */
    /**
     *
     */
    override fun onResume() {
        super.onResume()
        if (drawerLayout != null) (drawerLayout
            ?: return).setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
    }

    /**
     *
     */
    /**
     *
     */
    /**
     *
     */
    override fun onBackPressed() {
    }
}