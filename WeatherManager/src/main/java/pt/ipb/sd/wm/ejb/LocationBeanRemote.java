package pt.ipb.sd.wm.ejb;

import java.util.List;

import javax.ejb.Remote;

import pt.ipb.sd.wm.entity.Location;
import pt.ipb.sd.wm.entity.WeatherData;

@Remote
public interface LocationBeanRemote {

	//Create
	void createLocation(Location location);
	Location createLocation(String name);
	
	//Read
	Location getLocation(long id);
	List<WeatherData> getAllWeatherData(Location loc);
	List<Location> getLocations();
	
	//Update
	void updateLocation(long id, String newName);
	void updateLocation(long id);
	void updateLocation(Location location);
	
	//Delete
	void deleteLocation(Location location);
	void deleteLocation(long id);
	
}
