package com.dwikailham.beritaharian.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

@SuppressWarnings("ALL")
public class SqliteDatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "LowonganKerja.db";
    public static final String TABLE_NAME = "user_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAMA";
    public static final String COL_3 = "USERNAME";
    public static final String COL_4 = "PASSWORD";
    public static int DB_VERSION = 1;
    public static final String TABLE_NAME1 = "posting_table";
    public static final String COL1_1 = "ID";
    public static final String COL1_2 = "JUDUL";
    public static final String COL1_3 = "PERUSAHAAN";
    public static final String COL1_4 = "PERSYARATAN";
//    public static final String COL1_5 = "GAJI";
    public static final String COL1_6 = "TANGGAL";
    public static final String COL1_7 = "GAMBAR";

    public SqliteDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }

    @SuppressWarnings("unused")
    public SqliteDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " ("+COL_1+" INTEGER PRIMARY KEY AUTOINCREMENT,"+COL_2+" TEXT," +
                    COL_3+" TEXT,"+COL_4+" TEXT)");
        db.execSQL("insert into " + TABLE_NAME + "("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") values " +
                "(null,'tes','a','1')");
        db.execSQL("create table " + TABLE_NAME1 +" ("+COL1_1+" INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL1_2+" TEXT,"+COL1_3+" TEXT, "+COL1_4+" TEXT,  "+COL1_6+" TEXT, "+COL1_7+" TEXT)");
        db.execSQL("insert into " + TABLE_NAME1 + "("+COL1_1+", "+COL1_2+", "+COL1_3+", "+COL1_4+",  "+COL1_6+", "+COL1_7+") values " +
                "(null,'Supaya Gak Ada Audrey Selanjutnya','Pontianak, Kalimantan Barat','Media sosial lagi rame dengan tagar #JusticeforAudrey. ," +
                "Tagar tersebut dibuat untuk menceritakan sebuah musibah yang dialami oleh siswi SMP bernama Audrey di Pontianak.','06-04-2019','audrey')");
        db.execSQL("insert into " + TABLE_NAME1 + "("+COL1_1+", "+COL1_2+", "+COL1_3+", "+COL1_4+", "+COL1_6+", "+COL1_7+") values " +
                "(null,'Prediksi Final Piala Presidan, Arema VS Persebaya','Jakarta Pusat','Bermain imbang 2-2 di leg pertama, tentu merupakan keuntungan bagi Arema FC yang akan bertindak sebagai tuan rumah.\n" +
                "Dua gol dari Lizio dan Irfan Jaya sukses disamakan oleh Konate dan Hendro Siswanto. Momentum juara kini berada di kubu tim tuan rumah, namun Singo Edan tetap harus waspada dengan ketajaman Persebaya yang bisa merepotkan pertahanan Arema FC\n" +
                "\n" +
                "Penulis: Gigih\n','06-04-2019','areama')");
        db.execSQL("insert into " + TABLE_NAME1 + "("+COL1_1+", "+COL1_2+", "+COL1_3+", "+COL1_4+",  "+COL1_6+", "+COL1_7+") values " +
                "(null,'Kronologi Penemuan Surat Suara yang Sudah Tercoblos Di Malaysia','JAKARTA','Ketua Panwaslu Kuala Lumpur, Yaza Azzahra Ulyana menjelaskan, kronologis penemuan surat suara pasangan capres-cawapres nomor 01 Joko Widodo-Maruf Amin dan caleg Partai Nasdem yang sudah tercoblos.  ," +
                "Mendengar laporan itu, Yaza bersama seorang anggota Panwaslu, Rizki Israeni Nur menuju ke lokasi yang terletak di Taman Universiti Sungai Tangkas Bangi 43000 Kajang, Selangor. Tempat itu merupakan sebuah toko yang sudah dipenuhi dengan surat suara sebanyak kurang lebih 20 buah, 10 kantong plastik hitam dan kurang lebih 5 karung goni berwarna putih dengan tulisan Pos Malaysia','11-04-2019','pemungutan')");
        db.execSQL("insert into " + TABLE_NAME1 + "("+COL1_1+", "+COL1_2+", "+COL1_3+", "+COL1_4+",  "+COL1_6+", "+COL1_7+") values " +
                "(null,'Marvel Rilis Trailer Spesial Avengers : END GAME','Jakarta Pusat','Hari ini, tiket untuk film paling tunggu tahun ini, penjualan tiket Avengers: Endgame udah resmi dibuka. Mengantisipasi tanggal perilisan yang tinggal kurang dari sebulan, Marvel memanjakan penggemar dengan trailer ketiga dari film ke-22 dari rangkaian MCU ini," +
                "Menjadi konklusi dari Infinity Saga, Avengers: Endgame menghadirkan pertempuran klimaks melawan Thanos. Setelah dibuat hancur oleh kekuatan sang Mad Titan yang menggunakan infinity glove, tim Avengers siap untuk melakukan pembalasan dengan bantuan dari Carol Danvers alias Captain Marvel','06-04-2019','avengers')");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name,String username,String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,username);
        contentValues.put(COL_4,password);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    @SuppressWarnings("unused")
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    public Cursor getUserData(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME+" where "+COL_3+" = ? AND "+COL_4+" = ?", new String[] {username,password});
        return  res;
    }

    @SuppressWarnings("unused")
    public Cursor getUserDataById(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME+" where "+COL_1+" = ?", new String[] {id});
        return  res;
    }

    @SuppressWarnings("unused")
    public boolean updateData(String id, String name, String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,username);
        contentValues.put(COL_4,password);
        db.update(TABLE_NAME, contentValues, "ID = ?",new String[] { id });
        return true;
    }

    @SuppressWarnings("unused")
    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?",new String[] {id});
    }

    // Table Posting
    public boolean insertDataPosting(String judul, String perusahaan,String persyaratan, String tanggal, String gambar) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1_2,judul);
        contentValues.put(COL1_3,perusahaan);
        contentValues.put(COL1_4,persyaratan);
//        contentValues.put(COL1_5,gaji);
        contentValues.put(COL1_6,tanggal);
        contentValues.put(COL1_7,gambar);
        long result = db.insert(TABLE_NAME1,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllDataPosting() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME1,null);
        return res;
    }

    public Cursor getPostingData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME1+" where "+COL1_1+" = ?",new String[] {id});
        return  res;
    }

    public boolean updateDataPosting(String id, String judul, String perusahaan,String persyaratan, String tanggal, String gambar) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1_1,id);
        contentValues.put(COL1_2,judul);
        contentValues.put(COL1_3,perusahaan);
        contentValues.put(COL1_4,persyaratan);
//        contentValues.put(COL1_5,gaji);
        contentValues.put(COL1_6,tanggal);
        contentValues.put(COL1_7,gambar);
        db.update(TABLE_NAME1, contentValues, "ID = ?",new String[] { id });
        return true;
    }

    public Integer deleteDataPosting (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME1, "ID = ?",new String[] {id});
    }
}
