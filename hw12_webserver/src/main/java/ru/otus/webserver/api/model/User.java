package ru.otus.webserver.api.model;


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

  @Column(name = "admin")
  private boolean admin = false;

  public boolean isAdmin() {
    return admin;
  }

  private void setAdmin() {
    this.admin = true;
  }

  @OneToOne(targetEntity = AddressDataSet.class, cascade = CascadeType.ALL)
  @JoinColumn(name = "address_id")
  private AddressDataSet addressDataSet;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
  private Set<PhoneDataSet> phoneDataSet;

  public void setPassword(String password) {
    this.password = password;
    setAdmin();
  }

  public String getPassword() {
    return password;
  }

  @Column(name="password")
  private String password;

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
    this.phoneDataSet.forEach(phone -> phone.setUser(this));
    }

  @Override
  public String toString() {
    return "User{" +
        "id=" + id +
        ", name='" + name + '\'' +
        '}';
  }
}
