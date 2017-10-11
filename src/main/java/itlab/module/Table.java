package itlab.module;

import itlab.module.exceptions.NonExistingColumn;
import itlab.module.exceptions.UnsupportedValueException;
import itlab.module.types.Types;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Table implements Serializable {
    public String name;

    private Scheme scheme;

    private Map<String, Row> rows;

    public void setName(String name) {
        this.name = name;
    }

    public Table(String name, Scheme scheme) {
        this.rows = new HashMap<>();
        this.scheme = scheme;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Scheme getScheme() {
        return scheme;
    }

    public Map<String, Row> getRows() {
        return rows;
    }

    public String addRow(Map<String, String> values) throws UnsupportedValueException {
        String uuid = UUID.randomUUID().toString();
        rows.put(uuid, new Row(scheme, values));
        return uuid;
    }

    public Row getRow(String uuid) {
        return rows.get(uuid);
    }

    public void deleteRow(String uuid) {
        rows.remove(uuid);
    }

    public void updateRow(String uuid, Map<String, String> values) throws UnsupportedValueException {
        rows.replace(uuid, new Row(scheme, values));
    }

    public void updateRow(String uuid, String collumnName, String value) throws NonExistingColumn, UnsupportedValueException {
        Row cur = rows.get(uuid);
        Types t = scheme.getColumns().get(collumnName);
        if (t != null)
            cur.setValue(collumnName, value, t);
    }

    @Override
    public String toString() {
        return "Table{" +
                "name='" + name + '\'' +
                ", scheme=" + scheme +
                ", rows=" + rows +
                '}';
    }
}
