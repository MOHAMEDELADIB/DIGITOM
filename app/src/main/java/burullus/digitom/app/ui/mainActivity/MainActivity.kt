package burullus.digitom.app.ui.mainActivity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import burullus.digitom.app.R
import burullus.digitom.app.data.network.api.ElectricalHeader
import burullus.digitom.app.data.network.api.ICHeader
import burullus.digitom.app.data.network.api.MechenicalHeader
import burullus.digitom.app.data.network.api.OperationHeaders
import burullus.digitom.app.data.network.model.*
import burullus.digitom.app.ui.base.BaseActivity
import burullus.digitom.app.ui.home.Home
import burullus.digitom.app.ui.ocrscreen.OcrCaptureActivity
import burullus.digitom.app.ui.ocrscreen.OcrCaptureActivity.Companion.getkks
import burullus.digitom.app.ui.ocrscreen.OcrCaptureActivity.Companion.head
import kotlinx.android.synthetic.main.activity_main2.*

/**
 *
 */
/**
 *
 */
@Suppress("SENSELESS_COMPARISON")
class MainActivity : BaseActivity(), MainMvpview {
    private var mainAdapter: MainAdapter? = null
    private var mainAdapter2: MainAdapter? = null
    private var mainAdapter3: MainAdapter? = null
    private var detectedkks = ""
    /**
     *
     */
    /**
     *
     */
    lateinit var presenter: MainMvpPresenter
    /**
     *
     */
    /**
     *
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        setContentView(R.layout.activity_main2)
        activityopened = true
        presenter = MainPresenter(this)
        dback.setOnClickListener {
            presenter.backActivity()
        }
        hdata.setOnClickListener {
            presenter.homeActivity()
        }

        detectedkks = getkks()?.text.toString()
        initUI()
    }

    private fun initUI() {
        headtextdata.text = head
        eqkks.text = detectedkks
        mainAdapter = MainAdapter(arrayListOf(), this)
        mainAdapter2 = MainAdapter(arrayListOf(), this)
        mainAdapter3 = MainAdapter(arrayListOf(), this)
        rv_images.adapter = mainAdapter
        rv_images2.adapter = mainAdapter2
        rv_images3.adapter = mainAdapter3
        rv_images.layoutManager = GridLayoutManager(this, 1, GridLayoutManager.HORIZONTAL, false)
        rv_images3.layoutManager = GridLayoutManager(this, 1, GridLayoutManager.HORIZONTAL, false)
        rv_images2.layoutManager = GridLayoutManager(this, 1, GridLayoutManager.HORIZONTAL, false)
        setHeaderNames()

        getdata(detectedkks, head)
    }

    private fun setHeaderNames() {
        when (head) {
            "Operations " -> {
                textView8.text = OperationHeaders[0]
                textView9.text = OperationHeaders[1]
                textView11.text = OperationHeaders[2]
                textView12.text = OperationHeaders[3]
            }
            "Mechanical " -> {
                textView8.text = MechenicalHeader[0]
                textView9.text = MechenicalHeader[1]
                textView11.text = MechenicalHeader[2]
                textView12.text = MechenicalHeader[3]
            }
            "Electrical " -> {
                textView8.text = ElectricalHeader[0]
                textView9.text = ElectricalHeader[1]
                textView11.text = ElectricalHeader[2]
                textView12.text = ElectricalHeader[3]
            }
            "I&C " -> {
                textView8.text = ICHeader[0]
                textView9.text = ICHeader[1]
                textView11.text = ICHeader[2]
                textView12.text = ICHeader[3]
            }
        }
    }

    /**
     *
     */
    /**
     *
     */
    override fun getdata(kks: String, header: String) {
        presenter.getdata(detectedkks, header)
    }

    /**
     *
     */
    /**
     *
     */
    override fun showProgress() {
        scrollLayout.visibility = View.INVISIBLE
        progressBar3.visibility = View.VISIBLE
    }

    /**
     *
     */
    /**
     *
     */
    override fun hideProgress() {
        scrollLayout.visibility = View.VISIBLE
        progressBar3.visibility = View.INVISIBLE
    }

    /**
     *
     */
    /**
     *
     */
    override fun back() {
        val intent = Intent(this@MainActivity, OcrCaptureActivity::class.java)
        startActivity(intent)
    }

    /**
     *
     */
    /**
     *
     */
    override fun homePage() {
        val intent = Intent(this@MainActivity, Home::class.java)
        startActivity(intent)
    }

