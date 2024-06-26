package com.nbcteam5.nbccontact.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.nbcteam5.nbccontact.presentation.main.MainActivity
import com.nbcteam5.nbccontact.R
import com.nbcteam5.nbccontact.util.Constant.Companion.CHANNEL_ID
import com.nbcteam5.nbccontact.util.Constant.Companion.NOTIFICATION_ID

class AlarmReceiver : BroadcastReceiver() {

    lateinit var notificationManager: NotificationManager

    override fun onReceive(context: Context, intent: Intent) {
        notificationManager = context.getSystemService(
            Context.NOTIFICATION_SERVICE) as NotificationManager

        createNotificationChannel(context)
        deliverNotification(context)
    }
}

class Constant {
    companion object {
        // 아이디 선언
        const val NOTIFICATION_ID = 0
        const val CHANNEL_ID = "notification_channel"

        // 알림 시간 설정
        const val ALARM_5TIMER = 5
        const val ALARM_10TIMER = 10
        const val ALARM_30TIMER = 30
    }
}
// Notification 을 띄우기 위한 Channel 등록
fun createNotificationChannel(context: Context){
    val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    val notificationChannel = NotificationChannel(
        CHANNEL_ID, // 채널의 아이디
        "채널 이름입니다.", // 채널의 이름
        NotificationManager.IMPORTANCE_HIGH
        /*
        1. IMPORTANCE_HIGH = 알림음이 울리고 헤드업 알림으로 표시
        2. IMPORTANCE_DEFAULT = 알림음 울림
        3. IMPORTANCE_LOW = 알림음 없음
        4. IMPORTANCE_MIN = 알림음 없고 상태줄 표시 X
         */
    )
    notificationChannel.enableLights(true) // 불빛
    notificationChannel.enableVibration(true) // 진동 여부
    notificationChannel.description = "채널의 상세정보입니다." // 채널 정보
    notificationManager.createNotificationChannel(
        notificationChannel)
}

// Notification 등록
private fun deliverNotification(context: Context){
    val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    val contentIntent = Intent(context, MainActivity::class.java)
    val contentPendingIntent = PendingIntent.getActivity(
        context,
        NOTIFICATION_ID, // requestCode
        contentIntent, // 알림 클릭 시 이동할 인텐트
        PendingIntent.FLAG_IMMUTABLE
        /*
        1. FLAG_UPDATE_CURRENT : 현재 PendingIntent를 유지하고, 대신 인텐트의 extra data는 새로 전달된 Intent로 교체
        2. FLAG_CANCEL_CURRENT : 현재 인텐트가 이미 등록되어있다면 삭제, 다시 등록
        3. FLAG_NO_CREATE : 이미 등록된 인텐트가 있다면, null
        4. FLAG_ONE_SHOT : 한번 사용되면, 그 다음에 다시 사용하지 않음
        5. FLAG_IMMUTABLE : 
         */
    )

    val builder = NotificationCompat.Builder(context, CHANNEL_ID)
        .setSmallIcon(R.mipmap.ic_launcher_foreground) // 아이콘
        .setContentTitle("타이틀 입니다.") // 제목
        .setContentText("전화를 거세요") // 내용
        .setContentIntent(contentPendingIntent)
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setAutoCancel(true)
        .setDefaults(NotificationCompat.DEFAULT_ALL)

    notificationManager.notify(NOTIFICATION_ID, builder.build())
}