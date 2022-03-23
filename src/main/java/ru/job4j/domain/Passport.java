package ru.job4j.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "passport")
public class Passport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int serial;
    private int number;

    @Temporal(TemporalType.TIMESTAMP)
    private Date validityPeriod;

    public Passport() {
    }

    public Passport(String name, int serial, int number, Date validityPeriod) {
        this.name = name;
        this.serial = serial;
        this.number = number;
        this.validityPeriod = validityPeriod;
    }

    public String getName() {
        return name;
    }

    public int getSerial() {
        return serial;
    }

    public int getNumber() {
        return number;
    }

    public Date getValidityPeriod() {
        return validityPeriod;
    }
}
