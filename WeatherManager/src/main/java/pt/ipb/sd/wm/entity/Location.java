package pt.ipb.sd.wm.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Location implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	long id;
	String name;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="location", cascade = CascadeType.ALL)
	private List<WeatherData> weatherDataList;
	
	List<WeatherData> weatherData = new ArrayList<>();
	
	public Location() {
		super();
	}
	
	public Location(String name) {
		this.name = name;
	}
	
	public void add(WeatherData l) {
		weatherData.add(l);
	}
	
	public List<WeatherData> getWeatherData() {
		return weatherData;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName (String name) {
		this.name = name;
	}
	
	public List<WeatherData> getWeatherDataList() {
		return weatherDataList;
	}
}

