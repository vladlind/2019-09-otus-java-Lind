package ru.otus.jdbc.api.model;

import ru.otus.jdbc.api.dao.Id;

/**
 * @author sergey
 * created on 03.02.19.
 */
public class User {
  @Id
  private long id = 0;
  private final String name;
  private final int age;

  public User(String name, int age) {
    this.name = name;
    this.age = age;
  }

  public User(long id, String name, int age) {
    this.id = id;
    this.name = name;
    this.age = age;
  }

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  @Override
  public String toString() {
    return "User{" +
        "id=" + id +
        ", name='" + name + '\'' +", age="+ age +
        '}';
  }
}
