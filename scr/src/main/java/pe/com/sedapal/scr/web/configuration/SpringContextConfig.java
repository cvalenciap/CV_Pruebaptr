package pe.com.sedapal.scr.web.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.scheduling.annotation.EnableScheduling;


@Configuration
//@EnableAspectJAOutoProxy(proxyTargetClass = false)
//@ComponentScan(basePackageClasses = { SpringDef.class }, excludeFilters = { @Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class) })
//@PropertySources({ @PropertySource(value = "classpath:application.properties"), })
@ComponentScan (basePackages = {"pe.com.sedapal"})
@EnableScheduling
public class SpringContextConfig {
	
	@Bean(name = "messageSource")
	public ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasenames("classpath:messages", "classpath:common");
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setUseCodeAsDefaultMessage(true);
		return messageSource;
	}

}
