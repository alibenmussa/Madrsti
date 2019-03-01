package main.views.stages.template;



import javafx.scene.control.Button;
import main.DatabaseManager;
import main.views.dialog.Dialog;

import java.sql.*;

public class Staff {
    private String full_name;
    private Date birthday;
    private String state;
    private String gender;
    private String living_address;
    private Integer national_id;
    private String nationality;
    private String job_description;
    private String email;
    private Integer phone_number;
    private String major;
    private String degree;
    private String University;
    private String user_name;
    private String password;
    private String graduateYear;
    private final Button edit;
    private final Button delete;


    public Staff() {
        this.edit = new Button("EDIT");
        this.delete = new Button("DELETE");

    }


    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLiving_address() {
        return living_address;
    }

    public void setLiving_address(String living_address) {
        this.living_address = living_address;
    }

    public Integer getNational_id() {
        return national_id;
    }

    public void setNational_id(Integer national_id) {
        this.national_id = national_id;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getJob_description() {
        return job_description;
    }

    public void setJob_description(String job_description) {
        this.job_description = job_description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(Integer phone_number) {
        this.phone_number = phone_number;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getUniversity() {
        return University;
    }

    public void setUniversity(String university) {
        University = university;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGraduateYear() {
        return graduateYear;
    }

    public void setGraduateYear(String graduateYear) {
        this.graduateYear = graduateYear;
    }

    public Button getEdit() {
        return edit;
    }

    public Button getDelete() {
        return delete;
    }
}

