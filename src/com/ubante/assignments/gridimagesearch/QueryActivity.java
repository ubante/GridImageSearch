package com.ubante.assignments.gridimagesearch;
/*
 * This is the launcher activity.
 * User can enter a search query.
 * 
 * XXX needs infinite scroll or Load More button
 * XXX needs to add some kind of label underneath resulting images
 */
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.ubante.assignments.gridimagesearch.ResultArrayAdapter;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

public class QueryActivity extends Activity {
	EditText etQuery;
	GridView gvResults;
	Button btnSearch;
	ArrayList<Result> imageResults = new ArrayList<Result>();
	String googleApiUrl = "https://ajax.googleapis.com/ajax/services/search/images";
	String googleApiParams = "?v=1.0&rsz=8&start=0";
	ResultArrayAdapter imageAdapter;
	static final int REQUEST_CODE = 1;
	String imageSize, colorFilter, imageType = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_query);
		setupViews();
		imageAdapter = new ResultArrayAdapter(this, imageResults);
		gvResults.setAdapter(imageAdapter);
		gvResults.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adaptor, View parent,
					int position, long rowId) {
				Intent i = new Intent(getApplicationContext(), DisplayActivity.class);
				Result imageResult = imageResults.get(position);
				i.putExtra("result", imageResult);
				startActivity(i);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.query, menu);
		return true;
	}
	
	public void setupViews() {
		etQuery = (EditText) findViewById(R.id.etQuery2);
		gvResults = (GridView) findViewById(R.id.gvResults2);
		btnSearch = (Button) findViewById(R.id.btnSearch2);
	}
	
	public void onImageSearch(View v) {
		/*
		 * https://developers.google.com/image-search/v1/jsondevguide#json_args
		 * imgcolor=black
		 * imgsz=small|medium|large|xlarge
		 * imgtype=face
		 */
		String query = etQuery.getText().toString();
		Toast.makeText(this, "Searching for " + query, Toast.LENGTH_LONG).show();
		AsyncHttpClient client = new AsyncHttpClient();
		String extraParams = "&imgcolor=" + colorFilter + 
				"&imgsize=" + imageSize + 
				"&imgtype=" + imageType;
		
		// TODO need to figure out how to filter sites
		
				
		client.get(googleApiUrl + googleApiParams + extraParams +
				"&q=" + Uri.encode(query),
				new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONObject response) {
				JSONArray imageJsonResults = null;
				try {
					imageJsonResults = response.getJSONObject("responseData").
							getJSONArray("results");
					imageResults.clear();
					imageAdapter.addAll(Result.fromJSONArray(imageJsonResults));
					Log.d("DEBUG", imageResults.toString());
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void onSettings(View v) {
		Toast.makeText(this, "Switching to Settings", Toast.LENGTH_SHORT).show();
		Intent settingsIntent = new Intent(this, SettingsActivity.class);
		//startActivity(settingsIntent);
		startActivityForResult(settingsIntent, REQUEST_CODE);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
			Toast.makeText(this,  data.getExtras().getString("ImageSize"), Toast.LENGTH_SHORT).show();
		}
		imageSize = data.getExtras().getString("ImageSize");
		colorFilter = data.getExtras().getString("ColorFilter");
		imageType = data.getExtras().getString("imageType");
	}

}














