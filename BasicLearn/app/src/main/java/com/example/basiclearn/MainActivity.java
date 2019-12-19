package com.example.basiclearn;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity {

    int[] images = new int[]{
            R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4
    };

    String[] books = new String[]{
            "疯狂","疯狂降级","疯狂扣税","疯狂政府"
    };

    int currentImg = 1;
    private int alpha = 255;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //图片浏览
        final Button plus = findViewById(R.id.plus);
        final Button min = findViewById(R.id.min);
        final ImageView imageView1 = findViewById(R.id.image1);
        final ImageView imageView2 = findViewById(R.id.image2);
        final Button next = findViewById(R.id.next);

        final Button toNext_bt = findViewById(R.id.toNext);
        toNext_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DateChooseActivity.class);
                startActivity(intent);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentImg >= 3) {
                    currentImg = -1;
                }
                //图片未回收，先清治回收该图片
                BitmapDrawable bitmapDrawable = (BitmapDrawable) imageView1.getDrawable();
                if (!bitmapDrawable.getBitmap().isRecycled()) {
                    bitmapDrawable.getBitmap().recycle();
                }
                //改变ImageView显示的图片
                imageView1.setImageBitmap(BitmapFactory.decodeResource(getResources(), images[++currentImg]));
            }
        });
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == plus) {
                    alpha += 20;
                } else if (view == min) {
                    alpha -= 20;
                }
                if (alpha >= 255) {
                    alpha -= 20;
                } else if (alpha <= 0) {
                    alpha = 0;
                }
                imageView1.setAlpha(alpha);
            }
        };
        plus.setOnClickListener(listener);
        min.setOnClickListener(listener);
        imageView1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                BitmapDrawable bitmapDrawable = (BitmapDrawable) imageView1.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                //缩放比例
                double scale = bitmap.getWidth() / 320.0;
                int x = (int)(motionEvent.getX() * scale);
                int y = (int) (motionEvent.getY() * scale);
                if (x + 120 > bitmap.getWidth()) {
                    x = bitmap.getWidth() - 120;
                }
                if (y + 120 > bitmap.getHeight()) {
                    y = bitmap.getHeight() - 120;
                }
                //显示图片的指定区域
                imageView2.setImageBitmap(Bitmap.createBitmap(bitmap,x,y,120,120));
                imageView2.setAlpha(alpha);
                return true;
            }
        });


        //自动填充编辑框
        final AutoCompleteTextView textView = findViewById(R.id.autoTextView);
        ArrayAdapter<String> arrayList = new ArrayAdapter<>(this,android.R.layout.simple_dropdown_item_1line,books);
        textView.setAdapter(arrayList);

        //Spinner列表
        Spinner spinner = findViewById(R.id.spinner);
        spinner.setAdapter(spinnerAdapter);
    }

    BaseAdapter spinnerAdapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return 10;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        //返回的view将作为每一项
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            LinearLayout linearLayout = new LinearLayout(MainActivity.this);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            ImageView imageView = new ImageView(MainActivity.this);
            imageView.setImageResource(R.drawable.image1);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(70, 70);
            imageView.setLayoutParams(params);
            TextView textView = new TextView(MainActivity.this);
            textView.setText(i + " ");
            textView.setTextSize(20);
            textView.setTextColor(Color.RED);
            linearLayout.addView(imageView);
            linearLayout.addView(textView);
            return linearLayout;
        }
    };
}
