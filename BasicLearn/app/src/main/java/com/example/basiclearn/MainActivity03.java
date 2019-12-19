package com.example.basiclearn;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class MainActivity03 extends Activity {

    private int[] data = new int[100];
    int hasData = 0;
    int mProgressStatus = 0;

    EditText editText;

    static private int NOTIFICATION_ID = 0x1111;
    private NotificationManager notificationManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //显示进度的进度条
        requestWindowFeature(Window.FEATURE_PROGRESS);
        //不显示进度的进度条
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);

        setContentView(R.layout.main_activity03);

        final ProgressBar bar = findViewById(R.id.bar);
        final ProgressBar bar2 = findViewById(R.id.bar2);
        final Button bt1 = findViewById(R.id.bt1);
        final Button bt2 = findViewById(R.id.bt2);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //显示不带进度的进度条
                setProgressBarIndeterminateVisibility(true);
                //显示带进度的进度条
                setProgressBarVisibility(true);
                setProgress(100);
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //隐藏不带进度的进度条
                setProgressBarIndeterminateVisibility(false);
                //隐藏带进度的进度条
                setProgressBarVisibility(false);
            }
        });

        final Handler mHandler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                if (msg.what == 0x111) {
                    bar.setProgress(mProgressStatus);
                    bar2.setProgress(mProgressStatus);
                }
            }
        };

        new Thread() {
            public void run() {
                while (mProgressStatus < 100) {
                    mProgressStatus = doWork();
                    Message message = new Message();
                    message.what = 0x111;
                    mHandler.sendMessage(message);
                }
            }
        }.start();

        final ImageView imageView = findViewById(R.id.image);
        SeekBar seekBar = findViewById(R.id.seekbar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            //滑块位置改变
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                imageView.setAlpha(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        RatingBar ratingBar = findViewById(R.id.rationbar);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float progress, boolean b) {
                //10个星星代表最大值255
                System.out.println(progress);
                imageView.setAlpha((int) progress * 255 / 10);
            }
        });

        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        final Bitmap bitmap = ((BitmapDrawable) getResources().getDrawable(R.drawable.image1)).getBitmap();

        Button send = findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity03.this, TabHostTestActivity.class);
                PendingIntent pi = PendingIntent.getActivity(MainActivity03.this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
                NotificationCompat.Builder builder1 = new NotificationCompat.Builder(MainActivity03.this);
                builder1.setContentTitle("通知")
                        .setContentText("你好你好你好")
                        .setContentIntent(pi)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setLargeIcon(bitmap)
                        .setPriority(NotificationCompat.PRIORITY_MAX);
                notificationManager.notify(NOTIFICATION_ID, builder1.build());
                Toast.makeText(MainActivity03.this, "klkl", Toast.LENGTH_LONG).show();
            }
        });

        Button cancel = findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                manager.cancel(NOTIFICATION_ID);
                openOptionsMenu();
            }
        });

        editText = findViewById(R.id.show);

        final Button button = findViewById(R.id.toNext);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity03.this, TabHostTestActivity.class);
                startActivity(intent);
            }
        });

//        TabHost tabHost = findViewById(R.id.tabhost);
//        tabHost.setup();
//        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("已接电话").setContent(R.id.tab01));
//        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("未接电话").setContent(R.id.tab02));
//        tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("呼出电话").setContent(R.id.tab03));

    }

    final int FONT_10 = 0x111;
    final int FONT_12 = 0x112;
    final int FONT_14 = 0x113;
    final int FONT_16 = 0x114;
    final int FONT_18 = 0x115;
    final int PLAIN_ITEM = 0x11b;

    final int FONT_GREEN = 0X118;
    final int FONT_RED = 0X116;
    final int FONT_BLUE = 0X117;

    final int MALE = 0x121;
    final int FEMALE = 0x122;

    final int GREEN = 0X123;
    final int RED = 0X124;
    final int BLUE = 0X125;

    MenuItem[] items = new MenuItem[3];
    String[] colorNames = new String[]{"绿色", "蓝色", "红色"};

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        SubMenu genderMenu = menu.addSubMenu("你的性别");
        genderMenu.setHeaderIcon(R.drawable.cc);
        genderMenu.setHeaderTitle("选择你的性别");
        genderMenu.add(0, MALE, 0, "男性");
        genderMenu.add(0, FEMALE, 0, "女性");
        genderMenu.setGroupCheckable(0, true, true);
        SubMenu colorMenu = menu.addSubMenu("你喜欢的颜色是");
        colorMenu.setHeaderIcon(R.drawable.dd);
        colorMenu.setHeaderTitle("你喜欢的颜色是");
        items[0] = colorMenu.add(0, RED, 0, colorNames[2]).setCheckable(true);
        items[1] = colorMenu.add(0, GREEN, 0, colorNames[0]).setCheckable(true);
        items[2] = colorMenu.add(0, BLUE, 0, colorNames[1]).setCheckable(true);
        items[2].setAlphabeticShortcut('u');
        items[2].setIntent(new Intent(this, TabHostTestActivity.class));
        SubMenu subMenu = menu.addSubMenu("启动activity");
        subMenu.setIcon(R.drawable.dd);
        subMenu.setHeaderTitle("选择启动的程序");
        MenuItem item = subMenu.add("查看经典");
        item.setIntent(new Intent(MainActivity03.this, TabHostTestActivity.class));
        return super.onCreateOptionsMenu(menu);
    }

    //    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
