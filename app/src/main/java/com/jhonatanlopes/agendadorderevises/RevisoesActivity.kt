package com.jhonatanlopes.agendadorderevises

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.jhonatanlopes.agendadorderevises.ui.revisoes.ListaRevisoesFragment

class RevisoesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.revisoes_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ListaRevisoesFragment.newInstance())
                .commitNow()
        }
    }

}
