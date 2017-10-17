package GUI;

import itlab.module.Table;
import itlab.module.types.Types;
import itlab.service.controllers.DatabaseControllerDirect;
import itlab.view.IView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by mafio on 16.10.2017.
 */
public class NewTable implements Initializable {
    @FXML
    private TextField new_table_name;
    @FXML
    private TableView<Field> scheme;
    @FXML
    private ListView types_list;

    private IView parentView;
    private String currentDB;
    private ObservableList<Field> fields;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fields = FXCollections.observableArrayList();

        TableColumn<Field, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nameColumn.setOnEditCommit(event -> ((Field) event.getTableView().getItems().get(event.getTablePosition().getRow())).setName(event.getNewValue()));
        TableColumn<Field, String> typeColumn = new TableColumn<>("Type");
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        typeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        typeColumn.setOnEditCommit(event -> ((Field) event.getTableView().getItems().get(event.getTablePosition().getRow())).setType(event.getNewValue()));
        scheme.setEditable(true);
        scheme.getItems().clear();
        scheme.getColumns().clear();
        scheme.getColumns().addAll(nameColumn, typeColumn);
        scheme.setItems(fields);

        types_list.setItems(FXCollections.observableArrayList(DatabaseControllerDirect.getInstance().getAllTypes()));
    }

    public void setParentView(IView parentView) {
        this.parentView = parentView;
    }

    public void setCurrentDB(String currentDB) {
        this.currentDB = currentDB;
    }

    @FXML
    public void addColumn(ActionEvent event) {
        fields.add(new Field());
    }

    @FXML
    private void createTable(ActionEvent event) {
        Map<String, String> scheme_map = new HashMap<>();
        for (Field f : fields
                ) {
            scheme_map.put(f.getName(), f.getType().toUpperCase());
        }
        parentView.createTable(currentDB, new_table_name.getText(), scheme_map);
        Stage stage = (Stage) scheme.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
}
