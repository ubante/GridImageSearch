package com.ubante.assignments.gridimagesearch;
/*
 * From the QueryActivity, the user may click on an icon.  If so, this activity
 * will fetch and display the image.
 */

import com.loopj.android.image.SmartImageView;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class DisplayActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display);
		Result result = (Result) getIntent().getSerializableExtra("result");
		SmartImageView ivImage = (SmartImageView) findViewById(R.id.ivResult);
		ivImage.setImageUrl(result.getFullUrl());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display, menu);
		return true;
	}

}
