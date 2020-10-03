package burullus.digitom.app.utils

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.ContextWrapper
import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi
import burullus.digitom.app.R

/**
 * Helper class to manage notification channels, and create notifications.
 */
@RequiresApi(Build.VERSION_CODES.O)
internal class NotificationHelper
/**
 * Registers notification channels, which can be used later by individual notifications.

 * @param ctx The application context
 */
    (ctx : Context) : ContextWrapper(ctx) {
    private val manager : NotificationManager by lazy {
        getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }


    init {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val chan1 = NotificationChannel(
                PRIMARY_CHANNEL,
                getString(R.string.google_app_id), NotificationManager.IMPORTANCE_HIGH
            )
            chan1.lightColor = Color.GREEN
            chan1.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
            manager.createNotificationChannel(chan1)
        }
    }

    /**
     * Get a notification of type 1

     * Provide the builder rather than the notification it's self as useful for making notification
     * changes.

     * @param title the title of the notification
     * *
     * @param body the body text for the notification
     * *
     * @return the builder as it keeps a reference to the notification (since API 24)
     */
    fun getNotification1(title : String, body : String) : Notification.Builder {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            val build = Notification.Builder(applicationContext, PRIMARY_CHANNEL)
                .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(smallIcon)
                .setAutoCancel(false)
                .setVisibility(Notification.VISIBILITY_PUBLIC)
            return build
        } else {
            val build = Notification.Builder(applicationContext)
                .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(smallIcon)
                .setAutoCancel(true)
                .setVisibility(Notification.VISIBILITY_PUBLIC)
                .setPriority(Notification.PRIORITY_MAX)
            return build
        }
    }


    /**
     * Send a notification.

     * @param id The ID of the notification
     * *
     * @param notification The notification object
     */
    fun notify(id : Int, notification : Notification.Builder) {
        manager.notify(id, notification.build())
    }


    /**
     * Get the small icon for this app

     * @return The small icon resource id
     */
    private val smallIcon : Int
        get() = android.R.drawable.stat_notify_chat

    companion object {
        val PRIMARY_CHANNEL = "default"

    }
}