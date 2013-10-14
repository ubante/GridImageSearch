package com.ubante.assignments.gridimagesearch;
/* 
 * Users can set search options here.
 * For example, size, color, type and site.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class SettingsActivity extends Activity {
	Spinner spnImageSize, spnColorFilter, spnImageType;
	EditText etSiteFilter;
	Button btnSave;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		
		addListenerOnSpinnerItemSelection();
		addListenerOnButton();
	}

	void addListenerOnSpinnerItemSelection() {
		/* 
		 * Not sure what this does but the emulator dies without this.
		 */
		spnImageSize = (Spinner) findViewById(R.id.spnImageSize);
		spnColorFilter = (Spinner) findViewById(R.id.spnColorFilter);
		spnImageType = (Spinner) findViewById(R.id.spnImageType);
		etSiteFilter = (EditText) findViewById(R.id.etSiteFilter);
		
	}
	
	void addListenerOnButton() {
		/*
		 * XXX This should be replaced with an onClick
		 */
		spnImageSize = (Spinner) findViewById(R.id.spnImageSize);
		spnColorFilter = (Spinner) findViewById(R.id.spnColorFilter);
		spnImageType = (Spinner) findViewById(R.id.spnImageType);
		btnSave = (Button) findViewById(R.id.btnSaveSettings);
		
		btnSave.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String toastString = "OnClickListener:\nImageSize: " + String.valueOf(spnImageSize.getSelectedItem()) +
						"\nColorFilter: " + String.valueOf(spnColorFilter.getSelectedItem()) +
						"\nImageType: " + String.valueOf(spnImageType.getSelectedItem()) +
						"\nSiteFilter: " + etSiteFilter.getText().toString();
				Toast.makeText(SettingsActivity.this, toastString,Toast.LENGTH_SHORT).show();
//				Intent i = new Intent(SettingsActivity.this, QueryActivity.class);
//				Settings.setImageSize(String.valueOf(spnImageSize.getSelectedItem()));
//				startActivity(i);
				Intent settings = new Intent();
				settings.putExtra("ImageSize", String.valueOf(spnImageSize.getSelectedItem()));
				settings.putExtra("ColorFilter", String.valueOf(spnColorFilter.getSelectedItem()));
				settings.putExtra("ImageType", String.valueOf(spnImageType.getSelectedItem()));
				settings.putExtra("SiteFilter", etSiteFilter.getText().toString());
				setResult(RESULT_OK, settings);
				SettingsActivity.this.finish();
			}
			
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.settings, menu);
		return true;
	}

}