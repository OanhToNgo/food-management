/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodmanagement;

/**
 *
 * @author oanhn
 */
import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;
public class FoodManagement {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       Container fridge = new Container();
       int mainChoice;
       fridge.addFromFile("sample.txt");
       ArrayList<String> mainMenuLst = new ArrayList();
       mainMenuLst.add("Add a new food");
       mainMenuLst.add("Search a food by name");
       mainMenuLst.add("Remove the food by ID");
       mainMenuLst.add("Print the food list in the descending order of expired date");
       mainMenuLst.add("Store the food list to text(.txt)file");
       mainMenuLst.add("Quit");
       System.out.println("Welcome to Food Management - @ 2021 by Ngo To Oanh - SE160787");
        do {  
            System.out.println();
            Menu mainMenu = new Menu();
            mainChoice = mainMenu.getIntChoice(mainMenuLst);
            switch(mainChoice){
                case 1:
                    int subChoice;
                    do { 
                        fridge.addNewFood();
                        ArrayList<String> addSubLst = new ArrayList();
                        System.out.println("Do you want to add more. Press 1 to add.");
                        addSubLst.add("Yes,add more.");
                        addSubLst.add("No,finish.");
                        Menu subAddMenu = new Menu();
                        subChoice = subAddMenu.getIntChoice(addSubLst);    
                    } while (subChoice-1!=1);
                    break;
                case 2:
                    int secondSubChoice;
                    do {  
                        fridge.searchFoodbyName();
                        ArrayList<String> searchSubLst = new ArrayList();
                        System.out.println("Do you want to search more? Press 1 to search");
                        searchSubLst.add("Yes, search more.");
                        searchSubLst.add("No,finish.");
                        Menu subSearchMenu = new Menu();
                        secondSubChoice = subSearchMenu.getIntChoice(searchSubLst);                   
                    } while (secondSubChoice-1 !=1);
                    break;
                case 3:
                    fridge.removeFood();
                    break;
                            
                case 4:
                    fridge.printFoodListDescByDate();
                    break;
                case 5:
                    String destFile;
                    destFile = Util.getID("Please input the desired file name with extension .txt:","Please try again.","([^\\s]+(\\.(?i)(txt|doc|csv|pdf))$)");
                    fridge.saveToFile(destFile);
                    break;
                case 6:
                    System.out.println("Good bye!");
                    break;
                    
            }
            
        } while (mainChoice!=6);
      
        
                
      
        
    }
    
}
