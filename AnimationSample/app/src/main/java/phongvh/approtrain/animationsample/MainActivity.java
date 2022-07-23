package phongvh.approtrain.animationsample;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import androidx.annotation.Nullable;

public class MainActivity extends Activity implements ServiceConnection {
    ImageView imageView;
    Animator animator;
    Animation animation;
    ObjectAnimator objectAnimator;
    MediaPlayer mediaPlayer;
    AnimationDrawable drawable;
    Intent intent;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        imageView = (ImageView) findViewById(R.id.img_animation);
//        imageView.setBackgroundResource(R.drawable.drawable_anim);
        imageView.setImageDrawable(getResources().getDrawable(R.drawable.drawable_anim));
        drawable = (AnimationDrawable) imageView.getBackground();

//        animator = AnimatorInflater.loadAnimator(this, R.animator.rotation);
//        animator.setTarget(imageView);
        animation = AnimationUtils.loadAnimation(this, R.anim.translate);
        objectAnimator = ObjectAnimator.ofFloat(imageView, "rotation", 0f, 360f);
        objectAnimator.setDuration(5000);
        objectAnimator.setInterpolator(new LinearInterpolator());
        objectAnimator.setRepeatMode(ValueAnimator.REVERSE);
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        intent = new Intent(getBaseContext(), PlayService.class);
//        CommonReceiver airPlaneReceiver = new CommonReceiver();
//        registerReceiver(airPlaneReceiver, new IntentFilter("PLAY_MEDIA_PLAYER"));
    }

    public void startAnimation(View v) {
        if (null != drawable) {
            drawable.start();
        }
        bindService(intent, this, BIND_AUTO_CREATE);
    }
//        if (null != animator) {
//            animator.start();
//        }
//        if (null != animation) {
//            imageView.startAnimation(animation);
//        }

//        if (null != objectAnimator) {
//            objectAnimator.start();
//            mediaPlayer = MediaPlayer.create(this, R.raw.meo_keu);
//            mediaPlayer.start();
//            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                @Override
//                public void onCompletion(MediaPlayer mp) {
//                    objectAnimator.cancel();
//                    imageView.clearAnimation();
//                }
//            });
//        }

//        if (null != drawable) {


//            drawable.start();
//            mediaPlayer = MediaPlayer.create(this, R.raw.meo_keu);
//            mediaPlayer.start();
//            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                @Override
//                public void onCompletion(MediaPlayer mp) {
//                    objectAnimator.cancel();
//                    imageView.clearAnimation();
//                }
//            });

    public void stopAnimation(View view) {
//        animator.cancel();
//        animation.cancel();
//        objectAnimator.cancel();
        drawable.stop();
        imageView.clearAnimation();
        unbindService(this);
//        mediaPlayer.stop();
    }

    @Override
    public void onServiceConnected(ComponentName name,
                                   IBinder service) {
        PlayService.LocalBinder binder = (PlayService.LocalBinder) service;
        Log.i("MainActivity", "MediaPlayer status: " + binder.getService().getMediaPlayerStatus());
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }
}
