package com.herokuapps.gymticks_android;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.json.JSONException;
import org.json.JSONObject;
	
public class User {
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private int adminTo;
	private URL profilePicUrl;
	private Date createdAt;
	
	public User(int id, String firstName, String lastName, String email,
			int adminTo, URL profilePicUrl, Date createdAt) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.adminTo = adminTo;
		this.profilePicUrl = profilePicUrl;
		this.createdAt = createdAt;
	}

	public User(JSONObject jObj) {
		super();
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
		
		try {
			this.id = jObj.getInt("id");
			this.firstName = jObj.getString("first_name");
			this.lastName = jObj.getString("last_name");
			this.email = jObj.getString("email");
			if (!jObj.isNull("admin_to"))
				this.adminTo = jObj.getInt("admin_to");
			//Temporarily set all picture URLs to test URL
			this.profilePicUrl = new URL("http://s3.amazonaws.com/gym_ticks_local_testing/users/profile_pics/000/000/015/original/profile_pic.jpg?1363021215");
			//this.profilePicUrl = new URL(jObj.getString("profile_pic_url"));
			if (!jObj.isNull("created_at"))
				this.createdAt = df.parse(jObj.getString("created_at"));
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (ParseException e) {
		    e.printStackTrace();
		} catch (MalformedURLException e) {
		    e.printStackTrace();
		}		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAdminTo() {
		return adminTo;
	}

	public void setAdminTo(int adminTo) {
		this.adminTo = adminTo;
	}

	public URL getProfilePicUrl() {
		return profilePicUrl;
	}

	public void setProfilePicUrl(URL profilePicUrl) {
		this.profilePicUrl = profilePicUrl;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
}
