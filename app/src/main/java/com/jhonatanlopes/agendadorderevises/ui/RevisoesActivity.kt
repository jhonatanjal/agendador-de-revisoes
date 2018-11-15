package com.jhonatanlopes.agendadorderevises.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.jhonatanlopes.agendadorderevises.R
import com.jhonatanlopes.agendadorderevises.db.entity.Revisao

class RevisoesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.revisoes_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ListaRevisoesFragment.newInstance())
                .commitNow()
        }
        Revisao("fe", "re")
    }

}
