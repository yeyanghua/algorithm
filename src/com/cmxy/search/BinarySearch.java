package com.cmxy.search;

import java.util.HashMap;

public class BinarySearch<T extends Comparable> {

    /**
     * 非递归的实现
     *
     * @param arr    被查询的数组
     * @param target 要查找的目标
     * @param <T>
     * @return
     */
    public static <T extends Comparable> int search(T arr[], T target) {
        int l = 0;
        int r = arr.length - 1;
        while (l <= r) {
            //用加法会有bug，因为假设两个数都是非常大的整形，有可能l+r就溢出了
            //int mid = (l + r) / 2;
            int mid = l + (r - l) / 2; //虽然展开和 (l+r)/2 一样 但是避免了直接r+l可能导致的int类型溢出
            //等于中间值
            if (arr[mid].equals(target)) {
                return mid;
            }
            //小于中间值 则去左半边找
            if (target.compareTo(arr[mid]) < 0) {
                r = mid - 1;

            }
            //大于中间值
            if (target.compareTo(arr[mid]) > 0) {
                l = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 递归实现
     *
     * @param arr
     * @param target
     * @param <T>
     * @return
     */
    public static <T extends Comparable> int search2(T arr[], T target) {
        int index = search2(arr, 0, arr.length - 1, target);
        return index;
    }

    private static <T extends Comparable> int search2(T arr[], int l, int r, T target) {
        //递归终止条件
        if (l > r) {
            return -1;
        }
        int mid = l + (r - l) / 2;
        if (target.compareTo(arr[mid]) == 0) {
            return mid;
        }
        //查询的值小于中间值，则到左半边查询
        if (target.compareTo(arr[mid]) < 0) {
            r = mid - 1;
            return search2(arr, l, r, target);
        }
        //查询的值大于中间值,则到右半边查询
        if (target.compareTo(arr[mid]) > 0) {
            l = mid + 1;
            return search2(arr, l, r, target);
        }
        return -1;
    }


    public static void main(String[] args) {
        Integer[] arr = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 15, 18};
        int index = BinarySearch.search2(arr, 9);
        System.out.println(index);
    }
}
