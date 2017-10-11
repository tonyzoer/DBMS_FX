package itlab.module.types;

import itlab.module.exceptions.UnsupportedValueException;

public class DoubleT extends Type {
    Double value;

    DoubleT(String s) throws UnsupportedValueException {
        super(s);
    }


    @Override
    public void setValue(String s) throws UnsupportedValueException {
        try {
            value = Double.parseDouble(s);
        } catch (NumberFormatException e) {
            throw new UnsupportedValueException(value + "not suported for class Double", e);
        }
    }


    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DoubleT)) return false;

        DoubleT doubleT = (DoubleT) o;

        return getValue() != null ? getValue().equals(doubleT.getValue()) : doubleT.getValue() == null;
    }

    @Override
    public int hashCode() {
        return getValue() != null ? getValue().hashCode() : 0;
    }


    @Override
    public String toString() {
        return "DoubleT{" +
                "value=" + value +
                '}';
    }
}