    /**
     *
     */
    /**
     *
     */
    override fun operationData(data: OperationData) {
        if (data.fault != null) {
            val fault = ArrayList<DataSheet>()
            fault.add(data.fault)
            mainAdapter2?.updateAllTask(fault)
        }
        if (!data.system.pid.isNullOrEmpty()) {
            mainAdapter3?.updateAllTask(data.system.pid)
        }
        if (!data.system.trouble.isNullOrEmpty()) {
            mainAdapter?.updateAllTask(data.system.trouble)
        }
        eqdesc.text = data.description
        sysdesc.text = data.system.description
        updateUI()
    }

    /**
     *
     */
    /**
     *
     */
    override fun mechanicalData(data: MechenicalData) {
        if (!data.dataSheet.isNullOrEmpty()) mainAdapter?.updateAllTask(data.dataSheet)
        if (!data.drawing.isNullOrEmpty()) mainAdapter2?.updateAllTask(data.drawing)
        if (!data.mdata.isNullOrEmpty()) mainAdapter3?.updateAllTask(data.mdata)
        if (data.description.isNotEmpty()) eqdesc.text = data.description
        if (data.system.description.isNotEmpty()) sysdesc.text = data.system.description
        updateUI()
    }

    /**
     *
     */
    /**
     *
     */
    override fun electricalData(data: ElectricalData) {
        if (!data.dataSheet.isNullOrEmpty()) mainAdapter3?.updateAllTask(data.dataSheet)
        if (!data.wiring.isNullOrEmpty()) mainAdapter2?.updateAllTask(data.wiring)
        if (!data.trouble.isNullOrEmpty()) {
            mainAdapter?.updateAllTask(data.trouble)
        }
        if (data.description.isNotEmpty()) eqdesc.text = data.description
        if (data.system.description.isNotEmpty()) sysdesc.text = data.system.description

        updateUI()
    }

    /**
     *
     */
    /**
     *
     */
    override fun icData(data: ICData) {
        if (!data.datasheet.isNullOrEmpty()) mainAdapter?.updateAllTask(data.datasheet)
        if (!data.faults.isNullOrEmpty()) mainAdapter2?.updateAllTask(data.faults)
        if (!data.interconnection.isNullOrEmpty()) mainAdapter3?.updateAllTask(data.interconnection)
        if (data.description.isNotEmpty()) eqdesc.text = data.description
        if (data.system.description.isNotEmpty()) sysdesc.text = data.system.description
        updateUI()
    }

    /**
     *
     */
    /**
     *
     */
    override fun onerror(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun updateUI() {
        if (mainAdapter?.itemCount == 0) {
            textView8.text = ""
            val lp = textView8.layoutParams as ConstraintLayout.LayoutParams
            lp.height = 0
            lp.setMargins(0, 0, 0, 0)
            textView8.layoutParams = lp
            textView8.visibility = View.INVISIBLE
            rv_images.visibility = View.INVISIBLE
        }
        if (mainAdapter2?.itemCount == 0) {
            textView9.text = ""
            val lp = textView9.layoutParams as ConstraintLayout.LayoutParams
            lp.height = 0
            lp.setMargins(0, 0, 0, 0)
            textView9.layoutParams = lp
            textView9.visibility = View.INVISIBLE
            rv_images2.visibility = View.INVISIBLE
        }
        if (mainAdapter3?.itemCount == 0) {
            textView11.text = ""
            val lp = textView11.layoutParams as ConstraintLayout.LayoutParams
            lp.height = 0
            lp.setMargins(0, 0, 0, 0)
            textView11.layoutParams = lp
            textView11.visibility = View.INVISIBLE
            rv_images3.visibility = View.INVISIBLE
        }
        if (info1.text == "" && info2.text == "" && info3.text == "") {
            textView12.text = ""
            val lp = textView12.layoutParams as ConstraintLayout.LayoutParams
            lp.height = 0
            lp.setMargins(0, 0, 0, 0)
            textView12.layoutParams = lp
            textView12.visibility = View.INVISIBLE
        }

    }

    companion object {
        /**
         *
         */
        /**
         *
         */
        lateinit var imagesArray: List<DataSheet>
        /**
         *
         */
        /**
         *
         */
        var currentPosition: Int = 0
        /**
         *
         */
        /**
         *
         */
        var currentImage: String = ""
        /**
         *
         */
        /**
         *
         */
        var activityopened: Boolean = false
    }

}

