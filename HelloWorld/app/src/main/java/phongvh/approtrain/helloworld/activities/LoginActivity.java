package phongvh.approtrain.helloworld.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import phongvh.approtrain.helloworld.R;
import androidx.annotation.Nullable;
public class LoginActivity extends Activity {
    public final String TAG = LoginActivity.class.getSimpleName();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_activity);
        Button btnGoto = (Button) findViewById(R.id.btn_goto);
        btnGoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Using explicit intent
//                Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                Intent i = new Intent("ACTION_GOTO");
                i.putExtra("user_name", "phong.vh");
                i.putExtra("password", "123456");
                startActivity(i);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }
}
