package phongvh.approtrain.animationsample;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class PlayService2 extends Service {
    MediaPlayer mediaPlayer;

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = MediaPlayer.create(this, R.raw.meo_keu);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
        return new PlayService2.LocalBinder();
    }

    public boolean getMediaPlayerStatus() {
        return mediaPlayer.isPlaying();
    }

    @Override
    public void onDestroy() {
        mediaPlayer.stop();
        mediaPlayer.release();
        super.onDestroy();
    }

    class LocalBinder extends Binder {
        public PlayService2 getService() {
            return PlayService2.this;
        }
    }
}
