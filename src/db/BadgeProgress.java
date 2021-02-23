package db;

public class BadgeProgress {

    private String name;
    private StepProgress[] stepProgress;

    public BadgeProgress(BadgeInformation badgeInformation) {
        this.name = badgeInformation.getName();

        String[] steps = badgeInformation.getSteps();
        this.stepProgress = new StepProgress[steps.length];

        for (int i = 0; i < steps.length; i++) {
            this.stepProgress[i] = new StepProgress(steps[i]);
        }
    }

    public String getName() {
        return this.name;
    }

    public StepProgress[] getStepProgress() {
        return this.stepProgress;
    }

    public boolean isCompleted() {
        for(int i = 0; i < this.stepProgress.length; i++) {
            if (!this.stepProgress[i].isCompleted()) {
                return false;
            }
        }
        return true;
    }

    void validate() {
        Argument.ensureNotNull("BadgeProgress.stepProgress", this.stepProgress);
        for(StepProgress s : this.stepProgress) {
            s.validate();
        }
    }
}
