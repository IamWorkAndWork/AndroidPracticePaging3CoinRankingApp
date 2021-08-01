package app.practice.cryptowongnaitest.bindingeadapter

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import app.practice.cryptowongnaitest.R
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
    imageView.load(imageUrl) {
        error(R.drawable.ic_bitcoin_btc)
    }
}