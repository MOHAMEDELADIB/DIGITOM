@file:Suppress("NAME_SHADOWING")

package burullus.digitom.app.ui.paging

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.BackgroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import burullus.digitom.app.R
import burullus.digitom.app.data.network.model.responses.SearchResult
import burullus.digitom.app.ui.mainActivity.MainActivity
import burullus.digitom.app.ui.ocrscreen.OcrCaptureActivity.Companion.kkssearch
import kotlinx.android.synthetic.main.pagetext.view.*
import java.util.*


/**
 *
 */
class PagingAdapter(

    private var pagelist : ArrayList<SearchResult>,
    private val ctx : Context,

    ) : RecyclerView.Adapter<PagingAdapter.MyViewHolder>() {
    /**
     *
     */
    private var view : View? = null
    override fun onCreateViewHolder(
        parent : ViewGroup, viewType : Int
    ) : MyViewHolder {
        view = LayoutInflater.from(ctx.applicationContext)
            .inflate(R.layout.pagetext, parent, false)

        return MyViewHolder(view)
    }

    /**
     *
     */
    fun updateAllTask(list : ArrayList<SearchResult>) {
        pagelist.addAll(list)
        notifyItemInserted(pagelist.size)
    }

    /**
     *
     */
    fun getheight() : Int {
        view!!.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
        if (view!!.constrain_size.measuredHeight > 0) return view!!.constrain_size.measuredHeight
        return 0
    }

    /**
     *
     */
    override fun onBindViewHolder(
        holder : MyViewHolder,
        position : Int
    ) {
        holder.kkstext.text = pagelist[position].kks
        holder.kksdescription.text = pagelist[position].description
        setHighLightedText(holder.kkstext, kkssearch.toUpperCase(Locale.US))
    }

    /**
     *
     */
    override fun getItemCount() : Int {
        return pagelist.size
    }

    /**
     *
     */


    inner class MyViewHolder(itemView : View?) :
        RecyclerView.ViewHolder(itemView!!), View.OnClickListener {
        /**
         *
         */
        var kkstext : TextView = itemView!!.findViewById(R.id.kkstext2)

        /**
         *
         */
        var kksdescription : TextView = itemView!!.findViewById(R.id.kksdescription2)

        /**
         *
         */
        private var image : ImageButton = itemView!!.findViewById(R.id.imageView6)

        var constraintLayout : ConstraintLayout = itemView!!.findViewById(R.id.constrain_size)

        init {
            itemView!!.setOnClickListener(this)
            image.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    //kkssearch = pagelist[position].kks
                    val intent = Intent(ctx, MainActivity::class.java)
                    intent.putExtra("pressedkks", pagelist[position].kks)
                    ctx.startActivity(intent)
                }
            }
        }

        /**
         *
         */
        override fun onClick(view : View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                kkssearch = pagelist[position].kks
                val intent = Intent(ctx, MainActivity::class.java)
                ctx.startActivity(intent)
            }
        }

    }

    /**
     *
     */
    private fun setHighLightedText(tv : TextView, textToHighlight : String) {
        val tvt = tv.text.toString()
        var ofe = tvt.indexOf(textToHighlight, 0)
        val wordToSpan : Spannable = SpannableString(tv.text)
        var ofs = 0
        while (ofs < tvt.length && ofe != -1) {
            ofe = tvt.indexOf(textToHighlight, ofs)
            if (ofe == -1) break else {
                // set color here
                wordToSpan.setSpan(
                    BackgroundColorSpan(Color.parseColor("#808A00E5")),
                    ofe,
                    ofe + textToHighlight.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                tv.setText(wordToSpan, TextView.BufferType.SPANNABLE)
            }
            ofs = ofe + 1
        }
    }
}