package com.jhonatanlopes.agendadorderevisoes.ui

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.jhonatanlopes.agendadorderevisoes.R
import com.jhonatanlopes.agendadorderevisoes.utilities.AlarmeUtil
import com.jhonatanlopes.agendadorderevisoes.utilities.CHANNEL_ID
import kotlinx.android.synthetic.main.revisoes_activity.*

class RevisoesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.revisoes_activity)
        setSupportActionBar(toolbar)
        criaNotificationChannel()
        AlarmeUtil(this).confAlarme()
    }

    private fun criaNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance)

            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}
