package phongvh.approtrain.sendbroadcastapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendBroadCast(View v) {
        sendBroadcast(new Intent("PLAY_MEDIA_PLAYER"));
    }
}