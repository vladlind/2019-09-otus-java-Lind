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

    SessionFactory sessionFactory = HibernateUtils.buildSessionFactory
            ("hibernate.cfg.xml", User.class, AddressDataSet.class, PhoneDataSet.class);

    SessionManagerHibernate sessionManager = new SessionManagerHibernate(sessionFactory);
    UserDao userDao = new UserDaoHibernate(sessionManager);
    DBServiceUser dbServiceUser = new DbServiceUserImpl(userDao);

    User user1 = new User(1L, "Вася");
    User user2 = new User(2L, "Петя");
    User user3 = new User(3L, "Коля");

    AddressDataSet address1 = new AddressDataSet("улица Мира, дом 1");

    AddressDataSet address2 = new AddressDataSet("улица Мира, дом 2");

    AddressDataSet address3 = new AddressDataSet("улица Мира, дом 3");

    Set<PhoneDataSet> phoneDataSets1 = new HashSet<>();
    phoneDataSets1.add(new PhoneDataSet("1111"));
    phoneDataSets1.add(new PhoneDataSet("2222"));

    Set<PhoneDataSet> phoneDataSets2 = new HashSet<>();
    phoneDataSets2.add(new PhoneDataSet("3333"));
    phoneDataSets2.add(new PhoneDataSet("4444"));

    Set<PhoneDataSet> phoneDataSets3 = new HashSet<>();
    phoneDataSets3.add(new PhoneDataSet("5555"));
    phoneDataSets3.add(new PhoneDataSet("6666"));

    user1.setAddressDataSet(address1);
    user1.setPhoneDataSet(phoneDataSets1);
    user2.setAddressDataSet(address2);
    user2.setPhoneDataSet(phoneDataSets2);
    user3.setAddressDataSet(address3);
    user3.setPhoneDataSet(phoneDataSets3);

    long id1 = dbServiceUser.saveUser(user1);
    long id2 = dbServiceUser.saveUser(user2);
    long id3 = dbServiceUser.saveUser(user3);

    Optional<User> mayBeCreatedUser1 = dbServiceUser.getUser(id1);
    Optional<User> mayBeCreatedUser2 = dbServiceUser.getUser(id2);
    Optional<User> mayBeCreatedUser3 = dbServiceUser.getUser(id3);
    System.out.println("--------------");

    System.out.println(mayBeCreatedUser1.get().getName());
    System.out.println("----");
    System.out.println(mayBeCreatedUser2.get().getName());
    System.out.println("----");
    System.out.println(mayBeCreatedUser3.get().getName());
    System.out.println("--------------");
    System.out.println(mayBeCreatedUser1.get().getAddressDataSet().getStreet());
    System.out.println("----");
    System.out.println(mayBeCreatedUser2.get().getAddressDataSet().getStreet());
    System.out.println("----");
    System.out.println(mayBeCreatedUser3.get().getAddressDataSet().getStreet());
    System.out.println("--------------");
    mayBeCreatedUser1.get().getPhoneDataSet().forEach(v -> System.out.println(v.getNumber()));
    System.out.println("----");
    mayBeCreatedUser2.get().getPhoneDataSet().forEach(v -> System.out.println(v.getNumber()));
    System.out.println("----");
    mayBeCreatedUser3.get().getPhoneDataSet().forEach(v -> System.out.println(v.getNumber()));
  }
}
