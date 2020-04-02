package com.kuliah.pm2aplikasitaksonomiikan;

import java.util.ArrayList;
import java.util.HashMap;
import android.util.Log;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class ViewHapus extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_hapus);

		final OperasiDatabase myDb = new OperasiDatabase(this);
		final ArrayList<HashMap<String, String>> MebmerList = myDb
				.UpdateLihatSeluruh();

		// listView1
		ListView lisView1 = (ListView) findViewById(R.id.listView1);

		SimpleAdapter sAdap;
		sAdap = new SimpleAdapter(ViewHapus.this, MebmerList,
				R.layout.activity_column, new String[] { "kode", "filum",
						"kelas" }, new int[] { R.id.ColID, R.id.ColFilum,
						R.id.ColKelas });
		lisView1.setAdapter(sAdap);

		lisView1.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> myAdapter, View myView,
					int position, long mylng) {
				// Show on new activity
				Intent newActivity = new Intent(ViewHapus.this,
						HapusActivity.class);
				newActivity.putExtra("MemID",
						MebmerList.get(position).get("kode").toString());
				startActivity(newActivity);

			}
		});

		// btnCancel (Cancel)
		final Button cancel = (Button) findViewById(R.id.btnCancel);
		cancel.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// Open Form Main
				Intent newActivity = new Intent(ViewHapus.this,
						TaksonomiIkanActivity.class);
				startActivity(newActivity);
			}
		});
	}
}
