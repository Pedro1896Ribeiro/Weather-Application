package pt.ipb.sd.wm.ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import pt.ipb.sd.wm.entity.Location;
import pt.ipb.sd.wm.entity.WeatherData;

@Remote
public interface WeatherBeanRemote {

	// Create
	WeatherData createWD(WeatherData weatherData);
	long createWD(long mintemperature, long maxtemperature, String condition, long precipitation, long wind_speed, String wind_direction,
			int uv_radiation, Date date, Location location);

	//Read
	long getMinTemperature(long id);
	long getMaxTemperature(long id);
	String getCondition(long id);
	long getPricipitation(long id);
	long getWind_speed(long id);
	String getWind_direction(long id);
	int getUv_radiation(long id);
	Date getDateTime(long id);
	
	WeatherData getWeatherData(long id);
	List<WeatherData> getAllWeatherData();
	

	// Delete
	void deleteWD(WeatherData weatherData);
	void deleteWD(long id);

}
