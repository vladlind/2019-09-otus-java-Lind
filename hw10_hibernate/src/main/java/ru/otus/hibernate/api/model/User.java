package ru.otus.hibernate.api.model;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "id")
  private long id;

  @Column(name = "name")
  private String name;

  public AddressDataSet getAddressDataSet() {
    return addressDataSet;
  }

  public void setAddressDataSet(AddressDataSet addressDataSet) {
    this.addressDataSet = addressDataSet;
  }

  public Set<PhoneDataSet> getPhoneDataSetSet() {
    return phoneDataSetSet;
  }

  public void setPhoneDataSetSet(Set<PhoneDataSet> phoneDataSetSet) {
    this.phoneDataSetSet = phoneDataSetSet;
  }

  @OneToOne(targetEntity = AddressDataSet.class, cascade = CascadeType.ALL)
  @JoinColumn(name = "address_id")
  private AddressDataSet addressDataSet;

  @OneToMany(targetEntity = PhoneDataSet.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name="user_id")
  private Set<PhoneDataSet> phoneDataSetSet;

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

  @Override
  public String toString() {
    return "User{" +
        "id=" + id +
        ", name='" + name + '\'' +
        '}';
  }
}
