package org.dieschnittstelle.ess.corsproxy.test;

import org.dieschnittstelle.ess.corsproxy.CORSProxyService;
import org.dieschnittstelle.ess.corsproxy.CORSRequest;

import org.dieschnittstelle.ess.corsproxy.CORSResponse;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;

import java.net.URI;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/*
 * see https://github.com/AdamBien/JakartaEE-essentials-archetype/find/master as a basis for this implementation
 */
public class TestCORS {

    private static CORSProxyService corsProxy;

    @BeforeAll
    public static void createAPIProxy() {
        /*
         * Hier wird mit den Mitteln der Rest Client Implementierung aus Eclipse Microprofile
         * fuer die beiden angegebenen Interfaces MyDataItemCRUD und
         * MyDataItemNameService jeweils ein Proxy Objekt generiert (siehe WSV:24-29).
         *
         * ? wie und wo kann dieser Mechanismus im Rahmen von Dependency Injection
         * im Hintergrund genutzt werden? (siehe MSD:39)
         */
        URI uri = URI.create("http://localhost:8090/api");
        RestClientBuilder builder = RestClientBuilder
                .newBuilder()
                .baseUri(uri)
                .register(JacksonJaxbJsonProvider.class);

        corsProxy = builder.build(CORSProxyService.class);
    }

    @Test
    public void run() {
        CORSRequest req = new CORSRequest();
        req.setMethod(CORSRequest.CORSHttpMethod.GET);
        req.setUrl("https://www.visitberlin.de/en/top-museums");

        CORSResponse resp = corsProxy.runCORSRequest(req);

        System.out.println("Received response for request " + req + ":\n" + resp);

    }

}