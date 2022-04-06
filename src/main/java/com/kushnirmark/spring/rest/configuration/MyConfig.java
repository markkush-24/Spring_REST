package com.kushnirmark.spring.rest.configuration;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "com.kushnirmark.spring.rest")
@EnableWebMvc // эта анотация заменяет нам <mvc:annotation-driven/> из xml
@EnableTransactionManagement // вместо <tx:annotation-driven transaction-manager="transactionManager"/>
public class MyConfig {

    @Bean
    public DataSource dataSource(){ // создаем данный метод для подключения к БД SQL / аналог xml
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try {
            dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
            dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/my_db?useSSL=false&serverTimezone=UTC");
            dataSource.setUser("bestuser");
            dataSource.setPassword("bestuser");
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(){ // создаем фабрику сессий
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("com.kushnirmark.spring.rest.entity");

        //Прописываем свойства hibernate с помощью JAVA , создаем объект Properties , назначем ему 2 свойства и после
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.dialect","org.hibernate.dialect.MySQLDialect");
        hibernateProperties.setProperty("hibernate.show_sql","true");

        //объект Properties назначем нашей sessionFactory
        sessionFactory.setHibernateProperties(hibernateProperties);
        return sessionFactory;
    }


    @Bean
    public HibernateTransactionManager transactionManager(){
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        //чтобы получить sessionFactory из класса LocalSessionFactoryBean нам необходимо использовать метод getObject
        transactionManager.setSessionFactory(sessionFactory().getObject());

        return transactionManager;
    }
}
