package comp3111.popnames;

import org.apache.commons.csv.*;
import edu.duke.*;
import javafx.util.Pair;

public class AnalyzeNames {

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
	     	return "information on the name at the specified rank is not available";
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
	 

	 public static Pair<String, String> recommendBabyName(String dadName, int dadYOB, String momName, int momYOB, int vintageYear) {
		 int dadRank = getRank(dadYOB, dadName, "M");
		 int momRank = getRank(momYOB, momName, "F");
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
		 int yourRank=getRank(YOB,yourName,yourGender);	
		 String younger=new String("Younger");
		 mateRank=(yourRank==-1?66:yourRank);		
		 mateYOB=(preference.equals(younger)?YOB+1:YOB-1);
		 String mateName=getName(mateYOB, mateRank,mateGender);		 
		 System.out.println(preference+" "+preference.equals(younger));
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