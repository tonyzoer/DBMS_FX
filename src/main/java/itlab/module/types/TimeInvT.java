package itlab.module.types;

import itlab.module.exceptions.UnsupportedValueException;

import java.time.DateTimeException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeInvT extends Type {

    LocalTime begin;
    LocalTime end;
    TimeInvT(String s) throws UnsupportedValueException {
        super(s);
    }

    public LocalTime getBegin() {
        return begin;
    }

    public void setBegin(LocalTime begin) {
        this.begin = begin;
    }

    public LocalTime getEnd() {
        return end;
    }

    public void setEnd(LocalTime end) {
        this.end = end;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TimeInvT)) return false;

        TimeInvT timeInvT = (TimeInvT) o;

        if (getBegin() != null ? !getBegin().equals(timeInvT.getBegin()) : timeInvT.getBegin() != null) return false;
        return getEnd() != null ? getEnd().equals(timeInvT.getEnd()) : timeInvT.getEnd() == null;
    }

    @Override
    public int hashCode() {
        int result = getBegin() != null ? getBegin().hashCode() : 0;
        result = 31 * result + (getEnd() != null ? getEnd().hashCode() : 0);
        return result;
    }


    @Override
    public void setValue(String s) throws UnsupportedValueException {
    try{
        String[] si=s.split("/");
        if (si.length==2){
        begin= LocalTime.parse(si[0], DateTimeFormatter.ofPattern("HH:mm:ss[.ssssss]"));
        end= LocalTime.parse(si[1], DateTimeFormatter.ofPattern("HH:mm:ss[.ssssss]"));
        }else{
            throw new UnsupportedValueException("Not founded time splitter or more then 1 for type TimeInterval");
        }
    }catch (DateTimeException ex){
        throw new UnsupportedValueException(s+" not suportet value for TimeInterval Type",ex);
    }
    }

    @Override
    public String toString() {
        return "TimeInvT{" +"value={"+
                "begin=" + begin +
                ", end=" + end +
                "}}";
    }
}
