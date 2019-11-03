package cn.chenzw.toolkit.spring.initializer;

import org.springframework.web.WebApplicationInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class ToolkitInitializer implements WebApplicationInitializer {


    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        // Tomcat7-不支持
        // servletContext.addListener(new AppContextStartListener());
    }
}
