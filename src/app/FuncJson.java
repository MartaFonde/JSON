package app;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.json.JsonValue.ValueType;
import javax.net.ssl.HttpsURLConnection;

public class FuncJson {
    public FuncJson() {
    }

    public JsonValue leeJSON(String ruta) {
        JsonReader reader = null;
        JsonValue jsonV = null;

        try {
            if (ruta.toLowerCase().startsWith("http://")) {
                URL url = new URL(ruta);
                InputStream is = url.openStream();
                reader = Json.createReader(is);
            } else if (ruta.toLowerCase().startsWith("https://")) {
                URL url = new URL(ruta);
                HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
                InputStream is = conn.getInputStream();
                reader = Json.createReader(is);
            } else {
                reader = Json.createReader(new FileReader(ruta));
            }
            jsonV = reader.read();
            /*
             * JsonStructure jsonSt = reader.read();
             * System.out.println(jsonSt.getValueType()); JsonObject jsonObj =
             * reader.readObject(); System.out.println(jsonObj.getValueType()); JsonArray
             * jsonArr = reader.readArray(); System.out.println(jsonArr.getValueType());
             */
        } catch (IOException e) {
            System.out.println("Erro procesando documento Json " + e.getLocalizedMessage());
        }
        if (reader != null)
            reader.close();
        return jsonV;
    }

    public JsonObject ciudad(String ciudad) {
        return (JsonObject) leeJSON("https://api.openweathermap.org/data/2.5/weather?q=" + ciudad
                + ",es&lang=es&APPID=8f8dccaf02657071004202f05c1fdce0");
    }

    public JsonObject localizacion(double lat, double lon) {
        return (JsonObject) leeJSON("https://api.openweathermap.org/data/2.5/weather?lat=" + lat + "&lon=" + lon
                + "&APPID=8f8dccaf02657071004202f05c1fdce0");
    }

    public JsonObject locProximas(double lat, double lon, int cantLoc) {
        return (JsonObject) leeJSON("https://api.openweathermap.org/data/2.5/find?lat=" + lat + "&lon=" + lon + "&cnt="
                + cantLoc + "&APPID=8f8dccaf02657071004202f05c1fdce0");
    }

    public int idCiudad(String ciudad) {
        return ciudad(ciudad).getInt("id");
    }

    public String nombreCiudad(double lat, double lon) {
        return localizacion(lat, lon).getString("name");
    }

    public Coordenada coord(JsonObject ciudad) { 
        Coordenada coord = new Coordenada(ciudad.getJsonObject("coord").getJsonNumber("lat").doubleValue(),
                                            ciudad.getJsonObject("coord").getJsonNumber("lon").doubleValue());
      
        return coord;
    }

