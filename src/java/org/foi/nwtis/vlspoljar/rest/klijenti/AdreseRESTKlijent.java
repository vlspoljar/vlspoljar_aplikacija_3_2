/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.foi.nwtis.vlspoljar.rest.klijenti;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:AdreseRESTResource<br>
 * USAGE:
 * <pre>
 *        AdreseRESTKlijent client = new AdreseRESTKlijent();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Branko
 */
public class AdreseRESTKlijent {
    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/vlspoljar_aplikacija_2_2/webresources";

    public AdreseRESTKlijent(String korisnik, String portfelj) {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        String resourcePath = java.text.MessageFormat.format("korisniciREST/{0}/{1}", new Object[]{korisnik, portfelj});
        webTarget = client.target(BASE_URI).path(resourcePath);
    }

    public void setResourcePath(String korisnik, String portfelj) {
        String resourcePath = java.text.MessageFormat.format("korisniciREST/{0}/{1}", new Object[]{korisnik, portfelj});
        webTarget = client.target(BASE_URI).path(resourcePath);
    }

    public void putJson(Object requestEntity) throws ClientErrorException {
        webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public void delete() throws ClientErrorException {
        webTarget.request().delete();
    }

    public String getJson() throws ClientErrorException {
        WebTarget resource = webTarget;
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
    }

    public void close() {
        client.close();
    }
    
}
