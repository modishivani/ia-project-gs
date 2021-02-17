package db;

public class StepProgress {
    private String name;
    private boolean isCompleted;

    public StepProgress(String name) {
        this.name = name;
        this.isCompleted = false;
    }



    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
