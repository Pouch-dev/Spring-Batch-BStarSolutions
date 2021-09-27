package com.example.springbatch.model;

import javax.persistence.*;
import java.util.Date;

@Entity
//@Table(name = "UserManagement")
public class UserManagement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    private String name;
    private String departments;
    private String salary;
    private Date time;

    public UserManagement(Integer id, String name, String departments, String salary, Date time) {
        this.id = id;
        this.name = name;
        this.departments = departments;
        this.salary = salary;
        this.time = time;
    }

    public UserManagement() {
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

    public String getDepartments() {
        return departments;
    }

    public void setDepartments(String departments) {
        this.departments = departments;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
     public String toString() {
         final StringBuffer sb = new StringBuffer("User{");
         sb.append("id=").append(id);
         sb.append(", name='").append(name).append('\'');
         sb.append(", departments='").append(departments).append('\'');
         sb.append(", salary=").append(salary);
         sb.append('}');
         return sb.toString();
     }
 }
