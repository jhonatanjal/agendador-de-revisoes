package com.jhonatanlopes.agendadorderevisoes.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.jhonatanlopes.agendadorderevisoes.utilities.AlarmeUtil
import com.jhonatanlopes.agendadorderevisoes.workers.RevisoesDoDiaWorker

class AlarmeReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {

        val workRequest = OneTimeWorkRequestBuilder<RevisoesDoDiaWorker>().build()
        WorkManager.getInstance().enqueue(workRequest)

        context?.let { AlarmeUtil(it).confAlarme() }
    }
}