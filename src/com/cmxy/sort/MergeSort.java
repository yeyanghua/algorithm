package com.cmxy.sort;

import com.cmxy.helper.SortTestHelper;

import java.util.Arrays;

/**
 * 归并排序
 * 对一个数组排序，将数组分为两部分，分别对两边的数组排序然后归并以此类推。
 */
public class MergeSort {
    // 我们的算法类不允许产生任何实例
    private MergeSort() {
    }

    // 将arr[l...mid]和arr[mid+1...r]两部分进行归并
    private static void merge(Comparable[] arr, int l, int mid, int r) {
        //开辟一个临时空间用于辅助排序
        Comparable[] aux = Arrays.copyOfRange(arr, l, r + 1);

        // 初始化，i指向左半部分的起始索引位置l;j指向右半部分起始索引位置mid+1
        int i = l, j = mid + 1;
        for (int k = l; k <= r; k++) {
            if (i > mid) {  // 如果左半部分元素已经全部处理完毕
                arr[k] = aux[j - l];
                j++;
            } else if (j > r) {   // 如果右半部分元素已经全部处理完毕
                arr[k] = aux[i - l];
                i++;
            } else if (aux[i - l].compareTo(aux[j - l]) < 0) {  // 左半部分所指元素 < 右半部分所指元素
                arr[k] = aux[i - l];
                i++;
            } else {  // 左半部分所指元素 >= 右半部分所指元素
                arr[k] = aux[j - l];
                j++;
            }
        }
    }

    // 递归使用归并排序,对arr[l...r]的范围进行排序
    private static void sort(Comparable[] arr, int l, int r) {
        //递归终止条件
        if (l >= r) {
            return;
        }
        int mid = (l + r) / 2;
        //对左半边排序
        sort(arr, l, mid);
        //对右半边排序,
        sort(arr, mid + 1, r);
        //归并
        //优化(针对近乎有序的数组)
        if (arr[mid].compareTo(arr[mid + 1]) > 0) {
            merge(arr, l, mid, r);
        }
    }

    public static void sort(Comparable[] arr) {
        int n = arr.length;
        sort(arr, 0, n - 1);
    }

    // 测试MergeSort
    public static void main(String[] args) {

        // Merge Sort是我们学习的第一个O(nlogn)复杂度的算法
        // 可以在1秒之内轻松处理100万数量级的数据
        // 注意：不要轻易尝试使用SelectionSort, InsertionSort或者BubbleSort处理100万级的数据
        // 否则，你就见识了O(n^2)的算法和O(nlogn)算法的本质差异：）
        int N = 1000000;
        Integer[] arr = SortTestHelper.generateRandomArray(N, 0, 100000);
        // Integer[] arr2 = arr.clone();
        SortTestHelper.testSort("com.cmxy.sort.MergeSort", arr);
        //  SortTestHelper.testSort("com.cmxy.sort.SelectionSort", arr2);
        return;
    }
}