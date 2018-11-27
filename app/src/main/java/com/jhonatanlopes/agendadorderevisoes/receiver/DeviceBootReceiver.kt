package com.jhonatanlopes.agendadorderevisoes.receiver

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.jhonatanlopes.agendadorderevisoes.utilities.HORARIO_ALARME
import com.jhonatanlopes.agendadorderevisoes.utilities.NOTIFICACAO_REQUEST_CODE
import java.util.*

class DeviceBootReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == "android.intent.action.BOOT_COMPLETED") {
            confAlarme(context)
        }
    }

    private fun confAlarme(context: Context) {
        val calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, HORARIO_ALARME)
            set(Calendar.MINUTE, 0)
        }

        val alarmMgr = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val alarmIntent = Intent(context, AlarmeReceiver::class.java).let { intent ->
            PendingIntent.getBroadcast(context, NOTIFICACAO_REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        }

        alarmMgr.set(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, alarmIntent)
    }
}