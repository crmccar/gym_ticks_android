package com.herokuapps.gymticks_android;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.DateFormat;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.herokuapps.gymticks2_android.R;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class RoutePage extends ListActivity {
	private static final String apiUrl = "http://gym-ticks.herokuapp.com";
	
	private int routeId;
	private int gymId;
	private Route route;
	private ArrayList<Completion> completionList = new ArrayList<Completion>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Bundle extras = getIntent().getExtras();
		this.gymId = Integer.valueOf(extras.getSerializable("gym_id").toString());
		this.routeId = Integer.valueOf(extras.getSerializable("route_id").toString());
		
		setContentView(R.layout.activity_route_page);
		
		getRouteData();
		
		if (route != null) {
			DateFormat df = DateFormat.getDateInstance();
			
			TextView text = (TextView) findViewById(R.id.route_name);
			text.setText(route.getName());
			text = (TextView) findViewById(R.id.route_rating);
			text.setText(route.getRating());
			if (route.getCreatedAt() != null) {
				text = (TextView) findViewById(R.id.route_set_date);
				text.setText("Set on " + df.format(route.getCreatedAt()));
			}
			text = (TextView) findViewById(R.id.route_set_by);
			text.setText("Set by " + route.getSetter());
			text = (TextView) findViewById(R.id.route_location);
			text.setText("Located at " + route.getLocation());
		}
		
		getCompletionData();
		
		CompletionAdapter adapter = new CompletionAdapter(this,
				R.layout.route_completion_list_row,
				completionList);
		setListAdapter(adapter);
	}

	protected void onListItemClick(ListView l, View v, int position, long id) {
		Intent intent = new Intent(this, UserPage.class);
		intent.putExtra("user_id", completionList.get(position).getUser().getId());
		startActivity(intent);
	}
	
	private void getRouteData() {
		String jsonString = "";
		
		try {  
		   URL url = new URL(apiUrl + "/api/v1/routes/" + gymId);
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
		   JSONObject jObj = new JSONObject(jsonString);
		   if (jObj != null) {
			   this.route = new Route(jObj);
		   }
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	private void getCompletionData() {
		String jsonString = "";

		try {  
		   URL url = new URL(apiUrl + "/api/v1/route_completions?route_id=" + routeId);
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
		   JSONArray jArray = new JSONArray(jsonString);
		   for (int i=0; i < jArray.length(); i++)
		   {
			   JSONObject jObj = jArray.getJSONObject(i);
			   if (jObj != null) {
				   this.completionList.add(new Completion(jObj));
			   }
		   }
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}	
}
