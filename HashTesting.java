/************************************************************************************************
* Omar Ramirez
* CSC 103 Project 5: Analysis of Hashing, Double Hashing, and Chain Hashing
* HashTesting.java
*
* The class HashTesting is the tester of the Table, TableDoubleHash, and TableChainHash files.
* 
* The main purpose is to determine the number of collisions per element placed into each 
* table and reporting the results of all three tests in a table. It will do this by reading 
* from a file called names.txt and placing the names into each table based on the key given in
* the infile.
*
*************************************************************************************************/
import java.util.*;
import java.io.*;

class HashTesting{
   public static void main(String[] args) throws IOException {
   
      String name;
      int keys, counter = 0;                       //variables for storing the information from the file
      int linearCollisions, linearTotal = 0;       //as well as for determining the averages for each of the table hashings.
      int doubCollisions, doubTotal = 0;
      int chainCollisions, chainTotal = 0;
      double linearAvg, doubAvg, chainAvg;
      
      //creating each of the hash tables being able to store up to 241 elements 
      Table < Integer, String > linear = new Table < Integer, String > (241);
      TableDoubleHash < Integer, String > doub = new TableDoubleHash < Integer, String > (241);
      TableChainHash <Integer, String > chain = new TableChainHash < Integer, String > (241);
        
      File inFile = new File("names.txt");     // file we are reading the data from 
       
      if(inFile.exists()){                       // if the file exists, read it and 
         Scanner read = new Scanner(inFile);     // store it's information in the variables  
         
         // Set up for the analysis between tables
         System.out.println("Collisions per Attempted placement in Tables");    
         System.out.println("Attempt    Linear      Double      Chain");
         
         while(read.hasNext()){
            name = read.next();        // reading the names and keys from the file
            keys = read.nextInt();
            
            linear.put(keys, name);    // use the data to insert it into tables
            doub.put(keys, name); 
            chain.put(keys, name); 
            counter++;
            
            linearCollisions = linear.getCollisions();         //getting the collisions each time an element in inputted
            doubCollisions = doub.getCollisions();             //into each hash table and setting it equal to their respective variables 
            chainCollisions = chain.getCollisions();
            
            linearTotal += linearCollisions;    //summing the collisions per element to their respective variables
            doubTotal += doubCollisions;
            chainTotal += chainCollisions;
            
            //printing out the collisions per element added  
            System.out.println(counter + "            " + linear.getCollisions() + "           " + doub.getCollisions() + "             " + chain.getCollisions());
            
         }
         
         //determining the average collisions of each hash table by dividing the total collisions 
         //after adding the 200 elements and diving by the amount of elements (manyItems)
         linearAvg = (double)linearTotal / linear.getManyItems();
         doubAvg = (double)doubTotal / doub.getManyItems();
         chainAvg = (double)chainTotal / chain.getManyItems();
         
         //printing out the averages 
         System.out.println("\nLinear Average: " + linearAvg);
         System.out.println("Double Average: " + doubAvg);
         System.out.println("Chain Average: " + chainAvg);
      
      
         read.close();    // close the inFile
            
      } else {
         System.out.println("File does not exist.");  // just in case the file does not exist
      }
      
      
   }//end of main method           
}//end of class HashTesting