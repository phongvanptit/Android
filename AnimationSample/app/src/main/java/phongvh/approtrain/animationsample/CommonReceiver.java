package phongvh.approtrain.animationsample;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

public class CommonReceiver extends BroadcastReceiver {
    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name,
                                       IBinder service) {

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    public void onReceive(Context context,
                          Intent intent) {
        Intent i = new Intent(context, PlayService.class);
        context.startService(i);
        Log.i("CommonReceiver", "onReceive");
    }
}
