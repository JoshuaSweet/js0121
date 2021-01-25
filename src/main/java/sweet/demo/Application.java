package sweet.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@SpringBootApplication
public class Application {
	
	/**
	 * Boiler plate for messages.properties auto configuration.
	 */
	
	@Bean
	public MessageSource messageSource() {
		
	    ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
	    messageSource.setBasenames( "classpath:messages" );
	    messageSource.setDefaultEncoding( "UTF-8" );
	    return messageSource;
	    
	}
	
	/**
	 * Boiler plate for messages.properties auto configuration.
	 */
	
	@Bean
	public LocalValidatorFactoryBean getValidator() {
		
	    LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
	    bean.setValidationMessageSource( messageSource() );
	    return bean;
	    
	}
    
	public static void main( String[] args ) {
		
		SpringApplication.run( Application.class, args );
		
	}
	
}
