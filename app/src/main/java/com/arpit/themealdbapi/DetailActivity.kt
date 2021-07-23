package com.arpit.themealdbapi

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_detail.tvTitle
import kotlinx.android.synthetic.main.item.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)


            val title = intent.getStringExtra("originalTitle")
            val category = intent.getStringExtra("category")
            val tags = intent.getStringExtra("tags")
            val area = intent.getStringExtra("area")
            val thumb = intent.getStringExtra("thumb")
            val instructions = intent.getStringExtra("instructions")
            val youtube = intent.getStringExtra("youtube")

            tvTitle.text = title
            tvCategoryD.text = category
            tvTagsD.text= tags
            tvAreaD.text = area
            tvInstructions.text = instructions
            tvYoutubeD.text = youtube
            Glide.with(applicationContext).load(thumb).into(ivThumb)


        tvYoutubeD.setOnClickListener {
            val intentApp = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:$youtube"))
            val intentBrowser = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=$youtube"))
            try {
                this.startActivity(intentApp)
            } catch (ex: ActivityNotFoundException) {
                this.startActivity(intentBrowser)
            }

        }

    }
}