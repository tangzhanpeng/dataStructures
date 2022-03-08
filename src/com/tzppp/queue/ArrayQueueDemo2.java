package com.tzppp.queue;

import java.util.Scanner;

/**
 * 环形数组
 * 思路分析：
 * 1. front变量的含义做一个调整，front指向队列的第一个元素，也就是说arr[front]就是队列的第一个元素
 * front的初始值为0
 * 2. rear变量的含义也做一个调整，rear指向队列的最后一个元素的后一个位置，因为希望空出一个空间作为一个约定
 * rear的初始值也为0
 * 3. 当队列满时，条件就是（rear + 1）% maxSize = front
 * 4. 队列为空的条件就是，rear = front
 * 5. 当我们这样分析后，队列中有效的数据个数 （rear + maxsize - front）% maxSize
 * 6. 我们就可以在原来的代码上修改，形成环形数组
 * 7. 当前类没有空出一个空间
 */
public class ArrayQueueDemo2 {
    public static void main(String[] args) {
        //测试一把
        //创建一个队列
        ArrayQueue2 arrayQueue = new ArrayQueue2(3);
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

// 使用数据模拟队列
class ArrayQueue2 {
    private int maxSize;// 表示数组的最大容量
    private int front; // 指向队列头
    private int rear; // 指向队列尾
    private int[] arr; // 该数组用于存放数据，模拟队列

    // 创建队列的构造器
    public ArrayQueue2(int maxSize) {
        this.maxSize = maxSize;
        this.arr = new int[maxSize];
        this.front = 0;// 指向队列头部，分析出front是指向队列头的前一个位置
        this.rear = 0;// 指向队列尾部，指向队列尾的数据（即包含队列的最后一个数据）
    }

    // 判断队列是否满
    public boolean isFull() {
        return rear - front == maxSize;
    }

    // 判断队列是否为空
    public boolean isEmpty() {
        return rear == front;
    }

    // 添加数据到队列
    public void addQueue(int n) {
        // 判断队列是否满
        if (isFull()) {
            System.out.println("队列满，不能加入数据～");
            return;
        }
        arr[rear % maxSize] = n;
        rear++;
    }

    //获取队列的数据，出队列
    public int getQueue() {
        // 判断队列是否为空
        if (isEmpty()) {
            // 抛出异常
            throw new RuntimeException("队列为空，不能取出数据～");
        }
        int tem = arr[front % maxSize];
        front++;
        return tem;
    }

    //显示队列的所以数据
    public void showQueue() {
        //遍历
        if (isEmpty()) {
            System.out.println("队列空的，没有数据～～");
            return;
        }
        for (int i = front, j = rear, count = 0; i < j; count++, i++) {
            System.out.printf("arr[%d]=%d\n", count, arr[i % maxSize]);
        }
    }

    // 显示队列的头数据，注意不是取出数据
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，没有数据～");
        }
        return arr[front];
    }
}