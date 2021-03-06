package com.herokuapps.gymticks_android;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.herokuapps.gymticks2_android.R;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class GymList extends ListActivity {
	private static final String apiUrl = "http://gym-ticks.herokuapp.com/api/v1/gyms";
	private List<Gym> gymList = new ArrayList<Gym>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_gym_list);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1,
				getServerData());
		setListAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.gym_list, menu);
		return true;
	}

	protected void onListItemClick(ListView l, View v, int position, long id) {
		Intent intent =new Intent(this, RouteList.class);
		intent.putExtra("gym_id", gymList.get(position).getId());
		startActivity(intent);
	}
	
	private String[] getServerData() {
		String jsonString = "";
		String[] results = null;
		
		try {  
		   URL url = new URL(apiUrl);  
		   BufferedReader in = new BufferedReader(new InputStreamReader(  
				   url.openStream()));
		   String inputLine;  
	
		   while ((inputLine = in.readLine()) != null) {  
			   jsonString = inputLine;  
		   }  
		   in.close();  
		}
		catch (IOException e) {  
		   e.printStackTrace();  
		}
       
		try {
			System.out.println(jsonString);
		   JSONArray jArray = new JSONArray(jsonString);
		   for (int i=0; i < jArray.length(); i++)
		   {
			   JSONObject jObj = jArray.getJSONObject(i);
			   if (jObj != null) {
				   gymList.add(new Gym(jObj));
			   }
		   }
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		results = new String[gymList.size()];
		for (int i=0; i < results.length; i++) {
			results[i] = gymList.get(i).getName();
		}

		return results;
	}
}
