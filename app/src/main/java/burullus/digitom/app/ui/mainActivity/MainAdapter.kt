package burullus.digitom.app.ui.mainActivity

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import burullus.digitom.app.R
import burullus.digitom.app.data.network.model.DataSheet
import burullus.digitom.app.ui.PhotoActivity.SlideActivity
import burullus.digitom.app.ui.mainActivity.MainActivity.Companion.currentImage
import burullus.digitom.app.ui.mainActivity.MainActivity.Companion.currentPosition
import burullus.digitom.app.ui.mainActivity.MainActivity.Companion.imagesArray
import com.squareup.picasso.Picasso

/**
 *
 */
class MainAdapter(private var imagelist: List<DataSheet>, private val ctx: Context?) :
    RecyclerView.Adapter<MainAdapter.MyViewHolder>() {
    /**
     *
     */
    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): MyViewHolder {
        val view = LayoutInflater.from(ctx?.applicationContext)
            .inflate(R.layout.item_image, parent, false)
        return MyViewHolder(view)
    }

    /**
     *
     */
    fun updateAllTask(list: List<DataSheet>) {
        imagelist = list
        notifyDataSetChanged()
    }

    /**
     *
     */
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val image = imagelist[position]
        val imageView = holder.photoImageView
        this.ctx?.let {
            Picasso.get()
                .load(image.image)
                .tag(it)
                .placeholder(R.drawable.download)
                .error(R.drawable.errord)
                .fit()
                .centerCrop()
                .into(imageView)
        }
    }

    /**
     *
     */
    override fun getItemCount(): Int {
        return imagelist.size
    }

    /**
     *
     */
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        /**
         *
         */
        var photoImageView: ImageView = itemView.findViewById(R.id.iv_photo)

        init {
            itemView.setOnClickListener(this)
        }

        /**
         *
         */
        override fun onClick(view: View) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                imagesArray = imagelist
                currentImage = imagelist[position].image
                currentPosition = position
                val intent = Intent(ctx, SlideActivity::class.java)
                ctx?.startActivity(intent)
            }
        }
    }

}

