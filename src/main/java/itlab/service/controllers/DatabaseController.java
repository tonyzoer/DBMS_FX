package itlab.service.controllers;

import itlab.module.Database;
import itlab.module.exceptions.*;

import java.util.List;
import java.util.Map;

public interface DatabaseController {
  public abstract void createDatabase(String name);
  public abstract void deleteDatabase(String name) throws NonExistingFile;
  public abstract Database getDatabase(String name) throws NonExistingFile;
  public abstract void saveDatabase(String name) throws NonSaveableDBException;
  public abstract void loadDatabase(String name) throws NonExistingFile;
  public abstract void addTable(String databaseName, String tableName, Map<String, String> columns) ;
  public abstract Map<String , String> getTableRows(String databaseName, String tableName) throws NonExistingTable;
  public abstract Map<String,Map<String,String>> getTableRowsAsMap(String databaseName, String tableName) throws NonExistingTable;
  public abstract Map<String,String> getTableScheme(String databaseName, String tableName) throws NonExistingTable;
  public abstract void removeTable(String databaseName, String tableName) throws NonExistingTable;
  public abstract void renameTable(String databaseName, String tableNameCurrent, String tableNameNew) throws NonExistingTable;
  public abstract void addRowToTable(String databaseName, String tableName, Map<String, String> columnValues) throws NonExistingColumn, UnsupportedValueException,NonExistingTable;
  public abstract void removeRowFromTable(String databaseName, String tableName, String rowUUID) throws NonExistingRow, NonExistingTable;
  public abstract void updateRowInTable(String databaseName, String tableName, String rowUUID, Map<String, String> columnValues) throws NonExistingTable,NonExistingColumn,UnsupportedValueException;
  public abstract void tableIntersection(String table1, String table2, String newTableName) throws NonExistingTable;
  public abstract void tableDifference(String table1, String table2, String newTableName)throws NonExistingTable;
  public List<String> getAllDatabases();
  public List<String> getAllTables(String databaseName);
}
