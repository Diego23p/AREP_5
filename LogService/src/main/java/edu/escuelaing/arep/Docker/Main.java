package edu.escuelaing.arep.Docker;

import java.io.BufferedReader;
import org.json.JSONObject;
import spark.Request;
import spark.Response;
import static spark.Spark.*;

/**
 *
 * @author Diego23p
 */
public class Main {

    public static void main(String args[]) {

        port(getPort());
        get("/Datos", (req, res) -> inputDataPage(req, res) );
        get("/Resultados", (req, res) -> resultsPage(req, res));

    }

    /**
     * Recibe los datos solicitados en el servicio REST por el endpoit /Datos
     * @param req requerimientos de la solicitud
     * @param res respuesta al llamado REST
     * @return Formulario de entrada de la cadena
     */
    private static String inputDataPage(Request req, Response res) {
        String pageContent
                = "<!DOCTYPE html>"
                + "<html>"
                + "<body>"
                + "<h2>Registro de cadenas con Docker y AWS</h2>"
                + "<form action=\"/Resultados\">"
                + "  Ingrese la cadena a insertar <br>"
                + "  <input type=\"text\" name=\"cadena\" >"
                + "  <br><br>"
                + "  <input type=\"submit\" value=\"Submit\">"
                + "</form>"
                + "</body>"
                + "</html>";
        return pageContent;
    }
    
    /**
     * Realiza la solicitud de conexion a la base de datos 
     * @param req requerimientos de la solicitud
     * @param res respuesta al llamado REST
     * @return Objeto JSON con la consulta a DB
     */
    private static JSONObject resultsPage(Request req, Response res) {
        
        String cadena = req.queryParams("cadena");
        
        Conexion conexion = new Conexion();
        
        conexion.addData(cadena);
        
        res.header("Content-Type","application/json");
        
        return conexion.getAllData();
        
    }

    /**
     * Obtiene el puerto de la variable de entorno
     * @return la variable de entorno o 35001 por defecto
     */
    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 35001;
    }

}
