@file:Suppress("UselessCallOnNotNull")

package burullus.digitom.app.ui.paging

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import burullus.digitom.app.R
import burullus.digitom.app.data.network.model.responses.SearchResult
import burullus.digitom.app.ui.base.BaseActivity
import burullus.digitom.app.ui.ocrscreen.OcrCaptureActivity.Companion.head
import burullus.digitom.app.ui.ocrscreen.OcrCaptureActivity.Companion.kkssearch
import kotlinx.android.synthetic.main.paging_activity.*

/**
 *
 */
class Paging : BaseActivity(), PagingMvpView {
    private var adapter : PagingAdapter? = null
    private var isLoading : Boolean = false
    private var nextpage : String = ""
    private var previouspage : String = ""
    private var pageIndex = 1
    private var linearLayoutManager : LinearLayoutManager? = null
    private var detectedkks : String = ""

    /**
     *
     */
    lateinit var presenter : PagingMvpPresenter

    /**
     *
     */
    override fun onCreate(savedInstanceState : Bundle?) {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        super.onCreate(savedInstanceState)
        setContentView(R.layout.paging_activity)
        presenter = PagingPresenter(this)
        back.setOnClickListener {
            presenter.backpressed()
        }
        detectedkks = kkssearch
        initUI()
    }

    private fun initUI() {
        headtext.text = head
        adapter = PagingAdapter(arrayListOf(), this)
        page_recycler.layoutManager = GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false)
        page_recycler.setItemViewCacheSize(0)
        page_recycler.adapter = adapter
        getdata(detectedkks, head)
        page_recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            @SuppressLint("SyntheticAccessor")
            override fun onScrolled(page_recycler : RecyclerView, dx : Int, dy : Int) {
                super.onScrolled(page_recycler, dx, dy)

                if (!page_recycler.canScrollVertically(1)) {
                    isLoading = true
                    pageIndex++
                    if (!nextpage.isNullOrEmpty()) presenter.nextpage(
                        nextpage.replace(
                            "http",
                            "https"
                        )
                    )
                }

            }
        })
    }

    private fun getdata(detectedkks : String, head : String) {
        presenter.loaddata(detectedkks, head)
    }

    /**
     *
     */
    override fun onerror(message : String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun onsucess(mpage : burullus.digitom.app.data.network.model.responses.Paging) {
        val handler = Handler(Looper.myLooper() ?: return)
        val recyclerViewState = page_recycler.layoutManager?.onSaveInstanceState()
        handler.postDelayed({
            if (!this@Paging.isFinishing) {

                val list = mpage.results
                nextpage = mpage.next
                previouspage = mpage.previous
                page_recycler.adapter = adapter
                linearLayoutManager = LinearLayoutManager(this@Paging)
                page_recycler.layoutManager = linearLayoutManager
                adapter?.updateAllTask(list as ArrayList<SearchResult>)
                hideprogressbar()
                page_recycler.layoutManager?.onRestoreInstanceState(recyclerViewState)

                if (adapter!!.itemCount > 10) page_recycler.smoothScrollBy(0, adapter!!.getheight())
            }
        }, 500)

    }

    /**
     *
     */
    override fun showprogrssbar() {
        SPbar.visibility = View.VISIBLE
    }

    /**
     *
     */
    override fun hideprogressbar() {
        SPbar.visibility = View.INVISIBLE
    }

    /**
     *
     */
    override fun backActivity() {
        super.onBackPressed()
    }

    companion object {
        /**
         *
         */
        var dywdith : Int = 0
    }
}


