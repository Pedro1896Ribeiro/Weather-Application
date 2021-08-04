
package pt.ipb.sd.wm.ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import pt.ipb.sd.wm.entity.Location;
import pt.ipb.sd.wm.entity.WeatherData;

/**
 * Session Bean implementation class WeatherBean
 */
@Stateless
@LocalBean
public class WeatherBean implements WeatherBeanRemote, WeatherBeanLocal {

	@PersistenceContext(name = "WeatherService")
	EntityManager em;

	public WeatherBean() {
	}

	@Override
	public WeatherData createWD(WeatherData weatherData) {
		em.persist(weatherData);
		return weatherData;
	}

	@Override
	public long createWD(long mintemperature, long maxtemperature, String condition, long precipitation,
			long wind_speed, String wind_direction, int uv_radiation, Date date, Location location) {
		WeatherData weatherData = new WeatherData(mintemperature, maxtemperature, condition, precipitation, wind_speed,
				wind_direction, uv_radiation, date, location);
		em.persist(weatherData);
		return weatherData.getId();
	}

	@Override
	public long getMinTemperature(long id) {
		WeatherData weatherData = em.find(WeatherData.class, id);
		return weatherData.getMintemperature();
	}

	@Override
	public long getMaxTemperature(long id) {
		WeatherData weatherData = em.find(WeatherData.class, id);
		return weatherData.getMaxtemperature();
	}

	@Override
	public long getPricipitation(long id) {
		WeatherData weatherData = em.find(WeatherData.class, id);
		return weatherData.getPrecipitation();
	}

	@Override
	public String getCondition(long id) {
		WeatherData weatherData = em.find(WeatherData.class, id);
		return weatherData.getCondition();
	}

	@Override
	public long getWind_speed(long id) {
		WeatherData weatherData = em.find(WeatherData.class, id);
		return weatherData.getWind_speed();
	}

	@Override
	public String getWind_direction(long id) {
		WeatherData weatherData = em.find(WeatherData.class, id);
		return weatherData.getWind_direction();
	}

	@Override
	public int getUv_radiation(long id) {
		WeatherData weatherData = em.find(WeatherData.class, id);
		return weatherData.getUv_radiation();
	}

	@Override
	public Date getDateTime(long id) {
		WeatherData weatherData = em.find(WeatherData.class, id);
		return weatherData.getDate();
	}

	@Override
	public List<WeatherData> getAllWeatherData() {
		TypedQuery<WeatherData> query = em.createQuery("select n from WeatherData n ORDER BY n.location.name",
				WeatherData.class);
		return query.getResultList();
	}

	@Override
	public WeatherData getWeatherData(long id) {
		return em.find(WeatherData.class, id);
	}

	@Override
	public void deleteWD(WeatherData weatherData) {
		weatherData = em.merge(weatherData);
		em.remove(weatherData);
	}

	@Override
	public void deleteWD(long id) {
		deleteWD(getWeatherData(id));
	}

}