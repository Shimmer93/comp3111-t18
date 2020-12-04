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
/** 
 * This class aids with the display of the result.
 * */
public class TableWrapper {
	private TableView<List<String>> table;
	private final ObservableList<List<String>> tableItems; 
	/** 
	 * This is the constructor that initialize the columns of the table
	 * @param  columnNames The list of column names to be added to the table.
	 * */
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
	/** 
	 * This function helps to add a row to the table
	 * @param row The row to be added to the table
	 * */
	public void addRow(List<String> row) {
		tableItems.add(row);
	}
	/** 
	 * This function returns the table view 
	 * */
	public TableView<List<String>> getTable() {
		return table;
	}
}
