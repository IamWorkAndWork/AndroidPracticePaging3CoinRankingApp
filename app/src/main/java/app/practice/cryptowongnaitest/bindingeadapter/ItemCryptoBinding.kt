package app.practice.cryptowongnaitest.bindingeadapter

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import app.practice.cryptowongnaitest.R
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.load
import org.jsoup.Jsoup

@BindingAdapter("parseHtml")
fun parseHtml(textView: TextView, description: String?) {
    if (description != null) {
        val desc = Jsoup.parse(description).text()
        textView.text = desc
    }
}

@BindingAdapter("loadImage")
fun loadImage(imageView: ImageView, imageUrl: String) {
    val svgImageLoader = ImageLoader.Builder(imageView.context)
        .componentRegistry {
            add(SvgDecoder(imageView.context))
        }
        .build()

    imageView.load(imageUrl, svgImageLoader) {
        crossfade(true)
        error(R.drawable.ic_bitcoin_btc)
    }
}