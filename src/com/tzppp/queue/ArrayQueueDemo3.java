package com.tzppp.queue;

import java.util.Scanner;

/**
 * 环形数组空出一个位置
 */
public class ArrayQueueDemo3 {
    public static void main(String[] args) {
        AnnularQueue arrayQueue = new AnnularQueue(3);
        char key = ' '; //接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出持续");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列取出数据");
            System.out.println("h(head):查看队列头数据");
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    arrayQueue.showQueue();
                    break;
                case 'a':
                    System.out.println("输入一个数");
                    int value = scanner.nextInt();
                    arrayQueue.addQueue(value);
                    break;
                case 'g': // 取出数据
                    try {
                        int res = arrayQueue.getQueue();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h': // 查看队列头的数据
                    try {
                        int res = arrayQueue.headQueue();
                        System.out.printf("队列头的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出");
    }
}

class AnnularQueue {
    private int maxSize;// 表示数组的最大容量
    private int front; // 指向队列头
    private int rear; // 指向队列尾
    private int[] arr; // 该数组用于存放数据，模拟队列

    // 创建队列的构造器
    public AnnularQueue(int maxSize) {
        this.maxSize = maxSize + 1;
        this.arr = new int[this.maxSize];
    }

    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    public boolean isEmpty() {
        return rear == front;
    }

    public void addQueue(int n) {
        // 判断队列是否满
        if (isFull()) {
            System.out.println("队列满，不能加入数据～");
            return;
        }
        arr[rear] = n;
        rear = (rear + 1) % maxSize;
    }

    public int getQueue() {
        // 判断队列是否为空
        if (isEmpty()) {
            // 抛出异常
            throw new RuntimeException("队列为空，不能取出数据～");
        }
        int tem = arr[front];
        front = ++front % maxSize;
        return tem;
    }

    public void showQueue() {
        //遍历
        if (isEmpty()) {
            System.out.println("队列空的，没有数据～～");
            return;
        }
        for (int i = front, j = rear, count = 0; i != j; count++) {
            System.out.printf("arr[%d]=%d\n", count, arr[i]);
            i = ++i % maxSize;
        }
    }

    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，没有数据～");
        }
        return arr[front];
    }
}