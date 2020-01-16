package ru.otus.hibernate.api.model;

import javax.persistence.*;

@Entity
@Table(name = "phones")
public class PhoneDataSet {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "phone_id")
    private long id;

    @Column(name = "number")
    private String number;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public PhoneDataSet() {
    }

    public PhoneDataSet(String number){
        setNumber(number);
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
