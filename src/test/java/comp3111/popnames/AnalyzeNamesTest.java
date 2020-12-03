package comp3111.popnames;

import org.junit.Test;

import javafx.util.Pair;

import static org.junit.Assert.*;

public class AnalyzeNamesTest {
	
    @Test 
    public void testGetRankNotFound() {
    	AnalyzeNames a = new AnalyzeNames();
    	int i = a.getRank(2019, "XXX", "M");
		assertEquals(i, -1);
    }
    
    @Test 
    public void testGetRankMale() {
    	AnalyzeNames a = new AnalyzeNames();
    	int i = a.getRank(2019, "David", "M");
    	assertTrue(i==27);
    }
    
    @Test 
    public void testGetRankFemale() {
    	AnalyzeNames a = new AnalyzeNames();
    	int i = a.getRank(2019, "Desire", "F");
    	assertTrue(i==2192);
    }
    	
    @Test 
    public void testGetNameMale() {
    	AnalyzeNames a = new AnalyzeNames();
    	String name = a.getName(2019, 27, "M");
    	assertTrue(name.equals("David"));
    }
    
    @Test 
    public void testGetNameFemale() {
    	AnalyzeNames a = new AnalyzeNames();
    	String name = a.getName(2019, 2192, "F");
    	assertTrue(name.equals("Desire"));
    }
    
    @Test 
    public void testGetMaxRankInPeriodMale() {
    	AnalyzeNames a = new AnalyzeNames();
    	int result = a.getMaxRankInPeriod(2000, 2001, "M");
    	assertEquals(result, 12295);
    }
    
    @Test 
    public void testGetMaxRankInPeriodFemale() {
    	AnalyzeNames a = new AnalyzeNames();
    	int result = a.getMaxRankInPeriod(2000, 2001, "F");
    	assertEquals(result, 17966);
    }
    
    @Test
    public void testRecommendBabyNameRanked() {
    	AnalyzeNames a = new AnalyzeNames();
    	Pair<String, String> result = a.recommendBabyName("David", 1980, "Helen", 1982, 2000);
    	assertTrue(result.getKey().equals("Joshua") && result.getValue().equals("Madeleine"));
    }
    
    @Test
    public void testRecommendBabyNameNotRanked() {
    	AnalyzeNames a = new AnalyzeNames();
    	Pair<String, String> result = a.recommendBabyName("Nobita", 1980, "Shizuka", 1982, 2000);
    	assertTrue(result.getKey().equals("Jacob") && result.getValue().equals("Emily"));
    }
    
    @Test
    public void testRecommendedMateNameRanked() {
    	AnalyzeNames a = new AnalyzeNames();
    	String result = a.recommendedMateName("David", 1980, "M", "F", "Younger");
    	assertEquals(result, "Sarah");
    }
    
    @Test
    public void testRecommendedMateNameNotRanked() {
    	AnalyzeNames a = new AnalyzeNames();
    	String result = a.recommendedMateName("Suneo", 1980, "M", "F", "Younger");
    	assertEquals(result, "Jennifer");
    }
    
    @Test
    public void testCompatibleScoreRanked() {
    	AnalyzeNames a = new AnalyzeNames();
    	float score = a.compatibleScore("Bob", "M", 1949, "Mary", "F", 1950);
    	assertTrue(score - 0.01234567 < 1e-8 && score - 0.01234567 > -1e-8);
    }
    
    @Test
    public void testCompatibleScoreNotRanked() {
    	AnalyzeNames a = new AnalyzeNames();
    	float score = a.compatibleScore("Doraemon", "F", 1949, "Hellokitty", "M", 1950);
    	assertTrue(score == 1);
    }

}
