package com.example.basiclearn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class DateChooseActivity extends AppCompatActivity {

    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main02);

        DatePicker datePicker = findViewById(R.id.datapicker);
        TimePicker timePicker = findViewById(R.id.timepicker);
        editText = findViewById(R.id.show);
        //获取当前时间
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR);
        minute = calendar.get(Calendar.MINUTE);

        Button next = findViewById(R.id.toNext);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DateChooseActivity.this, MainActivity03.class);
                startActivity(intent);
            }
        });

        //datepicker初始化
        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int year, int month, int day) {
                DateChooseActivity.this.year = year;
                DateChooseActivity.this.month = month;
                DateChooseActivity.this.day = day;
                showDate(year, month, day, hour, minute);
            }
        });

        //timepicker初始化
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int hour, int minute) {
                DateChooseActivity.this.hour = hour;
                DateChooseActivity.this.minute = minute;
                showDate(year, month, day, hour, minute);
            }
        });
    }

    private void showDate(int year, int month, int day, int hour, int minute) {
        editText.setText(year + "年" + month + "月" + day +"日" + hour + "时" + minute + "分");
    }
}
