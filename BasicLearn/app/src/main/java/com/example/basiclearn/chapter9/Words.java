package com.example.basiclearn.chapter9;

import android.net.Uri;
import android.provider.BaseColumns;

public final class Words {

    //定义contentprovider的authority
    public static final String AUTHORITY = "com.example.basiclearn.chapter9";
    //定义一个静态内部类
    public static final class Word implements BaseColumns {
        //定义content所允许操作的三个数据列
        public static final String _ID = "_id";
        public static final String WORD = "word";
        public static final String DETAIL = "detail";
        //定义content提供服务的两个uri
        public static final Uri DICT_CONTENT_URI = Uri.parse("content://" + AUTHORITY
                + "/words");
        public static final Uri WORD_CONTENT_URI = Uri.parse("content://" + AUTHORITY
                + "/word");

    }
}
