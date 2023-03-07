/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author abder
 */
public class WeatherData {

    private int lon, lat, pressure, humidity;
    private String description,name;
    private float temp;

    public void setWeather(String s) {

        int start = s.indexOf("\"humidity\":") + ("\"humidity\":").length();
        int finish = start + s.substring(start).indexOf(",");
                if(finish>start + s.substring(start).indexOf("}"))
            finish=start + s.substring(start).indexOf("}");
        humidity = Integer.valueOf(s.substring(start, finish));

        start = s.indexOf("\"lat\":") + ("\"lat\":").length();
        finish = start + s.substring(start).indexOf(",");
        if(finish>start + s.substring(start).indexOf("}"))
            finish=start + s.substring(start).indexOf("}");
        lat = Integer.valueOf(s.substring(start, finish));

        start = s.indexOf("\"lon\":") + ("\"lon\":").length();
        finish = start + s.substring(start).indexOf(",");
                if(finish>start + s.substring(start).indexOf("}"))
            finish=start + s.substring(start).indexOf("}");
        lon = Integer.valueOf(s.substring(start, finish));

        start = s.indexOf("\"pressure\":") + ("\"pressure\":").length();
        finish = start + s.substring(start).indexOf(",");
                if(finish>start + s.substring(start).indexOf("}"))
            finish=start + s.substring(start).indexOf("}");
        pressure = Integer.valueOf(s.substring(start, finish));

        start = s.indexOf("\"name\":") + ("\"name\":").length();
        finish = start + s.substring(start).indexOf(",");
                if(finish>start + s.substring(start).indexOf("}"))
            finish=start + s.substring(start).indexOf("}");
        name= (s.substring(start, finish));

        start = s.indexOf("\"temp\":") + ("\"temp\":").length();
        finish = start + s.substring(start).indexOf(",");
                if(finish>start + s.substring(start).indexOf("}"))
            finish=start + s.substring(start).indexOf("}");
        temp = Float.valueOf(s.substring(start, finish));
        
        start = s.indexOf("\"description\":") + ("\"description\":").length();
        finish = start + s.substring(start).indexOf(",");
                if(finish>start + s.substring(start).indexOf("}"))
            finish=start + s.substring(start).indexOf("}");
        description = (s.substring(start, finish));
        

    }

    public int getLon() {
        return lon;
    }

    public void setLon(int lon) {
        this.lon = lon;
    }

    public int getLat() {
        return lat;
    }

    public void setLat(int lat) {
        this.lat = lat;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getTemp() {
        return temp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "WeatherData{" + "lon=" + lon + ", lat=" + lat + ", pressure=" + pressure + ", humidity=" + humidity + ", description=" + description + ", temp=" + temp + ", name=" +name+ '}';
    }

}
