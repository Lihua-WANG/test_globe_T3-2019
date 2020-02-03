package com.stevecrossin.grocerytracker.screens;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.stevecrossin.grocerytracker.R;
import com.stevecrossin.grocerytracker.database.AppDataRepo;
import com.stevecrossin.grocerytracker.entities.User;
import com.stevecrossin.grocerytracker.other.PasswordScrambler;

import java.util.Date;

public class Signup extends AppCompatActivity {

    Button Bsubmit, Bcancel;
    EditText etName, etAge, etHeight, etWeight,  etPostcode, etNumberOfHouseHoldMember, etHouseholdAdults, etHouseholdChildren, etEmail, etPassword;
    private AppDataRepo repository;
    private Login.LoginTask authenticationTask = null;
    private Spinner cbGender;
    private Spinner cbShopNumber;

    private int selectedGenderPosition=0;
    private int selectedShopNumber=0;

    private final String CHANNEL_ID = "ReceiptUpdate_notifications";
    private final int NOTIFICATION_ID = 001;
    int type = AlarmManager.RTC_WAKEUP;
    // new Date (): represents the current date, which can be replaced with the requested date according to the project requirements
    // getTime (): This method of date can also represent the number of milliseconds elapsed from 00:00 on January 1, 1970 to the present
    long triggerAtMillis = new Date().getTime();
    long intervalMillis = 1000 * 60;
    public class GlobalValues {
        // 周期性的闹钟
        public final static String TIMER_ACTION_REPEATING = "com.example.TIMER_ACTION_REPEATING";
        // 定时闹钟
        public final static String TIMER_ACTION = "com.example.TIMER_ACTION";
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        repository = new AppDataRepo(this);

        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        etHeight = findViewById(R.id.etHeight);
        etWeight = findViewById(R.id.etWeight);
        cbGender = findViewById(R.id.cbGender);
        cbShopNumber = findViewById(R.id.cbShopNumber);
        etPostcode = findViewById(R.id.etPostcode);
        etNumberOfHouseHoldMember = findViewById(R.id.etNumberOfHouseHoldMember);
        etHouseholdAdults = findViewById(R.id.etHouseHoldAdults);
        etHouseholdChildren = findViewById(R.id.etHouseHoldChild);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);

        Bsubmit = findViewById(R.id.Bsubmit);
        Bcancel = findViewById(R.id.Bcancel);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.gender, android.R.layout.simple_spinner_dropdown_item);

        cbGender.setAdapter(adapter);
        cbGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedGenderPosition = position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.shopnumber, android.R.layout.simple_spinner_dropdown_item);

        cbShopNumber.setAdapter(adapter2);
        cbShopNumber.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedShopNumber = position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    /*
    RG - Made some changes to add the data into a new User object, and then place into AppDB.
    NOTE: Not sure if it is storing properly.
     */

    /**
     * Submits sign up. Checks if gender was selected, if not, shows toast and returns to the task
     * After this validation check passes. it will run the insertUser method from AppDataRepo. This method has an exception check -
     * as the email needs to be a unique field, if the end user tries to sign up with an account that already exists, the operation will fail,
     * a Toast will be shown advising email already exists and to login, and then will navigate them to that activity. Otherwise, the insert will be successful
     * and they will be navigated to the welcome screen.
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("StaticFieldLeak")
    public void submitSignUp(View view) {
        String genderValue="";
        if(selectedGenderPosition==0){
            Toast.makeText(Signup.this, "Please select gender", Toast.LENGTH_LONG).show();
            return;
        }
        else
            genderValue = getResources().getStringArray(R.array.gender)[selectedGenderPosition];

        String shopNumberValue="";
        if(selectedShopNumber==0){
            Toast.makeText(Signup.this, "Please select how often you shop", Toast.LENGTH_LONG).show();
            return;
        }
        else
            shopNumberValue = getResources().getStringArray(R.array.shopnumber)[selectedShopNumber];

        final User newUser;
        try {
            newUser = new User(etName.getText().toString(), etEmail.getText().toString(),
                    PasswordScrambler.scramblePassword(etPassword.getText().toString()), etAge.getText().toString(), etHeight.getText().toString(), etWeight.getText().toString(),
                    genderValue, etPostcode.getText().toString(),
                    etNumberOfHouseHoldMember.getText().toString(), etHouseholdAdults.getText().toString(), etHouseholdChildren.getText().toString(), shopNumberValue);
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    if(repository.insertUser(newUser))
                    {
                        Intent intent = new Intent (Signup.this, Welcome.class);
                        startActivity(intent);
                    }
                    else
                    {
                        runOnUiThread(
                                new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(Signup.this, "An account with this email address already exists, please login", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent (Signup.this, Login.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                });
                    }
                    return null;
                }
            }.execute();
        }  catch (Exception ex) {
            Toast.makeText(Signup.this, "Error scrambling password", Toast.LENGTH_SHORT).show();
        }

        /**
         * Notification build settings.
         */
        createdNotificationChannel();

        Intent receiptIntent = new Intent(this, Login.class);
        receiptIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent receiptPendingIntent = PendingIntent.getActivity(this, 0, receiptIntent, PendingIntent.FLAG_ONE_SHOT);

        Intent AddIntent = new Intent(this, Receipts.class);
        AddIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent AddPendingIntent = PendingIntent.getActivity(this, 0, AddIntent, PendingIntent.FLAG_ONE_SHOT);

        AlarmManager alarmmanager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        assert alarmmanager != null;
        //Intent sets the component to start, here starts the broadcast
        Intent alarmIntent = new Intent();
        alarmIntent.setAction(GlobalValues.TIMER_ACTION);
        //The PendingIntent object sets the action, whether it is an Activity or Service, or broadcast!
        PendingIntent sender = PendingIntent.getBroadcast(this, 0, alarmIntent,0);
        //Register an alarm
//        alarmmanager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 1 * 1000, sender);
        alarmmanager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime(), intervalMillis, receiptPendingIntent);


        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_baseline_notifications);
        builder.setContentTitle("Receipt Update");
        builder.setContentText("Hi, please add your latest grocery receipts");
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setAutoCancel(true);
        builder.setContentIntent(receiptPendingIntent);
        builder.addAction(R.drawable.ic_baseline_notifications, "Add Receipt", AddPendingIntent);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(NOTIFICATION_ID,builder.build());

    }

    public void cancelSignUp(View view) {
        Intent intent = new Intent (this, Login.class);
        startActivity(intent);
    }

    /**
     * Build notification channels in order to show on over Level8.0 Android SDK.
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createdNotificationChannel()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            CharSequence name = "Personal Notifications";
            String description = "Include all the personal notifications";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, name, importance);

            notificationChannel.setDescription(description);

            NotificationManager notificatiionManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificatiionManager.createNotificationChannel(notificationChannel);
        }
    }
}



