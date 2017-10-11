package itlab.view;

import java.util.Map;

public interface IView {
    public abstract void createDatabase(String name);
    public abstract void deleteDatabase(String name);
    public abstract void showDatabase(String name);
    public abstract void saveDatabase(String name);
    public abstract void loadDatabase(String name) ;
    public abstract void createTable(String databaseName, String tableName, Map<String, String> collumns) ;
    public abstract void showTable(String databaseName, String tableName) ;
    public abstract void showTableScheme(String databaseName, String tableName);
    public abstract void removeTable(String databaseName, String tableName);
    public abstract void renameTable(String databaseName, String tableNameCurrent, String tableNameNew);
    public abstract void addRowToTable(String databaseName, String tableName, Map<String, String> collumnValues);
    public abstract void removeRowFromTable(String databaseName, String tableName, String rowUUID);
    public abstract void updateRowInTable(String databaseName, String tableName, String rowUUID, Map<String, String> collumnValues);
    public abstract void tableIntersection(String table1, String table2, String newTableName);
    public abstract void tableDifference(String table1, String table2, String newTableName);
    public abstract void showAllTables(String databaseName);
    public void showAllDatabases();
    public void showAllExsistingTypes();
    public void help();
}
