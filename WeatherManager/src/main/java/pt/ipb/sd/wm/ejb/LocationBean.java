package pt.ipb.sd.wm.ejb;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import pt.ipb.sd.wm.entity.Location;
import pt.ipb.sd.wm.entity.WeatherData;

/**
 * Session Bean implementation class LocationBean
 */
@Stateless
@LocalBean
public class LocationBean implements LocationBeanRemote, LocationBeanLocal {

	@PersistenceContext(name = "WeatherService")
	EntityManager em;

	public LocationBean() {
	}

	@Override
	public void createLocation(Location location) {
		em.persist(location);
	}

	@Override
	public Location createLocation(String name) {
		Location l = new Location();
		l.setName(name);
		em.persist(l);
		return l;
	}

	@Override
	public List<Location> getLocations() {
		TypedQuery<Location> query = em.createQuery("select l from Location l ORDER BY l.name", Location.class);
		return query.getResultList();
	}

	@Override
	public List<WeatherData> getAllWeatherData(Location l) {
		TypedQuery<WeatherData> query = em.createQuery(
				"select l.weatherDataList from Location l WHERE l.id = :id AND l.weatherDataList IS NOT EMPTY",
				WeatherData.class);
		query.setParameter("id", l.getId());
		return query.getResultList();
	}

	@Override
	public void deleteLocation(Location location) {
		location = em.merge(location);
		em.remove(location);
	}

	@Override
	public void deleteLocation(long id) {
		deleteLocation(getLocation(id));
	}

	@Override
	public Location getLocation(long id) {
		Location location = em.find(Location.class, id);
		return location;
	}

	@Override
	public void updateLocation(long id, String newName) {
		Location location = em.find(Location.class, id);
		location = em.merge(location);
		location.setName(newName);
	}

	@Override
	public void updateLocation(long id) {
		Location location = em.find(Location.class, id);
		location = em.merge(location);
		location.setName(location.getName());
	}

	@Override
	public void updateLocation(Location location) {
		location = em.merge(location);
		location.setName(location.getName());
	}

}
