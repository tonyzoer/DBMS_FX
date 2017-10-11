package itlab.module;

import itlab.module.exceptions.NonExistingTable;
import itlab.module.exceptions.TableAlreadyExsists;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Database implements Serializable {
    Map<String, Table> tables;
    String name;

    public Database(String name, Map<String, Table> tables) {
        this.tables = tables;
        this.name = name;
    }

    public Database(String name) {
        this.name = name;
        tables = new HashMap<>();
    }

    public Map<String, Table> getTables() {
        return tables;
    }

    public String createTable(String name, Scheme scheme) throws TableAlreadyExsists {
        if (!tables.containsKey(name)) {
            Table t = new Table(name, scheme);
            tables.put(name, t);
            return name;
        } else {
            throw new TableAlreadyExsists(name + " table already exists");
        }
    }

    public void deleteTable(String name) {
        tables.remove(name);
    }

    public Table getTable(String name) throws NonExistingTable {

        if (tables.containsKey(name))
            return tables.get(name);
        else throw new NonExistingTable("Table " + name +" in database " + this.name + " not exists");
    }

    public void save() {
        try {
            String PATH = "/remote/dir/server/";
            String directoryName = PATH.concat("database/");
            String filename = name + ".db";

            File directory = new File(directoryName);
            if (!directory.exists()) {
                directory.mkdirs();
                // If you require it to make the entire directory path including parents,
                // use directory.mkdirs(); here instead.
            }

            File f = new File(directoryName + filename);
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream out = new ObjectOutputStream(fos);
            out.writeObject(this);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load() {
        try {
            String PATH = "/remote/dir/server/";
            String directoryName = PATH.concat("database/");
            String filename = name + ".db";

            File directory = new File(directoryName);
            if (!directory.exists()) {
                directory.mkdirs();
                // If you require it to make the entire directory path including parents,
                // use directory.mkdirs(); here instead.
            }
            Object db = new ObjectInputStream(new FileInputStream(directoryName + filename)).readObject();
            if (db instanceof Database) {
                Database lDatabase = (Database) db;
                this.tables = lDatabase.tables;
            }
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    public void delete() {
        String PATH = "/remote/dir/server/";
        String directoryName = PATH.concat("database/");
        String filename = name + ".db";

//        File directory = new File(directoryName);
//        if (!directory.exists()) {
//            directory.mkdirs();
//            // If you require it to make the entire directory path including parents,
//            // use directory.mkdirs(); here instead.
//        }
        File f = new File(directoryName + filename);
        boolean b= f.delete();
        System.out.println(b);
    }

    public List<Row> substractTables(String table1, String table2) throws NonExistingTable {
        if(tables.get(table1) == null || tables.get(table2) == null) throw new NonExistingTable();
        Table first = tables.get(table1);
        Table second = tables.get(table2);
        if(!first.getScheme().equals(second.getScheme())) return null;

        List<Row> firstList=new ArrayList(first.getRows().values());
        List<Row> firstListTemp=new ArrayList(first.getRows().values());
        List<Row> secondList=new ArrayList(second.getRows().values());
        firstList.removeAll(secondList);
        secondList.removeAll(firstListTemp);
        firstList.addAll(secondList);

        return firstList;
    }
    public List<Row> intersectionTable(String table1, String table2) throws NonExistingTable{
        if(tables.get(table1) == null || tables.get(table2) == null) throw new NonExistingTable();
        Table first = tables.get(table1);
        Table second = tables.get(table2);
        if(!first.getScheme().equals(second.getScheme())) return null;

        List<Row> firstList=new ArrayList(first.getRows().values());
        List<Row> firstListTemp=new ArrayList(first.getRows().values());
        List<Row> secondList=new ArrayList(second.getRows().values());
        firstList.removeAll(secondList);
        firstListTemp.removeAll(firstList);
        return firstListTemp;
    }

    @Override
    public String toString() {
        return "Database{" +
                "tables=" + tables +
                ", name='" + name + '\'' +
                '}';
    }
}
