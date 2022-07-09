package phongvh.approtrain.helloworld.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import phongvh.approtrain.helloworld.R;
import phongvh.approtrain.helloworld.adapters.UserAdapter;
import phongvh.approtrain.helloworld.modals.User;

public class LoginActivity extends Activity {
    public final int LOGIN_REQUEST = 1000;
    public final int SIGNUP_REQUEST = 2000;
    public final String TAG = LoginActivity.class.getSimpleName();
    private TextView txtSignUp, txtForget;
    private Button btnLogin;
    private ListView mLvUser;
    private ArrayList<User> mListUser = new ArrayList<>();
    private UserAdapter mUserAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        btnLogin = (Button) findViewById(R.id.btn_login);
        txtSignUp = (TextView) findViewById(R.id.txt_signup);
        mLvUser = (ListView) findViewById(R.id.lv_user);
        mUserAdapter = new UserAdapter(this, mListUser);
        mLvUser.setAdapter(mUserAdapter);

        txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivityForResult(intent, SIGNUP_REQUEST);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Using explicit intent
//                Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                Intent i = new Intent("ACTION_GOTO");
                i.putExtra("user_name", "phong.vh");
                i.putExtra("password", "123456");
                // Using implicit Intent
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
        if (requestCode == SIGNUP_REQUEST && resultCode == RESULT_OK) {
            mListUser.add((User) data.getSerializableExtra("register_info"));
            mUserAdapter.notifyDataSetChanged();
            Log.i(TAG, "" + mListUser.size());
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
