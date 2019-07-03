package com.example.laraa.grades;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SummaryActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_CAMERA = 1;
    private TextView textView;
    private Button permissionBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        permissionBtn = (Button) findViewById(R.id.permissionBtn);
        showAverage();

        permissionBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                requestPermission();
            }
        });
    }


    private void requestPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "// Permission is not granted!", Toast.LENGTH_SHORT).show();
        }
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "// Permission is not granted!", Toast.LENGTH_SHORT).show();

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CAMERA)) {
                Toast.makeText(this, "// Show an explanation to the user *asynchronously*", Toast.LENGTH_SHORT).show();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);
            }
        } else {
            Toast.makeText(this, "// Permission has already been granted!", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CAMERA: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "// permission was granted, yay!!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "// permission denied, boo!", Toast.LENGTH_SHORT).show();

                }
            }
        }
    }

    private void showAverage() {
        Intent intent = getIntent();
        double message = intent.getDoubleExtra("Note", 0);
        message = roundDecimal(message, 2);
        textView = (TextView) findViewById(R.id.avTextView);
        textView.setText(Double.toString(message));
    }


    private double roundDecimal(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.homeItem:
                Intent intentHome = new Intent(this, MainActivity.class);
                this.startActivity(intentHome);
                break;
            case R.id.summaryItem:
                Intent intentSummary = new Intent(this, SummaryActivity.class);
                this.startActivity(intentSummary);
                break;
            case R.id.listItem:
                Intent intentList = new Intent(this, MyRecyclerView.class);
                this.startActivity(intentList);
                break;
            default:
                return super.onContextItemSelected(item);
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}

