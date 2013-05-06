package com.herokuapps.gymticks_android;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.json.JSONException;
import org.json.JSONObject;

public class Completion {
	private String climbType;
	private String completionType;
	private Date completionDate;
	private Date createdAt;
	private Date sendDate;
	private Date updatedAt;
	private Route route;
	private User user;
	
	public Completion(String climbType, String completionType,
			Date completionDate, Date createdAt, Date sendDate,
			Date updatedAt, Route route, User user) {
		super();
		this.climbType = climbType;
		this.completionType = completionType;
		this.completionDate = completionDate;
		this.createdAt = createdAt;
		this.sendDate = sendDate;
		this.updatedAt = updatedAt;
		this.route = route;
		this.user = user;
	}

	public Completion(JSONObject jObj) {
		super();
		
		SimpleDateFormat datef = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
		SimpleDateFormat datetimef = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
		
		try {
			this.climbType = jObj.getString("climb_type");
			this.completionType = jObj.getString("completion_type");
			if (!jObj.isNull("completion_date"))
				this.completionDate = datef.parse(jObj.getString("completion_date"));
			if (!jObj.isNull("created_at"))
				this.createdAt = datetimef.parse(jObj.getString("created_at"));
			if (!jObj.isNull("send_date"))
				this.sendDate = datetimef.parse(jObj.getString("send_date"));
			if (!jObj.isNull("updated_at"))
				this.updatedAt = datetimef.parse(jObj.getString("updated_at"));
			this.route = new Route(jObj.getJSONObject("route"));
			this.user = new User(jObj.getJSONObject("user"));
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (ParseException e) {
		    e.printStackTrace();
		}
	}
	
	public String getClimbType() {
		return climbType;
	}

	public void setClimbType(String climbType) {
		this.climbType = climbType;
	}

	public String getCompletionType() {
		return completionType;
	}

	public void setCompletionType(String completionType) {
		this.completionType = completionType;
	}
	
	public Date getCompletionDate() {
		return completionDate;
	}

	public void setCompletionDate(Date completionDate) {
		this.completionDate = completionDate;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
