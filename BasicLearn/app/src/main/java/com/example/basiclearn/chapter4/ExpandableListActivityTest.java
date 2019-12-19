package com.example.basiclearn.chapter4;

import android.app.ExpandableListActivity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.basiclearn.R;

public class ExpandableListActivityTest extends ExpandableListActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        ExpandableListAdapter adapter = new BaseExpandableListAdapter() {

            int[] logos = new int[] {
                    R.drawable.avenda1,
                    R.drawable.avenda2,
                    R.drawable.avenda3
            };
            private String[] armTypes = new String[]{
              "神族","虫族","人族"
            };
            private String[][] arms = new String[][]{
                    {"狂战士","龙骑士","黑暗神堂","电兵"},
                    {"小狗","刺蛇","飞龙","自爆飞机"},
                    {"机枪兵","护士MM","幽灵"}
            };

            @Override
            public int getGroupCount() {
                return armTypes.length;
            }

            @Override
            public int getChildrenCount(int i) {
                return arms[i].length;
            }

            @Override
            public Object getGroup(int i) {
                return arms;
            }

            @Override
            public Object getChild(int i, int i1) {
                return arms[i][i1];
            }

            @Override
            public long getGroupId(int i) {
                return i;
            }

            @Override
            public long getChildId(int i, int i1) {
                return i1;
            }

            @Override
            public boolean hasStableIds() {
                return true;
            }

            @Override
            public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
                LinearLayout linearLayout = new LinearLayout(ExpandableListActivityTest.this);
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                ImageView logo = new ImageView(ExpandableListActivityTest.this);
                logo.setImageResource(logos[i]);
                linearLayout.addView(logo);
                TextView textView = getTextView();
                textView.setText(armTypes[i]);
                linearLayout.addView(textView);
                return linearLayout;
            }

            private TextView getTextView(){
                AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 64);
                TextView textView = new TextView(ExpandableListActivityTest.this);
                return textView;
            }

            @Override
            public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
                TextView textView = getTextView();
                textView.setText(getChild(i, i1).toString());
                return textView;
            }

            @Override
            public boolean isChildSelectable(int i, int i1) {
                return true;
            }
        };

        setListAdapter(adapter);
    }
}
