package com.cmxy.datastruct;

import java.util.Arrays;

//最大堆
public class MaxHeap<Item extends Comparable> {

    protected Item[] data;
    protected int count;
    protected int capacity;

    public MaxHeap(int capacity) {
        this.data = (Item[]) new Comparable[capacity + 1];//这里+1是因为，存储的数据从1开始，即数组0的位置空着
        this.count = 0;
        this.capacity = capacity;
    }

    public MaxHeap(Item[] arr) {
        int n = arr.length;

        data = (Item[]) new Comparable[n + 1];
        this.capacity = n;//容量
        //先将所有元素放入数组
        for (int i = 0; i < n; i++) {
            data[i + 1] = arr[i];
        }
        count = n;//元素数量
        //从第一个不为叶子节点的节点开始遍历
        for (int i = count / 2; i >= 1; i--) {
            shiftDown(i);
        }
    }

    // 返回堆中的元素个数
    public int size() {
        return count;
    }

    // 返回一个布尔值, 表示堆中是否为空
    public boolean isEmpty() {
        return count == 0;
    }

    //将元素加入到堆中
    public void insert(Item item) {
        //如果数量大于等于数组的容量则
        assert count + 1 <= capacity;
        //将数据插入数组的末尾,数组的第一个位置不放元素
        data[count + 1] = item;
        count++;
        //寻找合适的位置
        shiftUp(count);
    }

    private void shiftUp(int k) {
        while (k > 1 && data[k / 2].compareTo(data[k]) < 0) {
            swap(k, k / 2);
            k /= 2;
        }
    }

    // 交换堆中索引为i和j的两个元素
    private void swap(int i, int j) {
        Item t = data[i];
        data[i] = data[j];
        data[j] = t;
    }


    public Item extractMax() {
        if (count <= 0) {
            throw new RuntimeException("堆已为空");
        }
        Item ret = data[1];
        data[1] = data[count];
        shiftDown(1);
        count--;
        return ret;
    }


    private void shiftDown(int k) {
        //由最大堆的定义可知，只要有左孩子肯定还没到底。
        while (2 * k <= count) {
            int j = 2 * k;//左孩子
            //start 这个里的判断是为了确定左右孩子的大小，然后当前的数只需要和大的比较即可
            //右孩子大于左孩子
            if (j + 1 <= count && data[j + 1].compareTo(data[j]) > 0) {
                j++;//j++后就变成 右孩子了
            }
            //end
            //如果当前比较的数字大于等于他的左（或者右）孩子则跳出当前循环
            if (data[k].compareTo(data[j]) >= 0) {
                break;
            }
            swap(k, j);
            k = j;
        }
    }

    @Override
    public String toString() {
        return "MaxHeap{" +
                "data=" + Arrays.toString(data) +
                ", count=" + count +
                ", capacity=" + capacity +
                '}';
    }

    public static <Item> void main(String[] args) {
        Item[] arr = (Item[]) new Object[]{5, 4, 2, 9, 8, 6, 3, 7, 1, 0};
        MaxHeap maxHeap = new MaxHeap(10);
        for (int i = 0; i < 10; i++) {
            maxHeap.insert((Comparable) arr[i]);
        }
    }
}
