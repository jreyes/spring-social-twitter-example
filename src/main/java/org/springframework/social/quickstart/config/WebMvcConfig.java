package org.springframework.social.quickstart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.quickstart.user.UserInterceptor;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.inject.Inject;

@Configuration
@EnableWebMvc
public class WebMvcConfig
    extends WebMvcConfigurerAdapter
{
// ------------------------------ FIELDS ------------------------------

    @Inject
    private UsersConnectionRepository usersConnectionRepository;

// ------------------------ INTERFACE METHODS ------------------------

// --------------------- Interface WebMvcConfigurer ---------------------

    public void addInterceptors( InterceptorRegistry registry )
    {
        registry.addInterceptor( new UserInterceptor( usersConnectionRepository ) );
    }

    public void addViewControllers( ViewControllerRegistry registry )
    {
        registry.addViewController( "/signin" );
        registry.addViewController( "/signout" );
    }

// -------------------------- OTHER METHODS --------------------------

    @Bean
    public ViewResolver viewResolver()
    {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix( "/WEB-INF/views/" );
        viewResolver.setSuffix( ".jsp" );
        return viewResolver;
    }
}