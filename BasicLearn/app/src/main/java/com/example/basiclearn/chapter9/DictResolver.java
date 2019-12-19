package com.example.basiclearn.chapter9;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.example.basiclearn.R;
import com.example.chapter8.DataBaseHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DictResolver extends AppCompatActivity {

    DataBaseHelper dbHelper;
    ContentResolver contentResolver;
    Button insert;
    Button search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dict);

//        dbHelper = new DataBaseHelper(this, "dict.db3", 1);

        contentResolver = getContentResolver();

        insert = findViewById(R.id.insert);
        search = findViewById(R.id.search);

        insert.setOnClickListener(listener);
        search.setOnClickListener(listener);
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.insert:
                    insertClick();
                    break;
                case R.id.search:
                    searchClick();
                    break;
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        if (dbHelper != null) {
////            dbHelper.close();
//        }
    }

    private void insertClick() {
        EditText text = findViewById(R.id.word);
        String word = (text.getText().toString());
        text = findViewById(R.id.detail);
        String detail = text.getText().toString();
        ContentValues values = new ContentValues();
        values.put(Words.Word.WORD, word);
        values.put(Words.Word.DETAIL, detail);
        contentResolver.insert(Words.Word.DICT_CONTENT_URI, values);
//        insertData(dbHelper.getWritableDatabase(), word, detail);
    }

    private void searchClick() {
        EditText text = findViewById(R.id.key);
        String key = text.getText().toString();
//        Cursor cursor = dbHelper.getReadableDatabase().rawQuery(
//                "select * from dict where word like ? or detail like" +
//                        "?", new String[]{"%"+key+"%","%"+key+"%"});
        Cursor cursor = contentResolver.query(Words.Word.DICT_CONTENT_URI,null,
                "word like ? or detail like" + "?",
                new String[]{"%"+key+"%","%"+key+"%"},null);
//        Bundle data = new Bundle();
//        data.putSerializable("data", convertCursorToList(cursor));
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,
                convertCursorToList(cursor),R.layout.list_line,
                new String[]{"word","detail"},new int[]{R.id.fileName,R.id.detail});
        ListView listView = findViewById(R.id.result);
        listView.setAdapter(simpleAdapter);
    }
//
//    private void insertData(SQLiteDatabase db, String word, String detail){
//        db.execSQL("insert into dict values(null, ?, ?)",
//                new String[]{word, detail});
//    }

    private ArrayList<Map<String, String>> convertCursorToList(Cursor cursor) {
        ArrayList<Map<String, String>> result = new ArrayList<>();
        while(cursor.moveToNext()){
            Map<String ,String> item = new HashMap<>();
            //取出第2列，3列中的值
            item.put("word", cursor.getString(1));
            item.put("detail", cursor.getString(2));
            result.add(item);
        }
        return result;
    }
}
