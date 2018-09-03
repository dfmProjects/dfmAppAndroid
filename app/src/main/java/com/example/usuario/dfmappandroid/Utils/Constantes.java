package com.example.usuario.dfmappandroid.Utils;

public class Constantes {

    static final String WEBSERVICE = "http://web3.disfrimur.com:8060/wsdl/REST/service.php";
    static final String PATH = "http://web3.disfrimur.com:8060/";
    static final String ID_NOTICIA = "id";
    static final String ALL_NOTICIAS = "http://web3.disfrimur.com:8060/noticia/list/";

    public static String getPATH() { return PATH;}

    public static String getWEBSERVICE() {
        return WEBSERVICE;
    }

    public static String getIdNoticia() {
        return ID_NOTICIA;
    }

    public static String getAllNoticias() {
        return ALL_NOTICIAS;
    }
}