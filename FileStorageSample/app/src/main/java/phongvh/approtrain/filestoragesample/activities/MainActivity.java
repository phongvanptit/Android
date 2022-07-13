package phongvh.approtrain.filestoragesample.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

import phongvh.approtrain.filestoragesample.R;
import phongvh.approtrain.filestoragesample.utils.Logger;

public class MainActivity extends Activity {
    private int mCount = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        Button btnPrint = (Button) findViewById(R.id.btn_log);
        Logger logger = Logger.getInstance(this);
        btnPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logger.printLog(MainActivity.class.getSimpleName(), "File storage sample " + mCount++);
            }
        });
    }
}