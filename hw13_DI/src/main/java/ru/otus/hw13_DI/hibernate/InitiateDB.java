//package ru.otus.hw13_DI.hibernate;
//
//import org.hibernate.SessionFactory;
//import ru.otus.webserver.api.dao.UserDao;
//import ru.otus.webserver.api.model.AddressDataSet;
//import ru.otus.webserver.api.model.PhoneDataSet;
//import ru.otus.webserver.api.model.User;
//import ru.otus.webserver.api.service.DBServiceUser;
//import ru.otus.webserver.api.service.DbServiceUserImpl;
//import ru.otus.webserver.hibernate.sessionmanager.SessionManagerHibernate;
//
//import java.util.HashSet;
//import java.util.Set;
//
//public class InitiateDB {
//    public InitiateDB(UserDao userDao) {
//        initiateDBwithUsers(userDao);
//    }
//
//    private void initiateDBwithUsers(UserDao userDao) {
//
//        SessionFactory sessionFactory = HibernateUtils.buildSessionFactory
//                ("hibernate.cfg.xml", User.class, AddressDataSet.class, PhoneDataSet.class);
//
//        SessionManagerHibernate sessionManager = new SessionManagerHibernate(sessionFactory);
//        DBServiceUser dbServiceUser = new DbServiceUserImpl(userDao);
//
//        User user1 = new User(0, "Вася");
//        User user2 = new User(0, "Петя");
//        User user3 = new User(0, "Коля");
//
//        AddressDataSet address1 = new AddressDataSet("улица Мира, дом 1");
//
//        AddressDataSet address2 = new AddressDataSet("улица Мира, дом 2");
//
//        AddressDataSet address3 = new AddressDataSet("улица Мира, дом 3");
//
//        Set<PhoneDataSet> phoneDataSets1 = new HashSet<>();
//        phoneDataSets1.add(new PhoneDataSet("1111"));
//        phoneDataSets1.add(new PhoneDataSet("2222"));
//
//        Set<PhoneDataSet> phoneDataSets2 = new HashSet<>();
//        phoneDataSets2.add(new PhoneDataSet("3333"));
//        phoneDataSets2.add(new PhoneDataSet("4444"));
//
//        Set<PhoneDataSet> phoneDataSets3 = new HashSet<>();
//        phoneDataSets3.add(new PhoneDataSet("5955"));
//        phoneDataSets3.add(new PhoneDataSet("6666"));
//        phoneDataSets3.add(new PhoneDataSet("7777"));
//
//        user1.setAddressDataSet(address1);
//        user1.setPhoneDataSet(phoneDataSets1);
//        user2.setAddressDataSet(address2);
//        user2.setPhoneDataSet(phoneDataSets2);
//        user3.setAddressDataSet(address3);
//        user3.setPhoneDataSet(phoneDataSets3);
//
//        user3.setPassword("11111");
//
//        long id1 = dbServiceUser.saveUser(user1);
//        long id2 = dbServiceUser.saveUser(user2);
//        long id3 = dbServiceUser.saveUser(user3);
//    }
//}
