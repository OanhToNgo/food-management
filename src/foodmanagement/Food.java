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
public class Food implements Comparable<Food>{
    String foodID;
    String name;
    double weight;
    String type;
    String place;
    Date expireDate;

    public Food(String foodID, String name, double weight, String type, String place, Date expireDate) {
        this.foodID = foodID;
        this.name = name;
        this.weight = weight;
        this.type = type;
        this.place = place;
        this.expireDate = expireDate;
    }
    

    public String getFoodID() {
        return foodID;
    }

    public void setFoodID(String foodID) {
        this.foodID = foodID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    @Override
    public String toString() {
        return "Food{" + "foodID=" + foodID + ", name=" + name + ", weight=" + weight + ", type=" + type + ", place=" + place + ", expireDate=" + expireDate + '}';
    }
    
    public void print(){
        System.out.printf("|%6s|%20s|%8.1f|%20s|%20s|%20s|",foodID,name,weight,type,place,expireDate);
        System.out.println();
    }

    @Override
    public int compareTo(Food o) {
        if(getExpireDate() == null || o.getExpireDate() == null)
            return 0;
        return getExpireDate().compareTo(o.getExpireDate());
    }
    
    
}
