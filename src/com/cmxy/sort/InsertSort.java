package com.cmxy.sort;

import com.cmxy.helper.SortTestHelper;

import java.util.Arrays;

/**
 * 插入排序：每步将一个待排序元素，按其排序码大小插入到前面已经排好序的一组元素中，直到元素全部插入为止
 */
public class InsertSort {
    private InsertSort() {
    }

    //插入排序有提前结束的可能，所以理论上插入排序的性能要优于选择排序
    public static void sort(Comparable[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j - 1].compareTo(arr[j]) < 0) {
                    swap(arr, j, j - 1);
                } else {
                    //如果已经大于前面的元素，则跳出循环。
                    break;
                }
            }
        }
    }
    //优化过的代码
    public static void sort2(Comparable[] arr) {
        for (int i = 0; i < arr.length; i++) {
            //先将要比较的数据拷贝一份出来
            Comparable temp = arr[i];
            int j;//保存temp应该插入的位置
            for (j = i; j > 0; j--) {
                if (arr[j - 1].compareTo(temp) > 0) {
                    arr[j] = arr[j - 1];
                } else {
                    break;
                }
            }
            arr[j] = temp;
        }
    }


    private static void swap(Object[] arr, int i, int j) {
        Object t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}
