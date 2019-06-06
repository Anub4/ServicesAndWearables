package app;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.widget.Toast;

import np.com.softwarica.servicesandwearables.MainActivity;
import np.com.softwarica.servicesandwearables.R;

public class BroadcastReceiverExample extends BroadcastReceiver {

    private NotificationManagerCompat notificationManagerCompat;
    Context context;

    public BroadcastReceiverExample(Context context) {
        this.context = context;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        boolean noConnectivity;
        notificationManagerCompat = NotificationManagerCompat.from(context);

        if(ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
            noConnectivity = intent.getBooleanExtra(
                    ConnectivityManager.EXTRA_NO_CONNECTIVITY, false);


            if (noConnectivity) {
                Toast.makeText(context,"Disconnected", Toast.LENGTH_SHORT).show();
                DisplayNotification();
            }
            else{
                Toast.makeText(context,"Connected",Toast.LENGTH_SHORT).show();
                DisplayNotification2();
            }
        }
    }


    private void DisplayNotification() {
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationChannel channel = new NotificationChannel("1", "Channel1", NotificationManager.IMPORTANCE_HIGH);
        Notification notification = new Notification.Builder(context)
                .setSmallIcon(R.drawable.ic_wifi_black_24dp)
                .setContentTitle("No connection")
                .setContentText("No Connectivity")
                .setChannelId(channel.getId())
                .build();

        manager.notify(1,notification);
    }

    private void DisplayNotification2() {
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationChannel channel = new NotificationChannel("1", "Channel1", NotificationManager.IMPORTANCE_HIGH);
        Notification notification = new Notification.Builder(context)
                .setSmallIcon(R.drawable.ic_wifi_black_24dp)
                .setContentTitle("Connected")
                .setContentText("You have been connected")
                .setChannelId(channel.getId())
                .build();

        manager.notify(2,notification);
    }


}
