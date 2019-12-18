package ru.otus.hibernate;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.hibernate.api.model.AddressDataSet;
import ru.otus.hibernate.api.model.PhoneDataSet;
import ru.otus.hibernate.api.model.User;
import ru.otus.hibernate.api.service.DbServiceUserImpl;
import ru.otus.hibernate.api.dao.UserDao;
import ru.otus.hibernate.api.service.DBServiceUser;
import ru.otus.hibernate.hibernate.HibernateUtils;
import ru.otus.hibernate.hibernate.dao.UserDaoHibernate;
import ru.otus.hibernate.hibernate.sessionmanager.SessionManagerHibernate;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class DbServiceDemo {
  private static Logger logger = LoggerFactory.getLogger(DbServiceDemo.class);

  public static void main(String[] args) {
    // Все главное см в тестах
    SessionFactory sessionFactory = HibernateUtils.buildSessionFactory
            ("hibernate.cfg.xml", User.class, AddressDataSet.class, PhoneDataSet.class);

    SessionManagerHibernate sessionManager = new SessionManagerHibernate(sessionFactory);
    UserDao userDao = new UserDaoHibernate(sessionManager);
    DBServiceUser dbServiceUser = new DbServiceUserImpl(userDao);

    User user1 = new User(0, "Вася");
    User user2 = new User(1L, "Петя");

    AddressDataSet address1 = new AddressDataSet();
    address1.setStreet("улица Мира, дом 1");

    AddressDataSet address2 = new AddressDataSet();
    address2.setStreet("улица Мира, дом 2");

    PhoneDataSet phone1 = new PhoneDataSet();
    PhoneDataSet phone2 = new PhoneDataSet();
    phone1.setNumber("1111");
    phone2.setNumber("2222");

    Set<PhoneDataSet> phoneDataSets = new HashSet<PhoneDataSet>();
    phoneDataSets.add(phone1);
    phoneDataSets.add(phone2);

    user1.setAddressDataSet(address1);
    user1.setPhoneDataSetSet(phoneDataSets);
    user2.setAddressDataSet(address2);
    user2.setPhoneDataSetSet(phoneDataSets);

    long id1 = dbServiceUser.saveUser(user1);
    Optional<User> mayBeCreatedUser1 = dbServiceUser.getUser(id1);

    long id2 = dbServiceUser.saveUser(user2);
    Optional<User> mayBeCreatedUser2 = dbServiceUser.getUser(id2);

  }
}
