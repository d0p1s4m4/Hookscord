/*
 *  Copyright (c) d0p1 <d0p1@yahoo.com> - 2016 
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <d0p1@yahoo.com> wrote this file.  As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return.               d0p1
 * ----------------------------------------------------------------------------
 */
package fr.d0p1.hookscord.utils;

import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


/**
 *
 * @author d0p1 <d0p1@yahoo.com>
 */
public class Message {
    
    private String _username;
    private String _iconUrl;
    private String _text;
    private ArrayList<Attachment> _attachments;
    
    public Message() {
        this(null, null, null);
    }
    
    public Message(String username) {
        this(username, null, null);
    }
    
    public Message(String username, String iconUrl) {
        this(username, iconUrl, null);
    }
    
    public Message(String username, String iconUrl, String text) {
        this._username = username;
        this._iconUrl = iconUrl;
        this._text = text;
        this._attachments = new ArrayList<Attachment>();
    }
    
    public void setText(String text) {
        this._text = text;
    }
    
    public void setUsername(String username) {
        this._username = username;
    }
    
    public void setIconUrl(String iconUrl) {
        this._iconUrl = iconUrl;
    }
    
    public void pushAttachment(Attachment attachment) {
        this._attachments.add(attachment);
    }
    
    public JSONObject toJson() {
        JSONObject result = new JSONObject();
        result.put("username", this._username);
        result.put("icon_url", this._iconUrl);
        result.put("text", this._text);
        if (!this._attachments.isEmpty()) {
            JSONArray array = new JSONArray();
            for (Attachment attachment : this._attachments) {
                array.add(attachment.toJson());
            }
            result.put("attachments", array);
        }
        return (result);
    }
}
