package ru.otus.hw13_DI.domain;

import javax.persistence.*;

@Entity
@Table(name = "address")
public class AddressDataSet {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "address_id")
    private long id;

    @Column(name = "street")
    private String street;

    @OneToOne(mappedBy = "addressDataSet")
    private User user;

    public AddressDataSet() {
    }

    public AddressDataSet(String street) {
        setStreet(street);
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreet() {
        return street;
    }
}
