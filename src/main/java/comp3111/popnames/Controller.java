/**
 * Building on the sample skeleton for 'ui.fxml' COntroller Class generated by SceneBuilder 
 */
package comp3111.popnames;

import java.util.*;
import java.util.Map.Entry;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Pair;
import javafx.scene.control.RadioButton;

public class Controller {

    @FXML
    private Tab tabTaskZero;

    @FXML
    private TextField textfieldNameF;

    @FXML
    private TextField textfieldYear;

    @FXML
    private Button buttonRankM;

    @FXML
    private TextField textfieldNameM;

    @FXML
    private Button buttonRankF;

    @FXML
    private Button buttonTopM;

    @FXML
    private Button buttonTopF;

    @FXML
    private Button buttonSummary;
    
    @FXML
    private Tab tabReport1;

    @FXML
    private TextField textFieldTopN;

    @FXML
    private RadioButton radioMale1;

    @FXML
    private RadioButton radioFemale1;

    @FXML
    private TextField textFieldTo1;

    @FXML
    private TextField textFieldFrom1;
    
    @FXML
    private ToggleGroup T1;

    @FXML
    private Tab tabReport2;

    @FXML
    private ToggleGroup T11;

    @FXML
    private Tab tabReport3;
    
    @FXML
    private TextField textFieldTopN3;
    
    @FXML
    private RadioButton radioMale3;

    @FXML
    private RadioButton radioFemale3;

    @FXML
    private TextField textFieldTo3;

    @FXML
    private TextField textFieldFrom3;

    @FXML
    private ToggleGroup T111;

    @FXML
    private Tab tabApp1;
    
    @FXML
    private TextField textFieldDadName;

    @FXML
    private TextField textFieldMomName;

    @FXML
    private TextField textFieldDadYOB;

    @FXML
    private TextField textFieldMomYOB;

    @FXML
    private RadioButton radioWithVin;

    @FXML
    private ToggleGroup isWithVin;

    @FXML
    private RadioButton radioWithoutVin;

    @FXML
    private TextField textFieldVin;

    @FXML
    private Button buttonGetRecom;

    @FXML
    private Tab tabApp2;

    @FXML
    private Tab tabApp3;
    
    @FXML
    private TextField textFieldiName;

    @FXML
    private RadioButton radioiMale;

    @FXML
    private RadioButton radioiFemale;
    
    @FXML
    private ToggleGroup iGender;

    @FXML
    private TextField textFieldiYOB;

    @FXML
    private TextField textFieldiNameMate;

    @FXML
    private RadioButton radioiMaleMate;

    @FXML
    private RadioButton radioiFemaleMate;
    
    @FXML
    private ToggleGroup iGenderMate;

    @FXML
    private RadioButton radioYounger;
    
    @FXML
    private RadioButton radioOlder;
    
    @FXML
    private ToggleGroup iPreference;



    @FXML
    private Button buttonGetScore;


    @FXML
    private TextArea textAreaConsole;
    
    
    boolean verifyInputNotEmpty(TextInputControl ... cs) {
    	boolean result = true;
    	for (TextInputControl c : cs) {
    		if (c.getText().trim().isEmpty()) {
    			result = false;
    			break;
    		}
    	}
    	return result;
    }
    
    boolean verifyYearInRange(int ... is) {
    	boolean result = true;
    	for (int i : is) {
    		if (i > 2019 || i < 1880) {
    			result = false;
    			break;
    		}
    	}
    	return result;
    }

    void displayTable(TableView<?> table, String oReport) {
    	try {
	    	FXMLLoader loader = new FXMLLoader();
	    	loader.setLocation(getClass().getResource("/table.fxml"));
	   		Pane root = (Pane) loader.load();
			root.getChildren().add(table);
	   		Scene scene = new Scene(root);
	   		Stage stage = new Stage();
	   		stage.setScene(scene);
	   		stage.setTitle("Report");
	   		stage.show();
    	} catch(Exception e) {
    		oReport += "Error: Cannot Display Table. Please contact the developer if this message is seen.";
    	}
    }
    
