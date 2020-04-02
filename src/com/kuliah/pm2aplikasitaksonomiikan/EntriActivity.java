package com.kuliah.pm2aplikasitaksonomiikan;

import android.view.View;
import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class EntriActivity extends Activity {
	EditText _filu, _kela, _bang, _kelu, _marg, _jeni;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_entri);
	}

	public void simpan(View v) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Apakah Anda ingin menyimpan data?")
				.setCancelable(false)
				.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
						insertData();
					}
				})
				.setNegativeButton("Tidak",
						new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
							}
						}).show();
	}
	public void insertData() {
		OperasiDatabase db = new OperasiDatabase(this);
		_filu = (EditText) findViewById(R.id.editText6);
		_kela = (EditText) findViewById(R.id.editText1);
		_bang = (EditText) findViewById(R.id.editText2);
		_kelu = (EditText) findViewById(R.id.editText3);
		_marg = (EditText) findViewById(R.id.editText4);
		_jeni = (EditText) findViewById(R.id.editText5);

		// insert data Ikan
		Log.d("Insert: ", "Inserting ..");
		db.addIkan(new Ikan(_filu.getText().toString(), _kela.getText()
				.toString(), _bang.getText().toString(), _kelu.getText()
				.toString(), _marg.getText().toString(), _jeni.getText()
					.toString()));
			// Baca seluruh Ikan
			Log.d("Reading: ", "Baca seluruh Ikan..");
			List<Ikan> fish = db.getAllIkan();

			for (Ikan cn : fish) {
			String log = "Kode: " + cn.getID() + " ,Filum : " + cn.getFilum()
						+ " ,Kelas : " + cn.getKelas();
				// Writing Contacts to log
				Log.d("isinya : ", log);
			}
		}
		public void selesai(View v) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("Apakah Anda ingin keluar?")
					.setCancelable(false)
					.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
							EntriActivity.this.finish();
						}
					})
					.setNegativeButton("Tidak",
							new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
									dialog.cancel();
								}
							}).show();
		}
	}
