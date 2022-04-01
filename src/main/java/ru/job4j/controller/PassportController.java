package ru.job4j.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.domain.Passport;
import ru.job4j.service.PassportService;
import java.util.List;

@RestController
@RequestMapping("/api/passport")
public class PassportController {

    private final PassportService passportService;

    public PassportController(PassportService passportService) {
        this.passportService = passportService;
    }

    @PostMapping("/save")
    public ResponseEntity<Passport> save(@RequestBody Passport passport) {
        boolean rsl = passportService.save(passport);
        if (!rsl) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> update(@PathVariable int id, @RequestBody Passport passport) {
        boolean rsl = passportService.update(id, passport);
        return ResponseEntity.status(rsl ? HttpStatus.OK : HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        boolean rsl = passportService.delete(id);
        return ResponseEntity.status(rsl ? HttpStatus.OK : HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/findAll")
    public List<Passport> findAll() {
        return passportService.findAll();
    }

    @GetMapping("/findBySerial/{serial}")
    public List<Passport> findBySerial(@PathVariable int serial) {
        return passportService.findBySerial(serial);
    }

    @GetMapping("/findPassNoValid")
    public List<Passport> findNoValid() {
        return passportService.findNoValid();
    }

    @GetMapping("/findPassReplaceable")
    public List<Passport> findReplaceable() {
        return passportService.findReplaceable();
    }

}
