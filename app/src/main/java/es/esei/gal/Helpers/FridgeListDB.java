package es.esei.gal.Helpers;

import android.content.ClipData;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import es.esei.gal.Models.FridgeListLinesModel;
import es.esei.gal.Models.ItemModel;
import es.esei.gal.Models.MasterListModel;

public class FridgeListDB extends SQLiteOpenHelper {
    private static final String FRIDGELISTTABLENAME = "FRIDGELISTTABLE";
    private static final String FRIDGELINESTABLENAME = "FRIDGELINESTABLE";
    private static final String INVENTTABLENAME = "INVENTTABLE";

    public FridgeListDB(Context context) {
        super(context, "FridgeList.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE "+INVENTTABLENAME+
                "(ITEMID INTEGER PRIMARY KEY NOT NULL," +
                "ITEMNAME TEXT NOT NULL," +
                "PRICE REAL NOT NULL DEFAULT 0" +
                ")");

        db.execSQL("CREATE TABLE "+ FRIDGELISTTABLENAME +
                "(FRIDGELISTID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "LISTNAME TEXT NOT NULL," +
                "ISDONE INTEGER NOT NULL DEFAULT 0," +
                "CATEGORY TEXT DEFAULT 'Variado' " +
                ")");
        db.execSQL("CREATE TABLE "+ FRIDGELINESTABLENAME +
                "(LINENUM INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "FRIDGELISTID INTEGER NOT NULL," +
                "ITEMID INTEGER NOT NULL," +
                "ITEMNAME TEXT NOT NULL," +
                "QUANTITY INTEGER NOT NULL," +
                "FORMAT INTEGER NOT NULL DEFAULT 0," +
                "PRICE REAL NOT NULL DEFAULT 0)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+INVENTTABLENAME);
        db.execSQL("DROP TABLE IF EXISTS "+ FRIDGELISTTABLENAME);
        db.execSQL("DROP TABLE IF EXISTS "+ FRIDGELINESTABLENAME);

    }

    public Boolean insertItem(String name,String itemId, double price){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("ITEMID",itemId);
        cv.put("ITEMNAME",name);
        cv.put("PRICE",price);
        return  db.insert(INVENTTABLENAME,null,cv) != -1;
    }
    private Cursor getItemCursorById(int itemId){
        return this.
                getWritableDatabase().
                rawQuery("SELECT * " +
                                "FROM INVENTTABLE " +
                                "WHERE ITEMID = "+itemId,
                        null);

    }
    public ItemModel getItemById(int itemId)
    {
        Cursor c = getItemCursorById(itemId);
        int  x = c.getColumnCount();
        try{
            if(c.moveToFirst())
                return new ItemModel(c.getInt(0),c.getString(1),c.getDouble(2));
            else
                return null;
        }catch(NumberFormatException e)
        {
            throw e;
        }
    }

    public ArrayList<MasterListModel> getAllFridgeLists()
    {
        ArrayList<MasterListModel> toret = new ArrayList<>();
        Cursor c = getFridgeLists();
        int  x = c.getColumnCount();
        try{
            if(c.moveToFirst())
                do{
                    toret.add(new MasterListModel(Integer.parseInt(c.getString(0).toString()),c.getString(1).toString(),c.getString(2).toString()));
                }while(c.moveToNext());
            else
                return toret;
        }catch(NumberFormatException e)
        {
            throw e;
        }


        return toret;
    }

    private Cursor getFridgeLists() {
        return this.
                getWritableDatabase().
                rawQuery("SELECT * " +
                                "FROM "+FRIDGELISTTABLENAME,
                        null);
    }

    private Cursor getFridgeLinesCursor(int fridgeListId) {
        return this.
                getWritableDatabase().
                rawQuery("SELECT * " +
                                "FROM "+FRIDGELINESTABLENAME+" WHERE FRIDGELISTID ="+fridgeListId,
                        null);
    }
    public ArrayList<FridgeListLinesModel> getFridgeLines(int fridgeListId)
    {
        ArrayList<FridgeListLinesModel> toret = new ArrayList<>();
        Cursor c = getFridgeLinesCursor(fridgeListId);
        int  x = c.getColumnCount();

        try{
            if(c.moveToFirst())
                do{
                    toret.add(new FridgeListLinesModel(c.getInt(2),c.getString(3),c.getDouble(6),c.getInt(5) == 1,c.getInt(4)));
                }while(c.moveToNext());
            else
                return toret;
        }catch(NumberFormatException e)
        {
            throw e;
        }


        return toret;
    }

    public Cursor getFridgeLinesById(int fridgeListId){
        return this.getWritableDatabase().rawQuery("SELECT * "+
                "FROM "+FRIDGELINESTABLENAME+" WHERE FRIDGELISTID = "+fridgeListId,null);
    }

    public int insertFridgeList(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        Cursor c = null;
        cv.put("LISTNAME",name);
        if(db.insert(FRIDGELISTTABLENAME,null,cv) != -1) {
            c = this.
                    getWritableDatabase().
                    rawQuery("select * from "+ FRIDGELISTTABLENAME, null);
            if(c.moveToLast())
                return Integer.parseInt(c.getString(0));

        }
        return -1;

    }




    public int insertFridgeLine(int listId,int itemId, String itemName,int quantity,int format) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("ITEMID",itemId);
        cv.put("FRIDGELISTID",listId);
        cv.put("ITEMNAME",itemName);
        cv.put("QUANTITY",quantity);
        cv.put("FORMAT",format);
        if(db.insert(FRIDGELINESTABLENAME,null,cv) != -1)
        {

            Cursor c = this.
                    getWritableDatabase().
                    rawQuery("select * from "+ FRIDGELISTTABLENAME, null);
            if(c.moveToLast())
                return Integer.parseInt(c.getString(0));
        }
        return -1;
    }



}
