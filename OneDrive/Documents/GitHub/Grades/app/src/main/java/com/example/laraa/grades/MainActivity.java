package com.example.laraa.grades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Button addButton;
    private TextView textView;
    private Button summaryBtn;
    ArrayList<Double> notenListe = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.noteInput);
        addButton = (Button) findViewById(R.id.addButton);
        textView = (TextView) findViewById(R.id.noteText);
        summaryBtn = (Button) findViewById(R.id.summaryBtn);

        setupButtonSave();
        sendMessage();
    }

    public void sendMessage() {
        summaryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), SummaryActivity.class);
                intent.putExtra("Note", calculateGrade());
                startActivity(intent);
            }
        });
    }

    private void setupButtonSave() {
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(editText.getText())) {
                    Toast.makeText(MainActivity.this, "Empty Input!", Toast.LENGTH_SHORT).show();
                    return;
                }
                double note = Double.parseDouble(editText.getText().toString());
                if (note <= 0 || note > 6) {
                    Toast.makeText(MainActivity.this, "Invalid Mark!", Toast.LENGTH_SHORT).show();
                    return;
                }
                notenListe.add(note);
                calculateGrade();
                editText.getText().clear();
            }
        });
    }


    private double calculateGrade() {
        double sum = 0;
        double av = 0;
        if (!notenListe.isEmpty()) {
            for (double note : notenListe) {
                sum += note;
            }
            av = sum / notenListe.size();
        }
        textView.setText(String.valueOf(av));
        return av;
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
