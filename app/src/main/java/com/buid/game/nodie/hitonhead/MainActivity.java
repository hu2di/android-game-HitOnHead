package com.buid.game.nodie.hitonhead;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView tvScore, tvHigh;
    private int score = 0;
    private int high = 0;

    private GridView gvMain;
    private int[] data = new int[9];
    private GVMainAdapter adapter;

    private Handler handler;
    private Runnable runnable;
    private int delay = 1000;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        play();
    }

    private void init() {
        tvScore = findViewById(R.id.tvScore);
        tvHigh = findViewById(R.id.tvHigh);

        gvMain = findViewById(R.id.gvMain);

        for (int i = 0; i < data.length; i++) {
            data[i] = 0;
        }
        adapter = new GVMainAdapter(this, data);

        gvMain.setAdapter(adapter);
        gvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (data[i] == 1) {
                    score++;
                    tvScore.setText("Score: " + score);

                    data[i] = 0;
                    adapter.notifyDataSetChanged();
                } else {
                    score--;
                    tvScore.setText("Score: " + score);
                }
            }
        });

    }

    private void play() {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                count++;
                if (count % 5 == 0 && delay >= 100) {
                    delay = delay - 50;
                }

                build();

                handler.postDelayed(runnable, delay);
            }
        };
        handler.postDelayed(runnable, 0);
    }

    private void build() {
        int position = new Random().nextInt(data.length);
        for (int i = 0; i < data.length; i++) {
            if (i == position) {
                data[i] = 1;
            } else {
                data[i] = 0;
            }
        }

        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getHigh();
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveHigh();
    }

    private void saveHigh() {
        SharedPreferences preferences = this.getSharedPreferences("SETTINGS", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        if (score > high) {
            high = score;
        }
        editor.putInt("HIGH", high);
        editor.apply();
    }

    private void getHigh() {
        SharedPreferences preferences = this.getSharedPreferences("SETTINGS", Context.MODE_PRIVATE);
        high = preferences.getInt("HIGH", 0);
        tvHigh.setText("High: " + high);
    }
}
