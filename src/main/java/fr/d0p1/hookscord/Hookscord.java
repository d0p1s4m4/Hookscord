/*
 *  Copyright (c) d0p1 <d0p1@yahoo.com> - 2016
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <d0p1@yahoo.com> wrote this file.  As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return.               d0p1
 * ----------------------------------------------------------------------------
 */
package fr.d0p1.hookscord;

import fr.d0p1.hookscord.utils.Message;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author d0p1 <d0p1@yahoo.com>
 */
public class Hookscord {
    
    private enum EHttpMethod {
        GET,
        POST
    };
    
    private final static Logger LOGGER = Logger.getLogger(Hookscord.class.getName());
    private final static String USER_AGENT = "Mozilla/5.0 (X11; Linux x86_64; rv:49.0) Gecko/20100101 Firefox/49.0";
    
    private URL _url = null;
    
    /**
     * 
     * @param url discord webhook url
     * @throws MalformedURLException 
     */
    public Hookscord(String url) throws MalformedURLException {
        this._url = new URL(url + "/slack");
    }
    
    /**
     * Open connection to webhook endpoint
     * @param method (eg: EHttpMethod.POST)
     * @return 
     */
    private HttpURLConnection _doConnection(EHttpMethod method) {
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) this._url.openConnection();
            
            connection.setRequestMethod(method.toString());
            connection.setRequestProperty("User-Agent", USER_AGENT);
            connection.setRequestProperty("Content-type", "application/json; charset=windows-1252");
            connection.setDoOutput(true);
            connection.setDoInput(true);
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
            connection = null;
        }
        return (connection);
    }
    
    /**
     * 
     * @param payload String to send to webserver
     * @return true if success false otherwise
     */
    private boolean _doPost(String payload) {
        HttpURLConnection connection = this._doConnection(EHttpMethod.POST);
        if (connection == null) {
            return (false);
        }
        connection.setRequestProperty("Content-length", String.valueOf(payload.length()));
        DataOutputStream outputStream;
        try {
                outputStream = new DataOutputStream(connection.getOutputStream());
                outputStream.writeBytes(payload);
                outputStream.flush();
                outputStream.close();
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
            return (false);
        }
        try {
            if (connection.getResponseCode() != 200) {
                return (false);
            }
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
            return (false);
        }
        return (true);
    }
    
    /**
     * Send a message to a channel
     * @param msg an instance of Message
     * @throws IOException 
     */
    public void sendMessage(Message msg) throws IOException {
        if (!this._doPost(msg.toJson().toString())) {
            throw new IOException("Can't perform task");
        }
    }
    
}