    public String unixTimeToString(long unixTime) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return Instant.ofEpochSecond(unixTime).atZone(ZoneId.of("GMT+1")).format(formatter);
    }

    public DatosMeteoCiudad meteoCiudad(JsonObject ciudad) { 
        DatosMeteoCiudad data = new DatosMeteoCiudad();
        long dt = (long) ciudad.getInt("dt");
        data.setName(ciudad.getString("name"));
        data.setDate(unixTimeToString(dt));
        data.setTemp(ciudad.getJsonObject("main").getJsonNumber("temp").doubleValue());
        data.setHumidity(ciudad.getJsonObject("main").getJsonNumber("humidity").intValue());
        data.setClouds(ciudad.getJsonObject("clouds").getJsonNumber("all").intValue());
        data.setWind(ciudad.getJsonObject("wind").getJsonNumber("speed").doubleValue());
        data.setWeather(ciudad.getJsonArray("weather").getJsonObject(0).getJsonString("description").toString());
        return data;
    }

    public DatosMeteoCiudad[] colecMeteoCiudades(double lat, double lon, int n) {
        JsonObject datosCiudades = locProximas(lat, lon, n);
        JsonArray lista = datosCiudades.getJsonArray("list"); 
        DatosMeteoCiudad[] data = new DatosMeteoCiudad[n];

        for (int i = 0; i < lista.size(); i++) {
            data[i] = meteoCiudad(lista.getJsonObject(i));
            mostrarMeteoCiudad(data[i]);
            System.out.println("\n-------------------\n");
        }
        return data;
    }

    public void mostrarMeteoCiudad(DatosMeteoCiudad data) {
        data.mostrar();
    }

    public JsonObject trivia(){
        return (JsonObject)leeJSON("https://opentdb.com/api.php?amount=20&category=18&difficulty=hard");
    }

    public Question[] questionsTrivia(JsonObject obj){
        JsonArray results = obj.getJsonArray("results");
        Question[] questions = new Question[results.size()];
        Question q;

        for (int i = 0; i < results.size(); i++) {
            q = new Question();
            q.setQuest(results.getJsonObject(i).getString("question"));                         
            q.setCorrectAnswer(results.getJsonObject(i).getString("correct_answer"));                         
            JsonArray incorrAnw = results.getJsonObject(i).getJsonArray("incorrect_answers");
            String[] incorrectAnswers = new String[incorrAnw.size()];
            for (int j = 0; j < incorrAnw.size(); j++) {
               incorrectAnswers[j] = incorrAnw.getString(j);
            }
            q.setIncorrectAnswers(incorrectAnswers);

            questions[i] = q;            
        }
        return questions;
    }

    public void mostrarTrivia(Question[] questions){
        for (Question q : questions) {
            q.mostrar();
            System.out.println();            
        }
    }    

    public JsonObject eventos(String localidad, int km, int eventos){
        return (JsonObject)leeJSON("http://api.eventful.com/json/events/search?l="+localidad+"&units=km&within="+km+"&page_size="+eventos+"&app_key=c2tPtVFTrSk8xnQS");
    }

    public Evento[] eventosProx(JsonObject obj){
        JsonObject eventsObj = obj.getJsonObject("events");
        JsonArray event = eventsObj.getJsonArray("event"); 
        Evento[] eventos = new Evento[event.size()];
        Evento e;
        for (int i = 0; i < event.size(); i++) {
            JsonObject ev = event.getJsonObject(i);
            e = new Evento();
            e.setTitle(ev.getString("title"));
            e.setCity(ev.getString("city_name"));            
            e.setDescription(dataAvailable(ev, "description"));
            e.setStartTime(dataAvailable(ev, "start_time"));   
            e.setVenueName(dataAvailable(ev, "venue_name"));      
            e.setVenueAddress(dataAvailable(ev, "venue_address")); 
            e.setCountry(ev.getString("country_name"));
            eventos[i] = e;
        }
        return eventos;
    }

    public void mostrarEventosInfo(Evento[] eventos){
        for (Evento evento : eventos) {
            evento.infoEvento();
            System.out.println("\n-------------------\n");
        }
    }

    public void mostrarEventosLugar(Evento[] eventos){
        for (Evento evento : eventos) {
            evento.infoLugar();
            System.out.println("\n-------------------\n");
        }
    }

    public void mostrarMeteoCiudadEvento(Evento[] eventos)
    {
        ArrayList<String> nombreCiudades = new ArrayList<String>();
        for (int i = 0; i < eventos.length; i++) {
            try{            
                if(!nombreCiudades.contains(eventos[i].getCity())){
                    nombreCiudades.add(eventos[i].getCity());
                    JsonObject tiempoCiudad = ciudad(eventos[i].getCity());
                    DatosMeteoCiudad datosCiudad = meteoCiudad(tiempoCiudad);
                    mostrarMeteoCiudad(datosCiudad);
                    System.out.println("\n-------------------\n");
                }                        
                
            }catch(NullPointerException e){
                System.out.println("City not found "+e.getMessage()+"\n");
            }
        }        
    }

    public String dataAvailable(JsonObject event, String key){
        if(event.get(key).getValueType() != JsonValue.ValueType.NULL){
            return event.getString(key);
        }else{
            return "No available";
        }
    }
}