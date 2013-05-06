package com.herokuapps.gymticks_android;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.herokuapps.gymticks2_android.R;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class UserPage extends ListActivity {
	private static final String apiUrl = "http://gym-ticks.herokuapp.com";
	
	private int userId;
	private User user;
	private ArrayList<Completion> completionList = new ArrayList<Completion>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Bundle extras = getIntent().getExtras();
		this.userId = Integer.valueOf(extras.getSerializable("user_id").toString());
		
		setContentView(R.layout.activity_user_page);
		
		getUserData();
		getCompletionData();
		
		if (user != null) {
			
			new DownloadImageTask((ImageView) findViewById(R.id.profile_pic), 180, 180)
	        .execute(user.getProfilePicUrl().toString());
			
			TextView text = (TextView) findViewById(R.id.user_name);
			text.setText(user.getFirstName() + " " + user.getLastName());
			text = (TextView) findViewById(R.id.climbs_completed);
			text.setText("Climbs Completed: " + completionList.size());
		}
		
		CompletionAdapter adapter = new CompletionAdapter(this,
				R.layout.user_completion_list_row,
				completionList);
		setListAdapter(adapter);
	}
	
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Intent intent = new Intent(this, RoutePage.class);
		intent.putExtra("gym_id", completionList.get(position).getRoute().getGymId());
		intent.putExtra("route_id", completionList.get(position).getRoute().getId());
		startActivity(intent);
	}
	
	private void getUserData() {
		String jsonString = "";
		
		try {  
		   URL url = new URL(apiUrl + "/api/v1/users/" + userId);
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
//		   JSONArray jArray = new JSONArray(jsonString);
//		   outerloop:
//		   for (int i=0; i < jArray.length(); i++)
//		   {
			   JSONObject jObj = new JSONObject(jsonString);
			   if (jObj != null) {
				   this.user = new User(jObj);
//				   break outerloop;
			   }
//		   }
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	private void getCompletionData() {
		String jsonString = "";

		try {  
		   URL url = new URL(apiUrl + "/api/v1/route_completions?user_id=" + userId);
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
