package com.cmxy.sort;

import com.cmxy.helper.SortTestHelper;

/**
 * 快速排序（20世纪最伟大的排序算法之一）
 */
public class QuickSort {

    public static void sort(Comparable[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    //对arr[l...r]部分进行快速排序
    private static void quickSort(Comparable[] arr, int l, int r) {
        //递归终止条件
        if (l >= r) {
            return;
        }
        int p = partition2(arr, l, r);
        quickSort(arr, l, p - 1);
        quickSort(arr, p + 1, r);
    }


    //优化过后的
    private static int partition(Comparable[] arr, int l, int r) {
        // 随机在arr[l...r]的范围中, 选择一个数值作为标定点pivot
        swap(arr, l, (int) (Math.random() * (r - l + 1)) + l);
        Comparable v = arr[l];
        int j = l; // arr[l+1...j] < v ; arr[j+1...i) > v
        for (int i = l + 1; i <= r; i++) {
            if (arr[i].compareTo(v) < 0) {
                j++;
                swap(arr, j, i);
            }
        }
        swap(arr, l, j);

        return j;
    }

    //对arr[l...r]部分进行partition操作
    //返回p，使得arr[l...p-1]<arr[p]<arr[p+1...r]
    //未优化部分
    private static int partition2(Comparable[] arr, int l, int r) {
        Comparable v = arr[l];
        //arr[l+1...j]<v<arr[j+1...i) 后边是开区间因为i是正在考察的值
        int j = l;//这样设定使得，初始情况下 arr[l+1...j]为空数组
        for (int i = l + 1; i <= r; i++) {
            //设置i=l+1使得arr[j+1...i)初始化的时候也为空数组
            //如果arr[i]>v 则什么都不要做，只需要往后考察
            //如果考察的数小于v则交换位置
            if (arr[i].compareTo(v) < 0) {
                j++;
                swap(arr, j , i);
            }
        }
        swap(arr, l, j);
        return j;
    }

    private static void swap(Object[] arr, int i, int j) {
        Object t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {
        int n = 1000000;
        Integer[] arr = SortTestHelper.generateRandomArray(n, 0, 100000);
        Integer[] arr2 = arr.clone();
        System.out.print("快速排序： ");
        SortTestHelper.testSort("com.cmxy.sort.QuickSort", arr);
        System.out.print("归并排序： ");
        SortTestHelper.testSort("com.cmxy.sort.MergeSort", arr2);
    }
}

