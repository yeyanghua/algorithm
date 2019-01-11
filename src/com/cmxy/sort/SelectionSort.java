package com.cmxy.sort;

import com.cmxy.helper.SortTestHelper;

/**
 * 插入排序:
 * 算法思想：每次从数组中找出最小的值放在第一个位置，再从剩下的数组中找出最小值放在剩下数组的第一个位置
 * 以此类推直到遍历完整个数组。
 * 时间复杂度 O(n²)
 */
public class SelectionSort {
    // 测试 SelectionSort
    public static void main(String[] args) {
        int N = 50000;
        Integer[] arr = SortTestHelper.generateRandomArray(N, 0, 10000);
        SortTestHelper.testSort("com.cmxy.sort.SelectionSort", arr);
    }

    public static void sort(Comparable[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            // 寻找[i, n)区间里的最小值的索引
            int minIndex = i;
            for (int j = i + 1; j < n; j++)
                // 使用compareTo方法比较两个Comparable对象的大小
                if (arr[j].compareTo(arr[minIndex]) < 0)
                    minIndex = j;

            swap(arr, i, minIndex);
        }
    }

    public static void sort2(Comparable[] arr) {
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            int minIndex = left;
            int maxIndex = right;
            // 在每一轮查找时, 要保证arr[minIndex] <= arr[maxIndex]
            if (arr[minIndex].compareTo(arr[maxIndex]) > 0) {
                swap(arr, minIndex, maxIndex);
            }
            for (int i = left + 1; i < right; i++) {
                if (arr[i].compareTo(arr[minIndex]) < 0) {
                    minIndex = i;
                } else if (arr[i].compareTo(arr[maxIndex]) > 0) {
                    maxIndex = i;
                }
            }
            swap(arr, left, minIndex);
            swap(arr, right, maxIndex);
            left++;
            right--;
        }
    }


    private static void swap(Object[] arr, int i, int j) {
        Object t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

}
