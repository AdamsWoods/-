package com.example.basiclearn;import android.app.Activity;import android.content.Intent;import android.content.SharedPreferences;import android.graphics.Canvas;import android.graphics.Color;import android.graphics.Paint;import android.graphics.Rect;import android.os.Bundle;import android.view.Menu;import android.view.MenuInflater;import android.view.MenuItem;import android.view.SurfaceHolder;import android.view.SurfaceView;import android.view.View;import android.widget.Button;import android.widget.ListView;import android.widget.TextView;import android.widget.Toast;import androidx.annotation.NonNull;import com.example.basiclearn.chapter10.ServiceActivity;import com.example.basiclearn.chapter9.DictResolver;import com.example.chapter8.AddGesture;import com.example.chapter8.Dict;import com.example.chapter8.GestureFlip;import com.example.chapter8.GestureTest;import com.example.chapter8.RecogniseActivity;import java.io.File;import java.text.SimpleDateFormat;import java.util.Date;import java.util.Timer;import java.util.TimerTask;public class HandDrawActivity extends Activity {    private SurfaceHolder holder;    private Paint paint;    final int HEIGHT = 1600;    final int WIDTH = 320;    final int X_OFFSET = 5;    private int cx = X_OFFSET;    //实际的Y轴位置    int centerY = HEIGHT / 2;    Timer timer = new Timer();    TimerTask task = null;    private SharedPreferences preferences;    ListView listView;    TextView textView;    //记录当前的父文件夹    File currentParent;    //记录当前路径下的所有文件    File[] currentFiles = new File[]{};    @Override    protected void onCreate(Bundle savedInstanceState) {        super.onCreate(savedInstanceState);        setContentView(R.layout.activity_hand_draw);        Button button = findViewById(R.id.next);        button.setOnClickListener(new View.OnClickListener() {            @Override            public void onClick(View view) {                startActivity(new Intent(HandDrawActivity.this, PinBallActivity.class));            }        });        Button button1 = findViewById(R.id.move);        button1.setOnClickListener(new View.OnClickListener() {            @Override            public void onClick(View view) {//                startActivity(new Intent(HandDrawActivity.this, MoveBackActivity.class));                startActivity(new Intent(HandDrawActivity.this, ButterFlyActivity.class));            }        });        final SurfaceView surfaceView = findViewById(R.id.show);        holder = surfaceView.getHolder();        paint = new Paint();        paint.setColor(Color.GREEN);        paint.setStrokeWidth(3);        holder.addCallback(new SurfaceHolder.Callback() {            @Override            public void surfaceCreated(SurfaceHolder surfaceHolder) {            }            @Override            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {                drawBack();            }            @Override            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {                timer.cancel();            }        });        Button sin = findViewById(R.id.sin);        Button cos = findViewById(R.id.cos);        View.OnClickListener listener = new View.OnClickListener() {            @Override            public void onClick(final View view) {                holder = surfaceView.getHolder();                drawBack();                cx = X_OFFSET;                if (task != null) {                    task.cancel();                }                task = new TimerTask() {                    @Override                    public void run() {                        int cy = view.getId() == R.id.sin ?                                centerY - (int) (100 * Math.sin((cx - 5) * 2 * Math.PI / 150)) :                                centerY - (int) (100 * Math.cos((cx - 5) * 2 * Math.PI / 150));                        Canvas canvas = holder.lockCanvas(new Rect(cx, cy - 2, cx + 2, cy + 2));                        canvas.drawPoint(cx, cy, paint);                        cx++;                        if (cx > WIDTH) {                            task.cancel();                            task = null;                        }                        holder.unlockCanvasAndPost(canvas);                    }                };                timer.schedule(task, 0, 30);            }        };        sin.setOnClickListener(listener);        cos.setOnClickListener(listener);        preferences = getSharedPreferences("count", MODE_PRIVATE);        SharedPreferences.Editor editor = preferences.edit();        int count = preferences.getInt("count", 0);        Toast.makeText(this, "打开了" + count + "次", Toast.LENGTH_LONG).show();        //存入数据        editor.putInt("count", ++count);        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");        //提修改        editor.putString("time", sdf.format(new Date()));        editor.commit();        Button sdcard = findViewById(R.id.sdcard);        sdcard.setOnClickListener(clickListener);//        fileList1();        Button sql = findViewById(R.id.sql);        sql.setOnClickListener(clickListener);        Button gesture = findViewById(R.id.gesture);        gesture.setOnClickListener(clickListener);        Button filpper = findViewById(R.id.flipper);        filpper.setOnClickListener(clickListener);        Button add_gesture = findViewById(R.id.gesture_add);        add_gesture.setOnClickListener(clickListener);        Button read_gesture = findViewById(R.id.read_gesture);        read_gesture.setOnClickListener(clickListener);        Button dictresolver = findViewById(R.id.dictResolver);        dictresolver.setOnClickListener(clickListener);        Button serviceBt = findViewById(R.id.service);        serviceBt.setOnClickListener(clickListener);    }    View.OnClickListener clickListener = new View.OnClickListener() {        @Override        public void onClick(View view) {            switch (view.getId()) {                case R.id.sdcard:                    startActivity(new Intent(                            HandDrawActivity.this, SdcardViewActivity.class));                    break;                case R.id.sql:                    startActivity(new Intent(                            HandDrawActivity.this, Dict.class));                    break;                case R.id.gesture:                    startActivity(new Intent(                            HandDrawActivity.this, GestureTest.class));                    break;                case R.id.flipper:                    startActivity(new Intent(                            HandDrawActivity.this, GestureFlip.class));                    break;                case R.id.gesture_add:                    startActivity(new Intent(                            HandDrawActivity.this, AddGesture.class));                    break;                case R.id.read_gesture:                    startActivity(new Intent(                            HandDrawActivity.this, RecogniseActivity.class));                    break;                case R.id.dictResolver:                    startActivity(new Intent(                            HandDrawActivity.this, DictResolver.class));                    break;                case R.id.service:                    startActivity(new Intent(                            HandDrawActivity.this, ServiceActivity.class));                    break;            }        }    };    private void drawBack() {        Canvas canvas = this.holder.lockCanvas();        canvas.drawColor(Color.WHITE);        Paint paint = new Paint();        paint.setColor(Color.BLACK);        paint.setStrokeWidth(2);        //绘制坐标轴        canvas.drawLine(X_OFFSET, centerY, WIDTH, centerY, paint);        canvas.drawLine(X_OFFSET, 40, X_OFFSET, HEIGHT, paint);        holder.unlockCanvasAndPost(canvas);        holder.lockCanvas(new Rect(0, 0, 0, 0));        holder.unlockCanvasAndPost(canvas);    }    @Override    public boolean onCreateOptionsMenu(Menu menu) {        MenuInflater inflater = new MenuInflater(this);        inflater.inflate(R.menu.draw_menu, menu);        return super.onCreateOptionsMenu(menu);    }    @Override    public boolean onOptionsItemSelected(@NonNull MenuItem item) {        DrawView drawView = this.findViewById(R.id.draw);        switch (item.getItemId()) {            case R.id.red:                drawView.paint.setColor(Color.RED);                item.setChecked(true);                break;        }        return super.onOptionsItemSelected(item);    }}