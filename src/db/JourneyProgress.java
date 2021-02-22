package db;

public class JourneyProgress {

    private String name;
    private StepProgress[] stepProgress;

    public JourneyProgress(JourneyInformation journeyInformation) {
        this.name = journeyInformation.getName();

        String[] steps = journeyInformation.getSteps();
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

    void validate() {
        Argument.ensureNotNull("JourneyProgress.stepProgress", this.stepProgress);
        for(StepProgress s : this.stepProgress) {
            s.validate();
        }
    }
}

