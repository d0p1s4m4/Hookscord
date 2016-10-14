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

import org.json.simple.JSONObject;

/**
 *
 * @author d0p1 <d0p1@yahoo.com>
 */
public class Field {
    
    private String _title;
    private String _value;
    
    public Field() {
        this(null, null);
    }
    
    public Field(String title) {
        this(title, null);
    }
    
    public Field(String title, String value) {
        this._title = title;
        this._value = value;
    }
    
    public void setTitle(String title) {
        this._title = title;
    }
    
    public void setValue(String value) {
        this._value = value;
    }
    
    public JSONObject toJson() {
        JSONObject result = new JSONObject();
        result.put("title", this._title);
        result.put("value", this._value);
        return (result);
    }
    
}
