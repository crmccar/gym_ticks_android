package com.herokuapps.gymticks_android;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import org.json.JSONException;
import org.json.JSONObject;

public class Route {
	private int id;
	private int gymId;
	private String location;
	private String name;
	private String rating;
	private String setter;
	private Date createdAt;
	private Date retirementDate;
	private ArrayList<Completion> completions;
	
	public Route(int id, int gymId, String location, String name,
			String rating, String setter, 
			Date createdAt, Date retirementDate) {
		super();
		this.id = id;
		this.gymId = gymId;
		this.location = location;
		this.name = name;
		this.rating = rating;
		this.setter = setter;
		this.createdAt = createdAt;
		this.retirementDate = retirementDate;
	}	

	public Route(JSONObject jObj) {
		super();
		
		SimpleDateFormat datef = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
		SimpleDateFormat datetimef = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
		
		try {
			this.id = jObj.getInt("id");
			this.gymId = jObj.getInt("gym_id");
			this.location = jObj.getString("location");
			this.name = jObj.getString("name");
			this.rating = jObj.getString("rating");
			this.setter = jObj.getString("setter");
			if (!jObj.isNull("created_at"))
				this.createdAt = datetimef.parse(jObj.getString("created_at"));
			if (!jObj.isNull("retirement_date"))
				this.retirementDate = datef.parse(jObj.getString("retirement_date"));
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (ParseException e) {
		    e.printStackTrace();
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getGymId() {
		return gymId;
	}

	public void setGymId(int gymId) {
		this.gymId = gymId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRating() {
		return rating;
	}
	
	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getSetter() {
		return setter;
	}
	
	public void setSetter(String setter) {
		this.setter = setter;
	}
	
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getRetirementDate() {
		return retirementDate;
	}

	public void setRetirementDate(Date retirementDate) {
		this.retirementDate = retirementDate;
	}

	public ArrayList<Completion> getCompletions() {
		return completions;
	}

	public void setCompletions(ArrayList<Completion> completions) {
		this.completions = completions;
	}
}
