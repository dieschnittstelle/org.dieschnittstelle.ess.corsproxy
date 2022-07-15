package org.dieschnittstelle.ess.corsproxy;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

/*
 * see https://www.baeldung.com/cors-in-jax-rs
 * this set the headers allowing CORS request to the proxy service
 */
@Provider
public class CORSFilter implements ContainerResponseFilter, ContainerRequestFilter {

    public CORSFilter() {
        System.out.println("CORSFilter: instantiating...");
    }

    @Override
    public void filter(ContainerRequestContext requestContext,
                       ContainerResponseContext responseContext) {

        try {
            System.out.println("CORSFilter: got response: status is: " + responseContext.getStatus() + (requestContext == null ? ", no request context provided." : ", where request was: " + requestContext.getMethod() + " " + requestContext.getUriInfo().getRequestUri()));
            // always log if we have an unsuccessful status
            if (responseContext.getStatus() / 100 != 2) {
                System.out.println("CORSFilter: got response: entity is: " + responseContext.getEntity());
            }

            responseContext.getHeaders().add(
                    "Access-Control-Allow-Origin", "*");
            responseContext.getHeaders().add(
                    "Access-Control-Allow-Credentials", "true");
            responseContext.getHeaders().add(
                    "Access-Control-Allow-Headers",
                    "origin, content-type, accept, authorization");
            responseContext.getHeaders().add(
                    "Access-Control-Allow-Methods",
                    "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        }
        catch (Throwable t) {
            System.err.println("CORSFilter: got exception: " + t);
            t.printStackTrace();
        }
    }

    @Override
    public void filter(ContainerRequestContext containerRequestContext) {
        System.out.println("got request: " + containerRequestContext.getMethod() + " " + containerRequestContext.getUriInfo().getRequestUri());
    }
}
