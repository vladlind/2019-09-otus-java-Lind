package ru.otus.hw13_DI.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.otus.hw13_DI.domain.AddressDataSet;
import ru.otus.hw13_DI.domain.PhoneDataSet;
import ru.otus.hw13_DI.domain.User;


@Configuration
@EnableTransactionManagement
public class HibernateConfig {

    private final ApplicationContext applicationContext;

    public HibernateConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Bean
    public LocalSessionFactoryBean getSessionFactory() {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setConfigLocation(this.applicationContext.getResource("classpath:hibernate.cfg.xml"));
        factoryBean.setAnnotatedClasses(User.class, PhoneDataSet.class, AddressDataSet.class);
        return factoryBean;
    }

    @Bean
    public HibernateTransactionManager getTransactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(getSessionFactory().getObject());
        return transactionManager;
    }
}
