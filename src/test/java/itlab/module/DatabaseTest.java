package itlab.module;

import itlab.module.exceptions.NonExistingTable;
import itlab.module.exceptions.TableAlreadyExsists;
import itlab.module.types.Types;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by mafio on 11.10.2017.
 */
public class DatabaseTest {
    @Test
    public void tableDifference_ReturnEmptyList() throws TableAlreadyExsists, NonExistingTable {
        Database db = new Database("testDB");
        Map<String, Types> scheme = new HashMap<>() {
            {
                put("type", Types.STRING);
                put("type2", Types.STRING);
            }
        };
        db.createTable("Table1", new Scheme(scheme));
        db.createTable("Table2", new Scheme(scheme));
        List<Row> result = db.substractTables("Table1", "Table2");
        Assert.assertTrue(result.isEmpty());
    }

    @Test
    public void substractTables() throws Exception {
        Database db = new Database("testDB");
        Map<String, Types> scheme = new HashMap<>() {
            {
                put("type", Types.STRING);
                put("type2", Types.STRING);
            }
        };
        db.createTable("Table1", new Scheme(scheme));
        db.createTable("Table2", new Scheme(scheme));
        Map<String, String> row1 = new HashMap<>() {
            {
                put("type", "Value1");
                put("type2", "Value2");
            }
        };
        Map<String, String> row2 = new HashMap<>() {
            {
                put("type", "Value3");
                put("type2", "Value4");
            }
        };
        Map<String, String> row3 = new HashMap<>() {
            {
                put("type", "Value5");
                put("type2", "Value6");
            }
        };

        db.getTable("Table1").addRow(row1);
        db.getTable("Table2").addRow(row1);
        db.getTable("Table2").addRow(row3);
        db.getTable("Table1").addRow(row2);
        List<Row> result = db.substractTables("Table1", "Table2");
        Assert.assertTrue(result.size() == 2);
    }

    @Test
    public void intersectionTable() throws Exception {
        Database db = new Database("testDB");
        Map<String, Types> scheme = new HashMap<>() {
            {
                put("type", Types.STRING);
                put("type2", Types.STRING);
            }
        };
        db.createTable("Table1", new Scheme(scheme));
        db.createTable("Table2", new Scheme(scheme));
        Map<String, String> row1 = new HashMap<>() {
            {
                put("type", "Value1");
                put("type2", "Value2");
            }
        };
        Map<String, String> row2 = new HashMap<>() {
            {
                put("type", "Value3");
                put("type2", "Value4");
            }
        };
        Map<String, String> row3 = new HashMap<>() {
            {
                put("type", "Value5");
                put("type2", "Value6");
            }
        };

        db.getTable("Table1").addRow(row1);
        db.getTable("Table2").addRow(row1);
        db.getTable("Table2").addRow(row3);
        db.getTable("Table1").addRow(row2);
        List<Row> result = db.intersectionTable("Table1", "Table2");
        Assert.assertTrue(result.size() == 1);
    }

}