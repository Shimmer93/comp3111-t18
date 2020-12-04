package comp3111.popnames;

import static org.junit.Assert.*;
import org.junit.Test;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit.ApplicationTest;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

public class JavaFXTest extends ApplicationTest {

	private Scene s;
	private TextArea t;
	
	@Override
	public void start(Stage stage) throws Exception {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/ui.fxml"));
   		VBox root = (VBox) loader.load();
   		Scene scene =  new Scene(root);
   		stage.setScene(scene);
   		stage.setTitle("Popular Names");
   		stage.show();
   		s = scene;
		t = (TextArea)s.lookup("#textAreaConsole");
	}

	
	@Test
	public void testButtonRankTrue() {	
		//clickOn("#tabTaskZero");
		clickOn("#buttonRankM");
		//sleep(1000);
		String s1 = t.getText();
		clickOn("#buttonRankM");
		//sleep(1000);
		String s2 = t.getText();
		assertTrue(s1.equals(s2));
	}
	
	
	@Test
	public void testButtonRankFalse() {	
		//clickOn("#tabTaskZero");
		clickOn("#buttonRankM");
		//sleep(1000);
		String s1 = t.getText();
		clickOn("#buttonRankF");
		//sleep(1000);
		String s2 = t.getText();
		assertFalse(s1.equals(s2));
	}
	
	public void testSummary() {
		clickOn("#buttonSummary");
		String s = t.getText();
		assertTrue(s.startsWith("Summary"));
	}
	
	@Test
	public void testTextAreaConsole() {	
		t.setText("David");
		String s = t.getText();
		assertTrue(s.equals("David"));
	}
	
	@Test
	public void testReportTopNamesSuccess() {
		clickOn("#tabReport1");
		clickOn("#buttonReport1");
		String s = t.getText();
		assertTrue(s.startsWith("Over"));
	}
	
	@Test
	public void testReportTopNamesFailure() {
		clickOn("#tabReport1");
		TextField tf = (TextField)s.lookup("#textFieldTopN1");
		tf.setText("wrong");
		clickOn("#buttonReport1");
		String s = t.getText();
		assertTrue(s.startsWith("Error"));
	}
	
	@Test
	public void testReportNamePopularitySuccess() {
		clickOn("#tabReport2");
		clickOn("#buttonReport2");
		String s = t.getText();
		assertTrue(s.startsWith("In"));
	}
	
	@Test
	public void testReportNamePopularityFailure() {
		clickOn("#tabReport2");
		TextField tf = (TextField)s.lookup("#textFieldFrom2");
		tf.setText("wrong");
		clickOn("#buttonReport2");
		String s = t.getText();
		assertTrue(s.startsWith("Error"));
	}
	
	@Test
	public void testReportNameTrendSuccess() {
		clickOn("#tabReport3");
		clickOn("#buttonReport3");
		String s = t.getText();
		assertTrue(s.startsWith("Over"));
	}
	
	@Test
	public void testReportNameTrendFailure() {
		clickOn("#tabReport3");
		TextField tf = (TextField)s.lookup("#textFieldTopN3");
		tf.setText("wrong");
		clickOn("#buttonReport3");
		String s = t.getText();
		assertTrue(s.startsWith("Error"));
	}
	
	@Test
	public void testRecommendBabyNameSuccess() {
		clickOn("#tabApp1");
		clickOn("#buttonGetRecom");
		String s = t.getText();
		assertTrue(s.startsWith("Boy"));
	}
	
	@Test
	public void testRecommendBabyNameFailure() {
		clickOn("#tabApp1");
		TextField tf = (TextField)s.lookup("#textFieldDadYOB");
		tf.setText("wrong");
		clickOn("#buttonGetRecom");
		String s = t.getText();
		assertTrue(s.startsWith("Error"));
	}
	
	@Test
	public void testGetRecommendedMateNameSuccess() {
		clickOn("#tabApp2");
		clickOn("#buttonRecommendation");
		String s = t.getText();
		assertTrue(s.startsWith("Recom"));
	}
	
	@Test
	public void testGetRecommendedMateNameFailure() {
		clickOn("#tabApp2");
		TextField tf = (TextField)s.lookup("#textFieldYOB");
		tf.setText("wrong");
		clickOn("#buttonRecommendation");
		String s = t.getText();
		assertTrue(s.startsWith("Error"));
	}
	
	@Test
	public void testGetCompalibaleScoreSuccess() {
		clickOn("#tabApp3");
		clickOn("#buttonGetScore");
		String s = t.getText();
		assertTrue(s.startsWith("The"));
	}
	
	@Test
	public void testGetCompalibaleScoreFailure() {
		clickOn("#tabApp3");
		TextField tf = (TextField)s.lookup("#textFieldiYOB");
		tf.setText("wrong");
		clickOn("#buttonGetScore");
		String s = t.getText();
		assertTrue(s.startsWith("Error"));
	}
}
