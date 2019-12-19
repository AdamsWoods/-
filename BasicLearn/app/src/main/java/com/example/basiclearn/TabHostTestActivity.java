package com.example.basiclearn;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.basiclearn.chapter4.OtherActivity;

public class TabHostTestActivity extends TabActivity {

    int[] images = new int[]{
            R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4
    };

    static private int NOTIFICATION_ID = 0x1111;
    private NotificationManager notificationManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.tabhost_activity);
//        TabHost tabHost = findViewById(R.id.tabhost);
        final TabHost tabHost = getTabHost();
        LayoutInflater.from(this).inflate(R.layout.tabhost_activity, tabHost.getTabContentView(),true);
//        tabHost.setup();
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("已接电话").setContent(R.id.tab01));
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("未接电话").setContent(R.id.tab02));
        tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("呼出电话").setContent(R.id.tab03));

        final ImageSwitcher imageSwitcher = findViewById(R.id.imageswitcher);
        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(TabHostTestActivity.this);
                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                imageView.setScaleType(ImageView.ScaleType.CENTER);
                imageView.setBackgroundColor(0x0000000);
                return imageView;
            }
        });
        imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in));
        imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_out));

        Gallery gallery = findViewById(R.id.gallery);
        gallery.setAdapter(baseAdapter);
        gallery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                imageSwitcher.setImageResource(images[i % images.length]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Button dialog = findViewById(R.id.alertdailog);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder.setIcon(R.drawable.image2);
                builder.setTitle("我是一个alert dialog");
//                builder.setMessage("这是一个简单的消息");
                builder.setItems(new String[]{"红色","百色","律色"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        EditText editText = findViewById(R.id.show);
                        switch (which) {
                            case 0:
                                editText.setBackgroundColor(Color.RED);
                                break;
                            case 1:
                                editText.setBackgroundColor(Color.BLACK);
                                break;
                            case 2:
                                editText.setBackgroundColor(Color.GREEN);
                                break;
                        }
                    }
                });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        EditText editText = findViewById(R.id.show);
                        editText.setText("你点击了确定按钮");
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        EditText editText = findViewById(R.id.show);
                        editText.setText("你点击了取消按钮");
                    }
                });
                builder.setNeutralButton("你好", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        EditText editText = findViewById(R.id.show);
                        editText.setText("你点击了一个按钮");
                    }
                });
                builder.show();
            }
        });

        Button dialog2 = findViewById(R.id.alertdailog2);
        dialog2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(SINGLE_DIALOG);
            }
        });

        Button dialog3 = findViewById(R.id.alertdailog3);
        dialog3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog2();
            }
        });

        Button dialog4 = findViewById(R.id.alertdailog4);
        dialog4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                showDialog3();
                Toast toast = Toast.makeText(TabHostTestActivity.this, "自定以的提示",Toast.LENGTH_LONG);
                View toastView = toast.getView();
                ImageView imageView = new ImageView(TabHostTestActivity.this);
                imageView.setImageResource(R.drawable.image1);
                imageView.setLayoutParams(new LinearLayout.LayoutParams(80,80));
                LinearLayout layout = new LinearLayout(TabHostTestActivity.this);
                layout.addView(imageView);
                layout.addView(toastView);
                toast.setView(layout);
                toast.show();
            }
        });

        ImageView imageView = findViewById(R.id.image_pgbar);
        final AnimationDrawable ad = (AnimationDrawable) imageView.getDrawable();
        imageView.postDelayed(new Runnable() {
            @Override
            public void run() {
                ad.start();
            }
        }, 100);

        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        Button send= findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TabHostTestActivity.this, MainActivity03.class);
                PendingIntent pi = PendingIntent.getActivity(TabHostTestActivity.this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
                NotificationCompat.Builder builder1 = new NotificationCompat.Builder(TabHostTestActivity.this);
                builder1.setContentTitle("通知")
                        .setContentText("你好你好你好")
                        .setContentIntent(pi);
                notificationManager.notify(NOTIFICATION_ID, builder1.build());
                Toast.makeText(TabHostTestActivity.this,"klkl",Toast.LENGTH_LONG).show();
            }
        });

        Button cancel= findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                manager.cancel(NOTIFICATION_ID);
            }
        });

        Button bt= findViewById(R.id.toNext);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(TabHostTestActivity.this, OtherActivity.class);
                startActivity(intent);
            }
        });
    }

    BaseAdapter baseAdapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public Object getItem(int i) {
            return i;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ImageView imageView = new ImageView(TabHostTestActivity.this);
            imageView.setImageResource(images[i % images.length]);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setLayoutParams(new Gallery.LayoutParams(75, 100));
//            TypedArray typedArray = obtainStyledAttributes(R.styleable.);
//            imageView.setBackgroundResource(R.styleable.);
            return imageView;
        }
    };

    final int SINGLE_DIALOG = 0X113;

//    @Override
//    protected Dialog onCreateDialog(int id) {
//        switch (id) {
//            case SINGLE_DIALOG:
//                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
//                builder.setIcon(R.drawable.image3);
//                builder.setTitle("你是一个好屁");
//                builder.setSingleChoiceItems(new String[]{"bichit","比斯","你好"}, 0, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        EditText editText = findViewById(R.id.show);
//                        switch (i) {
//                            case 0:
//                                editText.setBackgroundColor(Color.RED);
//                                break;
//                            case 1:
//                                editText.setBackgroundColor(Color.BLACK);
//                                break;
//                            case 2:
//                                editText.setBackgroundColor(Color.BLUE);
//                                break;
//                        }
//                    }
//                });
//                return builder.create();
//        }
//        return null;
//    }

    @Override
    public Dialog onCreateDialog(int id, Bundle state) {
        switch (id) {
            case SINGLE_DIALOG:
                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setIcon(R.drawable.image3);
                builder.setTitle("单选列表对话框");
                builder.setSingleChoiceItems(new String[]{"bichit","比斯","你好"}, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        EditText editText = findViewById(R.id.show);
                        switch (i) {
                            case 0:
                                editText.setBackgroundColor(Color.RED);
                                break;
                            case 1:
                                editText.setBackgroundColor(Color.BLACK);
                                break;
                            case 2:
                                editText.setBackgroundColor(Color.BLUE);
                                break;
                        }
                    }
                });
                builder.setPositiveButton("确定", null);
                return builder.create();
        }
        return null;
    }

    boolean[] status = new boolean[]{true, false, false};
    String[] color = new String[]{"bichit","比斯","你好"};

    public void showDialog2() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.image4);
        builder.setTitle("多选列表对话框");
        builder.setMultiChoiceItems(color
                , status
                , new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position, boolean b) {
                        EditText editText = findViewById(R.id.show);
                        String text = " ";
                        for (int i = 0; i < status.length; i ++) {
                            if (status[i]) {
                                text += color[i] +"、";
                            }
                        }
                        editText.setText(text);
                    }
                });
        builder.setPositiveButton("确定",null);
        builder.create().show();
    }

    private int[] data = new int[100];
    int hasData = 0;
    int progressStatus = 0;

    public void showDialog3(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("自定义AlertDialog");
        builder.setIcon(R.drawable.ic_launcher_background);
//        SimpleAdapter simpleAdapter = new

    }
}