////        SubMenu
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    /**
//     * 另一个方式
//     * @param menu
//     * @return
//     */
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        SubMenu fontMenu = menu.addSubMenu("字体大学");
//        fontMenu.setIcon(R.mipmap.ic_launcher);
//        fontMenu.setHeaderIcon(R.drawable.aa);
//        MenuItem item = fontMenu.add(0, FONT_18, 0, "18");
//        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem menuItem) {
//                editText.setTextSize(18 * 2);
//                return true;
//            }
//        });
//        /**
//         * 省略。。。。。。
//         */
//        return super.onCreateOptionsMenu(menu);
//    }

    /**
     * 基础方式
     *
     * @param item
     * @return
     */
    //    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        SubMenu fontMenu = menu.addSubMenu("字体大小");
//        fontMenu.setIcon(R.drawable.cc);
//        fontMenu.setHeaderIcon(R.drawable.aa);
//        fontMenu.setHeaderTitle("选择字体大小");
//        fontMenu.add(0, FONT_10, 0, "10号字体");
//        fontMenu.add(0, FONT_12, 0, "12号字体");
//        fontMenu.add(0, FONT_14, 0 , "14号字体");
//        fontMenu.add(0, FONT_16, 0, "16号字体");
//        fontMenu.add(0, FONT_18, 0, "18号字体");
//
//        menu.add(0, PLAIN_ITEM, 0, "比普通菜单项");
//
//        SubMenu colorMenu = menu.addSubMenu("字体颜色");
//        colorMenu.setIcon(R.drawable.dd);
//        colorMenu.setHeaderIcon(R.drawable.bb);
//        colorMenu.setHeaderTitle("选择字体颜色");
//        colorMenu.add(0, FONT_BLUE, 0, "蓝色");
//        colorMenu.add(0, FONT_RED, 0, "红色");
//        colorMenu.add(0, FONT_GREEN, 0, "绿色");
//
//        return super.onCreateOptionsMenu(menu);
//    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case FONT_10:
                editText.setTextSize(10 * 2);
                break;
            case FONT_12:
                editText.setTextSize(12 * 2);
                break;
            case FONT_14:
                editText.setTextSize(14 * 2);
                break;
            case FONT_16:
                editText.setTextSize(16 * 2);
                break;
            case FONT_18:
                editText.setTextSize(18 * 2);
                break;
            case FONT_RED:
                editText.setTextColor(Color.RED);
                break;
            case FONT_BLUE:
                editText.setTextColor(Color.BLUE);
                break;
            case FONT_GREEN:
                editText.setTextColor(Color.GREEN);
                break;
            case PLAIN_ITEM:
                Toast.makeText(MainActivity03.this, "点击了我的item", Toast.LENGTH_LONG).show();
                break;
            case MALE:
                editText.setText("男生");
                item.setChecked(true);
                break;
            case FEMALE:
                editText.setText("女生");
                item.setChecked(true);
                break;
            case RED:
                if (item.isChecked()){
                    item.setChecked(false);
                } else {
                    item.setChecked(true);
                }
                editText.setText(editText.getText().append("红色"));
                break;
            case BLUE:
                if (item.isChecked()){
                    item.setChecked(false);
                } else {
                    item.setChecked(true);
                }
                editText.setText(editText.getText().append("蓝色"));
                break;
            case GREEN:
                if (item.isChecked()){
                    item.setChecked(false);
                } else {
                    item.setChecked(true);
                }
                editText.setText(editText.getText().append("绿色"));
                break;
        }
        return true;
    }

    public int doWork() {
        data[hasData++] = (int) (Math.random() * 100);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return hasData;
    }
}
