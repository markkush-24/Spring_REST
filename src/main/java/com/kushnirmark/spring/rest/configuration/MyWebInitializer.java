package com.kushnirmark.spring.rest.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
//Так как мы не используем в постоении проекта WEB.XML , то мы создаем этот класс который будет ответственный за DispatcherServlet
public class MyWebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        //Мы даем ссылку на "AplicationContext" в этом классе , за это ответственный этот метод
        return new Class[]{MyConfig.class};//добавляем элемент , которым является наш конфигурационный файл MyConfig
    }

    @Override
    protected String[] getServletMappings() {
        //Указываем URL связанный с нашим приложение его значение "/"
        return new String[]{"/"};
    }
}
