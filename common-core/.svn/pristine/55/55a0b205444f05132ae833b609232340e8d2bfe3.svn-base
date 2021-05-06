package pe.com.sedapal.common.web.configuration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import pe.com.sedapal.common.core.beans.MenuOption;
import pe.com.sedapal.common.core.utils.ConstantsCommon;
import pe.com.sedapal.common.core.utils.CoreUtils;
import pe.com.sedapal.seguridad.core.bean.Retorno;
import pe.com.sedapal.seguridad.core.bean.SistemaModuloOpcionBean;
import pe.com.sedapal.seguridad.ws.SeguridadClienteWs;

@Component
@ConfigurationProperties(value = "classpath:application.properties")
public class SimpleAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	
	private final Logger logger = LoggerFactory.getLogger(SimpleAuthenticationSuccessHandler.class);
	
	@Autowired
	private SeguridadClienteWs stub;	
	
	@Autowired
	private Environment env;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		// insertar un registro en la tabla accesos

		//Integer sistemaCodigo = Integer.parseInt(env.getProperty("parametro.sistema.codigo"));
		
		Retorno retorno = null;
		final String usuario = request.getParameter("username");
		final String codigoSistemaString = request.getAttribute("codigoSistema").toString();
		Integer sistemaCodigo = Integer.parseInt(codigoSistemaString);
		String strIp = "";
		String strToken = "";
		String strPath = "";
		try {
			Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
			if (details instanceof WebAuthenticationDetails)
				strIp = ((WebAuthenticationDetails) details).getRemoteAddress();

			strToken = request.getSession().getId();
			logger.debug(strToken);
			retorno = stub.autenticacionUsuarioCompletaWs(usuario, strIp, strToken, sistemaCodigo);
			
			if (ConstantsCommon.SUCCESS.equals(retorno.getCodigo())) {
				List<SistemaModuloOpcionBean> lstMenus = stub.obtenerMenueWs(usuario, sistemaCodigo);
				List<MenuOption> userMenuOptions = CoreUtils.getMenuOptions(lstMenus);
				request.getSession().setAttribute("segUserMenuOptions", (ArrayList<MenuOption>)userMenuOptions);
				
				if (retorno.getFlagCambiarClave() != null && Integer.parseInt(retorno.getFlagCambiarClave()) == ConstantsCommon.ESTADO_ACTIVO) {
					strPath = request.getContextPath() + "/restablecerClave";
				} else {
//					strPath = request.getContextPath() + "/inicio";	
					//comprobamos si se va al sistema de PTAR O AL NORMA
					Integer codigoModuloSptar = Integer.parseInt(env.getProperty("parametro.sistema.codigoSptar"));
					if(sistemaCodigo.intValue() == codigoModuloSptar.intValue()) {
						strPath = request.getContextPath() + "/inicioSptar";
					}else {
						strPath = request.getContextPath() + "/inicio";
					}	
				}
			} else {
				strPath = request.getContextPath() + "/exception";
			}

			Integer duracionSesion = Integer.parseInt(env.getProperty("parametro.sesion.duracion"));
			request.getSession().setMaxInactiveInterval(duracionSesion * 60);
			response.sendRedirect(strPath);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ServletException(e);
		}
	}
 
}