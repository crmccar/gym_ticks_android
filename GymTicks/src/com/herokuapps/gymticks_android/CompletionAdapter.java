package com.herokuapps.gymticks_android;

import java.text.DateFormat;
import java.util.ArrayList;

import com.herokuapps.gymticks2_android.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CompletionAdapter extends ArrayAdapter<Completion> {
    Context context; 
    int layoutResourceId;    
    ArrayList<Completion> objects;

	public CompletionAdapter(Context context, int layoutResourceId,
			ArrayList<Completion> objects) {
		super(context, layoutResourceId, objects);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.objects = objects;
	}

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        CompletionViewCache viewCache = null;
        
        if(row == null) {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            
            viewCache = new CompletionViewCache();
            
            //Used in both views
            viewCache.completionType = (TextView)row.findViewById(R.id.completion_type);
            viewCache.completionDate = (TextView)row.findViewById(R.id.completion_date);
            
            //Used in Route view
            viewCache.profilePic = (ImageView)row.findViewById(R.id.profile_pic);
            viewCache.completedBy = (TextView)row.findViewById(R.id.completed_by);
            
            //Used in User view
            viewCache.routeName = (TextView)row.findViewById(R.id.route_name);
            viewCache.rating = (TextView)row.findViewById(R.id.rating);
            
            row.setTag(viewCache);
        }
        else {
        	viewCache = (CompletionViewCache)row.getTag();
        }
        
        DateFormat df = DateFormat.getDateInstance();
        Completion completion = objects.get(position);
        
        User user = completion.getUser();

        viewCache.completionType.setText(completion.getCompletionType());
        viewCache.completionDate.setText("on " + df.format(completion.getCompletionDate()));
        
        if (viewCache.profilePic != null)
	        new DownloadImageTask(viewCache.profilePic)
	        .execute(user.getProfilePicUrl().toString());
        if (viewCache.completedBy != null)
        	viewCache.completedBy.setText(user.getFirstName() + " " + user.getLastName());
        
        if (viewCache.routeName != null)
        	viewCache.routeName.setText(completion.getRoute().getName());
        if (viewCache.rating != null)
        	viewCache.rating.setText(completion.getRoute().getRating());
        
        return row;
    }
    
    private class CompletionViewCache {
    	 //Used in both views
    	TextView completionType;
    	TextView completionDate;
    	
    	//Used in Route view
    	ImageView profilePic;
    	TextView completedBy;
    	
    	//Used in User view
    	TextView routeName;
    	TextView rating;
    }
}
