package com.example.basiclearn.chapter9;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.chapter8.DataBaseHelper;

public class DictProvider extends ContentProvider {

    private static UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
    private static final int WORDS = 1;
    private static final int WORD = 2;
    private DataBaseHelper dataBaseHelper;

    static {
        matcher.addURI(Words.AUTHORITY, "words", WORDS);
        matcher.addURI(Words.AUTHORITY, "word", WORD);
    }

    @Override
    public boolean onCreate() {
        dataBaseHelper = new DataBaseHelper(this.getContext(), "myDict.db3", 1);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
        switch (matcher.match(uri)) {
            case WORDS:
                return db.query("dict", strings, s, strings1,null, null, s1);
            case WORD:
                long id = ContentUris.parseId(uri);
                String where = Words.Word._ID + "=" + id;
                if (s != null && !s.equals("")){
                    where = where + " and " + s;
                }
                return db.query("dict", strings, where, strings1,null,null,s1);
            default:
                throw new IllegalArgumentException("未知uri" + uri);
        }
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (matcher.match(uri)) {
            case WORD:
                return "vnd.android.cursordir/com.example.dict";
            case WORDS:
                return "vnd.android.item/com.example.dict";
            default:
                throw new IllegalArgumentException("未知uri" + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
        long rowid = db.insert("dict", Words.Word._ID, contentValues);
        if (rowid > 0) {
            //在已有的uri后面追加id
            Uri wordUri = ContentUris.withAppendedId(uri, rowid);
            getContext().getContentResolver().notifyChange(wordUri, null);
            return wordUri;
        }
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
        int num = 0;
        switch (matcher.match(uri)) {
            case WORDS:
                num = db.delete("dict", s, strings);
                break;
            case WORD:
                long id = ContentUris.parseId(uri);
                String where = Words.Word._ID + "=" + id;
                if (s != null && !s.equals("")){
                    where = where + " and " + s;
                }
                num = db.delete("dict", where, strings);
                break;
            default:
                throw new IllegalArgumentException("未知uri" + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return num;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
        int num = 0;
        switch (matcher.match(uri)) {
            case WORD:
                long id = ContentUris.parseId(uri);
                String where = Words.Word._ID + "=" + id;
                if (s != null && !strings.equals("")){
                    where = where + " and " + strings;
                }
                num = db.update("dict", contentValues, where, strings);
                break;
            case  WORDS:
                num = db.update("dict", contentValues, s, strings);
                break;
            default:
                throw new IllegalArgumentException("未知uri" + uri);
        }
        getContext().getContentResolver().notifyChange(uri,null);
        return num;
    }
}
