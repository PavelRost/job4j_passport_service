package ru.job4j.service;

import org.springframework.stereotype.Service;
import ru.job4j.domain.Passport;
import ru.job4j.repository.PassportRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PassportService {

    private final PassportRepository passportRepository;

    public PassportService(PassportRepository passportRepository) {
        this.passportRepository = passportRepository;
    }

    public Passport save(Passport passport) {
        return passportRepository.save(passport);
    }

    public void update(int id, Passport passport) {
        passportRepository.updatePassport(passport.getName(),
                passport.getSerial(),
                passport.getNumber(),
                passport.getValidityPeriod(),
                id);
    }

    public void delete(int id) {
        passportRepository.deleteById(id);
    }

    public List<Passport> findAll() {
        List<Passport> rsl = new ArrayList<>();
        passportRepository.findAll().forEach(rsl::add);
        return rsl;
    }

    public List<Passport> findBySerial(int serial) {
        return new ArrayList<>(passportRepository.findPassportBySerial(serial));
    }

    public List<Passport> findNoValid() {
        List<Passport> temp = findAll();
        List<Passport> rsl = new ArrayList<>();
        for (var passport : temp) {
            if (passport.getValidityPeriod().getMonth() < new Date().getMonth()) {
                rsl.add(passport);
            }
        }
        return rsl;
    }

    public List<Passport> findReplaceable() {
        List<Passport> temp = findAll();
        List<Passport> rsl = new ArrayList<>();
        for (var passport : temp) {
            if ((passport.getValidityPeriod().getMonth() - new Date().getMonth()) < 3 && (passport.getValidityPeriod().getMonth() - new Date().getMonth()) > 0) {
                rsl.add(passport);
            }
        }
        return rsl;
    }


}
