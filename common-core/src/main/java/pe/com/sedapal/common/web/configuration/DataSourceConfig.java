package pe.com.sedapal.common.web.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@ComponentScan 
@ConfigurationProperties(value = "classpath:application.properties")
public class DataSourceConfig {
	
	@Autowired
	private Environment environment;

   @Bean
   public DataSource primaryDataSource() {
	   
	  String passworddbEncript = environment.getRequiredProperty("jdbc.password");
	  String passworddbDecrypt = passworddbEncript;	   	   
	   
	   HikariConfig config = new HikariConfig();
	   config.setJdbcUrl(environment.getRequiredProperty("jdbc.url"));
	   config.setUsername(environment.getRequiredProperty("jdbc.username"));
	   config.setPassword(passworddbDecrypt);	   
	   config.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
	   config.setConnectionInitSql("alter session set current_schema="
			   + environment.getRequiredProperty("oracle.schema.procedures") );
	   config.setConnectionTestQuery("select 1 from dual");
	   config.addDataSourceProperty("cachePrepStmts", "true");
	   config.addDataSourceProperty("prepStmtCacheSize", "250");
	   config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
	   return new HikariDataSource(config);	
   }
}