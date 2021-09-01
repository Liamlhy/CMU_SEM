import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

	public class summer{
	 static int findMaxCoverage(int shift[][], int numberLifeguards){
		 int maxTime = maxTime(shift);
		 int schedule_slot[] = new int[maxTime];
		 

		 // Mark the schedule_slot, 
		 // so that on the slot we know if there is a scheduled shift
		 for(int i = 0; i < numberLifeguards; i++) { 
			 int start = shift[i][0];
		     int end = shift[i][1];
		     for (int j = start;j< end; j++) {
		    	 //System.out.println(j);
		    	 schedule_slot[j]++;
		     }
		 }
		 //This is to calculate the number that only appear once until ith invterval
		 //(Remove these number will contribute to total remove of coverage in such shift) 
		 int single_number[] = new int[maxTime]; 
		 if (schedule_slot[0] == 1){
			 single_number[0] =1;
		 }
		 
		 for (int i = 1; i < maxTime; i++)
		    {
		        if (schedule_slot[i] == 1) {
		        	single_number[i] = single_number[i - 1] + 1;
		        	}
		        else {
		        	single_number[i] = single_number[i - 1];
		        }
		       // System.out.println(single_number[i]);
		    }
		 
		 //Find the total shift coverage, by calculating the number that all schedule slot cover so far
		 int totalCoverage = 0;
		 for (int i = 0; i < maxTime; i++) {
			 if(schedule_slot[i] != 0) {
				 totalCoverage++;
			 }
		 }
		
		 // Find the maxCoverage by calculating 
		 // how many shift are not covered after removing such shift
		 int totalMaxCoverage  = 0; 
		 for (int i = 0; i < numberLifeguards; i++)
		    {
		        int start = shift[i][0] - 1;
		        int end = shift[i][1] - 1;
		        //System.out.println(start);
				int totalRemoved = single_number[end] - single_number[start];
				//System.out.println(single_number[start]);
				if (totalCoverage - totalRemoved >= totalMaxCoverage) {
					//System.out.println(totalCoverage);
					totalMaxCoverage = totalCoverage - totalRemoved;
				}
				
		    }
		 System.out.println(totalMaxCoverage);
		 return totalMaxCoverage;
	 }
	 // Find the max time range for the shift
	 public static int maxTime(int[][] shift) {
	        int maxTime = shift[0][0];
	        for (int j = 0; j < shift.length; j++) {
	            for (int i = 0; i < shift[j].length; i++) {
	                if (shift[j][i] > maxTime) {
	                	maxTime = shift[j][i];
	                }
	            }
	        }
	        return maxTime;
	    }

	public static void main(String args[]) throws Exception {
		 {
			 //Read in a file
			 File file = new File("/Users/liam/Downloads/1.in");
			 Scanner sc = new Scanner(file);
			 int numberLifeguards = Integer.parseInt(sc.nextLine());
			 int shift[][] = new int[numberLifeguards][2];
			 while(sc.hasNextLine()) {
		         for (int i=0; i<shift.length; i++) {
		            String[] line = sc.nextLine().trim().split(" ");
		            for (int j=0; j<line.length; j++) {
		            	shift[i][j] = Integer.parseInt(line[j]);
		            }
		         }
			 }
			//Write to a file
			int maxCoverage = findMaxCoverage(shift, numberLifeguards);
			String output = Integer.toString(maxCoverage);
			BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/liam/Desktop/1.out"));
		    writer.write(output);
		    writer.close();
		    //Display output
		    File file2 = new File("/Users/liam/Desktop/1.out");
			Scanner sc2 = new Scanner(file2);
			System.out.println(Integer.parseInt(sc2.nextLine()));
		   
	 }
	 }
	 }
