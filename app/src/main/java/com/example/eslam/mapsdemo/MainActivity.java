package com.example.eslam.mapsdemo;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final int ERROR_REQUEST = 9001;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.btn);
        if (isServicesOk())
        {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(MainActivity.this,MapsActivity.class));
                }
            });
        }
    }

    private boolean isServicesOk() {
        Log.d(TAG, "isServicesOk : Checking Google Services Ok");
        int Avalibale = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MainActivity.this);
        if (Avalibale == ConnectionResult.SUCCESS) {
            Log.d(TAG, "isServicesOk : Checking Google Services Ok");
            return true;
        } else if (GoogleApiAvailability.getInstance().isUserResolvableError(Avalibale)) {
            Log.d(TAG, "isServicesOk : an Error occured but we resolve it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(MainActivity.this, Avalibale, ERROR_REQUEST);
            dialog.show();
        } else {
            Toast.makeText(MainActivity.this, "Error But We Can Solve it", Toast.LENGTH_LONG).show();
        }
        return false;
    }
}
