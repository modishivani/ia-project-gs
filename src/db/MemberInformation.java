package db;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.Hashtable;

public class MemberInformation {

    private String name;
    private int age;
    private int grade;
    private String email;
    private String parentName;
    private String parentEmail;
    private final Hashtable<String, ActivityProgress> badgeProgress;
    private final Hashtable<String, ActivityProgress> journeyProgress;

    public MemberInformation() {
        this.badgeProgress = new Hashtable<String, ActivityProgress>();
        this.journeyProgress = new Hashtable<String, ActivityProgress>();
    }

    //getter and setter methods for the class variables

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAge(String ageStr) {
        //making sure that the input is a number
        Argument.ensureInteger("member age", ageStr);
        this.age = Integer.parseInt(ageStr);
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public void setGrade(String gradeStr) {
        //making sure that the input is a number
        Argument.ensureInteger("member grade", gradeStr);
        this.grade = Integer.parseInt(gradeStr);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getParentEmail() {
        return parentEmail;
    }

    public void setParentEmail(String parentEmail) {
        this.parentEmail = parentEmail;
    }

    public Hashtable<String, ActivityProgress> getBadgeProgress() {
        return badgeProgress;
    }

    public Hashtable<String, ActivityProgress> getJourneyProgress() {
        return journeyProgress;
    }

    void validate()
            throws IllegalArgumentException {

        //validate member information variables
        Argument.ensureNotNullOrEmpty("member name", this.name);
        Argument.ensureInRange("member age", this.age, 0, 18);
        Argument.ensureInRange("member grade", this.grade, 0, 12);
        Argument.ensureContains("member email", this.email, "@");
        Argument.ensureContains("parent email", this.parentEmail, "@");
        Argument.ensureNotNull("member badge progress", this.badgeProgress);
        for (ActivityProgress b : this.badgeProgress.values()) {
            b.validate();
        }
        Argument.ensureNotNull("member journey progress", this.journeyProgress);
        for (ActivityProgress j : this.journeyProgress.values()) {
            j.validate();
        }
    }
}

