package pe.com.sedapal.common.web.springsecurity.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import pe.com.sedapal.common.core.utils.ConstantsCommon;
import pe.com.sedapal.seguridad.core.bean.Retorno;
import pe.com.sedapal.seguridad.ws.SeguridadClienteWs;

@Service("customUserDetailsService")
@ConfigurationProperties(value = "classpath:application.properties")
public class CustomUserDetailsService implements UserDetailsService {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

	@Autowired
	 private HttpServletRequest request;

	@Autowired
	private SeguridadClienteWs stub;
	
	@Autowired
	private Environment env;
		
	@Override
	public UserDetails loadUserByUsername(String username) {
		Integer sistemaCodigo = Integer.parseInt(env.getProperty("parametro.sistema.codigo"));
		Integer sistemaCodigoSptar = Integer.parseInt(env.getProperty("parametro.sistema.codigoSptar"));
		
		Retorno retorno = null;
		Retorno retornoSptar = null;
		List<String> permisos = null;
		List<GrantedAuthority> authorities = null;
		try {
		
			request.setCharacterEncoding("UTF-8");
			String password = request.getParameter("password");
			retorno = stub.autenticacionUsuarioWs(username.toUpperCase(),sistemaCodigo,password);
			request.setAttribute("codigoSistema", sistemaCodigo);
			if (ConstantsCommon.FAILURE.equals(retorno.getCodigo())) {
				retornoSptar = stub.autenticacionUsuarioWs(username.toUpperCase(),sistemaCodigoSptar,password);
				if (!ConstantsCommon.FAILURE.equals(retornoSptar.getCodigo())) {
					retorno = retornoSptar;					
					request.setAttribute("codigoSistema", sistemaCodigoSptar);
				}else {
					if(retornoSptar.getFlagCambiarClave()==null) {
						retorno = retornoSptar;
						request.setAttribute("codigoSistema", sistemaCodigoSptar);
					}
				}
			}
			
			if (ConstantsCommon.FAILURE.equals(retorno.getCodigo())) {
				throw new BadCredentialsException(retorno.getMensaje());
			}
			if(retorno.getFlagCambiarClave()==null)
				 retorno.setFlagCambiarClave("1");
	
			if(Integer.parseInt(retorno.getFlagCambiarClave()) != ConstantsCommon.ESTADO_INACTIVO){
				permisos = new ArrayList<>();
				permisos.add("restablecerClave");
			}else{
				permisos = stub.obtenerPermisosWs(username);
				permisos.add("cambioClave");
			}
			authorities = buildUserAuthority(permisos);

		} catch (UsernameNotFoundException e) {
			logger.error(e.getMessage(), e);
			throw new UsernameNotFoundException(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			if(e.getMessage()!=null && (e.getMessage().contains("error al realizar la operacion = I/O error")||e.getMessage().contains("error al realizar la operacion = No Encontrado"))){
				throw new AccessDeniedException("Error: No se puede acceder al webservice de seguridad");
			}
			throw new AccessDeniedException(e.getMessage());
		}
		return buildUserForAuthentication(retorno.getUsuario(),retorno.getClave(), authorities);
	}

	private List<GrantedAuthority> buildUserAuthority(List<String> permisos) {

		Set<GrantedAuthority> setAuths = new HashSet<>();

		for (String permiso : permisos) {
			setAuths.add(new SimpleGrantedAuthority(permiso));
		}

		return new ArrayList<>(setAuths);
	}

	private User buildUserForAuthentication(String usuario, String clave, List<GrantedAuthority> authorities) {
		return new User(usuario, clave, authorities);
	}

}
