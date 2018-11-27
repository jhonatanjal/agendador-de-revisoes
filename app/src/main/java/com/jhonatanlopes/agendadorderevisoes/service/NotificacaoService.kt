package com.jhonatanlopes.agendadorderevisoes.service

import android.app.IntentService
import android.app.PendingIntent
import android.content.Intent
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import android.util.Log
import com.jhonatanlopes.agendadorderevisoes.R
import com.jhonatanlopes.agendadorderevisoes.db.entity.Revisao
import com.jhonatanlopes.agendadorderevisoes.repository.RevisaoRepository
import com.jhonatanlopes.agendadorderevisoes.ui.RevisoesActivity
import com.jhonatanlopes.agendadorderevisoes.utilities.CHANNEL_ID
import com.jhonatanlopes.agendadorderevisoes.utilities.hoje

class NotificacaoService : IntentService("NotificacaoService") {
    override fun onHandleIntent(intent: Intent?) {
        val quantidadeDeRevisoes = revisoesDeHoje()

        if (quantidadeDeRevisoes > 0)
            notificaSobreRevisoes(quantidadeDeRevisoes)
    }

    private fun revisoesDeHoje(): Int {
        val repository = RevisaoRepository(this)
        val revisoes: List<Revisao> = repository.todasRevisoes()
        return revisoes.filter { it.data.hoje() }.size
    }

    private fun notificaSobreRevisoes(quantidade: Int) {
        val notificationId = 234
        val notificationTitle = "Revisões para hoje"
        val notificationText = "Você tem $quantidade revisões marcadas para hoje"

        val intent = Intent(this, RevisoesActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notificacao_revisao)
            .setContentTitle(notificationTitle)
            .setContentText(notificationText)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        NotificationManagerCompat.from(this).apply {
            notify(notificationId, builder.build())
        }
    }

}