package com.example.basiclearn.chapter4;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ClipDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.basiclearn.HandDrawActivity;
import com.example.basiclearn.PolygonActivity;
import com.example.basiclearn.R;
import com.example.basiclearn.Texture3DActivity;

import java.util.Timer;
import java.util.TimerTask;

//LauncherActivity继承了ListActivity
public class OtherActivity extends Activity {

//    //定义两个activity的名称
//    String[] names = {"设置程序参数","查看我的小图片"};
//    //定义两个activity对应实现的类
//    Class<?>[] classes = {ExpandableListActivityTest.class, PreferenceActivityTest.class};

    boolean flag = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chapter6_01);

        final ImageView imageView = findViewById(R.id.image1);

        final ClipDrawable clipDrawable = (ClipDrawable) imageView.getDrawable();
        final Handler handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                if (msg.what == 111 && clipDrawable!=null) {
                    imageView.setImageDrawable(clipDrawable);
                    clipDrawable.setLevel(clipDrawable.getLevel() + 200);
                }
            }
        };

        final ImageView imageView1 = findViewById(R.id.image2);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.image);
        animation.setFillAfter(true);
        imageView1.startAnimation(animation);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = 111;
                handler.sendMessage(message);
                if (clipDrawable != null &&clipDrawable.getLevel() >= 10000)
                    this.cancel();
            }
        }, 0, 300);
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
//                android.R.layout.simple_list_item_1, names);
//        setListAdapter(adapter);

        final EditText shape1 = findViewById(R.id.shape1);
//        openOptionsMenu();
//        openContextMenu();
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shape1.setFocusable(false);
                if (flag) {
                    openOptionsMenu();
                    flag = false;
                } else {
                    openContextMenu(imageView1);
                    flag = true;
                }
            }
        });
        registerForContextMenu(imageView1);

        Button button = findViewById(R.id.to_next);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OtherActivity.this, HandDrawActivity.class));
            }
        });
        Button button2 = findViewById(R.id.opengl1);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OtherActivity.this, Texture3DActivity.class));
            }
        });
        Button button3 = findViewById(R.id.opengl2);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OtherActivity.this, PolygonActivity.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.context_menu, menu);
        menu.setHeaderIcon(R.drawable.dd);
        menu.setHeaderTitle("HeaderTitle");
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        return super.onContextItemSelected(item);
    }

    //    //根据列表项返回指定的intent
//    @Override
//    protected Intent intentForPosition(int position) {
//        return new Intent(this, classes[position]);
//    }


}
