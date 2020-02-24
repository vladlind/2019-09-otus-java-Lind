package ru.otus.webserver;

import org.hibernate.SessionFactory;
import ru.otus.webserver.api.dao.UserDao;
import ru.otus.webserver.api.model.AddressDataSet;
import ru.otus.webserver.api.model.PhoneDataSet;
import ru.otus.webserver.api.model.User;
import ru.otus.webserver.hibernate.HibernateUtils;
import ru.otus.webserver.hibernate.InitiateDB;
import ru.otus.webserver.hibernate.dao.UserDaoHibernate;
import ru.otus.webserver.hibernate.sessionmanager.SessionManagerHibernate;
import ru.otus.webserver.server.UsersWebServer;
import ru.otus.webserver.server.UsersWebServerImpl;
import ru.otus.webserver.services.TemplateProcessor;
import ru.otus.webserver.services.TemplateProcessorImpl;
import ru.otus.webserver.services.UserAuthService;
import ru.otus.webserver.services.UserAuthServiceImpl;


public class Main {
    private static final int WEB_SERVER_PORT = 8081;
    private static final String TEMPLATES_DIR = "/templates/";

    public static void main(String[] args) throws Exception {

        SessionFactory sessionFactory = HibernateUtils.buildSessionFactory
                ("hibernate.cfg.xml", User.class, AddressDataSet.class, PhoneDataSet.class);

        SessionManagerHibernate sessionManager = new SessionManagerHibernate(sessionFactory);
        UserDao userDao = new UserDaoHibernate(sessionManager);

        InitiateDB initiateDB = new InitiateDB(userDao);

        UserAuthService userAuthServiceForFilterBasedSecurity = new UserAuthServiceImpl(userDao);

        TemplateProcessor templateProcessor = new TemplateProcessorImpl(TEMPLATES_DIR);

        UsersWebServer usersWebServer = new UsersWebServerImpl(WEB_SERVER_PORT,
                userAuthServiceForFilterBasedSecurity,
                userDao,
                templateProcessor);

        usersWebServer.start();
        usersWebServer.join();
    }
}
