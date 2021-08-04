package pt.ipb.sd.wm.ws;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import pt.ipb.sd.wm.ejb.LocationBeanRemote;
import pt.ipb.sd.wm.entity.Location;

@Path("/WeatherService")
public class WeatherWS {

	@EJB
	LocationBeanRemote locBean;

	@GET
	@Path("/list")
	@Produces("application/json")
	public List<Location> getLocationsList() {
		List<Location> l = locBean.getLocations();
		return l;
	}
}
