package anna.sejarahjakarta.helper;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import anna.sejarahjakarta.model.Tempat;

public class TempatAdapter {

	TempatHelper tempatHelper;
	Tempat tempat;
	public TempatAdapter(Context context){
		tempatHelper = new TempatHelper(context);
	}

	
	public List<Tempat> getSemua() {
		List<Tempat> lokasi = new ArrayList<Tempat>();
		String selectQuery = "SELECT  * FROM tempat where idkategori > 0";
		SQLiteDatabase db = tempatHelper.getReadableDatabase();
		Cursor c = db.rawQuery(selectQuery, null);
		if (c.moveToFirst()) {
			do {
				Tempat where = new Tempat();
				where.setID(c.getInt(c.getColumnIndex(Tempat.KEY_ID)));
				where.setNama(c.getString(c.getColumnIndex(Tempat.KEY_NAMA)));
				where.setAlamat(c.getString(c.getColumnIndex(Tempat.KEY_ALAMAT)));
				where.setLati(c.getString(c.getColumnIndex(Tempat.KEY_LATI)));
				where.setLong(c.getString(c.getColumnIndex(Tempat.KEY_LONG)));
				where.setIdkategori(c.getString(c.getColumnIndex(Tempat.KEY_IDKATEGORI)));
				lokasi.add(where);
			} while (c.moveToNext());
		}

		return lokasi;
	}
	public List<Tempat> getSemuaByID(String id) {
		List<Tempat> lokasi = new ArrayList<Tempat>();
		String selectQuery = "SELECT  * FROM tempat where _id = "+id+" ";
		SQLiteDatabase db = tempatHelper.getReadableDatabase();
		Cursor c = db.rawQuery(selectQuery, null);
		if (c.moveToFirst()) {
			do {
				Tempat where = new Tempat();
				where.setID(c.getInt(c.getColumnIndex(Tempat.KEY_ID)));
				where.setNama(c.getString(c.getColumnIndex(Tempat.KEY_NAMA)));
				where.setAlamat(c.getString(c.getColumnIndex(Tempat.KEY_ALAMAT)));
				where.setLati(c.getString(c.getColumnIndex(Tempat.KEY_LATI)));
				where.setLong(c.getString(c.getColumnIndex(Tempat.KEY_LONG)));
				where.setIdkategori(c.getString(c.getColumnIndex(Tempat.KEY_IDKATEGORI)));
				where.setDesk(c.getString(c.getColumnIndex(Tempat.KEY_DESK)));
				lokasi.add(where);
			} while (c.moveToNext());
		}

		return lokasi;
	}
	public List<Tempat> getKategori() {
		List<Tempat> lokasi = new ArrayList<Tempat>();
		String selectQuery = "SELECT  * FROM kategori ";
		SQLiteDatabase db = tempatHelper.getReadableDatabase();
		Cursor c = db.rawQuery(selectQuery, null);
		if (c.moveToFirst()) {
			do {
				Tempat where = new Tempat();
				where.setID(c.getInt(c.getColumnIndex(Tempat.KEY_ID)));
				where.setNama(c.getString(c.getColumnIndex(Tempat.KEY_NAMA)));
				lokasi.add(where);
			} while (c.moveToNext());
		}

		return lokasi;
	}
	public List<Tempat> getWisata(String id) {
		List<Tempat> lokasi = new ArrayList<Tempat>();
		String selectQuery = "SELECT  * FROM tempat where idkategori =" +id+" ";
		SQLiteDatabase db = tempatHelper.getReadableDatabase();
		Cursor c = db.rawQuery(selectQuery, null);
		if (c.moveToFirst()) {
			do {
				Tempat where = new Tempat();
				where.setID(c.getInt(c.getColumnIndex(Tempat.KEY_ID)));
				where.setNama(c.getString(c.getColumnIndex(Tempat.KEY_NAMA)));
				lokasi.add(where);
			} while (c.moveToNext());
		}

		return lokasi;
	}
	public void closeDB() {
		SQLiteDatabase db = tempatHelper.getReadableDatabase();
		if (db != null && db.isOpen())
			db.close();
	}
}