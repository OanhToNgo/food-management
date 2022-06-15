/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodmanagement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.Date;
import java.util.Collections;

import java.util.StringTokenizer;

/**
 *
 * @author oanhn
 */
public class Container {

    private ArrayList<Food> foodList = new ArrayList();
    private Scanner sc = new Scanner(System.in);

    //----------PART ONE: FROM COMPUTER------------
    public void addNewFood() {
        String id, name, type, place;
        Date date;
        double weight;
        int position;
        do {
            id = Util.getID("Input food's ID (Fiii): ", "The format of food is Fiii(i stands for a digit).", "^[F|f]\\d{3}$");
            position = searchFoodbyID(id);
            if (position >= 0) {
                System.out.println("The food ID is already exists. Please input another!");
            }

        } while (position != -1);
        name = Util.getString("Input food's name: ", "Please try again.");
        weight = Util.getDouble("Input food's weight: ", "Please try again.");
        type = Util.getString("Input food's type: ", "Please try again.");
        place = Util.getString("Input where to put food: ", "Please try again.");
        date = Util.getDate();
        foodList.add(new Food(id, name, weight, type, place, date));
        System.out.println("A new food is added successfully");
    }

    //return position of food by finding ID string
    public int searchFoodbyID(String foodID) {
        int position;
        if (foodList.isEmpty()) {
            return -1;
        }
        for (int i = 0; i < foodList.size(); i++) {
            if (foodList.get(i).getFoodID().equalsIgnoreCase(foodID)) {
                position = i;
                return position;
            }
        }
        return -1;
    }

    //return Food Object by finding Food's ID
    public Food searchFoodObjbyID(String foodID) {
        if (foodList.isEmpty()) {
            return null;
        }
        for (int i = 0; i < foodList.size(); i++) {
            if (foodList.get(i).getFoodID().equalsIgnoreCase(foodID)) {
                return foodList.get(i);
            }
        }
        return null;
    }

    //print the list of foods which have the same name
    public void searchFoodbyName() {
        String name;
        Food tmp = null;
        System.out.println("Please enter food's name you want to search: ");
        name = sc.nextLine().trim();
        if (foodList.isEmpty()) {
            System.out.println("The fridge is empty!");
        } else {
            for (int j = 0; j < foodList.size(); j++) {
                if (foodList.get(j).getName().equalsIgnoreCase(name)) {
                    tmp = foodList.get(j);
                    tmp.print();
                }
            }
            if (tmp == null) {
                System.out.println("This food does not exist!");
            }
        }

    }

    //remove Food by inputted ID and confirmation
    public void removeFood() {
        String id;
        int position;
        id = Util.getString("Input food's ID to remove", "Food's ID is required!");
        position = searchFoodbyID(id);
        if (position == -1) {
            System.out.println("This food's ID cannot find!");
        } else {
            ArrayList subLst = new ArrayList();
            subLst.add("1.Yes, remove.");
            subLst.add("2.No, don't remove.");
            Menu subMenu = new Menu();
            System.out.println("Are you sure that you want to remove this food? Press 1 to delete.");
            int subChoice = subMenu.getIntChoice(subLst);
            System.out.println("You chose: " + subChoice);
            if (subChoice == 1) {
                foodList.remove(position);
                System.out.println("The food is removed successfully!");
            } else {
                System.out.println("The food is still in the fridge.");
            }
        }
    }

    //print List by descending Date
    public void printFoodListDescByDate() {
        if (foodList.isEmpty()) {
            System.out.println("The fridge is empty!");
            return;
        }
        Collections.sort(foodList);
        Collections.reverse(foodList);
        System.out.println("The food list by descending:");
        System.out.printf("|%6s|%20s|%8s|%20s|%20s|%20s|\n","FoodID","Name","Weight","Type","Place","ExpiredDate");
        for (int i = 0; i < foodList.size(); i++) {
            foodList.get(i).print();
        }

    }



    //---------PART TWO: FROM FILE---------------------
    public void addFromFile(String fName) {
        try {
            File f = new File(fName);
            if (!f.exists()) {
                System.out.println("This file does not exist.");
                return;
            }
            FileReader fr = new FileReader(f);
            BufferedReader bf = new BufferedReader(fr);
            String details;
            while ((details = bf.readLine()) != null) {
                
                    double weight;
                    StringTokenizer stk = new StringTokenizer(details, ",");
                    String foodID = stk.nextToken().toUpperCase();
                    String name = stk.nextToken();
                    try {
                        weight = Double.parseDouble(stk.nextToken());
                    } catch (Exception e) {
                        continue;
                    }
                    String type = stk.nextToken();
                    String place = stk.nextToken();
                    Date date = Util.getDateFromFile(stk.nextToken());
                    if ((!searchIDFromFile(foodID))&&(Util.dateValidateFromFile(date)) && (Util.doubleValidateFromFile(weight))) {

                        Food fd = new Food(foodID, name, weight, type, place, date);
                        foodList.add(fd);
                    }   
            }
            bf.close();
            fr.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void saveToFile(String fName) {
        if (foodList.isEmpty()) {
            System.out.println("The list is empty!");
            return;
        }
        try {
            File f = new File(fName);
            FileWriter fw = new FileWriter(f);
            PrintWriter pw = new PrintWriter(fw);
            pw.println("The food list:");
            for (Food food : foodList) {
                pw.println("FoodId="+food.getFoodID() + "," +"FoodName=" +food.getName()
                        + "," +"Weight="+ food.getWeight() + "," +"FoodType="+ food.getType() + "," +"FoodPlace="+ food.getPlace()
                        + "," +"ExpiredDate="+ food.getExpireDate());

            }
            pw.close();
            fw.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }
    public boolean searchIDFromFile(String id){
        if(foodList.isEmpty())
            return false;
        for(int i = 0; i< foodList.size();i++)
            if(foodList.get(i).getFoodID().equalsIgnoreCase(id))
                return true;
        return false;
    }
}
