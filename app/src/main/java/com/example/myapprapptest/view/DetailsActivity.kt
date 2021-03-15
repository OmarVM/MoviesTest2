package com.example.myapprapptest.view

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.example.myapprapptest.R
import com.example.myapprapptest.repository.network.ConstantServer
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.content_details.*

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        setSupportActionBar(findViewById(R.id.toolbar))

        var title = intent.getStringExtra("mv_title")
        var date_release = intent.getStringExtra("mv_date_release")
        var overview = intent.getStringExtra("mv_overview")
        var img_url = intent.getStringExtra("mv_img_url")

        details_title.text = title
        details_date_release.text = date_release
        Picasso.get().load(ConstantServer.BASE_URL_IMG_W185 + img_url).into(details_img)
        details_overview.text = overview


        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }
}