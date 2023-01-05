package com.example.dictionaryapp;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

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

    // insertNewWord method is created for insert new word in to database
    public boolean insertNewWord(DictionaryWord word) {
        // get sqlite method
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        // create new instance from ContentValues for insert data
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

    // getWord method is created for get word data from database
    public DictionaryWord getWord(String word) {
        // get sqlite method
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        // create new instance from DictionaryWord
        DictionaryWord newWord;

        // create query for get word data
        String getDataQuery = "select * from " + dbTableName + " where " + dbTableColumnWord + " like '%" + word + "%'";
        // get data and store it in a new instance of the cursor
        Cursor wordCursor = sqLiteDatabase.rawQuery(getDataQuery,null);
        // check data
        if (wordCursor.getCount() > 0) {
            wordCursor.moveToFirst();
            newWord = new DictionaryWord(wordCursor.getString(1),wordCursor.getString(2),wordCursor.getString(3),wordCursor.getString(4),wordCursor.getString(5));
            return newWord;
        } else {
            return null;
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
