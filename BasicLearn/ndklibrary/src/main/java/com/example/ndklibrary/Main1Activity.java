package com.example.ndklibrary;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Main1Activity extends AppCompatActivity {

    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        TextView textView = findViewById(R.id.text);
        TextView textView1 = findViewById(R.id.text1);

        textView.setText(Facer.getFacer("-", "-", "~", "*"));
        textView1.setText(stringFromJNI());

        final ImageView iv = findViewById(R.id.image);
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.lenna);
//        iv.setImageBitmap(opBitmap(bitmap, Bitmap.Config.ARGB_8888));
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.lenna);
                iv.setImageBitmap(opBitmap(bitmap, Bitmap.Config.ARGB_8888));
            }
        });

    }

    public native String stringFromJNI();

    public native Bitmap opBitmap(Bitmap bitmap, Bitmap.Config argb8888);
}
