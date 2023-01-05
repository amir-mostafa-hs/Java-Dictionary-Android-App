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
        // get sqlite method
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        // create new instants from ContentValues for insert data
        ContentValues contentValues = new ContentValues();

        // put data in contentValues for insert data
        contentValues.put(dbTableColumnWord,word.Word);
        contentValues.put(dbTableColumnPersianTranslate,word.PersianTranslate);
        contentValues.put(dbTableColumnArabicTranslate,word.ArabicTranslate);
        contentValues.put(dbTableColumnPronounce,word.Pronounce);
        contentValues.put(dbTableColumnDescriptions,word.Descriptions);

        /*
            insert data and handle error with try catch.
            if insert data not successful this function return false, else return true
        */
        try {
            sqLiteDatabase.insert(dbTableName,null,contentValues);
            return  true;
        }catch (Exception error){
            return  false;
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
