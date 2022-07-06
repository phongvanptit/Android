package phongvh.approtrain.helloworld.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

import phongvh.approtrain.helloworld.R;

public class HomeActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        Intent i = getIntent();
        TextView username = (TextView) findViewById(R.id.txt_username);
        TextView password = (TextView) findViewById(R.id.txt_password);

        username.setText(i.getStringExtra("user_name"));
        password.setText(i.getStringExtra("password"));

    }
}