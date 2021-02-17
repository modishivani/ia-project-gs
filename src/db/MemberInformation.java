package db;

import java.util.ArrayList;

public class MemberInformation {

    private String name;
    private String age;
    private String grade;
    private String email;
    private String parentName;
    private String parentEmail;
    private ArrayList<BadgeProgress> badgeProgress;
    private ArrayList<JourneyProgress> journeyProgress;

    public MemberInformation() {
        this.badgeProgress = new ArrayList<>();
        this.journeyProgress = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
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

    public ArrayList<BadgeProgress> getBadgeProgress() {
        return badgeProgress;
    }

    public ArrayList<JourneyProgress> getJourneyProgress() {
        return journeyProgress;
    }
}
