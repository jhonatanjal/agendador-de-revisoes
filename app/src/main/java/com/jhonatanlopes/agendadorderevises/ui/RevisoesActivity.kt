package com.jhonatanlopes.agendadorderevises.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.jhonatanlopes.agendadorderevises.R
import kotlinx.android.synthetic.main.revisoes_activity.*

class RevisoesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.revisoes_activity)
        setSupportActionBar(toolbar)
    }
}
