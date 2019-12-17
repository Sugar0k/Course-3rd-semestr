package aFiles.assistsFiles;

import aFiles.Company;
import aFiles.Worker;

import java.util.*;

import static aFiles.assistsFiles.Global.*;

public class Filters {
    private Map<Integer, Worker> map;
    private boolean isSalary, isSelectedID,
        isFName, isLName, isSName;
    private int tSalary, bSalary;
    private int tID, bID;
    private String fName, lName, sName;
    public Filters(Map map) {
        this.map  = map;
        isSalary = false;
        isSelectedID = false;
        isFName = false;
        isLName = false;
        isSName = false;
    }

    //fLogic
    public Set<Worker> getSet(){
        Set<Worker> newSet = new TreeSet<Worker>();
        Set <Map.Entry <Integer, Worker>> setM = map.entrySet();
        for (Map.Entry <Integer, Worker> keyVal: setM) {
            Worker wr = keyVal.getValue();
            if (isSalary)
                if(!(wr.salary <= bSalary && wr.salary >= tSalary)) continue;
            if (isSelectedID)
                if (!(wr.id <= bID && wr.id >= tID)) continue;
            if (isFName)
                if (!wr.fName.contains(fName)) continue;
            if (isLName)
                if (!wr.lName.contains(lName)) continue;
            if (isSName)
                if (!wr.sName.contains(sName)) continue;

            newSet.add(wr);
        }

        return newSet;
    }

    //set what?
    public Filters setSalary(int tSalary, int bSalary) {
        this.tSalary = tSalary;
        this.bSalary = bSalary;
        return this;
    }

    public Filters setID(int tID, int bID) {
        this.tID = tID;
        this.bID = bID;
        return this;
    }

    public Filters setFName(String fName) {
        this.fName = fName;
        return this;
    }

    public Filters setLName(String lName) {
        this.lName = lName;
        return this;
    }

    public Filters setSName(String sName) {
        this.sName = sName;
        return this;
    }

    //set bool parameters true
    public Filters addSalary() {
        isSalary = true;
        return this;
    }

    public Filters addSelectedID(){
        isSelectedID = true;
        return this;
    }

    public Filters addFName(){
        isFName = true;
        return this;
    }

    public Filters addLName(){
        isLName = true;
        return this;
    }

    public Filters addSName(){
        isSName = true;
        return this;
    }

    //set bool parameters false
    public Filters deleteSalary(){
        isSalary = false;
        return this;
    }

    public Filters deleteSelectedID(){
        isSelectedID = false;
        return this;
    }

    public Filters deleteFName(){
        isFName = false;
        return this;
    }

    public Filters deleteLName(){
        isLName = false;
        return this;
    }

    public Filters deleteSName(){
        isSName = false;
        return this;
    }

    public static void main(String[] args){
        Map<Integer, Worker> map = new TreeMap<Integer, Worker>();
        map.put(1, new Worker(1, "Pavlyuk1", "Alexsandr1", "Petrovich1", 101, "dp 1"));
        map.put(2, new Worker(2, "Pavlyuk2", "Alexsandr2", "Petrovich2", 102, "dp 2"));
        map.put(3, new Worker(3, "Pavlyuk3", "Alexsandr3", "Petrovich3", 103, "dp 3"));
        map.put(4, new Worker(4, "Pavlyuk4", "Alexsandr4", "Petrovich4", 104, "dp 4"));
        map.put(5, new Worker(5, "Pavlyuk5", "Alexsandr5", "Petrovicha5", 105, "dp 5"));
        map.put(6, new Worker(6, "Pavlyuk6", "Alexsandr6", "Petrovich6", 106, "dp 6"));
        map.put(7, new Worker(7, "Pavlyuk7", "Alexsandr7", "Petrovich7", 107, "dp 7"));
        map.put(8, new Worker(8, "Pavlyuk8", "Alexsandr8", "Petrovich8", 108, "dp 8"));
        map.put(9, new Worker(9, "Pavlyuk9", "Alexsandr9", "Petrovich9", 109, "dp 9"));
        map.put(10, new Worker(10, "Pavlyuk10", "Alexsandr10", "Petrovich10", 110, "dp 10"));

        Filters filters = new Filters(map);
        filters.addSalary().setSalary(103,109).addSelectedID().setID(4, 6).addSName().setSName("a");
        Set<Worker> set = filters.getSet();

        for (Worker wr : set){
            System.out.println(wr.salary);
        }


    }
}
