package db;

public class Member {

    private String name;
    private String age;
    private String grade;
    private String email;
    private String parentName;
    private String parentEmail;
    private BadgeProgress badgeProgress;
    private JourneyProgress journeyProgress;

    public Member() {
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

    public BadgeProgress getBadgeProgress() {
        return badgeProgress;
    }

    public void setBadgeProgress(BadgeProgress badgeProgress) {
        this.badgeProgress = badgeProgress;
    }

    public JourneyProgress getJourneyProgress() {
        return journeyProgress;
    }

    public void setJourneyProgress(JourneyProgress journeyProgress) {
        this.journeyProgress = journeyProgress;
    }
}
