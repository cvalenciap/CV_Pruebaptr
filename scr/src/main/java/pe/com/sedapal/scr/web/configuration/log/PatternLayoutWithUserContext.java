/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.web.configuration.log;

import ch.qos.logback.classic.PatternLayout;

/**
 * The Class PatternLayoutWithUserContext.
 */
public class PatternLayoutWithUserContext extends PatternLayout{
	static {
        PatternLayout.defaultConverterMap.put("user", UserConverter.class.getName());
    }
}
