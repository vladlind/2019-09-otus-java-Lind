package ru.otus.hw13_DI;


import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.otus.hw13_DI.domain.AddressDataSet;
import ru.otus.hw13_DI.domain.PhoneDataSet;
import ru.otus.hw13_DI.domain.User;

import javax.sql.DataSource;
import java.util.Properties;


@Configuration
@EnableTransactionManagement
public class HibernateConfig {

    @Bean
    public LocalSessionFactoryBean getSessionFactory() {

        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setAnnotatedClasses(User.class, PhoneDataSet.class, AddressDataSet.class);
        factoryBean.setDataSource(getDataSource());
        factoryBean.setHibernateProperties(setHibernateProperties());
        return factoryBean;
    }

    @Bean
    public HibernateTransactionManager getTransactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(getSessionFactory().getObject());
        return transactionManager;
    }

    @Bean
    public static DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:OtusExamplesDB;DB_CLOSE_DELAY=-1");
        dataSource.setUsername("sa");
        dataSource.setPassword("sa");
        return dataSource;
    }

    private static Properties setHibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        properties.put("hibernate.hbm2ddl.auto", "create");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.enable_lazy_load_no_trans", "false");
        return properties;
    }
}
