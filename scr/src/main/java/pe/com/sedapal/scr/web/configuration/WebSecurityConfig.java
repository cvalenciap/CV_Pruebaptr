/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.web.configuration;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import pe.com.sedapal.common.web.configuration.SimpleAuthenticationSuccessHandler;

// TODO: Auto-generated Javadoc
/**
 * The Class WebSecurityConfig.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	//@Autowired
    //BCryptPasswordEncoder bcryptEncoder;
	
	/**
	 * Password encoder.
	 *
	 * @return the b crypt password encoder
	 */
	@Bean
	public BCryptPasswordEncoder passwordEncoder(){
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
	
	/** The custom user detail service. */
	@Autowired
	private UserDetailsService customUserDetailService;
	
	/** The success handler. */
	@Autowired
	private SimpleAuthenticationSuccessHandler successHandler;
	
//	 SIN LOGUEO 
	/*
	@Override
    protected void configure(HttpSecurity http) throws Exception {
    	 http.csrf().disable()
         .authorizeRequests()
				.antMatchers( "/about","/images/**","/webjars/**", "**", "*").permitAll() // se cambia a abierto para poder probar
//				.antMatchers( "/about","/images/**","/webjars/**").permitAll() // se cambia a abierto para poder probar
				.antMatchers("/admin/**").hasAnyRole("ADMIN")
				.antMatchers("/user/**").hasAnyRole("USER")
				.anyRequest().authenticated()
         .and()
	         .formLogin()
					.loginPage("/login")
					.permitAll()
					.successHandler(successHandler)
		.and()
	         .logout()
					.permitAll()
		 .and()
         		.exceptionHandling()
         		.accessDeniedPage("/403")
         .and()
         		.headers().frameOptions().sameOrigin();
    	 
    }
    */
	
    /* METODO PARA LOGUEAR */
    
    /* (non-Javadoc)
 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.HttpSecurity)
 */
@Override
    protected void configure(HttpSecurity http) throws Exception {
    	 http.csrf().disable()
         .authorizeRequests()
				.antMatchers("/validaSesion", "/olvido", "/about","/images/**","/webjars/**", "/js/**", "/css/**","/resources/**").permitAll() // se cambia a abierto para poder probar				.antMatchers("/admin/**").hasAnyRole("ADMIN")
				.antMatchers("/inicio**").not().hasAuthority("restablecerClave")
				.antMatchers("/inicio").not().anonymous()
				.antMatchers("/mantenimiento**").permitAll()
				.antMatchers("/rio**").hasAuthority("rio")
				.antMatchers("/clima**").hasAuthority("clima")
				.antMatchers("/colaborador**").hasAuthority("colaborador")
				.antMatchers("/reportesGrafico**").hasAuthority("reportesGrafico")
				.antMatchers("/configuracionCorreo**").hasAuthority("configuracionCorreo")
				.antMatchers("/bandejaCaudales**").hasAuthority("bandejaCaudales")
				.antMatchers("/reportesResumen**").hasAuthority("reportesResumen")
				.antMatchers("/represa**").hasAuthority("represa")
				.antMatchers("/represas**").hasAuthority("represas")
				.antMatchers("/reporteClima**").hasAuthority("reporteClima")
				.antMatchers("/reporteCaudales**").hasAuthority("reporteCaudales")
				.antMatchers("/reporteAlmacenamiento").hasAuthority("reporteAlmacenamiento")
				.antMatchers("/reporteAlmacenamientoPTAP").hasAuthority("reporteAlmacenamientoPTAP")
				.antMatchers("/procesoExtraccion**").hasAuthority("procesoExtraccion")
				.antMatchers("/correoGerencia**").hasAuthority("correoGerencia")
				.antMatchers("/correoResumen**").hasAuthority("correoResumen")
				.antMatchers("/restablecerClave**").hasAuthority("restablecerClave")
				.antMatchers("/cambioClave").hasAuthority("cambioClave")
				.antMatchers("/user/**").hasRole("USER")
				//INICIO - permisos de Spring para el Modulo SPTAR	
				.antMatchers("/inicioSptar**").not().hasAuthority("restablecerClave")
				.antMatchers("/inicioSptar").not().anonymous()
				.antMatchers("/general/**").not().anonymous()
				.antMatchers("/menu/**").not().anonymous()
				.antMatchers("/general/**").not().anonymous()
				.antMatchers("/mantenimientoSptar/**").not().anonymous()
				.antMatchers("/registroSptar/**").not().anonymous()
				.antMatchers("/aprobacionSptar/**").not().anonymous()
				//BYPASS
//				.antMatchers("/general/**").permitAll()
//				.antMatchers("/menu/**").permitAll()
//				.antMatchers("/general/**").permitAll()
//				.antMatchers("/mantenimientoSptar/**").permitAll()
//				.antMatchers("/registroSptar/**").permitAll()
//				.antMatchers("/aprobacionSptar/**").permitAll()
				//FIN - permisos de Spring para el Modulo SPTAR
				.anyRequest().authenticated()         
				.and()
	         .formLogin()
					.loginPage("/login")
					.permitAll()
					.successHandler(successHandler)
		.and()
	         .logout().logoutUrl("/logout").logoutSuccessUrl("/login?loggedout=true")
					.permitAll()
		 .and()
         		.exceptionHandling()
         		.accessDeniedPage("/403")
         .and()
         		.headers().frameOptions().sameOrigin();
    }
    
    
    /**
     * Configure global.
     *
     * @param auth the auth
     * @throws Exception the exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {    	
    	auth.userDetailsService(customUserDetailService).passwordEncoder(passwordEncoder()) ;
    }
    
    /**
     * Gets the spring security filter chain binded to error.
     *
     * @param springSecurityFilterChain the spring security filter chain
     * @return the spring security filter chain binded to error
     */
    @Bean
    public FilterRegistrationBean getSpringSecurityFilterChainBindedToError(@Qualifier("springSecurityFilterChain") Filter springSecurityFilterChain) {
    	FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(springSecurityFilterChain);
        registration.setDispatcherTypes(EnumSet.allOf(DispatcherType.class));
        return registration;
    }
    
}