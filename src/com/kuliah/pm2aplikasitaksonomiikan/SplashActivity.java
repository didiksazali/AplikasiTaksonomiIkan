package com.kuliah.pm2aplikasitaksonomiikan;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.content.Intent;
import android.widget.TextView;

public class SplashActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		Thread logoTimer = new Thread() {
			public void run() {
				TextView tv = (TextView) findViewById(R.id.textView1);
				try {
					int logoTimer = 0;
					tv.setText("proses sedang berjalan...sabar ya..");
					while (logoTimer < 5000) {
						sleep(100);
						logoTimer = logoTimer + 100;
					}
					;

				}

				catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				finally {

					Intent i = new Intent(getBaseContext(), TaksonomiIkanActivity.class);
					startActivity(i);
					finish();
				}
			}
		};

		logoTimer.start();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
