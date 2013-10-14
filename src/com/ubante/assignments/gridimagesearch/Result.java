package com.ubante.assignments.gridimagesearch;

import java.io.Serializable;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Result implements Serializable {
	private static final long serialVersionUID = -3836820730311330832L;
	private String fullUrl;
	private String thumbUrl;

	public Result(JSONObject json) {
		try {
			this.fullUrl = json.getString("url");
			this.thumbUrl = json.getString("tbUrl");
		} catch (JSONException e) {
			this.fullUrl = null;
			this.thumbUrl = null;
		}
	}

	public String getFullUrl() {
		return fullUrl;
	}

	public String getThumbUrl() {
		return thumbUrl;
	}

	@Override
	public String toString() {
		return "Result [toString()=" + super.toString() + "]";
	}
	
	public static ArrayList<Result> fromJSONArray(
			JSONArray array) {
		ArrayList<Result> results = new ArrayList<Result>();
		for (int x=0; x<array.length(); x++) {
			try {
				results.add(new Result(array.getJSONObject(x)));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		
		return results;
	}
	
	
}
