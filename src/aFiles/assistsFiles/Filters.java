package aFiles.assistsFiles;

import aFiles.Worker;

import java.util.*;

public class Filters {
    private Map<Integer, Worker> map;
    private boolean isSalary, isSelectedID,
        isFName, isLName, isSName, isDepartment;
    private int tSalary, bSalary;
    private int tID, bID;
    private String fName, lName, sName, department;

    public Filters(Map map) {
        this.map  = map;
        isSalary = false;
        isSelectedID = false;
        isFName = false;
        isLName = false;
        isSName = false;
        isDepartment = false;
    }

    public Filters() {
        isSalary = false;
        isSelectedID = false;
        isFName = false;
        isLName = false;
        isSName = false;
        isDepartment = false;
    }

    public Filters clearFilters(){
        isSalary = false;
        isSelectedID = false;
        isFName = false;
        isLName = false;
        isSName = false;
        isDepartment = false;
        return this;
    }
    //fLogic
    public Set<Worker> getSet(){
        Set<Worker> newSet = new TreeSet<Worker>();
        Set <Map.Entry <Integer, Worker>> setM = map.entrySet();
        for (Map.Entry <Integer, Worker> keyVal: setM) {
            Worker wr = keyVal.getValue();
            if (isSalary)
                if(!(wr.getSalary() <= bSalary && wr.getSalary() >= tSalary)) continue;
            if (isSelectedID)
                if (!(wr.getId() <= bID && wr.getId() >= tID)) continue;
            if (isFName)
                if (!wr.getFName().contains(fName)) continue;
            if (isLName)
                if (!wr.getLName().contains(lName)) continue;
            if (isSName)
                if (!wr.getSName().contains(sName)) continue;
            if (isDepartment)
                if (!wr.getDepartment().contains(department)) continue;

            newSet.add(wr);
        }

        return newSet;
    }

    public boolean isFiltered(){
        if (isDepartment ||
                isSName ||
                isLName ||
                isFName ||
                isSelectedID ||
                isSalary){
            return true;
        }
        return false;
    }

    //set what?
    public Filters setMap(Map map){
        this.map = map;
        return this;
    }

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

    public Filters setDepartment(String department) {
        this.department = department;
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

    public Filters addDepartment() {
        isDepartment = true;
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

    public Filters deleteDepartment() {
        isDepartment = false;
        return this;
    }
}
