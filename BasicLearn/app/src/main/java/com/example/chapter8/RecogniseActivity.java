package com.example.chapter8;

import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.basiclearn.R;

import java.util.ArrayList;

public class RecogniseActivity extends AppCompatActivity {

    GestureOverlayView gestureOverlayView;
    GestureLibrary library;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_gesture);
        library = GestureLibraries.fromFile("/sdcard/mygestures");
        if (library.load()) {
            Toast.makeText(this,"装载成功！",Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this,"失败!",Toast.LENGTH_LONG).show();
        }
        gestureOverlayView = findViewById(R.id.gesture);
        gestureOverlayView.addOnGesturePerformedListener(new GestureOverlayView.OnGesturePerformedListener() {
            @Override
            public void onGesturePerformed(GestureOverlayView gestureOverlayView, Gesture gesture) {
                //识别手势
                ArrayList<Prediction> predictions = library.recognize(gesture);
                ArrayList<String> result = new ArrayList<>();
                for (Prediction pred : predictions) {
                    if (pred.score > 2.0) {
                        result.add("与手势【" + pred.name + "】的相似度为" + pred.score);
                    }
                }
                if (result.size() > 0) {
                    ArrayAdapter adapter = new ArrayAdapter(
                            RecogniseActivity.this,
                            android.R.layout.simple_dropdown_item_1line,
                            result.toArray()
                    );
                    new AlertDialog.Builder(RecogniseActivity.this)
                        .setAdapter(adapter,null)
                        .setPositiveButton("保存",null).show();
                } else {
                    Toast.makeText(RecogniseActivity.this,"无法匹配",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
