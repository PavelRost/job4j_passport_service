package ru.job4j.controller;

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
    public Passport save(@RequestBody Passport passport) {
        return passportService.save(passport);
    }

    @PutMapping("/update/{id}")
    public void update(@PathVariable int id, @RequestBody Passport passport) {
        passportService.update(id, passport);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        passportService.delete(id);
    }

    @GetMapping("/findAll")
    public List<Passport> findAll() {
        return passportService.findAll();
    }

    @GetMapping("/findBySerial")
    public List<Passport> findBySerial(@RequestParam int serial) {
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
