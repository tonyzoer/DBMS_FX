package GUI;

import itlab.module.exceptions.NonExistingTable;
import itlab.module.exceptions.UnsupportedValueException;
import itlab.service.controllers.DatabaseController;
import itlab.service.controllers.DatabaseControllerDirect;
import itlab.view.IView;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import javafx.scene.control.TableColumn.CellDataFeatures;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class Controller implements Initializable, IView {
    String currentDatabase = "";
    String currentTable = "";

    @FXML
    private TextField new_database_name;
    @FXML
    private ListView<String> all_databases_list;
    @FXML
    private ListView<String> tables_list;
    @FXML
    private TableView table;

    private ObservableList<String> tables;
    private ObservableList<String> databases;
    private ObservableList<ObservableList> data;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        databases = FXCollections.observableArrayList();
        tables = FXCollections.observableArrayList();
        data = FXCollections.observableArrayList();
        all_databases_list.setItems(databases);
        tables_list.setItems(tables);
        showAllDatabases();
    }

    @FXML
    private void createDatabase(ActionEvent event) {
        if (!new_database_name.getText().equals(""))
            createDatabase(new_database_name.getText());
        else return;
        databases.add(new_database_name.getText());
        all_databases_list.setItems(databases);
        selectDatabase(new_database_name.getText());
        saveDatabase(currentDatabase);
    }

    @FXML
    private void saveDatabase(ActionEvent event) {
        saveDatabase(currentDatabase);
    }

    @FXML
    private void selectDatabase(ActionEvent event) {
        currentDatabase = all_databases_list.getSelectionModel().getSelectedItem();
        loadDatabase(currentDatabase);
        showAllTables(currentDatabase);
        currentTable = "";
    }

    @FXML
    private void deleteDatabase(ActionEvent event) {
        loadDatabase(all_databases_list.getSelectionModel().getSelectedItem());
        deleteDatabase(all_databases_list.getSelectionModel().getSelectedItem());
        databases.remove(all_databases_list.getSelectionModel().getSelectedItem());
//        tables_list.setItems(tables);
    }

    @FXML
    private void selectTable(ActionEvent event) {
        currentTable = tables_list.getSelectionModel().getSelectedItem();
        showTable(currentDatabase, currentTable);
    }

    @FXML
    private void deleteTable(ActionEvent event) {
        removeTable(currentDatabase, tables_list.getSelectionModel().getSelectedItem());
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
            table.getItems().clear();
            table.getColumns().clear();
            List<String> tableSchemeColumnNames = new ArrayList<String>(DatabaseControllerDirect.getInstance().getTableScheme(databaseName, tableName).keySet());
            TableColumn uuidCol=new TableColumn("UUID");
            uuidCol.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){
                public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                    return new SimpleStringProperty(param.getValue().get(tableSchemeColumnNames.size()).toString());
                }
            });
            table.getColumns().add(uuidCol);
            for (int i=0;i<tableSchemeColumnNames.size();i++
                    ) {
                TableColumn column=new TableColumn(tableSchemeColumnNames.get(i));
                final int j=i;
                column.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });

                table.getColumns().addAll(column);
            }

            Map<String,Map<String,String>> rows=DatabaseControllerDirect.getInstance().getTableRowsAsMap(databaseName,tableName);
            for (Map.Entry<String, Map<String, String>> entry:rows.entrySet()
            ){
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 0; i < tableSchemeColumnNames.size(); i++) {
                    row.add(entry.getValue().get(tableSchemeColumnNames.get(i)));
                }
                row.add(entry.getKey());
                data.add(row);

//                GenericRowModel row=new GenericRowModel(values);
//                table.setItems(this.rows);
            }
            table.setItems(data);

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
        List<String> tablesList = DatabaseControllerDirect.getInstance().getAllTables(databaseName);
        tables.addAll(tablesList);

    }

    @Override
    public void showAllDatabases() {
        databases.addAll(DatabaseControllerDirect.getInstance().getAllDatabases());
    }

    @Override
    public void showAllExsistingTypes() {

    }

    @Override
    public void help() {

    }


    private void selectDatabase(String name) {
        currentDatabase = name;
    }

}
