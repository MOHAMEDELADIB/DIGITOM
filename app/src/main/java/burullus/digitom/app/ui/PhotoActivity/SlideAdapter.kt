package burullus.digitom.app.ui.PhotoActivity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import burullus.digitom.app.R
import burullus.digitom.app.data.network.model.DataSheet
import burullus.digitom.app.ui.PhotoActivity.SlideActivity.Companion.getMaximumTextureSize
import burullus.digitom.app.ui.mainActivity.MainActivity.Companion.currentPosition
import burullus.digitom.app.ui.mainActivity.MainActivity.Companion.imagesArray
import com.github.chrisbanes.photoview.PhotoView
import com.squareup.picasso.Picasso

class SlideAdapter(
    private var imagelist: List<DataSheet>,
    private val next: ImageButton,
    private val pervious: ImageButton
) : RecyclerView.Adapter<SlideAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SlideAdapter.MyViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val photoView = inflater.inflate(R.layout.item_image2, parent, false)
        return MyViewHolder(photoView)
    }

    fun updateAllTask(list: List<DataSheet>) {
        imagelist = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return imagelist.size
    }

    override fun onBindViewHolder(holder: SlideAdapter.MyViewHolder, position: Int) {
        currentPosition = position
        if (currentPosition == (itemCount - 1)) next.visibility =
            View.INVISIBLE else next.visibility = View.VISIBLE
        if (currentPosition == 0) pervious.visibility = View.INVISIBLE else pervious.visibility =
            View.VISIBLE
        val imageView = holder.photoImageView
        val photo = imagesArray[position].image
        val height = getMaximumTextureSize() * .85

        val swdith = height.toInt()
        Picasso.get()
            .load(photo)
            .placeholder(R.drawable.download)
            .error(R.drawable.errord)
            .resize((swdith), 0)
            .onlyScaleDown()
            .into(imageView)
    }

    inner class MyViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var photoImageView: PhotoView = itemView.findViewById(R.id.imgfs)

    }


}