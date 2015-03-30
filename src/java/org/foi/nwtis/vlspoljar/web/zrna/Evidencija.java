/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.foi.nwtis.vlspoljar.web.zrna;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.foi.nwtis.vlspoljar.podaci.Adresa;
import org.foi.nwtis.vlspoljar.podaci.Korisnik;
import org.foi.nwtis.vlspoljar.podaci.Portfelj;
import org.foi.nwtis.vlspoljar.rest.klijenti.AdreseRESTKlijent;
import org.foi.nwtis.vlspoljar.rest.klijenti.KorisniciRESTKlijent;
import org.foi.nwtis.vlspoljar.rest.klijenti.PortfeljiRESTKlijent;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;

/**
 *
 * @author Branko
 */
@ManagedBean
@SessionScoped
public class Evidencija {

    private List<Korisnik> listaKorisnika;
    private List<Portfelj> listaPortfelja;
    private List<Adresa> listaAdresa;
    
    public Evidencija() {
    }

    public List<Korisnik> getListaKorisnika() throws JSONException {
        listaKorisnika = new ArrayList<>();
        KorisniciRESTKlijent client = new KorisniciRESTKlijent();
        JSONArray response = new JSONObject(client.getJson()).getJSONArray("korisnici");
        
        for(int i = 0; i < response.length(); i++) {
            JSONObject userJSON = response.getJSONObject(i);
            Korisnik korisnik = new Korisnik(userJSON.getInt("id"), userJSON.getString("kor_ime"), userJSON.getString("ime"), userJSON.getString("prezime"), userJSON.getString("email"), userJSON.getInt("vrsta"));
            listaKorisnika.add(korisnik);
        }
        client.close();
        return listaKorisnika;
    }

    public void setListaKorisnika(List<Korisnik> listaKorisnika) {
        this.listaKorisnika = listaKorisnika;
    }

    public List<Portfelj> getListaPortfelja(String korisnik) throws JSONException {
        listaPortfelja = new ArrayList<>();
        PortfeljiRESTKlijent client = new PortfeljiRESTKlijent(korisnik);
        JSONArray response = new JSONObject(client.getJson()).getJSONArray(korisnik);
        
        for(int i = 0; i < response.length(); i++) {
            JSONObject userJSON = response.getJSONObject(i);
            Portfelj portfelj = new Portfelj(userJSON.getInt("id"), userJSON.getString("naziv"));
            listaPortfelja.add(portfelj);
        }
        client.close();
        return listaPortfelja;
    }

    public void setListaPortfelja(List<Portfelj> listaPortfelja) {
        this.listaPortfelja = listaPortfelja;
    }

    public List<Adresa> getListaAdresa(String korisnik, String portfelj) throws JSONException {
        listaAdresa = new ArrayList<>();
        AdreseRESTKlijent client = new AdreseRESTKlijent(korisnik, portfelj);
        JSONArray response = new JSONObject(client.getJson()).getJSONArray(portfelj);
        
        for(int i = 0; i < response.length(); i++) {
            JSONObject userJSON = response.getJSONObject(i);
            Adresa adresa = new Adresa(userJSON.getInt("id"), userJSON.getString("adresa"));
            listaAdresa.add(adresa);
        }
        client.close();
        return listaAdresa;
    }

    public void setListaAdresa(List<Adresa> listaAdresa) {
        this.listaAdresa = listaAdresa;
    }
    
}
