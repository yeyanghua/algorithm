package com.cmxy.application.heap.data;

public class Patient implements Comparable {

    private String name;
    private Integer priority;

    public Patient(String name, Integer priority) {
        this.name = name;
        this.priority = priority;
    }

    //实现Comparable，重写compareTo方法
    @Override
    public int compareTo(Object o) {
        if (this.priority > ((Patient) o).priority)
            return 1;
        if (this.priority == ((Patient) o).priority)
            return 0;
        return -1;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "name='" + name + '\'' +
                ", priority=" + priority +
                '}';
    }
}
