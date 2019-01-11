package com.cmxy.sort;

import com.cmxy.helper.SortTestHelper;

//原地堆排序：不开辟新的数组 从0开始索引
public class HeapSort3<T extends Comparable> {

    // 测试 HeapSort
    public static void main(String[] args) {

        int N = 1000000;
        Integer[] arr = SortTestHelper.generateRandomArray(N, 0, 100000);
        SortTestHelper.testSort("com.cmxy.sort.HeapSort3", arr);

        return;
    }

    public static <T extends Comparable> void sort(T arr[]) {
        heapSort(arr, arr.length);
    }


    private static <T extends Comparable> void heapSort(T arr[], int n) {
        //从第一个不为叶子节点的节点开始遍历，因为索引从0开始，所以是 （n-1）/2
        // heapify的过程
        for (int i = (n - 1) / 2; i >= 0; i--) {
            shiftDown(arr, n, i);
        }
        for (int i = n - 1; i > 0; i--) {
            swap(arr[0], arr[i]);
            shiftDown(arr, i, 0);
        }
    }

    private static <T extends Comparable> void shiftDown(T[] arr, int n, int k) {
        //根据定义只要有左孩子 这棵树就不为空
        //索引从0开始所以最后一个左孩子为2*k+1
        while (2 * k + 1 < n) {
            int j = 2 * k + 1;
            //右孩子不越界 右孩子大于左孩子
            if (j + 1 < n && arr[j + 1].compareTo(arr[j]) > 0) {
                j++;
            }
            if (arr[k].compareTo(arr[j]) >= 0) {
                break;
            }
            swap(arr[k], arr[j]);
            k = j;
        }

    }

    private static <T extends Comparable> void swap(T t1, T t2) {
        T temp = t1;
        t1 = t2;
        t2 = temp;
    }
}
