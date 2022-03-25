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

    public ResponseEntity<Passport> save(Passport passport) {
        List<Passport> passports = findAll();
        for (var pass : passports) {
            if (pass.getSerial() == passport.getSerial() || pass.getNumber() == passport.getNumber()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        }
        passportRepository.save(passport);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    public ResponseEntity<Void> update(int id, Passport passport) {
        boolean status = passportRepository.existsById(id);
        if (status) {
            passport.setId(id);
            passportRepository.save(passport);
        }
        return ResponseEntity.status(status ? HttpStatus.OK : HttpStatus.NOT_FOUND).build();
    }

    public ResponseEntity<Void> delete(int id) {
        boolean status = passportRepository.existsById(id);
        if (status) {
            passportRepository.deleteById(id);
        }
        return ResponseEntity.status(status ? HttpStatus.OK : HttpStatus.NOT_FOUND).build();
    }

    public List<Passport> findAll() {
        return passportRepository.findAll();
    }

    public List<Passport> findBySerial(int serial) {
        return new ArrayList<>(passportRepository.findPassportBySerial(serial));
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
