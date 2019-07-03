package com.example.laraa.grades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class SummaryActivity extends AppCompatActivity {

    private TextView textView;
    private Button backBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        backBtn = (Button) findViewById(R.id.backBtn);

        showAverage();

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

