package phongvh.approtrain.animationsample;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class PlayService extends Service implements MediaPlayer.OnCompletionListener {
    MediaPlayer mediaPlayer;

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = MediaPlayer.create(this, R.raw.a_thousand_years);
        mediaPlayer.setOnCompletionListener(this);
    }

    @Override
    public int onStartCommand(Intent intent,
                              int flags,
                              int startId) {
        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
        return new PlayService.LocalBinder();
    }

    public boolean getMediaPlayerStatus() {
        return mediaPlayer.isPlaying();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        mediaPlayer.stop();
        mediaPlayer.release();
        super.onDestroy();

    }

    @Override
    public void onCompletion(MediaPlayer mp) {

    }

    class LocalBinder extends Binder {
        public PlayService getService() {
            return PlayService.this;
        }
    }
}
