package phongvh.approtrain.helloworld.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collections;

import phongvh.approtrain.helloworld.R;
import phongvh.approtrain.helloworld.adapters.UserAdapter;
import phongvh.approtrain.helloworld.database.Contracts;
import phongvh.approtrain.helloworld.database.DBHelper;
import phongvh.approtrain.helloworld.modals.User;

public class LoginActivity extends Activity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    public final int LOGIN_REQUEST = 1000;
    public final int SIGNUP_REQUEST = 2000;
    public final String TAG = LoginActivity.class.getSimpleName();
    private TextView txtSignUp, txtForget;
    private Button btnLogin;
    private ListView mLvUser;
    private ArrayList<User> mListUser = new ArrayList<>();
    private ArrayAdapter sortAdapter;
    private String[] sortOptions = {"A to Z", "Z to A"};
    private UserAdapter mUserAdapter;
    private Spinner mSpinner;
    private int mSpinnerOption = 0;
    private AlertDialog.Builder mBuilder;
    private EditText mLoginUserName, mLoginPassword, mEdtUserName, mEdtPassword, mEdtFullName, mEdtDob, mEdtPhoneNumber, mEdtAddress;
//    private View viewDialog;
    private final String AUTH_PREFERENCE = "auth_preference";
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;

    // Adding
    private Cursor mCursor;
    private DBHelper mHelper;
    private CursorAdapter mCursorAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        btnLogin = (Button) findViewById(R.id.btn_login);
        txtSignUp = (TextView) findViewById(R.id.txt_signup);
        mLvUser = (ListView) findViewById(R.id.lv_user);
        mUserAdapter = new UserAdapter(this, mListUser);
        mSpinner = (Spinner) findViewById(R.id.spinner_sort);
        sortAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, sortOptions);
        mBuilder = new AlertDialog.Builder(this);
//        viewDialog = this.getLayoutInflater().inflate(R.layout.sign_up_dialog, null);
        mLoginUserName = (EditText) findViewById(R.id.edt_username);
        mLoginPassword = (EditText) findViewById(R.id.edt_password);

        mPreferences = getSharedPreferences(AUTH_PREFERENCE, MODE_PRIVATE);
        mEditor = mPreferences.edit();

//        mListUser.add(new User("phong.vh", "123454", "Vu Hong Phong", "76237567832", "37865832", "Ha Noi"));
//        mListUser.add(new User("phong.vh", "123454", "Nguyen Van Minh", "76237567832", "37865832", "Ha Noi"));
//        mListUser.add(new User("phong.vh", "123454", "Tran Trong Dat", "76237567832", "37865832", "Ha Noi"));
//        mListUser.add(new User("phong.vh", "123454", "Dao Manh Cuong", "76237567832", "37865832", "Ha Noi"));
//        mListUser.add(new User("phong.vh", "123454", "Le Trung Hieu", "76237567832", "37865832", "Ha Noi"));
//        mListUser.add(new User("phong.vh", "123454", "Ngo Bao Son", "76237567832", "37865832", "Ha Noi"));
//        mLvUser.setAdapter(mUserAdapter);
        mSpinner.setOnItemSelectedListener(this);
        txtSignUp.setOnClickListener(this);
        btnLogin.setOnClickListener(this);

        // Todo: Using SQLite to save data
        mHelper = new DBHelper(this, Contracts.DB_NAME, null, Contracts.DB_VERSION);
        mCursor = mHelper.getCursor();
        mCursorAdapter = new CursorAdapter(this, mCursor) {
            @Override
            public View newView(Context context, Cursor cursor, ViewGroup parent) {
                return LayoutInflater.from(context).inflate(R.layout.item_user, parent, false);
            }

            @SuppressLint("Range")
            @Override
            public void bindView(View view, Context context, Cursor cursor) {
                TextView fullName = view.findViewById(R.id.tvFullName);
                TextView userName = view.findViewById(R.id.tvUserName);
                String strUserName = cursor.getString(cursor.getColumnIndex(Contracts.COLUMN_USERNAME));
                String strFullName = cursor.getString(cursor.getColumnIndex(Contracts.COLUMN_FULL_NAME));

                fullName.setText(strFullName);
                userName.setText(strUserName);
            }
        };
        mLvUser.setAdapter(mCursorAdapter);
        mLvUser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("Range")
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mHelper.delete(mCursor.getInt(mCursor.getColumnIndex(Contracts.COLUMN_ID)));
                mCursor.requery();
                mCursorAdapter.notifyDataSetChanged();
            }
        });

//        String userName = mPreferences.getString("user_name", "");
//        String passWord = mPreferences.getString("password", "");
//        mLoginUserName.setText(userName);
//        mLoginPassword.setText(passWord);
//        mSpinnerOption = mPreferences.getInt("position", 0);
        mSpinner.setAdapter(sortAdapter);
        mSpinner.setSelection(mSpinnerOption);
        Log.i(TAG, "onCreate");
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
        // Save data to preference
//        mEditor.putString("user_name", mLoginUserName.getText().toString());
//        mEditor.putString("password", mLoginPassword.getText().toString());
//        mEditor.putInt("position", mSpinnerOption);
//        mEditor.commit();
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txt_signup:
                // Using Activity
//                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
//                startActivityForResult(intent, SIGNUP_REQUEST);
                View viewDialog = this.getLayoutInflater().inflate(R.layout.sign_up_dialog, null);
                mEdtUserName = (EditText) viewDialog.findViewById(R.id.registerUserName);
                mEdtPassword = (EditText) viewDialog.findViewById(R.id.registerPassword);
                mEdtFullName = (EditText) viewDialog.findViewById(R.id.registerFullName);
                mEdtDob = (EditText) viewDialog.findViewById(R.id.registerDob);
                mEdtPhoneNumber = (EditText) viewDialog.findViewById(R.id.registerPhone);
                mEdtAddress = (EditText) viewDialog.findViewById(R.id.registerAddress);
                // Using Dialog
                mBuilder.setTitle("Sign up");
                mBuilder.setView(viewDialog);
                mBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });

                mBuilder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Save user info
                        User user = new User();
                        user.setUserName(mEdtUserName.getText().toString());
                        user.setPassWord(mEdtPassword.getText().toString());
                        user.setFullName(mEdtFullName.getText().toString());
                        user.setDateOfBirth(mEdtDob.getText().toString());
                        user.setPhoneNumber(mEdtPhoneNumber.getText().toString());
                        user.setAddress(mEdtAddress.getText().toString());
                        mHelper.insert(user);
                        mCursor.requery();
                        mUserAdapter.notifyDataSetChanged();
                    }
                });
                AlertDialog dialog = mBuilder.create();
                dialog.show();
                break;
            case R.id.btn_login:
                //Using explicit intent
//                Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                Intent i = new Intent("ACTION_GOTO");
                i.putExtra("user_name", "phong.vh");
                i.putExtra("password", "123456");
                // Using implicit Intent
//                startActivity(i);
                startActivityForResult(i, LOGIN_REQUEST);
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        switch (position) {
            case 1:
                Collections.sort(mListUser, (a, b) -> b.getFullName().compareTo(a.getFullName()));
                break;
            default:
                Collections.sort(mListUser, (a, b) -> a.getFullName().compareTo(b.getFullName()));
                break;
        }
        mSpinnerOption = position;
        mUserAdapter.notifyDataSetChanged();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
