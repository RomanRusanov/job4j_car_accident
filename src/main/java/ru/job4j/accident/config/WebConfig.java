package ru.job4j.accident.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
/**
 * @author Roman Rusanov
 * @version 0.1
 * @since 11.02.2021
 * email roman9628@gmail.com
 * Аннотация ComponentScan она сканирует проект и загружает бины в контекст.
 * Внутри этого класса создается объект ViewResolver.
 * Spring использует этот объект для поиска jsp. В нем сразу подключен JSTL.
 */
@Configuration
@ComponentScan("ru.job4j.accident")
public class WebConfig {

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver bean = new InternalResourceViewResolver();
        bean.setViewClass(JstlView.class);
        bean.setPrefix("./WEB-INF/view/");
        bean.setSuffix(".jsp");
        return bean;
    }
}