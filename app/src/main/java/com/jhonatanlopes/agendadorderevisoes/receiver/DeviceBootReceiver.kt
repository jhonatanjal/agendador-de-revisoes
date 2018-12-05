package com.jhonatanlopes.agendadorderevisoes.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.jhonatanlopes.agendadorderevisoes.utilities.AlarmeUtil

class DeviceBootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == "android.intent.action.BOOT_COMPLETED") {
            AlarmeUtil(context).confAlarme()
        }
    }
}