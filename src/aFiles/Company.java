package aFiles;

import java.io.Serializable;
import java.util.*;

public class Company implements Serializable{

    private static final long serialVersionUID = 4020926496140356425L;

    private Map<Integer, Worker> map;
    private Set<Department> set;

    public Company(){
        map = new TreeMap<>();
        set = new TreeSet<>();
    }

    public Map<Integer, Worker> getMap() {
        return map;
    }
    public Set<Department> getSet() {
        return set;
    }
    public boolean add(Worker wrk){
        if (!map.containsKey(wrk.getId())) {
            if (containsDep(wrk.getDepartment())) {
                Department dep = getDep(wrk.getDepartment());
                dep.updateUp(wrk.getSalary());
            } else set.add(new Department(wrk.getDepartment(), wrk.getSalary()));
            map.put(wrk.getId(), wrk);
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
        Department dep = getDep(wr.getDepartment());
        dep.updateDown(wr.getSalary());
        if (dep.isEmpty()) set.remove(dep);
        map.remove(wr.getId());
    }

    public double getMiddleSalary() {
        int data = 0, count = 0;
        for (Worker wr: map.values()){
            data += wr.getSalary();
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

