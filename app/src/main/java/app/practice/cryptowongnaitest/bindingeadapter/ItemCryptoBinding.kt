package app.practice.cryptowongnaitest.bindingeadapter

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import app.practice.cryptowongnaitest.R
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.load
import org.jsoup.Jsoup
import org.koin.core.KoinComponent
import org.koin.core.inject

object ItemCryptoBinding : KoinComponent {

    private val svgImageLoader: ImageLoader by inject()

    @JvmStatic
    @BindingAdapter("parseHtml")
    fun parseHtml(textView: TextView, description: String?) {
        if (description != null) {
            val desc = Jsoup.parse(description).text()
            textView.text = desc
        }
    }

    @JvmStatic
    @BindingAdapter("loadImage")
    fun loadImage(imageView: ImageView, imageUrl: String) {
        imageView.load(imageUrl, svgImageLoader) {
            crossfade(true)
            error(R.drawable.ic_bitcoin_btc)
        }
    }

}