package itlab;

import itlab.module.Database;
import itlab.module.Scheme;
import itlab.module.Table;
import itlab.module.exceptions.NonExistingTable;
import itlab.module.exceptions.TableAlreadyExsists;
import itlab.module.exceptions.UnsupportedValueException;
import itlab.module.types.Types;
import itlab.service.helpers.DatabaseHelper;

import java.util.HashMap;
import java.util.Map;

public class test {
    public static void main(String[] args) throws UnsupportedValueException, TableAlreadyExsists, NonExistingTable {
        save();
    }
    private static void load(){
        Database database = new Database("TestDB");
        database.load();
        Map<String,Table> tables =database.getTables();
        Table tb=tables.get("first table");
        System.out.println(tb.getRows());
    }
    private static void save() throws TableAlreadyExsists, UnsupportedValueException, NonExistingTable {
        Database database=new Database("TestDB");
        HashMap<String,Types> schemeMap=new HashMap<>();
        schemeMap.put("id",Types.INTEGER);
        schemeMap.put("user",Types.STRING);
        String currtableUuid= database.createTable("first table",new Scheme(schemeMap));
        Table t= database.getTable(currtableUuid);
        HashMap<String,String> columnValue=new HashMap<>();
        columnValue.put("id","1");
        columnValue.put("user","SUPERUSER");
        String rowUuid = t.addRow(columnValue);
        String rowUuid2 = t.addRow(columnValue);
        HashMap<String,String> columnValue3=new HashMap<>();
        columnValue.put("id","3");
        columnValue.put("user","SUPERUSER3");
        String uuid3= t.addRow(columnValue3);
        System.out.println(t.getRow(rowUuid));
        System.out.println(t.getRow(rowUuid2));
        System.out.println(t.getRow(uuid3));
        database.save();
    }
    private static void delete(){
        Database database = new Database("TestDB");
        DatabaseHelper.delete("TestDB");
    }
}
