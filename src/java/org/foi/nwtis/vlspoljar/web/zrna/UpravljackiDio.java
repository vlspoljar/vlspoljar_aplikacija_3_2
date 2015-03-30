/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.vlspoljar.web.zrna;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Branko
 */
@ManagedBean
@RequestScoped
public class UpravljackiDio {

    public String komanda;

    public UpravljackiDio() {
    }

    public String getKomanda() {
        return komanda;
    }

    public void setKomanda(String komanda) {
        this.komanda = komanda;
    }

    public void posaljiKomandu() {
        String odgovor = "";
        try {
            Socket veza = new Socket("127.0.0.1", 8090);
            InputStream is = veza.getInputStream();
            OutputStream os = veza.getOutputStream();
            os.write(komanda.getBytes());
            os.flush();
            veza.shutdownOutput();
            int znak;
            while (true) {
                if ((znak = is.read()) == -1) {
                    break;
                }
                odgovor += (char) znak;
            }
            is.close();
            os.close();
            veza.close();
        } catch (IOException ex) {
            Logger.getLogger(UpravljackiDio.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Odgovor: " + odgovor);
    }

}
