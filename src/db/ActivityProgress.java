package db;

public class ActivityProgress {

    private String name;
    private boolean[] stepProgress;

    public ActivityProgress(ActivityInformation activityInformation) {

        this.name = activityInformation.getName();

        String[] steps = activityInformation.getSteps();
        this.stepProgress = new boolean[steps.length];

        for (int i = 0; i < steps.length; i++) {
            this.stepProgress[i] = false;
        }
    }

    public String getName() {
        return this.name;
    }

     public boolean getStepProgress(int index) {
        Argument.ensureInRange("index", index, 0, this.stepProgress.length - 1);
        return this.stepProgress[index];
    }

    public void setStepProgress(int index, boolean isCompleted) {
        Argument.ensureInRange("index", index, 0, this.stepProgress.length - 1);
        this.stepProgress[index] = isCompleted;
    }

    void validate() throws IllegalArgumentException {
        Argument.ensureNotNull("ActivityProgress.stepProgress", this.stepProgress);
    }
}
