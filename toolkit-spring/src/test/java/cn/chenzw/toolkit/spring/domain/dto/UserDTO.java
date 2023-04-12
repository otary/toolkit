package cn.chenzw.toolkit.spring.domain.dto;

import java.util.Date;

public class UserDTO {

    private Integer id;

    private String name;
    private Date birthDate;

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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "UserDto{" + "id=" + id + ", name='" + name + '\'' + ", birthDate=" + birthDate + '}';
    }
}
