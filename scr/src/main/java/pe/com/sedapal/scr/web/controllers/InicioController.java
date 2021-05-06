/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.web.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pe.com.sedapal.scr.core.beans.PlanOperativo;
import pe.com.sedapal.scr.core.beans.SelectItemBean;
import pe.com.sedapal.scr.core.common.Constants;
import pe.com.sedapal.scr.core.common.ConstantsLaboratorio;
import pe.com.sedapal.scr.core.services.ICatalogoService;
import pe.com.sedapal.scr.web.common.Util;



// TODO: Auto-generated Javadoc
/**
 * The Class InicioController.
 */
@Controller
public class InicioController {
	
	/** The catalogo service. */
	@Autowired
	ICatalogoService catalogoService;

	/**
	 * Método que permite redirigir a la pantalla de inicio del sistema.
	 *
	 * @param model permite la abstracción completa de la tecnología de vista
	 * @param name Parametro nombre
	 * @param request se utiliza para almacenar información entre diferentes peticiones HTTP.
	 * @param principal the principal
	 * @return Objeto tipo String con el nombre de la vista de inicio
	 */
	@RequestMapping("/inicio")
	public String inicio(Model model, @RequestParam(value="name", required=false, defaultValue="World") String name,  HttpServletRequest request, Principal principal) {	    
		if (principal!=null && ((Authentication)principal).isAuthenticated()) {
			String codigoArea = obtenerAreaPorUsuario(Util.getUserLoged());			
			  if(codigoArea.equals("B")){
				  request.getSession().setAttribute(Constants.USR_CODARE, 1);
			  }else if(codigoArea.equals("F")){
				  request.getSession().setAttribute(Constants.USR_CODARE, 2);
			  }else{
				  request.getSession().setAttribute(Constants.USR_CODARE, -1);
			  }
	        return "inicio";
			 // return "inicioSptar";
	    }else{
	    	model.addAttribute("name", name);
        	return "login";
        }		
	}
	
	/**
	 * Método que permite redirigir a la pantalla de inicio del sistema.
	 *
	 * @param model permite la abstracción completa de la tecnología de vista
	 * @param name Parametro nombre
	 * @param request se utiliza para almacenar información entre diferentes peticiones HTTP.
	 * @return Objeto tipo String con el nombre de la vista de inicio
	 */
	@RequestMapping("/")
	public String home1(Model model, @RequestParam(value="name", required=false, defaultValue="World") String name,  HttpServletRequest request) {	   
		return "redirect:/inicio";
	}
	
	/**
	 * Obtener area por usuario.
	 *
	 * @param idUser the id user
	 * @return the string
	 */
	private String obtenerAreaPorUsuario(String idUser){
		List<SelectItemBean> usuariosPerfil = catalogoService.obtenerCatalogo(25);
		if(usuariosPerfil==null){
			return "";
		}
		for (SelectItemBean selectItemBean : usuariosPerfil) {
			//LOG.info(">>>USER  " + selectItemBean.getLabel());
			  if(idUser.equals(selectItemBean.getLabel())){
				  return selectItemBean.getDefecto();
			  }
		}
		return "";
	}
	 
}
