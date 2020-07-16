package burullus.digitom.app.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import burullus.digitom.app.R
import burullus.digitom.app.data.network.model.ArticleData
import burullus.digitom.app.utils.JustifyTextView

/**
 *
 */
class NewsAdapter(private var newsList: MutableList<ArticleData>, private val ctx: Context) :
    RecyclerView.Adapter<NewsAdapter.MyViewHolder>() {
    /**
     *
     */
    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): MyViewHolder {
        val view = LayoutInflater.from(ctx.applicationContext)
            .inflate(R.layout.news_text, parent, false)
        return MyViewHolder(view)
    }

    /**
     *
     */
    fun updateAllTask(list: MutableList<ArticleData>) {
        newsList = list
        notifyDataSetChanged()
    }

    /**
     *
     */
    override fun onBindViewHolder(
        holder: MyViewHolder,
        position: Int
    ) {
        holder.body.text = newsList[position].body
    }

    /**
     *
     */
    override fun getItemCount(): Int {
        return newsList.size
    }

    /**
     *
     */
    inner class MyViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        /**
         *
         */
        var body: JustifyTextView = itemView.findViewById(R.id.body)

    }

}