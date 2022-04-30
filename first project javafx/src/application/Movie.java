package application;

import java.io.Serializable;

public class Movie implements Serializable{
	private static final long serialVersionUID = 6248640183364000193L;
	
	private String Title;
	private int Year;
	private String Plot;
	private String Director;
	private String Poster;
	
	public Movie(String title, int year, String plot, String director, String poster) {
		this.Title = title;
		this.Year = year;
		this.Plot = plot;
		this.Director = director;
		this.Poster = poster;
	}
	
	public Movie(Movie inputMovie) {
		this.Title = inputMovie.getTitle();
		this.Year = inputMovie.getYear();
		this.Plot = inputMovie.getPlot();
		this.Director = inputMovie.getDirector();
		this.Poster = inputMovie.getPoster();
	}
	
	public String getTitle() {
		return Title;
	}
	
	public int getYear() {
		return Year;
	}
	
	public String getPlot() {
		return Plot;
	}
	
	public String getDirector() {
		return Director;
	}
	
	public String getPoster() {
		return Poster;
	}
}
