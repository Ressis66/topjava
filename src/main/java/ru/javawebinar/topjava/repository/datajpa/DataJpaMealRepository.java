package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.time.LocalDateTime;
import java.util.List;
@ActiveProfiles("datajpa")
@Repository
public class DataJpaMealRepository implements MealRepository {

    private final CrudMealRepository crudRepository;

    public DataJpaMealRepository(CrudMealRepository crudRepository) {
        this.crudRepository = crudRepository;
    }
    @Transactional
    @Override
    public Meal save(Meal meal, int userId) {
        crudRepository.getOne(userId);
        return
                crudRepository.save(meal);
    }
    @Transactional
    @Override
    public boolean delete(int id, int userId) {
       crudRepository.getOne(userId);
       return crudRepository.delete(id)!=0;
    }
    @Transactional
    @Override
    public Meal get(int id, int userId) {
        crudRepository.getOne(userId);
        return crudRepository.findById(id).orElse(null);
    }
    @Transactional
    @Override
    public List<Meal> getAll(int userId) {
        crudRepository.getOne(userId);
        return crudRepository.findAll();

    }
    @Transactional
    @Override
    public List<Meal> getBetweenHalfOpen(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        crudRepository.getOne(userId);

        return crudRepository.findByDateTime(startDateTime,endDateTime);
    }
}
