package com.kuliah.pm2aplikasitaksonomiikan;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

	public class OperasiDatabase extends SQLiteOpenHelper {

		// Versi Database
		private static final int DATABASE_VERSION = 1;

		// Nama Database
		private static final String DATABASE_NAME = "taksonomi";

		// Nama table Ikan
		private static final String TABLE_Ikan = "Ikan";

		// Nama atribut
		private static final String KEY_KODE = "kode";
		private static final String KEY_FILUM = "filum";
		private static final String KEY_KELAS = "kelas";
		private static final String KEY_BANGSA = "bangsa";
		private static final String KEY_KELUARGA = "keluarga";
		private static final String KEY_MARGA = "marga";
		private static final String KEY_JENIS = "jenis";

		public OperasiDatabase(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		// Buat Table
		@Override
		public void onCreate(SQLiteDatabase db) {
			String CREATE_Ikan_TABLE = "CREATE TABLE " + TABLE_Ikan + "("
				+ KEY_KODE + " INTEGER PRIMARY KEY," + KEY_FILUM + " TEXT,"
			+ KEY_KELAS + " TEXT, " + KEY_BANGSA + " TEXT," + KEY_KELUARGA
				+ " TEXT," + KEY_MARGA + " TEXT," + KEY_JENIS + " TEXT)";
			db.execSQL(CREATE_Ikan_TABLE);
		}

		// Upgrade database
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// Hapus table lama yang sudah ada
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_Ikan);

			// Buat table
			onCreate(db);
		}

		// Tambahkan data baru
		void addIkan(Ikan fish) {
			SQLiteDatabase db = this.getWritableDatabase();

			ContentValues values = new ContentValues();
			values.put(KEY_FILUM, fish.getFilum());
			values.put(KEY_KELAS, fish.getKelas());
			values.put(KEY_BANGSA, fish.getBangsa());
			values.put(KEY_KELUARGA, fish.getKeluarga());
			values.put(KEY_MARGA, fish.getMarga());
			values.put(KEY_JENIS, fish.getJenis());

			// Insert Record
			db.insert(TABLE_Ikan, null, values);
			db.close(); // Tutup koneksi databsae
		}

		// Mengambil satu data
		Ikan getIkan(String id) {
			SQLiteDatabase db = this.getReadableDatabase();

			Cursor cursor = db.query(TABLE_Ikan, new String[] { KEY_KODE,
					KEY_FILUM, KEY_KELAS }, KEY_KODE + "=?",

					new String[] { String.valueOf(id) }, null, null, null, null);
			if (cursor != null)
				cursor.moveToFirst();

			Ikan hasil = new Ikan(Integer.parseInt(cursor.getString(0)),
			cursor.getString(1),cursor.getString(2),cursor.getString(3),
			cursor.getString(4),cursor.getString(5), cursor.getString(6));

			// return hasil
			return hasil;
		}

		// Ambil seluruh data
		public List<Ikan> getAllIkan() {
			List<Ikan> ListIkan = new ArrayList<Ikan>();
			// Query untuk seluruh rekaman
			String selectQuery = "SELECT  * FROM " + TABLE_Ikan;

			SQLiteDatabase db = this.getWritableDatabase();
			Cursor cursor = db.rawQuery(selectQuery, null);

			// Iterasi untuk memasukkan ke dalam contactlist
			if (cursor.moveToFirst()) {
				do {
					Ikan contact = new Ikan();
					contact.setID(Integer.parseInt(cursor.getString(0)));
					contact.setFilum(cursor.getString(1));
					contact.setBangsa(cursor.getString(2));
					// Muat ke dalam contact
					ListIkan.add(contact);
				} while (cursor.moveToNext());
			}

			// kkembalian
			return ListIkan;
		}

		// Update satu rekaman
		public int updateIkan(String fs, Ikan fish) {
			
			SQLiteDatabase db = this.getWritableDatabase();

			ContentValues values = new ContentValues();
			Log.d("Update", fs + " " + fish.getFilum() + " " + fish.getKelas() + " " + fish.getBangsa() + " " + fish.getKeluarga() + " " + fish.getMarga() + " " + fish.getJenis());
			values.put(KEY_FILUM, fish.getFilum());
			values.put(KEY_KELAS, fish.getKelas());
			values.put(KEY_BANGSA, fish.getBangsa());
			values.put(KEY_KELUARGA, fish.getKeluarga());
			values.put(KEY_MARGA, fish.getMarga());
			values.put(KEY_JENIS, fish.getJenis());

			// update row
			return db.update(TABLE_Ikan, values, KEY_KODE + " = ?",
			new String[] { String.valueOf(fs) });

		}

		// Delete satu rekaman
		public int deleteIkan(String fs) {
			SQLiteDatabase db = this.getWritableDatabase();
			int hasil=db.delete(TABLE_Ikan, KEY_KODE + " = ?",
					new String[] { String.valueOf(fs) });
			db.close();
			return hasil;
		}

		// Hitung rekaman
		public int getContactsCount() {
			String countQuery = "SELECT  * FROM " + TABLE_Ikan;
			SQLiteDatabase db = this.getReadableDatabase();
			Cursor cursor = db.rawQuery(countQuery, null);
			cursor.close();

			// hasil perhitungan
			return cursor.getCount();
		}

		public ArrayList<HashMap<String, String>> UpdateLihatSeluruh() {
			try {
				ArrayList<HashMap<String, String>> myArrList = new ArrayList<HashMap<String, String>>();
				HashMap<String, String> map;
				SQLiteDatabase db;
				db = this.getReadableDatabase();
				String strSQL = "SELECT * FROM " + TABLE_Ikan;
				Cursor cursor = db.rawQuery(strSQL, null);
				if (cursor != null) {
					if (cursor.moveToFirst()) {
						do {
							map = new HashMap<String, String>();
							map.put("kode", cursor.getString(0));
						map.put("filum", cursor.getString(1));
						map.put("kelas", cursor.getString(2));
						map.put("bangsa", cursor.getString(3));
						map.put("keluarga", cursor.getString(4));
	map.put("marga", cursor.getString(5));
	map.put("jenis", cursor.getString(6));
							myArrList.add(map);
						} while (cursor.moveToNext());
					}
				}
				cursor.close();
				db.close();
				return myArrList;
			} catch (Exception e) {
				return null;

			}
		}
		
		public String[] CariDataUpdate(String strMemberID){
			try{
				String arrData[]=null;
				SQLiteDatabase db;
				db=this.getReadableDatabase();
				Cursor cursor = db.query(TABLE_Ikan, new String[] { "*" },"kode=?", new String[] { String.valueOf(strMemberID)},null,null,null,null);
				if (cursor!=null){
					if(cursor.moveToFirst()){
						arrData = new String[cursor.getColumnCount()];
						arrData[0]=cursor.getString(1);
						arrData[1]=cursor.getString(2);
						arrData[2]=cursor.getString(3);
						arrData[3]=cursor.getString(4);
						arrData[4]=cursor.getString(5);
						arrData[5]=cursor.getString(6);
					}
				}
				cursor.close();
				db.close();
				return arrData;
				}catch(Exception e){
					return null;
				}
		}
	}

			