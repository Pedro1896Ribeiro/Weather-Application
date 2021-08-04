package pt.ipb.sd.wm.backingbean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import pt.ipb.sd.wm.ejb.LocationBeanRemote;
import pt.ipb.sd.wm.ejb.WeatherBeanRemote;
import pt.ipb.sd.wm.entity.Location;
import pt.ipb.sd.wm.entity.WeatherData;

@SuppressWarnings("deprecation")
@ManagedBean
@SessionScoped
public class WeatherBackingBean {

	@EJB
	LocationBeanRemote locBean;
	@EJB
	WeatherBeanRemote weatherBean;

	private long mintemperature;
	private long maxtemperature;
	private String condition;
	private long precipitation;
	private long wind_speed;
	private String wind_direction;
	private int uv_radiation;
	private String date;

	private long createLocationDropdown;
	private long locationListDropdown;

	public long getMintemperature() {
		return mintemperature;
	}

	public void setMintemperature(long mintemp) {
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public long getLocationDropdown() {
		return createLocationDropdown;
	}

	public void setLocationDropdown(long createLocationDropdown) {
		this.createLocationDropdown = createLocationDropdown;
	}

	public long getLocationListDropdown() {
		return locationListDropdown;
	}

	public void setLocationListDropdown(long locationListDropdown) {
		this.locationListDropdown = locationListDropdown;
	}

	public Date convertDate() throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return format.parse(date);
	}

	public String createWeatherData() throws ParseException {
		Location location = locBean.getLocation(createLocationDropdown);
		WeatherData weather = new WeatherData(mintemperature, maxtemperature, condition, precipitation, wind_speed,
				wind_direction, uv_radiation, convertDate(), location);
		weather.setLocation(location);
		location.getWeatherDataList().add(weather);
		weather = weatherBean.createWD(weather);
		return "weather.jsf";
	}

	public List<WeatherData> getAllWeatherData() {
		if (locationListDropdown == 0)
			return weatherBean.getAllWeatherData();
		else {
			Location location = locBean.getLocation(locationListDropdown);
			return locBean.getAllWeatherData(location);
		}
	}

	public void deleteWeatherData(WeatherData data) {
		weatherBean.deleteWD(data);
	}

}
