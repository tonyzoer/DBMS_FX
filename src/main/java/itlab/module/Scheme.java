package itlab.module;

import itlab.module.types.Types;

import java.io.Serializable;
import java.util.Map;

public class Scheme implements Serializable {
    Map<String, Types> columns;

    public Scheme(Map<String, Types> columns) {
        this.columns = columns;
    }

    public Map<String, Types> getColumns() {
        return columns;
    }

    @Override
    public boolean equals(Object obj) {
        try {
            Scheme another = (Scheme) obj;
            if(columns.size() != another.columns.size()) return false;
            for(Map.Entry<String, Types> item : columns.entrySet()){
                if(another.columns.get(item.getKey()) != item.getValue())
                    return false;
            }
            return true;
        } catch (ClassCastException ex) {
            return false;
        }
    }
}
