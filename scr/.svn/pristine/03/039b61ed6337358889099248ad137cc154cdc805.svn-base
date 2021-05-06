package pe.com.sedapal.scr.web.controllers;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.core.beans.AnalistaBean;
import pe.com.sedapal.scr.core.services.IAnalistaService;


@Controller
public class InicioSptarController {
	/**
	 * Método que permite redirigir a la pantalla de inicio del sistema.
	 *
	 * @param model permite la abstracción completa de la tecnología de vista
	 * @param name Parametro nombre
	 * @param request se utiliza para almacenar información entre diferentes peticiones HTTP.
	 * @param principal the principal
	 * @return Objeto tipo String con el nombre de la vista de inicio
	 */
	
	private static final Logger log = LoggerFactory.getLogger(InicioSptarController.class);
		
	@Autowired
	private IAnalistaService analistaService;
	
	@RequestMapping("/inicioSptar")
	public String inicioSptar(Model model, @RequestParam(value="name", required=false, defaultValue="World") String name,  HttpServletRequest request, Principal principal,HttpServletResponse response) throws IOException {	    
		if (principal!=null && ((Authentication)principal).isAuthenticated()) {
			String nombreUsuario = principal.getName();
			try {
				List<AnalistaBean> ltaAnalista = analistaService.obtenerAnalistabyUsuario(nombreUsuario);
				if(ltaAnalista != null && ltaAnalista.size() > 0) {
					request.getSession().setAttribute("userAnalistaBean", (AnalistaBean)ltaAnalista.get(0));
					request.getSession().setAttribute("tipoAnalista", ((AnalistaBean)ltaAnalista.get(0)).getDescripcionFlagTipo());
					return "inicioSptar";
				}else {
					log.debug("### El Usuario logeado no esta asociado a un analista "+nombreUsuario);
					request.getSession().invalidate();
					response.sendRedirect(request.getContextPath() + "/logout");
					return "login";
				}
			} catch (GmdException e) {
				log.debug("### El Usuario logeado no esta asociado a un analista "+nombreUsuario);
				request.getSession().invalidate();
				response.sendRedirect(request.getContextPath() + "/logout");
				return "login";
			}
	    }else{
	    	model.addAttribute("name", name);
        	return "login";
        }			
	}	 
}
