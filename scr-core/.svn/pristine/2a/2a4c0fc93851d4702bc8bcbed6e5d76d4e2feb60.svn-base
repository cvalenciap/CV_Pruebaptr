package pe.com.sedapal.scr.core.services.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import pe.com.sedapal.scr.core.services.INotificacionService;

@Service
public class NotificacionServiceImpl implements INotificacionService{
    @Override
    @SuppressWarnings("unchecked")
    public Map<String, Object> generarCorreo(Map<String, Object> parametros) {
        StringBuffer cuerpoHTML = new StringBuffer();
        cuerpoHTML.append("<html>");
        cuerpoHTML.append("<head>");
        cuerpoHTML.append("</head>");
        cuerpoHTML.append("<body>");
        cuerpoHTML.append(parametros.get("BODY"));
        cuerpoHTML.append("</body>");
        cuerpoHTML.append("</html>");
        Map<String, Object> datos = new HashMap<String, Object>();
        List<String> listaDestinatarios = new ArrayList<String>();
        if(parametros.get("TO")!=null){
	        for (String destinatario : (ArrayList<String>)parametros.get("TO")) 
	        {
	            listaDestinatarios.add(destinatario);
	        }
        }
        datos.put("TO", listaDestinatarios);
        datos.put("BODY", cuerpoHTML.toString());
        datos.put("SUBJECT", parametros.get("SUBJECT"));
        datos.put("RUTAARCHIVOADJUNTO", parametros.get("RUTAARCHIVOADJUNTO"));
        datos.put("FROM", parametros.get("FROM"));
        datos.put("FROMCORREO", parametros.get("FROMCORREO"));
        datos.put("NOMBREARCHVADJ", parametros.get("NOMBREARCHVADJ"));
        return datos;
    }
}
