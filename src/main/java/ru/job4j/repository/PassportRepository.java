package ru.job4j.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.domain.Passport;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PassportRepository extends CrudRepository<Passport, Integer> {

    @Transactional
    @Modifying
    @Query("update Passport p set p.name = ?1, p.serial = ?2, p.number = ?3, p.validityPeriod = ?4 where p.id = ?5")
    void updatePassport(String name, int serial, int number, Date validityPeriod, int id);

    List<Passport> findPassportBySerial(int serial);


}
