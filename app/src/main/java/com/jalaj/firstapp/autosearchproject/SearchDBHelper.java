package com.jalaj.firstapp.autosearchproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

/**
 * Created by jalajmehta on 7/16/16.
 */

public class SearchDBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME ="SEARCHAPP";
    public static final int DB_VERSION = 1;
    public static final String saTableName = "sa_product";
    public static final String saColNameArray[] = {"sku_number","product_name","product_image"};
    public static final String bracketOpen = "(";
    public static final String bracketClose = ")";
    SQLiteDatabase searchDatabase;

    public  SearchDBHelper(Context context)
    {
        super(context,DB_NAME,null,DB_VERSION);

    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        String create_table = "Create Table "+saTableName+ bracketOpen +saColNameArray[0]+" INTEGER PRIMARY KEY," + saColNameArray[1] +" TEXT, "+ saColNameArray[2] +" TEXT" +bracketClose;
        db.execSQL(create_table);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public String[] getProductFullName(String searchString){
        int i=0;
        searchDatabase = this.getReadableDatabase();
        String selectQuery = "Select * FROM "+saTableName;
        String whereClause =  " WHERE "+saColNameArray[0] +"||"+saColNameArray[1]+"||"+saColNameArray[2] +" LIKE ?";
        String [] parameters = {"%"+searchString+"%"};
        String [] results = new String[10];
        Cursor cursor = searchDatabase.rawQuery(selectQuery+whereClause,parameters);
        cursor.moveToFirst();

        do {
            results[i]=cursor.getInt(0)+""+cursor.getString(1)+""+cursor.getString(2);

            i++;
            if (i>9) break;
        } while
                (cursor.moveToNext());
        return results;
    }

    public void buildDatabase()
    {
        searchDatabase = getWritableDatabase();

        ContentValues values;
        for (int i =0; i<10;i++){
           values = new ContentValues();
            values.put(saColNameArray[1],"ProductName"+i);
            values.put(saColNameArray[2],"ProductImage"+i);
            searchDatabase.insert(saTableName,null,values);
        }
searchDatabase.close();

    }


}
