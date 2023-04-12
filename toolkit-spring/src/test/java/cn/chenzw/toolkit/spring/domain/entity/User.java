package cn.chenzw.toolkit.spring.domain.entity;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private Integer id;
    private String name;
    private String sex;
    private Integer age;
    private Date birthDate;

    public User() {
    }

    public User(Integer id, String name, String sex, Integer age, Date birthDate) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.birthDate = birthDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name='" + name + '\'' + ", sex='" + sex + '\'' + ", age=" + age
                + ", birthDate=" + birthDate + '}';
    }
}