    /**
     *  Task Zero
     *  To be triggered by the "Summary" button on the Task Zero Tab 
     *  
     */
    @FXML
    void doSummary() {
    	int year = Integer.parseInt(textfieldYear.getText());
    	String oReport = AnalyzeNames.getSummary(year);
    	textAreaConsole.setText(oReport);
    }

  
    /**
     *  Task Zero
     *  To be triggered by the "Rank (female)" button on the Task Zero Tab
     *  
     */
    @FXML
    void doRankF() {
    	String oReport = "";
    	String iNameF = textfieldNameF.getText();
    	int iYear = Integer.parseInt(textfieldYear.getText());
    	int oRank = AnalyzeNames.getRank(iYear, iNameF, "F");
    	if (oRank == -1)
    		oReport = String.format("The name %s (female) has not been ranked in the year %d.\n", iNameF, iYear);
    	else
    		oReport = String.format("Rank of %s (female) in year %d is #%d.\n", iNameF, iYear, oRank);
    	textAreaConsole.setText(oReport);
    }

  
    /**
     *  Task Zero
     *  To be triggered by the "Rank (male)" button on the Task Zero Tab
     *  
     */
    @FXML
    void doRankM() {
    	String oReport = "";
    	String iNameM = textfieldNameM.getText();
    	int iYear = Integer.parseInt(textfieldYear.getText());
    	int oRank = AnalyzeNames.getRank(iYear, iNameM, "M");
    	if (oRank == -1)
    		oReport = String.format("The name %s (male) has not been ranked in the year %d.\n", iNameM, iYear);
    	else
    		oReport = String.format("Rank of %s (male) in year %d is #%d.\n", iNameM, iYear, oRank);
    	textAreaConsole.setText(oReport);
    }


    /**
     *  Task Zero
     *  To be triggered by the "Top 5 (female)" button on the Task Zero Tab
     *  
     */
    @FXML
    void doTopF() {
    	String oReport = "";
    	final int topN = 5;
    	int iYear = Integer.parseInt(textfieldYear.getText());
    	oReport = String.format("Top %d most popular names (female) in the year %d:\n", topN, iYear);
    	for (int i=1; i<=topN; i++)
    		oReport += String.format("#%d: %s\n", i, AnalyzeNames.getName(iYear, i, "F"));
    	textAreaConsole.setText(oReport);
    }


    /**
     *  Task Zero
     *  To be triggered by the "Top 5 (male)" button on the Task Zero Tab
     *  
     */
    @FXML
    void doTopM() {
    	String oReport = "";
    	final int topN = 5;
    	int iYear = Integer.parseInt(textfieldYear.getText());
    	oReport = String.format("Top %d most popular names (male) in the year %d:\n", topN, iYear);
    	for (int i=1; i<=topN; i++)
    		oReport += String.format("#%d: %s\n", i, AnalyzeNames.getName(iYear, i, "M"));
    	textAreaConsole.setText(oReport);
    }
    
    /**
     * Task One
     * To be triggered by the "Report" button on the Task One (Reporting 1) Tab
     * 
     */
    @FXML
    void reportTopNames() {
    	String oReport = "";
    	
    	if (verifyInputNotEmpty(textFieldTopN, textFieldFrom1, textFieldTo1)) {
	    	try {
	    		// Initialize values
		    	int topN = Integer.parseInt(textFieldTopN.getText());
		    	RadioButton selected = (RadioButton) T1.getSelectedToggle();
		    	String gender = Character.toString(selected.getText().charAt(0));
		    	int yearFrom = Integer.parseInt(textFieldFrom1.getText());
		    	int yearTo = Integer.parseInt(textFieldTo1.getText());
		    	
		    	if (verifyYearInRange(yearFrom, yearTo)) {
		    		// Initialize the table
		    		List<String> tableColumnNames = new ArrayList<>();
		    		for (int i=0; i<topN; i++)
		    			tableColumnNames.add(i == 0 ? "Year" : String.format("Top %d", i));
		    		TableWrapper table = new TableWrapper(tableColumnNames);
			   		
			   		// Deal with data
			   		Map<String, Integer> nameCounts = new TreeMap<String, Integer>();
					for (int year=yearFrom; year<=yearTo; year++) {
						// Count the times of top spots
						String name = AnalyzeNames.getName(year, 1, gender);
						if (nameCounts.containsKey(name))
							nameCounts.replace(name, nameCounts.get(name)+1);
						else
							nameCounts.put(name, 1);
						
						// add items to the table
						List<String> names = new ArrayList<>();
						names.add(Integer.toString(year));
						for (int i=1; i<=topN; i++) {
							String namei = AnalyzeNames.getName(year, i, gender);
							names.add(namei);
						}
						table.addRow(names);
					}
					
					// display the table
					displayTable(table.getTable(), oReport);
			    	
					// Compute the name with most top spots
			    	Entry<String, Integer> maxNameCount = Collections.max(nameCounts.entrySet(), new Comparator<Entry<String, Integer>>() {
			            public int compare(Entry<String, Integer> e1, Entry<String, Integer> e2) {
			                return e1.getValue()
			                    .compareTo(e2.getValue());
			            }
			        });
			    	
			    	oReport += String.format("Over the period %d to %d, %s for %s has hold the top spot most often for a total of %d times.\n", 
			    			yearFrom, yearTo, maxNameCount.getKey(), gender.toLowerCase(), maxNameCount.getValue());
		    	} else {
		    		oReport += "Error: Year Out of Range. Please check your input years.\n";
		    	}
		    } catch (Exception e) {
				oReport += "Error: Invalid Input. Please check your input values.\n";
			}
		} else {
			oReport += "Error: Empty Input. Please fill in all the text fields.\n";
		}
    	
    	// Show summary and error messages in the console
    	textAreaConsole.setText(oReport);
    }
    
