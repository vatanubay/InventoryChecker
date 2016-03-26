package com.example.inventorychecker.activity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;

import com.example.inventorychecker.Utils.Constant;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class LogoActivity extends AppCompatActivity {
    Handler handler;
    Runnable runnable;
    long delay_time;
    long time = 3000l;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_logo);
        File file = new File(Environment.getExternalStorageDirectory(), Constant.FileNameDataBase);
        if (!file.exists()) {
            try {
                copyDatabase();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        delay_time = time;
        handler.postDelayed(runnable, delay_time);
        time = System.currentTimeMillis();
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
        time = delay_time - (System.currentTimeMillis() - time);
    }

    public void copyDatabase() throws IOException {
        File file = new File(Environment.getExternalStorageDirectory(),Constant.FileNameDataBase);

        InputStream in = null;
        OutputStream out = null;
        try {
            in = getAssets().open(Constant.FileNameDataBase); //.I'm in a service, so I don't need context
            out = new FileOutputStream(file);

            int count = 0;
            byte[] buffer = new byte[1024 * 2];
            while ((count = in.read(buffer)) != -1) {
                out.write(buffer, 0, count);
                out.flush();
            }
        } catch (IOException err) {

        } finally {
            if (in != null)
                try {
                    in.close();
                } catch (IOException ignore) {
                }
            if (out != null)
                try {
                    out.close();
                } catch (IOException ignore) {
                }
        }

    }
}

        


   