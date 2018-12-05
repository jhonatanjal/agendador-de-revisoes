package com.jhonatanlopes.agendadorderevisoes.utilities

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import com.jhonatanlopes.agendadorderevisoes.receiver.AlarmeReceiver
import java.util.*

class AlarmeUtil(private val context: Context) {

    private val horaDoAlarme = 9

    fun confAlarme() {

        if (alarmeJaConfigurado()) {
            Log.i("- Alarme", "Já existe alarme")
            return
        }

        val dataAlarme = getDataAlarme()

        val alarmMgr: AlarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val alarmIntent = Intent(context, AlarmeReceiver::class.java).let { intent ->
            PendingIntent.getBroadcast(context, NOTIFICACAO_REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        }

        setAlarme(alarmMgr, dataAlarme, alarmIntent)
    }

    private fun getDataAlarme(): Calendar {
        return Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, horaDoAlarme)
            set(Calendar.MINUTE, 0)
            add(Calendar.DAY_OF_MONTH, 1)
        }
    }

    private fun setAlarme(
        alarmMgr: AlarmManager,
        dataHora: Calendar,
        alarmIntent: PendingIntent?
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            alarmMgr.setExact(
                AlarmManager.RTC_WAKEUP,
                dataHora.timeInMillis,
                alarmIntent
            )
        } else {
            alarmMgr.setRepeating(
                AlarmManager.RTC_WAKEUP,
                dataHora.timeInMillis,
                AlarmManager.INTERVAL_DAY,
                alarmIntent
            )
        }
        Log.i("- Alarme", "Alarme Criado para ${dataHora.time.formatado()}")
    }

    private fun alarmeJaConfigurado() = PendingIntent.getBroadcast(
        context,
        NOTIFICACAO_REQUEST_CODE,
        Intent(context, AlarmeReceiver::class.java),
        PendingIntent.FLAG_NO_CREATE
    ) != null
}