package itlab.view;
import itlab.module.exceptions.NonExistingTable;
import itlab.module.exceptions.UnsupportedValueException;
import itlab.service.controllers.DatabaseControllerDirect;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ConsoleView implements IView {
    static ConsoleView cv = new ConsoleView();
    String db;

    public static void main(String[] args) {
        cv.help();
        cv.processInput();
        return;
    }

    private void processInput() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        switch (input) {
            case "ShowAll":
                showAllDatabases();
                break;
            case "CreateDB":
                db = readString("Enter name of new Database");
                createDatabase(db);
                break;
            case "DeleteDB":
                deleteDatabase(readString("Enter name of Database"));
                break;
            case "ShowDB":
                showDatabase(readString("Enter name of Database"));
                break;
            case "SaveDB":
                saveDatabase(readString("Enter name of Database"));
                break;
            case "LoadDB":
                db = readString("Enter name of Database");
                loadDatabase(db);
                break;
            case "CreateTable":
                createTable(db, readString("Enter name of new Table"),
                        mapFromString(readString("Enter scheme in format 'name:TYPE;...;name:TYPE'")));
                break;
            case "ShowTable":
                showTable(db, readString("Enter name of Table"));
                break;
            case "ShowScheme":
                showTableScheme(db, readString("Enter name of Table"));
                break;
            case "AddRow":
                addRowToTable(readString("Enter name of Database"),
                        readString("Enter name of Table"),
                        mapFromString(readString("Enter Row in format 'columnName:Value;...;columnName:Value'")));
                break;
            case "RemoveRow":
                removeRowFromTable(db, readString("Enter name of new Table"), readString("Enter UUID of ROW"));
                break;
            case "UpdateRow":
                updateRowInTable(db, readString("Enter name of Table"), readString("Enter UUID of ROW"),
                        mapFromString(readString("Enter Row in format 'columnName:Value;...;columnName:Value'")));
                break;
            case "TableIntersection":
                tableIntersection(readString("Enter name of Table 1"),
                        readString("Enter name of Table 2"), readString("Enter name of new Table"));
                break;
            case "TableDifference":
                tableDifference(readString("Enter name of Table 1"),
                        readString("Enter name of Table 2"), readString("Enter name of new Table"));
                break;
            case "SelectDB":
                break;
            case "SelectTable":
                break;
        }
        processInput();
    }

    @Override
    public void createDatabase(String name) {
        DatabaseControllerDirect.getInstance().createDatabase(name);
    }

    @Override
    public void deleteDatabase(String name) {
        DatabaseControllerDirect.getInstance().deleteDatabase(name);
    }

    @Override
    public void showDatabase(String name) {
        //TODO make it beautiful
        System.out.println(DatabaseControllerDirect.getInstance().getDatabase(name));
    }

    @Override
    public void saveDatabase(String name) {
        DatabaseControllerDirect.getInstance().saveDatabase(name);
    }

    @Override
    public void loadDatabase(String name) {
        DatabaseControllerDirect.getInstance().loadDatabase(name);
    }

    @Override
    public void createTable(String databaseName, String tableName, Map<String, String> columns) {
        DatabaseControllerDirect.getInstance().addTable(databaseName, tableName, columns);
    }

    @Override
    public void showTable(String databaseName, String tableName) {
        //TODO make it beautiful
        try {
            System.out.println(DatabaseControllerDirect.getInstance().getTableRows(databaseName, tableName));
        } catch (NonExistingTable nonExistingTable) {
            nonExistingTable.printStackTrace();
        }
    }

    @Override
    public void showTableScheme(String databaseName, String tableName) {
        //TODO make it beautiful
        try {
            System.out.println(DatabaseControllerDirect.getInstance().getTableScheme(databaseName, tableName));
        } catch (NonExistingTable nonExistingTable) {
            nonExistingTable.printStackTrace();
        }
    }

    @Override
    public void removeTable(String databaseName, String tableName) {
        DatabaseControllerDirect.getInstance().removeTable(databaseName, tableName);
    }

    @Override
    public void renameTable(String databaseName, String tableNameCurrent, String tableNameNew) {
        renameTable(databaseName, tableNameCurrent, tableNameNew);
    }

    @Override
    public void addRowToTable(String databaseName, String tableName, Map<String, String> collumnValues) {
        try {
            DatabaseControllerDirect.getInstance().addRowToTable(databaseName, tableName, collumnValues);
        } catch (UnsupportedValueException e) {
            e.printStackTrace();
        } catch (NonExistingTable nonExistingTable) {
            nonExistingTable.printStackTrace();
        }
    }

    @Override
    public void removeRowFromTable(String databaseName, String tableName, String rowUUID) {
        try {
            DatabaseControllerDirect.getInstance().removeRowFromTable(databaseName, tableName, rowUUID);
        } catch (NonExistingTable nonExistingTable) {
            nonExistingTable.printStackTrace();
        }
    }

    @Override
    public void updateRowInTable(String databaseName, String tableName, String rowUUID, Map<String, String> collumnValues) {
        try {
            DatabaseControllerDirect.getInstance().updateRowInTable(databaseName, tableName, rowUUID, collumnValues);
        } catch (UnsupportedValueException e) {
            e.printStackTrace();
        } catch (NonExistingTable nonExistingTable) {
            nonExistingTable.printStackTrace();
        }
    }

    @Override
    public void tableIntersection(String table1, String table2, String newTableName) {

    }

    @Override
    public void tableDifference(String table1, String table2, String newTableName) {

    }

    @Override
    public void showAllTables(String databaseName) {

    }

    @Override
    public void showAllDatabases() {

    }

    @Override
    public void showAllExsistingTypes() {

    }

    @Override
    public void help() {

    }

    public void selectDB(String name) {
    }

    public void unselectDB() {
    }

    public void selectTable(String name) {
    }

    public void unselectTable() {
    }

    private String readString(String s) {
        System.out.println(s);
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return input;
    }

    private Map<String, String> mapFromString(String s) {
        Map<String, String> map = new HashMap();
        String pairs[] = s.split(";");
        for (String pair : pairs
                ) {
            String curpair[] = pair.split(":");
            map.put(curpair[0], curpair[1]);
        }
        return map;
    }

    ;
}
