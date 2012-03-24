package org.xcube.nfc.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.xcube.util.StringUtil;

@Path("/upc/{code}")
public class UpcResource {

	@GET
	@Produces("application/json")
	public String getJson(@PathParam("code") String upcCode) {
		return StringUtil.readFromStream(UpcResource.class.getResourceAsStream("/upc/" + upcCode + ".json"));
	}
}
