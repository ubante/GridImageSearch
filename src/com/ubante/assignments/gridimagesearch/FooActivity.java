package com.ubante.assignments.gridimagesearch;

/*
 * This is just for syntax testing and completely separate for from the other activities.
 */
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class FooActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_foo);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.foo, menu);
		return true;
	}

}
