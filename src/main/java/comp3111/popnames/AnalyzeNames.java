package comp3111.popnames;

import org.apache.commons.csv.*;
import edu.duke.*;
import javafx.util.Pair;
import java.util.*;
import java.util.Map.Entry;

public class AnalyzeNames {
	
	final public static int MIN_YEAR = 1800;
	final public static int MAX_YEAR = 2019;
			
	public static CSVParser getFileParser(int year) {
     FileResource fr = new FileResource(String.format("dataset/yob%s.csv", year));
     return fr.getCSVParser(false);
	}
 
	
	public static String getSummary(int year) {
		String oReport = "";	
		int totalBirths = 0;
		int totalBoys = 0;
		int totalGirls = 0;
		int totalNames = 0;
		int uniqueBoys = 0;
		int uniqueGirls = 0;
		
		oReport = String.format("Summary (Year of %d):\n", year);
		for (CSVRecord rec : getFileParser(year)) {
			int numBorn = Integer.parseInt(rec.get(2));
			totalBirths += numBorn;
			totalNames += 1;
			if (rec.get(1).equals("M")) {
				totalBoys += numBorn;
				uniqueBoys++;
			}
			else {
				totalGirls += numBorn;
				uniqueGirls++;
			}
		}
		
		oReport += String.format("Total Births = %,d\n", totalBirths);
		oReport += String.format("***Baby Girls = %,d\n", totalGirls);
		oReport += String.format("***Baby Boys = %,d\n", totalBoys);
		
		oReport += String.format("Total Number of Unique Names = %,d\n", totalNames);
		oReport += String.format("***Unique Names (baby girls) = %,d\n", uniqueGirls);
		oReport += String.format("***Unique Names (baby boys) = %,d\n", uniqueBoys);
		
		return oReport;
	}
	
	
	 public static int getRank(int year, String name, String gender) {
	     boolean found = false;
	     int oRank = 0;
	 	int rank = 1;
	     for (CSVRecord rec : getFileParser(year)) {
	         // Increment rank if gender matches param
	         if (rec.get(1).equals(gender)) {
	             // Return rank if name matches param
	             if (rec.get(0).equals(name)) {
	             	found = true;
	             	oRank = rank;
	             	break;
	             }
	             rank++;
	         }
	     }
	     if (found)
	     	return oRank;
	     else
	     	return -1;
	 }
	 
 
	 public static String getName(int year, int rank, String gender) {
	 	boolean found = false;
	     String oName = "";
	     int currentRank = 0;
	     
	     // For every name entry in the CSV file
	     for (CSVRecord rec : getFileParser(year)) {
	         // Get its rank if gender matches param
	         if (rec.get(1).equals(gender)) {
	             // Get the name whose rank matches param
	         	currentRank++;
	            if (currentRank == rank) {
	             	found = true;
	             	oName = rec.get(0);
	                break;
	            }
	         }
	     }     
	     if (found)
	     	return oName;
	     else
	     	return "Not available";
	 }
	 
	 public static int getMaxRank(int year, String gender) {
		 int maxRank = 0;
		 for (CSVRecord rec : getFileParser(year)) {
			 if (rec.get(1).equals(gender)) {
				 maxRank ++;
			 }
		 }
		 return maxRank;
	 }
	 
	 public static int getMaxRankInPeriod(int yearFrom, int yearTo, String gender) {
		 int maxRank = 0;
		 for (int year=yearFrom; year<=yearTo; year++) {
			 int new_maxRank = getMaxRank(year, gender);
			 if (new_maxRank > maxRank)
				 maxRank = new_maxRank;
		 }
		 return maxRank;
	 }
	 
	 public static int getNameCount(int year, String name, String gender) //auxiliary function for task 2
	 {

	 	int count = 0;
	     for (CSVRecord rec : getFileParser(year)) 
	     {
	         // Increment rank if gender matches param
	         if (rec.get(1).equals(gender)) 
	         {
	             // Return rank if name matches param
	             if (rec.get(0).equals(name))
	             {
	             	count=Integer.parseInt(rec.get(2));
	             	break;
	             }
	            
	         }
	     }
	    return count;
	 }

