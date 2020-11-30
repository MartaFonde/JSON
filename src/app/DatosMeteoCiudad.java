package app;

public class DatosMeteoCiudad {
    private String name;
    private String date;
    private double temp;
    private double humidity;
    private double clouds;
    private double wind;
    private String weather;

    public DatosMeteoCiudad() {

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getClouds() {
        return clouds;
    }

    public void setClouds(double clouds) {
        this.clouds = clouds;
    }

    public double getWind() {
        return wind;
    }

    public void setWind(double wind) {
        this.wind = wind;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void mostrar(){
        System.out.println("Name: " + getName());
        System.out.println("Date: " + getDate());
        System.out.println("Temp: " + getTemp());
        System.out.println("Humid: " + getHumidity());
        System.out.println("Clouds: " + getClouds());
        System.out.println("Wind: " + getWind());
        System.out.println("Weather: " + getWeather());
    }

}
