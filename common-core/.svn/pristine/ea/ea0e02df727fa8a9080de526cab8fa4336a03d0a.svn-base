package pe.com.sedapal.common.web.controllers;

import java.security.Principal;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UrlPathHelper;

import pe.com.sedapal.common.core.utils.ConstantsCommon;
import pe.com.sedapal.seguridad.core.bean.Retorno;
import pe.com.sedapal.seguridad.ws.SeguridadClienteWs;


@Controller
public class LoginController {
	
	@Autowired
	Environment env;

	@Autowired
	MessageSource messageSource;
	
	@Autowired
	private SeguridadClienteWs stub;
	
	 @RequestMapping("/login")
	    public String login(Model model, @RequestParam(value="name", required=false, defaultValue="World") String name, Principal principal) {
		 	if (principal!=null && ((Authentication)principal).isAuthenticated()) {
		        return "forward:/inicio";
		    }else{
		    	model.addAttribute("name", name);
	        	return "login";
	        }
	    }
	 

	 
	 
	 @RequestMapping("/olvido")
	    public String olvido(
	    		Model model, @RequestParam(value="name", required=false, defaultValue="World") String name,  HttpServletRequest request) {
	        model.addAttribute("name", name);
	        return ConstantsCommon.OLVIDO;
	    }
	 
	 	
	 
	 @RequestMapping(value = "/olvido", method = RequestMethod.POST)
	 public String olvidarClave(Model model, @RequestParam String codUsuario)  {			
								
			try {
				Retorno retorno = stub.olvidarClaveWs(codUsuario);
				
				if (ConstantsCommon.SUCCESS.equals(retorno.getCodigo())) {
					String mensaje = retorno.getMensaje();					
					model.addAttribute("forget", mensaje);
					return "login";								
				} 
				else {	
					String mensaje = retorno.getMensaje();	
					model.addAttribute("error", mensaje);
					return ConstantsCommon.OLVIDO;
				}
			} catch (Exception e) {
				String mensaje = e.getMessage();	
				model.addAttribute("error", mensaje);
				return ConstantsCommon.OLVIDO;
			}				
				 		
		}
	 
	 @RequestMapping("/403")
		public String accessDenied(Principal principal, ModelMap model,
				HttpServletRequest request) {

			String msg = "";
			String warning = "";
			if (principal == null) {
				msg = messageSource.getMessage("403.errorMessage",
						new String[] { messageSource.getMessage(
								"accesscontroller.anonymous", null,
								Locale.getDefault()) }, Locale.getDefault());
			} else {
				msg = messageSource.getMessage("403.errorMessage",
						new String[] { principal.getName() }, Locale.getDefault());
			}
			warning = messageSource.getMessage("403.warning",
					new String[] { request.getRemoteAddr() }, Locale.getDefault());

			model.addAttribute("errorMessage", msg);
			model.addAttribute("remoteAddress", warning);

			return "403";
		}
	 
	 @RequestMapping(value = "/validaSesion", method = RequestMethod.GET)
	 @ResponseBody
	 public ResponseEntity<Void> sendViaResponseEntity(Principal principal) {
		 if (principal!=null && ((Authentication)principal).isAuthenticated()) {
			 return new ResponseEntity<>(HttpStatus.OK);
		 }else{
			 return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	     }
		 
	 }
	 
}
