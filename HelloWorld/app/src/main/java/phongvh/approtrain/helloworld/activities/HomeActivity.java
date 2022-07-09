package phongvh.approtrain.helloworld.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import phongvh.approtrain.helloworld.R;

public class HomeActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_activity);
//        Intent i = getIntent();
//        TextView username = (TextView) findViewById(R.id.txt_username);
//        TextView password = (TextView) findViewById(R.id.txt_password);
//        username.setText(i.getStringExtra("user_name"));
//        password.setText(i.getStringExtra("password"));
//        Button btnConfirm = (Button) findViewById(R.id.btn_confirm);
//        btnConfirm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent();
//                intent.putExtra("confirm", "Login success");
//                setResult(RESULT_OK, intent);
//                finish();
//            }
//        });

    }
}