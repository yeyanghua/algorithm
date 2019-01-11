package com.cmxy.application.heap;

import com.cmxy.application.heap.data.Patient;

import java.awt.peer.CanvasPeer;

//优先队列的应用
//模拟医院排队，此时来了一个急诊病人（优先级最高）
public class HeapApplication {

    public static void main(String[] args) {
        //首先基于“堆”这种数据结构构建优先队列
        PriorityQueue priorityQueue = new PriorityQueue(10);
        //医院陆陆续续来了9个普通病人，他们在排队
        for (int i = 0; i < 9; i++) {
            Patient patient = new Patient("普通病人" + (i + 1), (int) (Math.random()*8));
            priorityQueue.enqueue(patient);
        }
        //此时突然来了一个急诊病人
        System.out.println(priorityQueue);
        Patient patient = new Patient("急诊病人", 10);
        priorityQueue.enqueue(patient);
        //医生开始处理
        System.out.println("当前处理的病人为:" + priorityQueue.dequeue());
        System.out.println("当前处理的病人为:" + priorityQueue.dequeue());
        System.out.println("当前处理的病人为:" + priorityQueue.dequeue());
        System.out.println("当前处理的病人为:" + priorityQueue.dequeue());
    }


}
