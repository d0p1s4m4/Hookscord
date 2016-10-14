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
public class Attachment {
    
    private String _authorName;
    private String _authorIcon;
    private String _color;
    private ArrayList<Field> _fields;
    
    public Attachment() {
        this(null, null, null);
    }
    
    public Attachment(String authorName) {
        this(authorName, null, null);
    }
    
    public Attachment(String authorName, String authorIcon) {
        this(authorName, authorIcon, null);
    }
    
    public Attachment(String authorName, String authorIcon, String color) {
        this._authorName = authorName;
        this._authorIcon = authorIcon;
        this._color = color;
        this._fields = new ArrayList<Field>();
    }
    
    public void setAuthorName(String authorName) {
        this._authorName = authorName;
    }
    
    public void setAuthorIcon(String authorIcon) {
        this._authorIcon = authorIcon;
    }
    
    public void setColor(String color) {
        this._color = color;
    }
    
    public void pushField(Field field) {
        this._fields.add(field);
    }
    
    public JSONObject toJson() {
        JSONObject result = new JSONObject();
        result.put("author_icon", this._authorIcon);
        result.put("author_name", this._authorName);
        result.put("color", this._color);
        if (!this._fields.isEmpty()) {
            JSONArray array = new JSONArray();
            for (Field field : this._fields) {
                array.add(field.toJson());
            }
            result.put("fields", array);
        }
        return (result);
    }
    
}
