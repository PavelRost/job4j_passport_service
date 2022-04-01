package ru.job4j.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.job4j.domain.Passport;
import ru.job4j.repository.PassportRepository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PassportService {

    private final PassportRepository passportRepository;

    public PassportService(PassportRepository passportRepository) {
        this.passportRepository = passportRepository;
    }

    public boolean save(Passport passport) {
        boolean rsl = false;
        Passport pass = passportRepository.findBySerialAndNumber(passport.getSerial(), passport.getNumber()).orElse(null);
        if (pass == null) {
            passportRepository.save(passport);
            rsl = true;
        }
        return rsl;
    }

    public boolean update(int id, Passport passport) {
        boolean rsl = passportRepository.existsById(id);
        if (rsl) {
            passport.setId(id);
            passportRepository.save(passport);
        }
        return rsl;
    }

    public boolean delete(int id) {
        boolean rsl = passportRepository.existsById(id);
        if (rsl) {
            passportRepository.deleteById(id);
        }
        return rsl;
    }

    public List<Passport> findAll() {
        return passportRepository.findAll();
    }

    public List<Passport> findBySerial(int serial) {
        return passportRepository.findPassportBySerial(serial);
    }

    public List<Passport> findNoValid() {
        return passportRepository.findPassportNoValid();
    }

    public List<Passport> findReplaceable() {
        Timestamp stamp = new Timestamp(System.currentTimeMillis());
        stamp.setMonth(stamp.getMonth() + 3);
        return passportRepository.findPassportReplaceable(stamp);
    }
}