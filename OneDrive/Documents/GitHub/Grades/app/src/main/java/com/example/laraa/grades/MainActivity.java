package com.example.laraa.grades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import ch.layout.SummaryActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Button addButton;
    private TextView textView;
    private Button summaryBtn;
    public double av;
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

    public void sendMessage(){
        summaryBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), SummaryActivity.class);
                intent.putExtra("Note", av);
                startActivity(intent);
            }
        });
    }

    private void setupButtonSave() {
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().toString().length() == 0){
                    editText.setText("0");
                }
                double note = Double.parseDouble(editText.getText().toString());
                notenListe.add(note);
                calculateGrade();
                editText.getText().clear();
            }});
        }

    private double calculateGrade() {
         double sum = 0;

        if (!notenListe.isEmpty()){
            for (double note : notenListe){
                sum += note;
            }
            av = sum / notenListe.size();
        }
        textView.setText(String.valueOf(av));
        return av;
    }
}
