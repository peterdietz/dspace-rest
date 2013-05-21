package org.dspace.rest;

import org.dspace.content.Collection;
import org.dspace.core.Context;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;

/*
The "Path" annotation indicates the URI this class will be available at relative to your base URL.  For
example, if this web-app is launched at localhost using a context of "hello" and no URL pattern is defined
in the web.xml servlet mapping section, then the web service will be available at:

http://localhost:8080/<webapp>/collections
 */
@Path("/collections")
public class CollectionsResource {

    /*
    The "GET" annotation indicates this method will respond to HTTP Get requests.
    The "Produces" annotation indicates the MIME response the method will return.
     */
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String list() {
        StringBuilder everything = new StringBuilder();
        try {
            Context context = new Context();

            Collection[] collections = Collection.findAll(context);
            for(Collection collection : collections) {
                everything.append(collection.getName() + "<br/>\n");
            }

        } catch (SQLException e) {
            return "ERROR: " + e.getMessage();
        }

        return "<html><title>Hello!</title><body>Collections<br/>" + everything.toString() + ".</body></html> ";
    }
}
