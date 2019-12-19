
package com.example.chapter8;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.basiclearn.R;

public class AddGesture extends AppCompatActivity {

    EditText editText;
    GestureOverlayView gestureOverlayView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_gesture);

        editText = findViewById(R.id.name);
        gestureOverlayView = findViewById(R.id.gesture);
        //手势的颜色
        gestureOverlayView.setGestureColor(Color.RED);
        //手势的宽度
        gestureOverlayView.setGestureStrokeWidth(4);
        //监听
        gestureOverlayView.addOnGesturePerformedListener(new GestureOverlayView.OnGesturePerformedListener() {
            @Override
            public void onGesturePerformed(GestureOverlayView gestureOverlayView, final Gesture gesture) {
                View saveDialog = getLayoutInflater().inflate(R.layout.save, null);
                ImageView imageView = saveDialog.findViewById(R.id.show);
                final EditText gestureName = saveDialog.findViewById(R.id.gesture_name);
                Bitmap bitmap = gesture.toBitmap(128,128,10,0xFFFF0000);
                imageView.setImageBitmap(bitmap);
                new AlertDialog.Builder(AddGesture.this).setView(saveDialog)
                        .setPositiveButton("保存", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                GestureLibrary gestureLibrary = GestureLibraries.fromFile("/sdcard/mygestures");
                                gestureLibrary.addGesture(gestureName.getText().toString(),gesture);
                                gestureLibrary.save();
                            }
                        })
                    .setNegativeButton("取消", null)
                    .show();
            }
        });
    }
}
