package com.moogle;// Name: Issawat Nilavongse
// Student ID: 6088099
// Section: 1

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SimpleMovieSearchEngine implements BaseMovieSearchEngine {
	public Map<Integer, Movie> movies;
	
	@Override
	public Map<Integer, Movie> loadMovies(String movieFilename) {
		String rgEx = "(\\d+)(,)(\"*)(.+)(\\()(\\d*)(\\))(\"*)(,)(.+)";
		this.movies = new HashMap<Integer, Movie>();
		Pattern p = Pattern.compile(rgEx);
		int id,year ; String title="";
		String line = null ;
			try {
				BufferedReader r = new BufferedReader(new FileReader(movieFilename));
				try {
					while((line = r.readLine()) != null)
					{
						
							Matcher m = p.matcher(line);
							if(m.find()) {
								id = Integer.parseInt(m.group(1));
								 title = m.group(4);
								year = Integer.parseInt(m.group(6));
								String[] tag = m.group(10).split("\\|");
								movies.put(id,new Movie(id, title, year));
								for(String i : tag) 
								movies.get(id).addTag(i);
							}
					}
					r.close();
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		
		return movies;
	}

	@Override
	public void loadRating(String ratingFilename) {
			try {
				BufferedReader r = new BufferedReader(new FileReader(ratingFilename));
				boolean start = false ;
					String line ;
					try {
						while((line = r.readLine()) != null)
						{
							String[] sep = line.split(",");
							if(start) 
							{
								if(movies.get(Integer.parseInt(sep[1])) != null)
								movies.get(Integer.parseInt(sep[1])).addRating(new User(Integer.parseInt(sep[0])), movies.get(Integer.parseInt(sep[1])), Double.parseDouble(sep[2]), Integer.parseInt(sep[3]));
							}	
							else start = true ;
						}
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			

	@Override
	public void loadData(String movieFilename, String ratingFilename) {
		movies = loadMovies(movieFilename);
		loadRating(ratingFilename);
			
	}

	@Override
	public Map<Integer, Movie> getAllMovies() {
		return movies;
	}

	@Override
	public List<Movie> searchByTitle(String title, boolean exactMatch) {

		List<Movie> results = new ArrayList<>() ;
		if(exactMatch)
		{
			for(Movie i : movies.values())
			{
				if(i.getTitle().equalsIgnoreCase(title+" "))
				{
					results.add(i);
				}
			}			
		}
		else {
			for(Movie i : movies.values())
			{
				if(i.getTitle().toLowerCase().contains(title.toLowerCase()))
				{
					results.add(i);
				}
			}		
		}
		
		return results;
	}

	@Override
	public List<Movie> searchByTag(String tag) {
		
		List<Movie> results = new ArrayList<>() ;
		for(Movie i : movies.values())
			{
				for(String t : i.getTags())
				{
					if(t.toLowerCase().equals(tag.toLowerCase()))
					{
						results.add(i);
						break;
					}
				}
			}
		return results;
	}

	@Override
	public List<Movie>searchByYear(int year) {

		List<Movie> results = new ArrayList<>() ;
		for(Movie i : movies.values())
			{
				{
					if(i.getYear() == year)
					{
						results.add(i);
					}
				}
			}
		return results;
	}

	@Override
	public List<Movie> advanceSearch(String title, String tag, int year) {
		boolean yearChk ; if(year == -1)  yearChk = false ; else yearChk = true ;
		boolean titleChk ; if(title == null)  titleChk = false ; else titleChk = true ;
		boolean tagChk ; if(tag == null)   tagChk = false ; else  tagChk = true ;
		List<Movie> results = new ArrayList<>() ;
		for(Movie i : movies.values())
			{
			boolean[] chk = {false,false,false};
				if(title!=null)
				{
					if(i.getTitle().toLowerCase().contains(title.toLowerCase()))
						chk[0] = true ;
				}
				if(tag!=null)
				{
					for(String k : i.getTags())
					{
						if(k.equalsIgnoreCase(tag))
						{
						chk[1] = true ;
						break;
						}
					}
				}
				if(year!=-1)
				{
					if(i.getYear() == year) chk[2] = true ;
				}
				if(chk[0] == titleChk && chk[1] ==  tagChk && chk[2] == yearChk)
				{
					results.add(i);
				}
			}
		return results;
	}

	@Override
	public List<Movie> sortByTitle(List<Movie> unsortedMovies, boolean asc) {
		
		if(asc) {
		unsortedMovies.sort(Comparator.comparing(Movie::getTitle));
		return unsortedMovies;
		}else {
			unsortedMovies.sort(Comparator.comparing(Movie::getTitle).reversed());
			return unsortedMovies;
			}
		
	}

	@Override
	public List<Movie> sortByRating(List<Movie> unsortedMovies, boolean asc) {

		if(asc) {
			unsortedMovies.sort(Comparator.comparing(Movie::getTitle));
			unsortedMovies.sort(Comparator.comparing(Movie::getMeanRating));

			return unsortedMovies;
		}else {
			unsortedMovies.sort(Comparator.comparing(Movie::getTitle).reversed());
			unsortedMovies.sort(Comparator.comparing(Movie::getMeanRating).reversed());

			return unsortedMovies;
		}
	}

}
