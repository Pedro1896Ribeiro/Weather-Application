package pt.ipb.sd.wm.backingbean;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import pt.ipb.sd.wm.ejb.LocationBeanRemote;
import pt.ipb.sd.wm.entity.Location;
import pt.ipb.sd.wm.entity.WeatherData;

@SuppressWarnings("deprecation")
@ManagedBean
@SessionScoped
public class LocationBackingBean {

	@EJB
	LocationBeanRemote bean;

	String createName;
	private String updateName;
	private long updateLocationId;

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public String getUpdateName() {
		return updateName;
	}

	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}

	public long getUpdateLocationId() {
		return updateLocationId;
	}

	public void setUpdateLocationId(long updateLocationId) {
		this.updateLocationId = updateLocationId;
	}

	public List<Location> getLocationList() {
		return bean.getLocations();
	}

	public List<WeatherData> getAllWeatherInfo(Location localidade) {
		return localidade.getWeatherData();
	}

	public void createLocation() {
		bean.createLocation(createName);
	}

	public void updateLocation() {
		bean.updateLocation(updateLocationId, updateName);
	}

	public void deleteLocation(Location location) {
		bean.deleteLocation(location);
	}
}
