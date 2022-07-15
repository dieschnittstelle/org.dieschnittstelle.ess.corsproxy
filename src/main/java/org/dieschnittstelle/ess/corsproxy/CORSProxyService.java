package org.dieschnittstelle.ess.corsproxy;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.dieschnittstelle.ess.corsproxy.CORSRequest;
import org.dieschnittstelle.ess.corsproxy.CORSResponse;

/*
 * Interface fuer eine "Geschaeftslogik"-Komponente, die die Namen der existierenden MyDataItem
 * Instanzen auf Basis elementarer CRUD Operationen ermittelt
 */

/*
 * Die @Path Annotation gibt die Wurzel-URL dieses Services bzw. dieser JAX-RS Ressource unterhalb der
 * URL der JAX-RS Application an (siehe dafuer die WebAPIRoot Klasse).
 */
@Path("/cors")
/*
 * Die @Produces/@Consumes Annotationen geben an, dass beim Argumente und Rueckgabewerte (falls vorhanden)
 * im JSON Format uebermittelt werden
 */
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public interface CORSProxyService {

    /*
     * Die @GET Annotation gibt an, dass fuer den Zugriff auf die annotierte Operation
     * ein HTTP GET Request verwendet werden soll
     */
    @PUT
    public CORSResponse runCORSRequest(CORSRequest request);

}