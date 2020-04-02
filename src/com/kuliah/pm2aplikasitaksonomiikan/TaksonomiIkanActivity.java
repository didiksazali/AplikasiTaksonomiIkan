package com.kuliah.pm2aplikasitaksonomiikan;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Menu;
import android.widget.Button;
import android.content.Intent;
import android.view.MenuInflater;

public class TaksonomiIkanActivity extends Activity {
	/** Called when the activity is first created. */
	private Button button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_taksonomiikan);
		button = (Button) findViewById(R.id.button1);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.menu_entri:
			Intent i = new Intent(this, EntriActivity.class);
			startActivity(i);
			return true;
		case R.id.menu_update:
			Intent u = new Intent(this, UpdateActivity.class);
			startActivity(u);
			return true;

		case R.id.menu_hapus:
			Intent h = new Intent(this, HapusActivity.class);
			startActivity(h);
			return true;

		case R.id.menu_laporan:
			Intent x = new Intent(this, LaporanActivity.class);
			startActivity(x);
			return true;
		case R.id.selesai:
			finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}