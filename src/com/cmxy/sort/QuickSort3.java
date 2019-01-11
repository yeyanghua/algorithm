package com.cmxy.sort;

import com.cmxy.helper.SortTestHelper;

import javax.jws.soap.SOAPBinding;
import java.util.Arrays;

//进一步优化双路快速排序->三路快速排序
//思想：之前的快速排序思想都是将数组分为两部分<v和>v,而三路快速排序则多了=v这一部分，在递归的时候对于=v的部分可以不用管
public class QuickSort3 {

    public static void sort(Comparable[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    private static void sort(Comparable[] arr, int l, int r) {
        //高级排序的末尾部分可以采用插入排序提高效率
        if (r - l <= 15) {
            insertSort(arr, l, r);
            return;
        }
        //partition
        swap(arr, l, (int) (Math.random() * (r - l + 1)) + l);
        Comparable v = arr[l];
        //这里这么设置是为了保证初始化的时候，数组都为空。
        int lt = l; //arr[l+1,lt]<v
        int gt = r + 1;//arr[gt,r]>v
        //前闭后开是因为i是正在考察的元素，不一定==v 所以不将i包括进去
        int i = l + 1;//arr[lt+1,i)==v
        while (i < gt) {
            if (arr[i].compareTo(v) < 0) {
                swap(arr, i, lt + 1);
                lt++;
                i++;
            } else if (arr[i].compareTo(v) > 0) {
                swap(arr, i, gt - 1);
                gt--;
            } else {
                i++;
            }
        }
        swap(arr, l, lt);
        sort(arr, l, lt - 1);
        sort(arr, gt, r);
    }

    //插入排序，用来辅助优化快速排序
    private static void insertSort(Comparable[] arr, int l, int r) {
        for (int i = l; i < r; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j - 1].compareTo(arr[j]) > 0) {
                    swap(arr, j, j - 1);
                } else {
                    break;
                }
            }
        }
    }

    private static void swap(Comparable arr[], int i, int j) {
        Comparable temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int N = 1000000;

        // 测试1 一般性测试
        System.out.println("一般性能测试, 数组长度 = " + N + " , 范围 [0, " + N + "]");

        Integer[] arr1 = SortTestHelper.generateRandomArray(N, 0, N);
        Integer[] arr2 = Arrays.copyOf(arr1, arr1.length);
        Integer[] arr3 = Arrays.copyOf(arr1, arr1.length);

        SortTestHelper.testSort("com.cmxy.sort.MergeSort", arr1);
        SortTestHelper.testSort("com.cmxy.sort.QuickSort2", arr2);
        SortTestHelper.testSort("com.cmxy.sort.QuickSort3", arr3);

        System.out.println();


        // 测试2 测试近乎有序的数组
        int swapTimes = 100;
        assert swapTimes >= 0;

        System.out.println("测试近乎有序的数组, 数组长度 = " + N + " , 交换次数 = " + swapTimes);

        arr1 = SortTestHelper.generateNearlyOrderedArray(N, swapTimes);
        arr2 = Arrays.copyOf(arr1, arr1.length);
        arr3 = Arrays.copyOf(arr1, arr1.length);

        SortTestHelper.testSort("com.cmxy.sort.MergeSort", arr1);
        SortTestHelper.testSort("com.cmxy.sort.QuickSort2", arr2);
        SortTestHelper.testSort("com.cmxy.sort.QuickSort3", arr3);

        System.out.println();


        // 测试3 测试存在包含大量相同元素的数组
        System.out.println("测试存在大量重复元素的数组, 长度 = " + N + " ,范围 [0,10]");

        arr1 = SortTestHelper.generateRandomArray(N, 0, 10);
        arr2 = Arrays.copyOf(arr1, arr1.length);
        arr3 = Arrays.copyOf(arr1, arr1.length);

        SortTestHelper.testSort("com.cmxy.sort.MergeSort", arr1);
        SortTestHelper.testSort("com.cmxy.sort.QuickSort2", arr2);
        SortTestHelper.testSort("com.cmxy.sort.QuickSort3", arr3);
    }
}
