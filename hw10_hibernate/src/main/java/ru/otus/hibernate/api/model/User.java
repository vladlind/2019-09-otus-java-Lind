package ru.otus.hibernate.api.model;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "name")
  private String name;

  @OneToOne(targetEntity = AddressDataSet.class, cascade = CascadeType.ALL)
  @JoinColumn(name = "address_id")
  private AddressDataSet addressDataSet;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<PhoneDataSet> phoneDataSet;

  public User() {
  }

  public User(long id, String name) {
    this.id = id;
    this.name = name;
  }

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public AddressDataSet getAddressDataSet() {
    return addressDataSet;
  }

  public void setAddressDataSet(AddressDataSet addressDataSet) {
    this.addressDataSet = addressDataSet;
  }

  public Set<PhoneDataSet> getPhoneDataSet() {
    return phoneDataSet;
  }

  public void setPhoneDataSet(Set<PhoneDataSet> phoneDataSet) {
    this.phoneDataSet = phoneDataSet;
  }

  @Override
  public String toString() {
    return "User{" +
        "id=" + id +
        ", name='" + name + '\'' +
        '}';
  }
}
