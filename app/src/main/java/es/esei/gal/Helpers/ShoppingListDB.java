package es.esei.gal.Helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ShoppingListDB extends SQLiteOpenHelper {
    private static final String SHOPPINGLISTTABLENAME = "SHOPPINGLISTTABLE";
    private static final String SHOPPINGLINESTABLENAME = "SHOPPINGLINESTABLE";
    private static final String INVENTTABLENAME = "INVENTTABLE";
    public ShoppingListDB( Context context) {
        super(context, "FridgeList.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+INVENTTABLENAME+
                "(ITEMID INTEGER PRIMARY KEY NOT NULL," +
                "ITEMNAME TEXT NOT NULL," +
                "QUANTITY REAL NOT NULL" +
                ")");

        db.execSQL("CREATE TABLE "+SHOPPINGLISTTABLENAME+
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "LISTNAME TEXT NOT NULL," +
                "ISDONE BOOLEAN NOT NULL DEFAULT 0" +
                ")");
        db.execSQL("CREATE TABLE "+SHOPPINGLINESTABLENAME+
                "(LINENUM INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "SHOPPINGLISTID INTEGER NOT NULL," +
                "ITEMID TEXT NOT NULL," +
                "ITEMNAME TEXT NOT NULL," +
                "QUANTITY INTEGER NOT NULL," +
                "TOTALPRICE DOUBLE NOT NULL" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+INVENTTABLENAME);
        db.execSQL("DROP TABLE IF EXISTS "+SHOPPINGLISTTABLENAME);
        db.execSQL("DROP TABLE IF EXISTS "+SHOPPINGLINESTABLENAME);

    }

    public Boolean insertItem(String name,String itemId, double price){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("ITEMID",itemId);
        cv.put("ITEMNAME",name);
        cv.put("QUANTITY",price);
        return  db.insert(INVENTTABLENAME,null,cv) != -1;
    }
    public Cursor getItemById(String itemId){
        return this.
                getWritableDatabase().
                rawQuery("SELECT * " +
                                "FROM INVENTTABLE " +
                                "WHERE ITEMID = "+itemId,
                        null);

    }

    public int insertShoppingList(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        Cursor c = null;
        cv.put("LISTNAME",name);
        if(db.insert(SHOPPINGLISTTABLENAME,null,cv) != -1) {
            c = this.
                    getWritableDatabase().
                    rawQuery("select * from "+SHOPPINGLISTTABLENAME, null);
            if(c.moveToLast())
                return Integer.parseInt(c.getString(0));

        }
        return -1;

    }
    public Cursor getShoppingLists() {
        return this.
                getWritableDatabase().
                rawQuery("SELECT * " +
                                "FROM SHOPPINGLISTTABLE",
                        null);
    }

    public Boolean insertShoppingLine(int listId,int itemId, String itemName,int quantity, double price) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("ITEMID",itemId);
        cv.put("SHOPPINGLISTID",listId);
        cv.put("ITEMNAME",itemName);
        cv.put("QUANTITY",quantity);
        cv.put("TOTALPRICE",price*quantity);
        return  db.insert(SHOPPINGLINESTABLENAME,null,cv) != -1;
    }



}
