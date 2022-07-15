package org.dieschnittstelle.ess;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.apache.logging.log4j.Logger;
import org.dieschnittstelle.ess.corsproxy.CORSFilter;
import org.dieschnittstelle.ess.corsproxy.CORSProxyServiceImpl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
 * JAX-RS Application. "Wurzel" der WebAPI, die durch die Webanwendung, in der diese Klasse
 * enthalten ist, bereit gestellt wird. Die URLs fuer die Zugriffe auf die einzelnen Ressourcen / "Services"
 * dieser Application ergeben sich aus der URL der Webanwendung, dem hier angegeben @ApplicationPath,
 * der @Path-URL der Ressourcenklasse bzw. des Interfaces sowie evtl. einer zusaetzlichen @Path Annotation
 * auf der zuzugreifenden Methode der Ressource (siehe JRS:53:"WebAPI mit JAX-RS und Resteasy/CXF").
 */
@ApplicationPath("/api")
public class WebAPIRoot extends Application {

    protected static Logger logger = org.apache.logging.log4j.LogManager.getLogger(WebAPIRoot.class);

    public WebAPIRoot() {
        logger.info("<constructor>");
    }

    /* da hier keine OpenAPI Beschreibung bereit gestellt wird, ist keine explizite Auflistung der REST Ressourcen erforderlich) */

    @Override
    public Set<Class<?>> getClasses() {
        return new HashSet<>(Arrays.asList(CORSProxyServiceImpl.class, CORSFilter.class));
    }
}