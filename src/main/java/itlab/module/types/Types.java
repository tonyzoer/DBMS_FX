package itlab.module.types;

public enum Types {
    CHAR,CHARINTERVAL,DOUBLE,INTEGER,STRING,STRINGN,TIMEINTERVAL,TIME;
    public String toString() {
        return name().charAt(0) + name().substring(1).toLowerCase();
    }
}
