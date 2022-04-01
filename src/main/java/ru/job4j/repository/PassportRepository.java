package ru.job4j.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.domain.Passport;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;


public interface PassportRepository extends CrudRepository<Passport, Integer> {

    List<Passport> findPassportBySerial(int serial);

    List<Passport> findAll();

    @Query("SELECT ps FROM Passport ps WHERE ps.validityPeriod < CURRENT_TIMESTAMP")
    List<Passport> findPassportNoValid();

    @Query("SELECT ps FROM Passport ps WHERE ps.validityPeriod > current_timestamp AND ps.validityPeriod < :date")
    List<Passport> findPassportReplaceable(@Param("date") Timestamp date);

    Optional<Passport> findBySerialAndNumber(int serial, int number);
}
