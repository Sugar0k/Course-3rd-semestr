package aFiles;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

public class Company implements Serializable{

    private static final long serialVersionUID = 4020926496140356425L;

    Map<Integer, Worker> map;

    public Company(){
        map = new TreeMap<Integer, Worker>();
    }

    public Map<Integer, Worker> getMap() {
        return map;
    }

    public boolean add(Worker wrk){
        if (!map.containsKey(wrk.id)) {
            map.put(wrk.id, wrk);
            return true;
        }

        return false;
    }

    public boolean contains(int id){
        if (map.containsKey(id)) return true;
        return false;
    }

    public void del(int key){
        map.remove(key);
    }

    public void print(){
        for(Worker wr: map.values()){
            System.out.println(wr.toString());
        }
    }


}

