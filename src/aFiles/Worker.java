package aFiles;

import java.io.Serializable;

public class Worker implements Serializable, Comparable<Worker> {

    private static final long serialVersionUID = 3193522421923157206L;

    public int id;
    public String lName, fName, sName;
    public int salary;
    public String department;

    public Worker() {
        id = 0;
        lName = "";
        fName = "";
        sName = "";
        salary = 0;
        department = "";
    }

    public Worker(int id,
           String lName,
           String fName,
           String sName,
           int salary,
           String department){
        this.id = id;
        this.lName = lName;
        this.fName = fName;
        this.sName = sName;
        this.salary = salary;
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public String getLName() {
        return lName;
    }

    public String getFName() {
        return fName;
    }

    public String getSName() {
        return sName;
    }

    public int getSalary() {
        return salary;
    }

    public String getDepartment() {
        return department;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return String.format("%5s %15s %15s %15s %10s %15s",
                id,
                lName,
                fName,
                sName,
                salary,
                department);
    }

    @Override
    public boolean equals(Object obj){
        if (obj != this) return false;
        Worker wrk = (Worker)obj;
        if (wrk.id == id) return true;
        return false;
    }

    @Override
    public int compareTo(Worker wr) {
        if (id < wr.id) return -1;
        if (id > wr.id) return 1;
        return 0;
    }
}
