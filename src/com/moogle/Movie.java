package com.moogle;// Name: Issawat Nilavongse
// Student ID: 6088099
// Section: 1

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Movie {
	private int mid;
	private String title;
	private int year;
	private Set<String> tags;
	private Map<Integer, Rating> ratings;	//mapping userID -> rating
	private Double avgRating;
	//additional
	
	public Movie(int _mid, String _title, int _year){
		
		mid = _mid ;
		title = _title ;
		year = _year ;
		tags = new HashSet<String>();
		ratings = new HashMap<Integer, Rating>();
		avgRating = 0.0 ;
		
	}
	
	public int getID() {
		
		return mid;
	}
	public String getTitle(){
		
		return title;
	}
	
	public Set<String> getTags() {
		
		return tags;
	}
	
	public void addTag(String tag){
		
		tags.add(tag);
	}
	
	public int getYear(){
		
		return year ;
	}
	
	public String toString()
	{
		return "[mid: "+mid+":"+title+" ("+year+") "+tags+"] -> avg rating: " + avgRating;
	}
	
	
	public double calMeanRating(){
		
		double in = 0.0 ;
		double n = 0 ;
		for(Rating i : ratings.values())
		{
			n++;
			in += i.rating ;
		}
		return in/n ;
	}
	
	public Double getMeanRating(){
		
		return avgRating ;
	}
	
	public void addRating(User user, Movie movie, double rating, long timestamp) {

		if(ratings == null) {
			ratings.put(user.getID(),new Rating(user,movie,rating,timestamp));}
		else {
			if(ratings.get(user.getID()) != null && ratings.get(user.getID()).timestamp >= timestamp)
			{
				ratings.remove(user.getID());
				ratings.put(user.getID(),new Rating(user,movie,rating,timestamp));
			}
			else ratings.put(user.getID(),new Rating(user,movie,rating,timestamp));
		}
		avgRating = calMeanRating();
		
	}
	
	public Map<Integer, Rating> getRating(){
			
		return ratings;
	}
	
}
