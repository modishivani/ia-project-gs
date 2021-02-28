package db;

public class ActivityInformation {
    private String name;
    private String description;
    private String[] steps;

    public ActivityInformation() {
    }

    public String[] getSteps() {
        return steps;
    }

    public void setSteps(String[] steps) {
        this.steps = steps;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    void validate() throws IllegalArgumentException {
        Argument.ensureNotNullOrEmpty("activity name", this.name);
        Argument.ensureNotNullOrEmpty("activity description", this.description);
        Argument.ensureNotNullOrEmpty("activity step 1", this.steps[0]);
        Argument.ensureNotNullOrEmpty("activity step 2", this.steps[1]);
        Argument.ensureNotNullOrEmpty("activity step 3", this.steps[2]);
        Argument.ensureNotNullOrEmpty("activity step 4", this.steps[3]);
        Argument.ensureNotNullOrEmpty("activity step 5", this.steps[4]);
    }
}