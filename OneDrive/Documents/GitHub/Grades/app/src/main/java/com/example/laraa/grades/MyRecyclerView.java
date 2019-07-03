package com.example.laraa.grades;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import java.util.ArrayList;
import java.util.List;

import myrecyclerview.MyAdapter;

public class MyRecyclerView extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button btn;
    private NotificationCompat.Builder builder;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private List<String> subjects = new ArrayList<String>();


    private static final int MY_NOTIFICATION_ID = 12345;
    private static final int MY_REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        btn = (Button) findViewById(R.id.notificationBtn);
        this.builder = new NotificationCompat.Builder(this);
        this.builder.setAutoCancel(true);
        setupRecyclerView();
        subjects.add("a");
        subjects.add("b");
        subjects.add("c");
    }

    private void setupRecyclerView() {
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new MyAdapter(subjects);
        recyclerView.setAdapter(mAdapter);

    }


    public void notiButtonClicked(View v) {
        this.builder.setSmallIcon(R.mipmap.ic_launcher);
        this.builder.setTicker("This is a ticker");
        this.builder.setWhen(System.currentTimeMillis() + 10 * 1000);
        this.builder.setContentTitle("this is the title");
        this.builder.setContentText("tis is the content text...");

        Intent intent = new Intent(this, MyRecyclerView.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, MY_REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        this.builder.setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

        Notification notification = builder.build();
        notificationManager.notify(MY_NOTIFICATION_ID, notification);
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
