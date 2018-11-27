package com.jhonatanlopes.agendadorderevisoes.ui

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.jhonatanlopes.agendadorderevisoes.R
import com.jhonatanlopes.agendadorderevisoes.receiver.AlarmeReceiver
import com.jhonatanlopes.agendadorderevisoes.utilities.CHANNEL_ID
import com.jhonatanlopes.agendadorderevisoes.utilities.HORARIO_ALARME
import com.jhonatanlopes.agendadorderevisoes.utilities.NOTIFICACAO_REQUEST_CODE
import com.jhonatanlopes.agendadorderevisoes.utilities.formatado
import kotlinx.android.synthetic.main.revisoes_activity.*
import java.util.*

class RevisoesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.revisoes_activity)
        setSupportActionBar(toolbar)
        criaNotificationChannel()
        confAlarme()
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

    private fun confAlarme() {

        if (alarmeJaConfigurado()) {
            Log.i("- Alarme", "Já existe alarme")
            return
        }

        val calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, HORARIO_ALARME)
            set(Calendar.MINUTE, 0)
            add(Calendar.DAY_OF_MONTH, 1)
        }

        val alarmMgr = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val alarmIntent = Intent(this, AlarmeReceiver::class.java).let { intent ->
            PendingIntent.getBroadcast(this, NOTIFICACAO_REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        }

        alarmMgr.set(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            alarmIntent
        ).also { Log.i("- Alarme", "Alarme Criado para ${calendar.time.formatado()}") }
    }

    fun alarmeJaConfigurado() = PendingIntent.getBroadcast(
        this,
        NOTIFICACAO_REQUEST_CODE,
        Intent(this, AlarmeReceiver::class.java),
        PendingIntent.FLAG_NO_CREATE
    ) != null
}
