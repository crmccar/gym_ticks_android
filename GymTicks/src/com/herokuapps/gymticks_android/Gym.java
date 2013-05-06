package com.herokuapps.gymticks_android;

import org.json.JSONException;
import org.json.JSONObject;

public class Gym {

	private int id;
	private String name;
	private String streetAddress;
	private String city;
	private String state;
	private String zip;

	public Gym(int id, String name, String streetAddress, String city,
			String state, String zip) {
		this.id = id;
		this.name = name;
		this.streetAddress = streetAddress;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}

	public Gym(JSONObject jsonObject) {
		try {
			this.id = jsonObject.getInt("id");
			this.name = jsonObject.getString("name");
			this.streetAddress = jsonObject.getString("street_address");
			this.city = jsonObject.getString("city");
			this.state = jsonObject.getString("state");
			this.zip = jsonObject.getString("zip");
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

}
