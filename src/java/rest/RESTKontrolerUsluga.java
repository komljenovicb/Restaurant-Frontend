/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

public class RESTKontrolerUsluga {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/FpisWS/";

    public RESTKontrolerUsluga() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("usluga");
    }

    public Response izmenUslugu_XML(Object requestEntity, String id) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.
                format("{0}", new Object[]{id})).
                request(javax.ws.rs.core.MediaType.APPLICATION_XML).
                put(javax.ws.rs.client.Entity.entity(
                        requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML),
                        Response.class);
    }

    public Response izmeniUslugu_JSON(Object requestEntity, int id) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.
                format("{0}", new Object[]{id})).
                request(javax.ws.rs.core.MediaType.APPLICATION_JSON).
                put(javax.ws.rs.client.Entity.
                        entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON),
                        Response.class);
    }

    public <T> T vratiUsluge_XML(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public <T> T vratiUsluge_JSON(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public Response obrisiUslugu(int uslugaID) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("{0}", new Object[]{uslugaID})).
                request().delete(Response.class);
    }

    public Response sacuvajUslugu_XML(Object requestEntity) throws ClientErrorException {
        return webTarget.path("").request(javax.ws.rs.core.MediaType.APPLICATION_XML).
                post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML), Response.class);
    }

    public Response sacuvajUslugu_JSON(Object requestEntity) throws ClientErrorException {
        return webTarget.path("").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).
                post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), Response.class);
    }

    public <T> T vratiUslugu_JSON(Class<T> responseType, String nazivUsluge) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("{0}", new Object[]{nazivUsluge}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void close() {
        client.close();
    }

}
