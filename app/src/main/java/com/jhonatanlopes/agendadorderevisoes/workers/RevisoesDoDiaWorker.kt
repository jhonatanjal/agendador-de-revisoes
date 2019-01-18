package com.jhonatanlopes.agendadorderevisoes.workers

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.jhonatanlopes.agendadorderevisoes.R
import com.jhonatanlopes.agendadorderevisoes.db.entity.Revisao
import com.jhonatanlopes.agendadorderevisoes.repository.RevisaoRepository
import com.jhonatanlopes.agendadorderevisoes.ui.RevisoesActivity
import com.jhonatanlopes.agendadorderevisoes.utilities.CHANNEL_ID
import com.jhonatanlopes.agendadorderevisoes.utilities.hoje

class RevisoesDoDiaWorker(
    private val context: Context,
    params: WorkerParameters
) : Worker(context, params) {

    override fun doWork(): Result {
        val quantidadeDeRevisoes: Int = revisoesDeHoje()

        if (quantidadeDeRevisoes > 0) {
            notificaSobreRevisoes(quantidadeDeRevisoes)
        }

        return Result.SUCCESS
    }

    private fun revisoesDeHoje(): Int {
        val repository = RevisaoRepository(context)
        val revisoes: List<Revisao> = repository.todasRevisoes()
        return revisoes.filter { it.data.hoje() }.size
    }

    private fun notificaSobreRevisoes(quantidade: Int) {
        val notificationId = 234
        val notificationTitle = "Revisões para hoje"
        val notificationText =
            if (quantidade > 1) "Você tem $quantidade revisões marcadas para hoje"
            else "Você tem $quantidade revisão marcada para hoje"

        val intent = Intent(context, RevisoesActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)

        val notificacaoBuilder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notificacao_revisao)
            .setContentTitle(notificationTitle)
            .setContentText(notificationText)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setDefaults(NotificationCompat.DEFAULT_ALL)

        NotificationManagerCompat.from(context).apply {
            notify(notificationId, notificacaoBuilder.build())
        }
    }
}