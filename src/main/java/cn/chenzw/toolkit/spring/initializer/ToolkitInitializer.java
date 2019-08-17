package cn.chenzw.toolkit.spring.initializer;

import cn.chenzw.toolkit.spring.listener.AppContextStartListener;
import org.springframework.web.WebApplicationInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class ToolkitInitializer implements WebApplicationInitializer {


    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        servletContext.addListener(new AppContextStartListener());
    }
}
