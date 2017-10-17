package GUI;

import itlab.module.types.Type;
import itlab.module.types.Types;
import javafx.beans.property.Property;
import javafx.beans.property.StringProperty;

/**
 * Created by mafio on 13.10.2017.
 */
public class Field {
    public Field(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public Field() {
        this.name = "name";
        this.type = Types.INTEGER.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String type;


}
