package pe.com.sedapal.common.web.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.core.session.SessionDestroyedEvent;
import org.springframework.stereotype.Component;

import pe.com.sedapal.seguridad.ws.SeguridadClienteWs;

@Component
public class LogoutListener implements ApplicationListener<SessionDestroyedEvent> {

	private final Logger logger = LoggerFactory.getLogger(LogoutListener.class);

	@Autowired
	SeguridadClienteWs stub;
	
	@Override
	public void onApplicationEvent(SessionDestroyedEvent event) {
		String token = event.getId();
		stub.logoutWs(token);
		logger.debug("### CERRANDO SESION ### {}", token);
	}

}