    /**
     * Task Four
     * To be triggered by the "Get Recommendation" button on the Task Four (Application 4) Tab
     * 
     */
    @FXML
    void disableVin() {
    	textFieldVin.setDisable(true);
    }
    
    @FXML
    void enableVin() {
    	textFieldVin.setDisable(false);
    }
    
    @FXML
    void getRecommendedBabyName() {
    	String oReport = "";
    	
    	if (verifyInputNotEmpty(textFieldDadName, textFieldDadYOB, textFieldMomName, textFieldMomYOB)
    			&& (radioWithoutVin.isSelected() || verifyInputNotEmpty(textFieldVin))) {
			try {
				// Initialize values
				String dadName = textFieldDadName.getText();
				String momName = textFieldMomName.getText();
				int dadYOB = Integer.parseInt(textFieldDadYOB.getText());
				int momYOB = Integer.parseInt(textFieldMomYOB.getText());	
				int vintageYear;
				if (radioWithVin.isSelected())
					vintageYear = Integer.parseInt(textFieldVin.getText());
				else
					vintageYear = 2019;
				
				if (verifyYearInRange(dadYOB, momYOB, vintageYear)) {
					// Compute recommended names
					Pair<String, String> namePair = AnalyzeNames.recommendBabyName(dadName, dadYOB, momName, momYOB, vintageYear);
					String boyName = namePair.getKey();
					String girlName = namePair.getValue();
					
					oReport = String.format("Boy name: %s\nGirl name: %s\n", boyName, girlName);
				} else {
					oReport += "Error: Year Out of Range. Please check your input years.\n";
				}
			} catch (Exception e) {
				oReport += "Error: Invalid Input. Please check your input values.\n";
			}
    	} else {
    		oReport += "Error: Empty Input. Please fill in all the text fields.\n";
    	}
    	
    	textAreaConsole.setText(oReport);
    }
    /**
     * Task Three
     * To be triggered by the "Report" button on the Task Three (Reporting 3) Tab
     * 
     */
    @FXML
    void reportNameTrend() {
    	String oReport = "";
    	
    	if (verifyInputNotEmpty(textFieldFrom3, textFieldTo3)) {
	    	try {
	    		// Initialize values
	    		int topN = Integer.parseInt(textFieldTopN.getText());
		    	RadioButton selected = (RadioButton) T111.getSelectedToggle();
		    	String gender = selected.getText();
		    	int yearFrom = Integer.parseInt(textFieldFrom3.getText());
		    	int yearTo = Integer.parseInt(textFieldTo3.getText());
		    	
		    	if (verifyYearInRange(yearFrom, yearTo) && yearFrom != yearTo) {
		    		// Initialize the table
		    		TableView<List<String>> table = new TableView<>();
		  
		    		for (int i=0; i<4; i++) {
		    			final int index = i;
		    			String colName = "";
			   			switch(i){
			   			case 0: colName = "Name";
			   			break;
			   			case 1: colName = "Lowest Rank\n[in year]";
			   			break;
			   			case 2: colName = "Highest Rank\n [in year]";
			   			break;
			   			case 3: colName = "Trend";
			   			break;
			   			}
			   				
			   			TableColumn<List<String>, String> tableColumn = new TableColumn<List<String>, String>(colName);
			   			tableColumn.setCellValueFactory(new Callback<CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
			   			    @Override
			   			    public ObservableValue<String> call(CellDataFeatures<List<String>, String> data) {
			   			        return new ReadOnlyStringWrapper(data.getValue().get(index)) ;
			   			    }
			   			});
			   			table.getColumns().add(tableColumn);
			   		}
			   		
			   		// Deal with data
			   		
			   		final ObservableList<List<String>> tableItems = FXCollections.observableArrayList();
			   		List<String> names = new ArrayList<>();
			   		List<String> finalNames = new ArrayList<>();
			   		// initialize the name list of yearFrom to go through
					for (int i=1; i<=topN; i++) {
						String namei = AnalyzeNames.getName(yearFrom, i, Character.toString(gender.charAt(0)));
						names.add(namei);
					}
					// loop through all years for each name in name list
					for (int i = 0; i < names.size(); i++) {
						String name = names.get(i);
						int hiRank = i + 1;
						int loRank = i + 1;
						int hiYear = yearFrom;
						int loYear = yearFrom;		
						boolean inTopN = true;
						for (int year=yearFrom + 1; year<=yearTo; year++) {
							int rank = AnalyzeNames.getRank(year, name, Character.toString(gender.charAt(0)));
							// in each year, check whether the names rank is out of TopN 
							if (rank > topN || rank == -1) {
								inTopN = false;
								break;
							}
							// update the highest/lowest rank/year
							if (rank > loRank) {
								loRank = rank;
								loYear = year;
							}
							if (rank < hiRank) {
								hiRank = rank;
								hiYear = year;
							}
							
							
						}
						if (!inTopN)
							continue;
						// add items to the table
						List<String> row = new ArrayList<>();
						row.add(name);
						row.add(Integer.toString(loRank)+"\n"+"["+ Integer.toString(loYear)+"]");
						row.add(Integer.toString(hiRank)+"\n"+"["+ Integer.toString(hiYear)+"]");
						if (hiYear == loYear)
							row.add("FLAT");
						else if(hiYear>loYear)
							row.add("UP");
						else
							row.add("DOWN");
								
						tableItems.add(row);
						finalNames.add(name);
					}
					table.setItems(tableItems);
					
					// display the table
					displayTable(table, oReport);
			    	
			    	
			    	oReport += String.format("Over the period %d to %d, the %d names below maintained a high level of popularity within Top %d: \n", 
			    			yearFrom, yearTo,finalNames.size(), topN) ;
			    	for (String str : finalNames ) {
			    		oReport += str;
			    		oReport += "\n";
			    	}
			    		
		    	} else {
		    		oReport += "Error: Year Out of Range. Please check your input years.\n";
		    	}
		    } catch (Exception e) {
				oReport += "Error: Invalid Input. Please check your input values.\n";
			}
		} else {
			oReport += "Error: Empty Input. Please fill in all the text fields.\n";
		}
    	
