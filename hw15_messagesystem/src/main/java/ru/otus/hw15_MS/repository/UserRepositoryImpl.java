package ru.otus.hw15_MS.repository;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.otus.hw15_MS.domain.User;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final SessionFactory sessionFactory;

    public UserRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<User> findByLogin(String login) {
        TypedQuery<User> query = this.sessionFactory.getCurrentSession()
                .createQuery("SELECT e FROM User e WHERE e.name = :Name", User.class);
        query.setParameter("Name", login);
        return Optional.ofNullable(query.getSingleResult());
    }

    @Override
    public ArrayList<User> findAll() {
        return new ArrayList<>(this.sessionFactory.getCurrentSession()
                .createQuery("FROM User", User.class).list());
    }

    @Override
    public long saveUser(User user) {
        if (user.getId() > 0) {
            this.sessionFactory.getCurrentSession().update(user);
        } else {
            this.sessionFactory.getCurrentSession().persist(user);
        }
        return user.getId();
    }
}
