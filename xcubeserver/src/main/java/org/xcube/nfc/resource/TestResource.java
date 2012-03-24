package org.xcube.nfc.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/test")
public class TestResource {

    @GET
    @Produces("text/xml")
    public String getTest() {
        return "<test>test</test>";
    }
}

