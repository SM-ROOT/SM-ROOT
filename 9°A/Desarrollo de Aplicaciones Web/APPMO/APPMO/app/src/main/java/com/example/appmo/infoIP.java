package com.example.appmo;

public class infoIP {

    /**
     *Evento que contiene la información de la conexion
     * **/

    private static Object infoIP = "192.168.0.103";

    public static String URL_WEB_SERVICES_USER = "http://" + infoIP + "/spinner/web-services/";
    public static String URL_WEB_SERVICES_CLIENT = "http://" + infoIP + "/appmo/spinner_client/";
    public static String URL_WEB_SERVICES_INGREDIENT = "http://" + infoIP + "/appmo/spinner_ingredient/";
    public static String URL_WEB_SERVICES_BREAD = "http://" + infoIP + "/appmo/spinner_bread/";

    /**
     *Nombre del evento para el objeto de conexión
     * **/
    public String getIp() {
        return (String) infoIP;
    }
}