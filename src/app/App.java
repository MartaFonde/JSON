package app;
import javax.json.JsonObject;

public class App {
    public static void main(String[] args) {
        FuncJson f = new FuncJson();

        // System.out.println(f.idCiudad("vigo"));
        // System.out.println(f.nombreCiudad(42.26, -8.78));

        // JsonObject vigo = f.ciudad("vigo");
        // System.out.println("Lat "+f.coord(vigo).getLat() + "lon: "+f.coord(vigo).getLon());

        // DatosMeteoCiudad d = f.meteoCiudad(vigo);
        // f.mostrarMeteoCiudad(d);

        // Coordenada c = f.coord(vigo);
        // DatosMeteoCiudad[] colec = f.colecMeteoCiudades(c.getLat(), c.getLon(), 6);

        // JsonObject obj = f.trivia();
        // Question[] q = f.questionsTrivia(obj);
        // f.mostrarTrivia(q);

        JsonObject obj = f.eventos("lalin", 100, 25);
        Evento[] eventos = f.eventosProx(obj);
        f.mostrarEventosInfo(eventos);
        f.mostrarEventosLugar(eventos);
        f.mostrarMeteoCiudadEvento(eventos);
  
    }
}