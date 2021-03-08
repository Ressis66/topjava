package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

public interface CrudMealRepository extends JpaRepository<Meal, Integer> {
    @Transactional
    @Modifying
//    @Query(name = User.DELETE)
    @Query("DELETE FROM Meal u WHERE u.id=:id")
    int delete(@Param("id") int id);


    @Transactional
    @Modifying
    @Query("select u from Meal u where u.dateTime > :startDateTime and u.dateTime < :endDateTime")
    List<Meal> findByDateTime(@Param("startDateTime") LocalDateTime startDateTime,
                                   @Param("endDateTime") LocalDateTime endDateTime);
}
