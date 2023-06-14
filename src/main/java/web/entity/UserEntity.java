package web.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users", schema = "public", catalog = "pp231")
public class UserEntity {
    @Id
    @Basic
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    @Column(name = "name", nullable = true, length = 255)
    private String name;
    @Basic
    @Column(name = "age", nullable = true)
    private Integer age;

    public UserEntity() {
    }

    public UserEntity(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public UserEntity(long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity user = (UserEntity) o;
        return Objects.equals(name, user.getName()) && Objects.equals(age, user.getAge());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}
