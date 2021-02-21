package db;

import java.util.ArrayList;

public class MemberInformation {

    private String name;
    private int age;
    private int grade;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
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

    void validate()
        throws IllegalArgumentException {

        Argument.ensureNotNullOrEmpty("MemberInformation.Name", this.name);

        Argument.ensureNotNull("MemberInformation.BadgeProgress", this.badgeProgress);
        for(BadgeProgress b : this.badgeProgress) {
            b.validate();
        }
    }
}
