package com.pets.pets.repository;

import com.pets.pets.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserRepo {

    public void saveUser(final User user) {
        try (Session session = HibernateUtil.openSession()) {
            final Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public User findById(final int id) {
        try (Session session = HibernateUtil.openSession()) {
            final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            final CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);

            final Root<User> root = criteriaQuery.from(User.class);
            criteriaQuery.select(root);
            criteriaQuery.where(criteriaBuilder.equal(root.get("id"), id));

            final Query<User> query = session.createQuery(criteriaQuery);
            final List<User> resultList = query.getResultList();

            return resultList.get(0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<User> findAllUsers() {
        try (Session session = HibernateUtil.openSession()) {
            final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            final CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);

            final Root<User> root = criteriaQuery.from(User.class);
            criteriaQuery.select(root);
            final Query<User> query = session.createQuery(criteriaQuery);

            return query.getResultList();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new ArrayList<>();
    }

}
