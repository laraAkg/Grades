package ch.layout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.laraa.grades.R;

public class SummaryActivity extends AppCompatActivity {

    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        Intent intent = getIntent();

        String message = intent.getStringExtra("Note");
         textView = (TextView) findViewById(R.id.avTextView);
        textView.setText(message);
    }
}

