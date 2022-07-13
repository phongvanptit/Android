package phongvh.approtrain.filestoragesample.utils;

import android.content.Context;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Logger {
    private static final String FILE_NAME = "log.txt";
    private static final Logger NEW_INSTANCE = new Logger();
    private static FileOutputStream mOutputStream;
    //    private OutputStreamWriter mWriter;
    private String mMessage;

    private Logger() {
    }

    public static Logger getInstance(Context context) {
        if (mOutputStream == null) {
            try {
                mOutputStream = context.openFileOutput(FILE_NAME, context.MODE_PRIVATE);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return NEW_INSTANCE;
    }

    public void printLog(String tag, String message) {
        mMessage = tag + ": " + message + "\n";
        try {
            mOutputStream.write(mMessage.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.i(tag, message);
    }
}