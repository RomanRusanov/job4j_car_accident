package ru.job4j.accident;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import ru.job4j.accident.config.DataConfig;
import ru.job4j.accident.config.WebConfig;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;
/**
 * @author Roman Rusanov
 * @version 0.1
 * @since 11.02.2021
 * email roman9628@gmail.com
 * Когда tomcat загружает наше приложение, он ищет класс,
 * который расширяет WebApplicationInitializer.
 * Tomcat создает контекст Spring и загружает DispatcherServlet.
 * DispatcherServlet будет обрабатывать все запросы. Он доступен по адресу,
 * указанному в addMapping().
 */
public class WebInit implements WebApplicationInitializer {

    public void onStartup(ServletContext servletCxt) {
        AnnotationConfigWebApplicationContext ac = new AnnotationConfigWebApplicationContext();
        ac.register(WebConfig.class, DataConfig.class, SecurityConfig.class);
        ac.refresh();
        initAppContext();
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        filter.setForceRequestEncoding(true);
        FilterRegistration.Dynamic encoding = servletCxt.addFilter("encoding", filter);
        encoding.addMappingForUrlPatterns(null, false, "/*");
        DispatcherServlet servlet = new DispatcherServlet(ac);
        ServletRegistration.Dynamic registration = servletCxt.addServlet("app", servlet);
        registration.setLoadOnStartup(1);
        registration.addMapping("/");
    }

    public void initAppContext() {
        AnnotationConfigApplicationContext context = AppContext.getInstance().getAppContext();
        context.scan("ru.job4j.accident");
        context.refresh();
    }

}