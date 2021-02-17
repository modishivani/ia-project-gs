package db;

public class Database {

    private final String troopNo;
    private final String year;

    public Database(
        String rootFolder,
        String troopNo,
        String year) {

        this.troopNo = troopNo;
        this.year = year;
    }
}
