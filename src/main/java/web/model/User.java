package web.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Table
public class User {
    @Id
    private long id;
    @Column
    private String name;
    @Column
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public User() {

    }
    public User(Long id, String name, Integer age) {
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
    public long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && age == user.age && name.equals(user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age);
    }
}
