package icaro.com.mycallgooglemapsapp;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.HashMap;
import java.util.Map;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // ...

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        String enderecos = "";
        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());

            Map<String, String> map = new HashMap<String, String>();

            map = remoteMessage.getData();


            int i = 1;
            for (Map.Entry<String, String> entry : map.entrySet()) {
                System.out.println(entry.getKey() + "/" + entry.getValue());
                Log.d(TAG, "Chave: " + entry.getKey());
                Log.d(TAG, "Valor: " + entry.getValue());

                if (i==1) {
                    enderecos = entry.getValue();
                    i=2;
                } else{
                    enderecos = enderecos +  "+to:" + entry.getValue();
                }
            }
           Log.d(TAG, "Enderecos: " + enderecos);

            final Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?daddr=" + enderecos));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setClassName("com.google.android.apps.maps","com.google.android.maps.MapsActivity");
            startActivity(intent);


            //     if (/* Check if data needs to be processed by long running job */ true) {
            // For long-running tasks (10 seconds or more) use Firebase Job Dispatcher.
            //         scheduleJob();
            //     } else {
            // Handle message within 10 seconds
            //         handleNow();
            //    }
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }
}