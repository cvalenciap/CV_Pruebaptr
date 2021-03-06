package pe.com.sedapal.scr.web.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

import pe.com.gmd.notificacion.correo.bean.AdjuntoCorreoBean;
import pe.com.gmd.notificacion.correo.bean.CabeceraCorreoBean;
import pe.com.gmd.notificacion.correo.bean.CorreoBean;
import pe.com.gmd.notificacion.correo.impl.NotificacionCorreoImpl;
import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.core.beans.DetalleGeneralBean;
import pe.com.sedapal.scr.correo.job.ETLReportesResumenJob;
import pe.com.sedapal.scr.web.controllers.GeneralController;

public class NotificacionUtil {
	
	final static Logger logger = Logger.getLogger(NotificacionUtil.class);
    
    GeneralController generalController;
    
    private static NotificacionUtil instanciaNotificacionUtil = null;
    
    public static NotificacionUtil getInstancia(GeneralController generalController){
        synchronized (NotificacionUtil.class) {
            if(instanciaNotificacionUtil==null)
                instanciaNotificacionUtil = new NotificacionUtil(generalController);
        }
        return instanciaNotificacionUtil;
    }
    
    public NotificacionUtil(GeneralController generalController) {
    	this.generalController = generalController;
	}
    
    @SuppressWarnings("unchecked")
    public void enviarCorreoElectronico(Map<String, Object> parametros) throws GmdException{
    	String puerto, host, auth, enable;
        try {
            Properties configuracionServidor = new Properties();                       
            DetalleGeneralBean datosCorreo = generalController.obtenerParametrosCorreo();
            puerto = datosCorreo.getVlDato01().substring(0, datosCorreo.getVlDato01().indexOf(":"));
            host = datosCorreo.getVlDato01().substring(datosCorreo.getVlDato01().indexOf(":")+1);
            host = host.replace("\n","");
            auth = datosCorreo.getVlDato05().substring(0, datosCorreo.getVlDato05().indexOf(":"));
            enable = datosCorreo.getVlDato05().substring(datosCorreo.getVlDato05().indexOf(":")+1);
            configuracionServidor.put("mail.smtp.auth", auth);
            configuracionServidor.put("mail.smtp.starttls.enable", enable);
            configuracionServidor.put("mail.smtp.host", host);
            configuracionServidor.put("mail.smtp.port", puerto);
            configuracionServidor.put("mail.smtp.ssl.trust", host);
            CabeceraCorreoBean cabeceraCorreoBean = new CabeceraCorreoBean();            
            if(parametros.get("FROM") != null){
                String remitente = (String)parametros.get("FROM");
                cabeceraCorreoBean.setNombreRemiente(remitente);
            }else{
            	cabeceraCorreoBean.setNombreRemiente(datosCorreo.getVlDato03());
            }
//            cabeceraCorreoBean.setCorreoRemitente(datosCorreo.getVlDato02().replace("\n",""));
            cabeceraCorreoBean.setCorreoRemitente(datosCorreo.getVlDato03().replace("\n",""));
            cabeceraCorreoBean.setClaveRemitente(datosCorreo.getVlDato04().replace("\n",""));            
            CorreoBean correoBean = new CorreoBean();            
            List<String> listaDestinatarios = (List<String>)parametros.get("TO");
            cabeceraCorreoBean.setCorreoDestino(listaDestinatarios);            
            List<String> listaCopia = (List<String>)parametros.get("CC");
            if(listaCopia!=null)
                cabeceraCorreoBean.setCorreoCopia(listaCopia);            
            String asunto = (String)parametros.get("SUBJECT");
            correoBean.setAsunto(asunto);            
            String cuerpoMensaje = (String)parametros.get("BODY");
            correoBean.setMensaje(cuerpoMensaje);
            correoBean.setCabeceraCorreoBean(cabeceraCorreoBean);            
            //Agregando archivos adjuntos, puede ser nulo
            if(parametros.get("RUTAARCHIVOADJUNTO") != null){
            	List<AdjuntoCorreoBean> listaAdjunto = new ArrayList<AdjuntoCorreoBean>();
                String rutaArchivo = (String)parametros.get("RUTAARCHIVOADJUNTO");
                
                AdjuntoCorreoBean adjuntoCorreo = new AdjuntoCorreoBean(rutaArchivo);
                adjuntoCorreo.setNombreArchivo((String)parametros.get("NOMBREARCHVADJ"));
                listaAdjunto.add(adjuntoCorreo);
                correoBean.setArchivosAdjuntos(listaAdjunto);
            }

            NotificacionCorreoImpl.obtenerInstancia().enviarCorreo(configuracionServidor, correoBean);
            logger.info("envio de correo exitoso");
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
    }
}
