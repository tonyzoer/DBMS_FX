package itlab.module.types;

import itlab.module.exceptions.UnsupportedValueException;

public class ValueTypeFabric {

    private static ValueTypeFabric instance;

    private ValueTypeFabric() {
    }

    public static ValueTypeFabric getInstance() {
        if (instance == null) {
            instance = new ValueTypeFabric();
        }
        return instance;
    }

    public Type createCorrectType(Types t, String val) throws UnsupportedValueException {
        switch (t) {
            case CHAR:
                return new CharT(val);
            case CHARINTERVAL:
                return new CharInvT(val);
            case DOUBLE:
                return new DoubleT(val);
            case INTEGER:
                return new IntegerT(val);
            case STRING:
                return new StringT(val);
            case STRINGN:
                String words[] = val.split(" ");
                Integer n = Integer.parseInt(words[words.length - 1]);
                return new StringT(val, n);
            case TIMEINTERVAL:
                return new TimeInvT(val);
            case TIME:
                return new TimeT(val);
        }
        return null;
    }

    public Type createCorrectType(Types t) throws UnsupportedValueException {
        switch (t) {
            case CHAR:
                return new CharT("");
            case CHARINTERVAL:
                return new CharInvT("");
            case DOUBLE:
                return new DoubleT("");
            case INTEGER:
                return new IntegerT("");
            case STRING:
                return new StringT("");
            case TIMEINTERVAL:
                return new TimeInvT("");
            case TIME:
                return new TimeT("");
        }
        return null;
    }
}
