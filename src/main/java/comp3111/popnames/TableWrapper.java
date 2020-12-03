package comp3111.popnames;

import java.util.List;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;

public class TableWrapper {
	private TableView<List<String>> table;
	private final ObservableList<List<String>> tableItems; 
	
	public TableWrapper(List<String> columnNames) {
		table = new TableView<List<String>>();
		tableItems = FXCollections.observableArrayList();
		table.setItems(tableItems);
		
		for (int i=0; i<columnNames.size(); i++) {
   			final int index = i;
   			TableColumn<List<String>, String> tableColumn = new TableColumn<List<String>, String>(columnNames.get(i));
   			tableColumn.setCellValueFactory(new Callback<CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
   			    @Override
   			    public ObservableValue<String> call(CellDataFeatures<List<String>, String> data) {
   			        return new ReadOnlyStringWrapper(data.getValue().get(index)) ;
   			    }
   			});
   			table.getColumns().add(tableColumn);
   		}
	}
	
	public void addRow(List<String> row) {
		tableItems.add(row);
	}
	
	public TableView<List<String>> getTable() {
		return table;
	}
}
