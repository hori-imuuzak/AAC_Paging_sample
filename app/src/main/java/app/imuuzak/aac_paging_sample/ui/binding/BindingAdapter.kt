package app.imuuzak.aac_paging_sample.ui.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

class BindingAdapter {
    companion object {
        @JvmStatic
        @BindingAdapter("bind:src")
        fun setImage(imageView: ImageView, url: String) {
            Glide.with(imageView.context).load(url).into(imageView)
        }
    }
}