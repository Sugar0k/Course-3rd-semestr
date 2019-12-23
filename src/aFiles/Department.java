package aFiles;

import java.io.Serializable;
import java.util.*;

public class Department implements Serializable, Comparable<Department>{

    private static final long serialVersionUID = 3014505982101513328L;

    private String title;
    private int quantity;
    private int commonSalary;
    private int middleSalary;
    private int minSalary;
    private int maxSalary;

    Vector<Integer> vector;

    public Department(String title, int salary){
        this.title = title;
        this.quantity = 1;
        this.commonSalary = salary;
        this.middleSalary = salary;
        this.minSalary = salary;
        this.maxSalary = salary;
        vector = new Vector();
        vector.add(salary);
    }

    public String getTitle() {
        return title;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getCommonSalary() {
        return commonSalary;
    }

    public int getMiddleSalary() {
        return middleSalary;
    }

    public int getMinSalary() {
        return minSalary;
    }

    public int getMaxSalary() {
        return maxSalary;
    }

    public void updateMinMax(int salary) {
        if (salary < minSalary) minSalary = salary;
        if (salary > maxSalary) maxSalary = salary;
    }

    public void updateUp(int salary){
        vector.add(salary);
        updateMinMax(salary);
        commonSalary += salary;
        quantity++;
        if (quantity == 0) {
            middleSalary = 0;
            return;
        }
        middleSalary = commonSalary / quantity;
    }

    public void updateDown(int salary){
        for (int i = 0; i < vector.size(); i++) {
            if (vector.get(i) == salary) {
                vector.remove(i);
                break;
            }
        }
        if (!vector.isEmpty()) {
            minSalary = vector.get(0);
            maxSalary = vector.get(0);
        }
        commonSalary -= salary;
        quantity--;
        if (quantity == 0) {
            middleSalary = 0;
            return;
        }
        for (int temp: vector) {
            updateMinMax(temp);
        }
        middleSalary = commonSalary / quantity;
    }

    public boolean isEmpty() {
        if (quantity == 0) return true;
        return false;
    }

    public boolean titleIs(String title) {
        return this.title.equals(title);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        String that;
        if (String.class == o.getClass()) {
            that = (String) o;
        } else if (getClass() == o.getClass()) {
            that = ((Department)o).title;
        } else return false;
        return title.equals(that);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, quantity, commonSalary);
    }

    @Override
    public int compareTo(Department department) {
        if (department.title.equals(title)) return 0;
        if (department.title.hashCode() < title.hashCode())
            return -1;
        else return 1;
    }
}
