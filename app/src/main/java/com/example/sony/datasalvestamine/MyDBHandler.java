package com.example.sony.datasalvestamine;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;

/*class stepsiaatselt töötab selle andmebaasiga. Vastutab kõige eest mis sa andmebaasiga teed. uuendad muudad ...*/
public class MyDBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    /*esimene version, esimese tabeliga. kui uuendad tabelit pead ka versiooni uuendama */
    private static final String DATABASE_NAME = "products.db";
    /*andmebaasi nimi*/
    public static final String TABLE_PRODUCTS = "products";
    /*tabeli nimi*/
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_PRODUCTNAME = "productname";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    /*house keeping stuff, need to pass info to superclass*/
    }

    /*iga kord kui lood tabeli esimest kord mida sa tahad et ma teeks*/
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_PRODUCTS + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," + COLUMN_PRODUCTNAME + " TEXT " +");";
        db.execSQL(query);
    }
    /*siis kui sa upgrade teed*/
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
/*database structure muutumise võu uuendamisel*/
        db.execSQL("DROP TABLE IF EXIST " + TABLE_PRODUCTS);
        onCreate(db);
        /*kustuta vana ära ja loo uus*/
    }
    /*add new row to the database*/
    public void addProduct(Products product){
        /*different values for different colums; essentially a list of values*/
        ContentValues values = new ContentValues();
        /*product name into that column*/
        values.put(COLUMN_PRODUCTNAME, product.get_productname());
        /*db = selle databaseiga mille me kirjutame*/
        SQLiteDatabase db = getWritableDatabase();
        /*lisab uue toote tabelisse*/
        db.insert(TABLE_PRODUCTS, null, values);
        db.close();
    }
    /*delete a product from database*/
    public void deleteProduct(String productName){
        SQLiteDatabase db = getWritableDatabase();
        /*drop kustutab terve tabeli ära, seega delete
        * kustutad tabelist kus toote nimi on võrdne sellega mis sisestati*/
        db.execSQL("DELETE FROM " + TABLE_PRODUCTS + " WHERE " + COLUMN_PRODUCTNAME + "=\"" + productName + "\";");
    }

    /*print out the database as a string*/
    public String databaseToString(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_PRODUCTS + " WHERE 1";

        /*cursor point to a location in your results*/
        Cursor c = db.rawQuery(query, null);
        /*move to the first row in your results*/
        c.moveToFirst();
        while (!c.isAfterLast()){
            if (c.getString(c.getColumnIndex("productname"))!= null){
                dbString += c.getString(c.getColumnIndex("productname"));
                dbString += "\n";
            }
            c.moveToNext();
        }
        db.close();
        return dbString;
    }
}
