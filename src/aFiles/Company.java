package aFiles;

import java.io.Serializable;
import java.util.*;

import static aFiles.assistsFiles.Global.gSecondTable;

public class Company implements Serializable{

    private static final long serialVersionUID = 4020926496140356425L;

    Map<Integer, Worker> map;
    Set<Department> set;

    public Company(){
        map = new TreeMap<>();
        set = new TreeSet<>();
    }

    public Map<Integer, Worker> getMap() {
        return map;
    }

    public boolean add(Worker wrk){
        if (!map.containsKey(wrk.id)) {
            if (containsDep(wrk.department)) {
                Department dep = getDep(wrk.department);
                System.out.println("deps " + dep.getQuantity());
                dep.updateUp(wrk.salary);
            } else set.add(new Department(wrk.department, wrk.salary));
            map.put(wrk.id, wrk);
            return true;
        }

        return false;
    }

    public boolean containsDep(String title) {
        for (Department dep: set) {
            if (dep.equals(title)) return true;
        }
        return false;
    }

    public Department getDep(String title) {
        for (Department dep: set) {
            if (dep.equals(title)) return dep;
        }
        return null;
    }

    public boolean contains(int id) {
        if (map.containsKey(id)) return true;
        return false;
    }

    public void del(Worker wr) {
        Department dep = getDep(wr.department);
        dep.updateDown(wr.salary);
        if (dep.isEmpty()) set.remove(dep);
        map.remove(wr.id);
    }

    public double getMiddleSalary() {
        int data = 0, count = 0;
        for (Worker wr: map.values()){
            data += wr.salary;
            count++;
        }
        if (count == 0) return 0;
        return data/count;
    }

    public void print(){
        for(Worker wr: map.values()){
            System.out.println(wr.toString());
        }
    }


}

