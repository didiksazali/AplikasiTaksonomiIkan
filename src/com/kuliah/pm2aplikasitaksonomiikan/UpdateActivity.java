package com.kuliah.pm2aplikasitaksonomiikan;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.app.Activity;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.app.AlertDialog;
import android.widget.Toast;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class UpdateActivity extends Activity {
	EditText _filu, _kela, _bang, _kelu, _marg, _jeni;
	String MemID;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update);

		// Read var from Intent
		Intent intent = getIntent();
		MemID = intent.getStringExtra("MemID");
		// int kunci = Integer.parseInt(MemID.get)
		// Show Data
		ShowData();
	}

	public void ShowData() {
		// txtMemberID, txtName, txtTel
		final EditText _filu = (EditText) findViewById(R.id.editText6);
		final EditText _kela = (EditText) findViewById(R.id.editText1);
		final EditText _bang = (EditText) findViewById(R.id.editText2);
		final EditText _kelu = (EditText) findViewById(R.id.editText3);

		final EditText _marg = (EditText) findViewById(R.id.editText4);
		final EditText _jeni = (EditText) findViewById(R.id.editText5);

		// new Class DB
		final OperasiDatabase myDb = new OperasiDatabase(this);
		// Show Data
		String arrData[] = myDb.CariDataUpdate(MemID);
		if (arrData != null) {
			_filu.setText(arrData[0]);
			_kela.setText(arrData[1]);
			_bang.setText(arrData[2]);
			_kelu.setText(arrData[3]);
			_marg.setText(arrData[4]);
			_jeni.setText(arrData[5]);
		}
	}

	public boolean UpdateData() {
		final EditText _filu = (EditText) findViewById(R.id.editText6);
		final EditText _kela = (EditText) findViewById(R.id.editText1);
		final EditText _bang = (EditText) findViewById(R.id.editText2);
		final EditText _kelu = (EditText) findViewById(R.id.editText3);
		final EditText _marg = (EditText) findViewById(R.id.editText4);
		final EditText _jeni = (EditText) findViewById(R.id.editText5);
		// Dialog
		final AlertDialog.Builder adb = new AlertDialog.Builder(this);
		AlertDialog ad = adb.create();
		// periksa filum
		if (_filu.getText().length() == 0) {
			ad.setMessage("Filum harus diisi !");
			ad.show();
			_filu.requestFocus();
			return false;
		}
		// periksa bangsa
		if (_bang.getText().length() == 0) {
			ad.setMessage("Bangsa harus diisi !");
			ad.show();
			_bang.requestFocus();
			return false;
		}
		// new Class DB
		final OperasiDatabase myDb = new OperasiDatabase(this);
		Log.d("MemID", "adalah " + MemID);
		// Save Data
		long saveStatus = myDb.updateIkan(MemID, new Ikan(_filu.getText()
				.toString(), _kela.getText().toString(), _bang.getText()
				.toString(), _kelu.getText().toString(), _marg.getText()
				.toString(), _jeni.getText().toString()));
		if (saveStatus <= 0) {
			ad.setMessage("Ada kesalahan.....!! " + saveStatus);
			ad.show();

			return false;
		}

		Toast.makeText(UpdateActivity.this, "Update Data sukses dilakukan. ",
				Toast.LENGTH_SHORT).show();
		return true;
	}

	public void simpan(View v) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Apakah Yakin Record ini akan di Update ?")
				.setCancelable(false)
				.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						UpdateData();
						finish();
						Intent b2ti = new Intent(UpdateActivity.this,
								TaksonomiIkanActivity.class);
						startActivity(b2ti);
					}
				})
				.setNegativeButton("Tidak",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
							}
						}).show();

	}

	public void selesai(View v) {
		finish();
		Intent b2vupdate = new Intent(UpdateActivity.this, ViewUpdate.class);
		startActivity(b2vupdate);
	}
}
