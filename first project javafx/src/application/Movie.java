package application;

public class Movie {
	private String Title;
	private int Year;
	private String Plot;
	private String Director;
	
	public Movie(String title, int year, String plot, String director) {
		this.Title = title;
		this.Year = year;
		this.Plot = plot;
		this.Director = director;
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
}
