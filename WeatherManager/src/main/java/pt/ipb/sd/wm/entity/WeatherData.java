package pt.ipb.sd.wm.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class WeatherData implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;
	long mintemperature;
	long maxtemperature;
	String condition;
	long precipitation;
	long wind_speed;
	String wind_direction;
	int uv_radiation;
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	@ManyToOne(fetch=FetchType.EAGER)
	Location location;

	public WeatherData() {

	}

	public WeatherData(long mintemperature, long maxtemperature, String condition, long precipitation, long wind_speed,
			String wind_direction, int uv_radiation, Date date, Location location) {
		super();
		this.mintemperature = mintemperature;
		this.maxtemperature = maxtemperature;
		this.condition = condition;
		this.precipitation = precipitation;
		this.wind_speed = wind_speed;
		this.wind_direction = wind_direction;
		this.uv_radiation = uv_radiation;
		this.date = date;
		this.location = location;
	}

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getMintemperature() {
		return mintemperature;
	}
	
	public void setMintemperature(long mintemp) {
		long transfer=0;
		if(this.mintemperature>this.maxtemperature) {
			transfer = mintemp;
			this.mintemperature = this.maxtemperature;
			this.maxtemperature = transfer;
		}
		this.mintemperature = mintemp;
	}
	
	public long getMaxtemperature() {
		return maxtemperature;
	}

	public void setMaxtemperature(long maxtemp) {
		this.maxtemperature = maxtemp;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public long getPrecipitation() {
		return precipitation;
	}

	public void setPrecipitation(long precipitation) {
		this.precipitation = precipitation;
	}

	public long getWind_speed() {
		return wind_speed;
	}

	public void setWind_speed(long wind_speed) {
		this.wind_speed = wind_speed;
	}

	public String getWind_direction() {
		return wind_direction;
	}

	public void setWind_direction(String wind_direction) {
		this.wind_direction = wind_direction;
	}

	public int getUv_radiation() {
		return uv_radiation;
	}
	
	public void setUv_radiation(int uv_radiation) {
		this.uv_radiation = uv_radiation;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDatestring() {
	SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return format.format(date);
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
}
