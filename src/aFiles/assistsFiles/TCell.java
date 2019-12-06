package aFiles.assistsFiles;

import aFiles.Worker;

public class TCell extends Worker {
    private int index;

    public TCell(int index, Worker worker){
        super(worker.id,
                worker.lName,
                worker.fName,
                worker.sName,
                worker.salary,
                worker.department);
        this.index = index;
    }

    public TCell(int index,
                 int id,
                 String lName,
                 String fName,
                 String sName,
                 int salary,
                 String department){
        super(id, lName, fName, sName, salary, department);
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}