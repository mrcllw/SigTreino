package br.com.sigtreino;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import br.com.sigtreino.filter.TokenFilter;

@SpringBootApplication
public class SigTreinoApplication {
	
	@Bean
	public FilterRegistrationBean getFilterJwt(){
		FilterRegistrationBean frb = new FilterRegistrationBean();
		frb.setFilter(new TokenFilter());
		frb.addUrlPatterns("/admin/*");
		return frb;
	}

	public static void main(String[] args) {
		SpringApplication.run(SigTreinoApplication.class, args);
	}
}
