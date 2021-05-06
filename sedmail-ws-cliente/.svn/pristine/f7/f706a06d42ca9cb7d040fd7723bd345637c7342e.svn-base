package pe.com.sedmail.cliente.util;


import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Date;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import pe.com.gmd.util.exception.GmdException;

public class JsonUtil {

    public static String convertirObjetoACadenaJson(Object objeto){
        Gson objetoGson = new GsonBuilder().registerTypeAdapter(Date.class, new DateSerializer("dd/MM/yyyy")).create();
        return objetoGson.toJson(objeto);
    }
    
    public static String obtenerJsonField(String cadena, String fieldName){
    	GsonBuilder objetoGsonBuilder = new GsonBuilder();
    	Gson objetoGson = objetoGsonBuilder.create();
    	JsonObject jsonObject = objetoGson.fromJson(cadena, JsonObject.class);
    	return jsonObject.get(fieldName).getAsString();
    }
    
    public static <T> T convertirCadenaJsonAObjeto(String cadena, Class<T> clase){        
        GsonBuilder objetoGsonBuilder = new GsonBuilder();
        objetoGsonBuilder.registerTypeAdapter(Date.class, new DateSerializer("dd/MM/yyyy"));
        Gson objetoGson = objetoGsonBuilder.create();
        objetoGson.fromJson(cadena, clase);
        return objetoGson.fromJson(cadena, clase);
    }
    
    //Se utiliza jackson debido a que con gson se tuvo problemas con los numericos
    public static <T> T convertirCadenaJsonPostAObjeto(String cadena, Class<T> clase) throws GmdException{        

        try {
			return new ObjectMapper().readValue(cadena, clase);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }
    
    public static String convertirObjetoACadenaJson(Object objeto, String formatoFecha){
        Gson objetoGson = new GsonBuilder().registerTypeAdapter(Date.class, new DateSerializer(formatoFecha)).create();
        return objetoGson.toJson(objeto);
    }
    
    public static <T> T convertirCadenaJsonAObjetoRequest(String cadena, Class<T> clase){        
    	Gson objetoGson=null;
        GsonBuilder objetoGsonBuilder = new GsonBuilder();        
        objetoGsonBuilder.registerTypeAdapter(Date.class, new DateSerializer("dd/MM/yyyy"));        
        objetoGson = objetoGsonBuilder.create();
        objetoGson.fromJson(cadena, clase);
        return objetoGson.fromJson(cadena, clase);
    }
    
    public static JsonArray convertirCadenaJsonAArrayJson(String cadena){
    	JsonArray jsonArray = new JsonParser().parse(cadena).getAsJsonArray();
    	return jsonArray;
    }
    
    /*
     * Permite convertir un json array a un type especifico
     */
    public static <T> T convertirCadenaJsonALista(String cadena, Type type){
		Gson objetoGson = null;
        GsonBuilder objetoGsonBuilder = new GsonBuilder();
        objetoGsonBuilder.registerTypeAdapter(Date.class, new DateSerializer("dd/MM/yyyy HH:mm:ss"));      
        objetoGson = objetoGsonBuilder.create();
        return objetoGson.fromJson(cadena, type);
    }
}
