package com.example.dictionaryapp;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/*
    This class create for create and connect
    to the sqlite database and save app data
*/
public class DatabaseController extends SQLiteOpenHelper {
    // save database name and version in to variable
    private static final String dbName = "Dictionary";
    private static final Integer dbVersion = 1;
    // save database table name in to variable
    private static final String dbTableName = "WordsTable";
    // save database table columns name in to variable
    private static final String dbTableColumnWordId = "WordId";
    private static final String dbTableColumnWord = "Word";
    private static final String dbTableColumnPersianTranslate = "PersianTranslate";
    private static final String dbTableColumnArabicTranslate = "ArabicTranslate";
    private static final String dbTableColumnPronounce = "Pronounce";
    private static final String dbTableColumnDescriptions = "Descriptions";

    public DatabaseController(Context context) {
        super(context, dbName, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        /*
            in this method, create createTableQuery variable and
            whit this variable create new database.
        */
        String createTableQuery =
                "create table " + dbTableName +"("+
                dbTableColumnWordId +" integer primary key autoincrement,"+
                dbTableColumnWord + " text,"+
                dbTableColumnPersianTranslate + " text,"+
                dbTableColumnArabicTranslate + " text,"+
                dbTableColumnPronounce + " text,"+
                dbTableColumnDescriptions + " text)";
        sqLiteDatabase.execSQL(createTableQuery);
    }

    public boolean insertNewWord(DictionaryWord word) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        return false;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