	 public static double getNamePercentage(int year, String name, String gender) //auxiliary function for task 2
	 {
		 double percentage;
		 int sum = 0;
		 int count = getNameCount(year,name,gender);
		 
	     for (CSVRecord rec : getFileParser(year)) 
	     {
	         // Increment rank if gender matches param
	         if (rec.get(1).equals(gender)) 
	         {
	            sum=sum+Integer.parseInt(rec.get(2));	            
	         }
	     }
	     if(sum>0) percentage=100.0*count/sum;
	     else percentage=0;
	    return percentage;
	 }
	 
	 public static Entry<String, Integer> getMaxNameCount(int yearFrom, int yearTo, String gender) {
		 Map<String, Integer> nameCounts = new TreeMap<String, Integer>();
		 for (int year=yearFrom; year<=yearTo; year++) {			
			String name = getName(year, 1, gender);
			if (nameCounts.containsKey(name))
				nameCounts.replace(name, nameCounts.get(name)+1);
			else
				nameCounts.put(name, 1);
		 }
		 
		 Entry<String, Integer> maxNameCount = Collections.max(nameCounts.entrySet(), new Comparator<Entry<String, Integer>>() {
            public int compare(Entry<String, Integer> e1, Entry<String, Integer> e2) {
                return e1.getValue()
                    .compareTo(e2.getValue());
            }
		 });
		 return maxNameCount;
	 }
	 
	 public static int getMostPopularYear(int yearFrom, int yearTo, String name, String gender) //auxiliary function for task 2
	 {
		 int mostPopularYear=yearFrom;
		 double currentBestPercentage=getNamePercentage(yearFrom,name,gender);
		 for(int year=yearFrom;year<yearTo; year++)
		 {
			 if(getNamePercentage(year+1,name,gender)>currentBestPercentage)
			 {
				 mostPopularYear=year+1;
				 currentBestPercentage=getNamePercentage(year+1,name,gender);
			 }
		 }
		 if(currentBestPercentage<=0)
			 return 0;
		 else
			 return mostPopularYear;
	 }
	 
	 public static int[] getHighAndLowRank(int yearFrom, int yearTo, String name, String gender) {
			int hiRank = getRank(yearFrom, name, gender); 
			int loRank = getRank(yearFrom, name, gender);
			int hiYear = yearFrom;
			int loYear = yearFrom;

			for (int year=yearFrom+1; year<=yearTo; year++) {
				int rank = getRank(year, name, gender);

				// update the highest/lowest rank/year
				if ((rank > loRank || rank == -1) && loRank != -1) {
					loRank = rank;
					loYear = year;
				}
				if (rank < hiRank && rank != -1) {
					hiRank = rank;
					hiYear = year;
				}
			}
			int result[] = {hiRank, hiYear, loRank, loYear};
			return result;
	 }
	 

	 public static Pair<String, String> recommendBabyName(String dadName, int dadYOB, String momName, int momYOB, int vintageYear) {
		 int dadRank = getRank(dadYOB, dadName, "M");
		 int momRank = getRank(momYOB, momName, "F");
		// if not ranked, we assign 1 as the rank
		 if (dadRank == -1)
			 dadRank = 1;
		 if (momRank == -1)
			 momRank = 1;
		 String boyName = getName(vintageYear, dadRank, "M");
		 String girlName = getName(vintageYear, momRank, "F");
		 return new Pair<String, String>(boyName, girlName);
	 }
	 

	 public static String recommendedMateName(String yourName, int YOB, String yourGender, String mateGender, String preference) //NK-T5 algorithm for task 5
	 {
		 int mateRank;
		 int mateYOB;
		 int yourRank = getRank(YOB,yourName,yourGender);	
		 mateRank = (yourRank == -1 ? 1 : yourRank);		
		 mateYOB = (preference.equals("Younger") ? YOB+1 : YOB-1);
		 String mateName = getName(mateYOB, mateRank, mateGender);		 
		 return mateName;
	 }
	 
	 

	 public static float compatibleScore (String iName, String iGender,int iYOB, String iNameMate, String iGenderMate, int oYOB) {
		 int oRank = getRank(iYOB, iName, iGender);
		 int oRankMate = getRank(oYOB, iNameMate, iGenderMate);
		 // if not ranked, we assign 1 as the rank
		 if (oRank == -1)
			 oRank = 1;
		 if (oRankMate == -1)
			 oRankMate = 1;
		 // we define the score as 1-|(oRank - oRankMate)|/max(oRank,oRankMate), which is in range (0,1]
		 float oScore =1-Float.valueOf(Math.abs((oRank - oRankMate)))/Math.max(oRank,oRankMate); 
		  
		 return oScore;
	 }


}