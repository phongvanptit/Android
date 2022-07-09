package phongvh.approtrain.helloworld.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

import phongvh.approtrain.helloworld.R;
import phongvh.approtrain.helloworld.modals.User;

public class SignUpActivity extends Activity {
    private EditText mEdtUserName, mEdtPassword, mEdtFullName, mEdtDob, mEdtPhoneNumber, mEdtAddress;
    private Button mBtnRegister;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_activity);
        mEdtUserName = (EditText) findViewById(R.id.registerUserName);
        mEdtPassword = (EditText) findViewById(R.id.registerPassword);
        mEdtFullName = (EditText) findViewById(R.id.registerFullName);
        mEdtDob = (EditText) findViewById(R.id.registerDob);
        mEdtPhoneNumber = (EditText) findViewById(R.id.registerPhone);
        mEdtAddress = (EditText) findViewById(R.id.registerAddress);
        mBtnRegister = (Button) findViewById(R.id.btn_signup);
        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User(mEdtUserName.getText().toString(), mEdtPassword.getText().toString(), mEdtFullName.getText().toString(), mEdtDob.getText().toString(),
                        mEdtPhoneNumber.getText().toString(), mEdtAddress.getText().toString());
                Intent intent = new Intent();
                intent.putExtra("register_info", user);
                setResult(RESULT_OK, intent);
                finish();
            }
        });


    }
}