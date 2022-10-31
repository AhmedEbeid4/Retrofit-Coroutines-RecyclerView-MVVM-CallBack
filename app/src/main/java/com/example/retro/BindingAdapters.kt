package com.example.retro

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("imageUrl")
fun bindImage(img:ImageView,imageUrl:String?){
    imageUrl?.let {
        val imageUri=it.toUri().buildUpon().scheme("https").build()
        Glide.with(img.context).load(imageUri)
            .apply(RequestOptions()
                .placeholder(R.drawable.loading_animation),
                ).error(R.drawable.ic_broken_image)
            .into(img)
    }
}