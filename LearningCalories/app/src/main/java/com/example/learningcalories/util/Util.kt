package com.example.learningcalories.util
import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.learningcalories.R
fun String.myExtension(parametre:String){
    println(parametre)
}
fun ImageView.installImage(url:String?,placeholder:CircularProgressDrawable){
    val options=RequestOptions().placeholder(placeholder).error(R.mipmap.ic_launcher_round)
    Glide.with(context).setDefaultRequestOptions(/*placeHolder*/options).load(/*hangiUrl*/url).into(this)
}
fun createplaceholder(context: Context) :CircularProgressDrawable{
    return CircularProgressDrawable(context).apply {//this:CircularProgressDrawable
        strokeWidth=8f //float type olarak verilmelidir.
        centerRadius=40f //float type olarak verilmelidir.
        start()
    }
}