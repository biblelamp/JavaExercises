package com.application;

import org.apache.myfaces.ee6.MyFacesContainerInitializer;
import org.apache.myfaces.webapp.MyFacesServlet;
import org.apache.myfaces.webapp.StartupServletContextListener;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.HashSet;
import java.util.Set;

/**
 * User: kirill.salyamov
 * Time: 2016-05-06 18:27
 */
@Configuration
@ComponentScan(basePackages = "com.application.jsf")
public class JsfConfig {

    @Bean
    public ServletRegistrationBean facesServletRegistration() {
        ServletRegistrationBean servletRegistrationBean = new JsfServletRegistrationBean(new MyFacesServlet());
        servletRegistrationBean.setLoadOnStartup(1);
        return servletRegistrationBean;
    }

    public class JsfServletRegistrationBean extends ServletRegistrationBean {

        public JsfServletRegistrationBean(Servlet servlet, String... urlMappings) {
            super(servlet, urlMappings);
        }

        @Override
        public void onStartup(ServletContext servletContext)
                throws ServletException {
            servletContext.setInitParameter("primefaces.THEME", "bootstrap");

            MyFacesContainerInitializer facesInitializer = new MyFacesContainerInitializer();
            Set<Class<?>> clazz = new HashSet<Class<?>>();
            clazz.add(JsfConfig.class);
            facesInitializer.onStartup(clazz, servletContext);

            servletContext.addListener(new StartupServletContextListener());

        }
    }
}