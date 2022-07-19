package phongvh.approtrain.sqliteexample.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

import phongvh.approtrain.sqliteexample.R;

public class EditActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_activity);
        EditText username = (EditText) findViewById(R.id.edt_username);
        EditText password = (EditText) findViewById(R.id.edt_password);
        Button button = (Button) findViewById(R.id.btn_save);
        Intent intent = getIntent();
        username.setText(intent.getStringExtra("username"));
        password.setText(intent.getStringExtra("password"));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("username", username.getText().toString());
                intent.putExtra("password", password.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