    	// Show summary and error messages in the console
    	textAreaConsole.setText(oReport);
    }
    /**
     * Task Six
     * To be triggered by the "Get Recommendation" button on the Task Four (Application 4) Tab
     * 
     */
    
    @FXML
    void getCompalibaleScore() {
    	String oReport = "";
    	
    	if (verifyInputNotEmpty(textFieldiName, textFieldiYOB, textFieldiNameMate)) {
			try {
				// Initialize values
				String iName = textFieldiName.getText();
				String iNameMate = textFieldiNameMate.getText();
				int iYOB = Integer.parseInt(textFieldiYOB.getText());	
				RadioButton iGenderSelected = (RadioButton) iGender.getSelectedToggle();
		    	String igender = iGenderSelected.getText();
		    	RadioButton iGenderMateSelected = (RadioButton) iGenderMate.getSelectedToggle();
		    	String igenderMate = iGenderMateSelected.getText();
				RadioButton iPreferenceSelected = (RadioButton) iPreference.getSelectedToggle();
		    	String ipreference = iPreferenceSelected.getText();
		    	int oYOB = iYOB;
			    if (ipreference.equals("Younger"))
			    	oYOB = iYOB + 1;
				else if (ipreference.equals("Older"))
					oYOB = iYOB - 1;
			    if (verifyYearInRange(iYOB)) {
			    	if (verifyYearInRange(oYOB)) {
						// Compute score
						float score = AnalyzeNames.compatibleScore(iName, Character.toString(igender.charAt(0)) , iYOB,iNameMate , Character.toString(igenderMate.charAt(0)), oYOB);

						oReport = String.format("The compatible score for %s and %s is %f %%.", iName,iNameMate,score*100);
						
					} 
			    	else {
						oReport += "Error: Your preferred mate age is not supported. Please check your input year and preference.\n";
						}
				}
			    else {
						oReport +="Error: Year Out of Range. Please check your input year.\n" ;
			    }
			} catch (Exception e) {
				oReport += "Error: Invalid Input. Please check your input values.\n";
			}
    	} else {
    		oReport += "Error: Empty Input. Please fill in all the text fields.\n";
    	}
    	
    	textAreaConsole.setText(oReport);
    }
    
}


