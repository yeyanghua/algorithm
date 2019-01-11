package com.cmxy.sort;

import com.cmxy.helper.SortTestHelper;

public class Main {

    public static void main(String[] args) {
        //在近乎有序的数组中测试两个算法
//        Integer[] arr = SortTestHelper.generateNearlyOrderedArray(200000,200);
//        Integer[] arr2 = arr.clone();
//        SortTestHelper.testSort("com.cmxy.sort.InsertSort","sort2",arr2);
//        SortTestHelper.testSort("com.cmxy.sort.SelectionSort",arr);

        Integer[] arr = SortTestHelper.generateRandomArray(50000, 0, 10000);
        Integer[] arr2 = arr.clone();
//        System.out.print("优化前： ");
//        SortTestHelper.testSort("com.cmxy.sort.SelectionSort","sort",arr);
//        System.out.print("优化后： ");
//        SortTestHelper.testSort("com.cmxy.sort.SelectionSort","sort2",arr2);

        SortTestHelper.testSort("com.cmxy.sort.MergeSort", arr);
        SortTestHelper.testSort("com.cmxy.sort.InsertSort", arr);
    }
}
