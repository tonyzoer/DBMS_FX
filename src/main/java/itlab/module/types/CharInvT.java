package itlab.module.types;

import itlab.module.exceptions.UnsupportedValueException;

import java.util.Set;

public class CharInvT extends Type {
    Set<Character> alphabet;
    String value;

    CharInvT(String s) throws UnsupportedValueException {
        super(s);
    }



    public void setValue(String value) {
        for (char c:value.toCharArray()
             ) {
            if (!alphabet.contains(c)){}
        }
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CharInvT)) return false;

        CharInvT charInvT = (CharInvT) o;

        if (alphabet != null ? !alphabet.equals(charInvT.alphabet) : charInvT.alphabet != null) return false;
        return value != null ? value.equals(charInvT.value) : charInvT.value == null;
    }

    @Override
    public int hashCode() {
        int result = alphabet != null ? alphabet.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CharInvT{" +
                "alphabet=" + alphabet +
                ", value='" + value + '\'' +
                '}';
    }
}
