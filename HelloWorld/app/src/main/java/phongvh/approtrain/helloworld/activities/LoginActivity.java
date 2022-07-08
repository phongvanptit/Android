package phongvh.approtrain.helloworld.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import phongvh.approtrain.helloworld.R;

public class LoginActivity extends Activity {
    public final int LOGIN_REQUEST = 1000;
    public final String TAG = LoginActivity.class.getSimpleName();
    private TextView txtSignIn, txtForget;
    private Button btnLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_activity);
        btnLogin = (Button) findViewById(R.id.btn_login);
        txtSignIn = (TextView) findViewById(R.id.txt_result);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Using explicit intent
//                Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                Intent i = new Intent("ACTION_GOTO");
                i.putExtra("user_name", "phong.vh");
                i.putExtra("password", "123456");
//                startActivity(i);
                startActivityForResult(i, LOGIN_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LOGIN_REQUEST && resultCode == RESULT_OK) {
//            txtConfirm.setText(data.getStringExtra("confirm"));
        }
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
