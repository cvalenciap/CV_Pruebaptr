package pe.com.sedapal.common.web.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pe.com.sedapal.common.core.utils.ConstantsCommon;
import pe.com.sedapal.common.core.utils.SecurityUtil;
import pe.com.sedapal.seguridad.core.bean.ResultBean;
import pe.com.sedapal.seguridad.core.bean.Retorno;
import pe.com.sedapal.seguridad.ws.SeguridadClienteWs;

@Controller
public class ClaveController {
	
	private static final Logger log = LoggerFactory.getLogger(ClaveController.class);

	@Autowired
	private SeguridadClienteWs stub;


	 @RequestMapping("/cambioClave")
	    public String cambioClave(String name) {
	        return "cambioClave";
	    }
	 @RequestMapping("/restablecerClave")
	    public String restablecerClave(String name) {
	        return "restablecerClave";
	    }
	 
		@RequestMapping(value = "/cambioClaveActualizar", method = RequestMethod.POST)
		public @ResponseBody ResultBean actualizarClave(Model model,
				@RequestParam(value = "currentpassword") String currentpassword,
				@RequestParam(value = "newpassword") String newpassword,
				@RequestParam(value = "confirmnewpassword") String confirmnewpassword) {

			return changePassword(model, currentpassword, newpassword, confirmnewpassword, ConstantsCommon.TYPE_CHANGE_PWD);
		}
		private void llenarPermisos(){
			
			List<String> permisos = null;
			permisos = stub.obtenerPermisosWs(SecurityUtil.getAuthenticationName()) ;
			permisos.add("cambioClave");
			
			SimpleGrantedAuthority authority = null;
			List<SimpleGrantedAuthority> updatedAuthorities = new ArrayList<>();
			for (String permiso : permisos) {					
				authority = new SimpleGrantedAuthority(permiso);	
				updatedAuthorities.add(authority);
			}
			
			
			SecurityContextHolder.getContext().setAuthentication(
			        new UsernamePasswordAuthenticationToken(
			                SecurityContextHolder.getContext().getAuthentication().getPrincipal(),
			                SecurityContextHolder.getContext().getAuthentication().getCredentials(),
			                updatedAuthorities)
			);
			
		}

	@RequestMapping(value = "/restablecerClaveForm", method = RequestMethod.POST)
	public @ResponseBody ResultBean restablecerClaveForm(Model model,HttpServletRequest request, HttpServletResponse response,Authentication authentication,
			@RequestParam(value = "currentpassword") String currentpassword,
			@RequestParam(value = "newpassword") String newpassword,
			@RequestParam(value = "confirmnewpassword") String confirmnewpassword) {

		return changePassword(model, currentpassword, newpassword, confirmnewpassword, ConstantsCommon.TYPE_RESTAB_PWD);
	}
	
	ResultBean changePassword(Model model, String currentpassword, String newpassword, String confirmnewpassword, Integer intType){
		ResultBean result= new ResultBean();
		String strMensajeError = "";
		Integer intCodigo = 0;
		Retorno retorno;
			try {
				
				retorno = stub.actualizarClaveWs(SecurityUtil.getAuthenticationName(), currentpassword, newpassword, confirmnewpassword);

				if (ConstantsCommon.SUCCESS.equals(retorno.getCodigo())) {
					strMensajeError = retorno.getMensaje();
					log.debug("Clave actualizada: %s",strMensajeError);
					intCodigo = 1;
					if(intType==2){
						llenarPermisos();
					}					
					
				} else {
					strMensajeError = retorno.getMensaje();
					log.debug("No se actualizo: %s", strMensajeError);
					model.addAttribute(ConstantsCommon.ERROR, strMensajeError);
					intCodigo = 0;
				}				
				
			} catch (Exception e) {
				strMensajeError = e.getMessage();	
				model.addAttribute(ConstantsCommon.ERROR, strMensajeError);
				intCodigo = 0;
			}
			result.setCodigo(intCodigo);
			result.setMensaje(strMensajeError);

		return result;		
	}

}
