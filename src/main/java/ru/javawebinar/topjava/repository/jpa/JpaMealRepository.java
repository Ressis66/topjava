package ru.javawebinar.topjava.repository.jpa;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;
import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;

import org.hibernate.Session;


@Repository
public class JpaMealRepository implements MealRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Meal save(Meal meal, int userId) {
        em.setProperty("user_id", userId);
        em.persist(meal);

       return meal;
    }

    @Override
    public boolean delete(int id, int userId) {
            em.createNamedQuery(Meal.DELETE)
                    .setParameter("id", id)
                    .executeUpdate();
        return true;
        }

    @Override
    public Meal get(int id, int userId) {
        return em.find(Meal.class, id);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return em.createNamedQuery(Meal.ALL_SORTED, Meal.class).getResultList();
    }

    @Override
    public List<Meal> getBetweenHalfOpen(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return em.createQuery("SELECT u FROM Meal u WHERE u.dateTime >= ?1 AND u.dateTime < ?2").
setParameter(1, startDateTime).setParameter(2,endDateTime).getResultList();
    }
}