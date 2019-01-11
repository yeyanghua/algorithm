package com.cmxy.application.heap;

import java.util.Arrays;

//优先队列，基于堆
public class PriorityQueue<T extends Comparable> {

    private T[] data;//数据
    private int size;//数据量
    private int capacity;//队列容量

    public PriorityQueue(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        data = (T[]) new Comparable[capacity + 1];//索引从1开始
    }

    //判断队列是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    //队列容量
    public int size() {
        return capacity;
    }

    //入队操作
    public void enqueue(T t) {
        //TODO 以后可以改成动态生成数组
        if (size + 1 > capacity) {
            throw new RuntimeException("队列已经满了，无法继续入队");
        }
        //将生成的元素放到数组末尾
        data[size + 1] = t;
        shiftUp(size + 1);
        size++;
    }

    //出队操作
    public T dequeue() {
        //出队只能从根节点开始
        if (isEmpty()) {
            throw new RuntimeException("队列已空，无法出队");
        }
        T ret = data[1];
        data[1] = data[size];//把最后一个元素放到开头，然后进行shift down操作
        shiftDown(1);
        size--;
        return ret;
    }

    private void shiftDown(int index) {
        //由最大堆的定义可知，只要有左孩子肯定还没到底。
        while (2 * index <= size) {
            int j = 2 * index;//左孩子
            //右孩子大于左孩子
            if (j + 1 <= size && data[j + 1].compareTo(data[j]) > 0) {
                j++;
            }
            //如果当前比较的数字大于等于他的左（或者右）孩子则跳出当前循环
            if (data[index].compareTo(data[j]) >= 0) {
                break;
            }
            swap(index, j);
            index = j;
        }
    }

    private void shiftUp(int index) {
        while (index > 1) {
            if (data[index].compareTo(data[index / 2]) > 0) {
                swap(index, index / 2);
                index /= 2;
            } else {
                return;
            }
        }
    }

    // 交换堆中索引为i和j的两个元素
    private void swap(int i, int j) {
        T t = data[i];
        data[i] = data[j];
        data[j] = t;
    }

    @Override
    public String toString() {
        return "PriorityQueue{" +
                "data=" + Arrays.toString(data) +
                ", size=" + size +
                ", capacity=" + capacity +
                '}';
    }

    public static <T> void main(String[] args) {
        T[] arr = (T[]) new Object[]{5, 4, 2, 9, 8, 6, 3, 7, 1, 0};
        PriorityQueue priorityQueue = new PriorityQueue<>(11);
        for (int i = 0; i < arr.length; i++) {
            priorityQueue.enqueue((Comparable) arr[i]);
        }
        for (int i = 0; i < 3; i++) {
            Comparable a = priorityQueue.dequeue();
            System.out.println(a);
        }
    }
}
