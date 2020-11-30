package app;

public class Evento {
    private String city;
    private String title;
    private String description;
    private String startTime;
    private String venueName;
    private String venueAddress;
    private String country;
    
    public Evento() {
        
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public String getVenueAddress() {
        return venueAddress;
    }

    public void setVenueAddress(String venueAddress) {
        this.venueAddress = venueAddress;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void infoEvento(){
        System.out.println("Title: "+getTitle());
        System.out.println("Description: "+getDescription());
        System.out.println("Start time: "+getStartTime());
    }

    public void infoLugar(){
        System.out.println("Title: "+getTitle());
        System.out.println("City: "+getCity());
        System.out.println("Venue name: "+getVenueName());
        System.out.println("Venue address: "+getVenueAddress());
        System.out.println("Country: "+getCountry());
    }
    
    
}