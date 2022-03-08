package com.tzppp.stack;

import java.util.Scanner;

public class LinkedStackDemo {
    public static void main(String[] args) {
        LinkedStack<Integer> linkedStack = new LinkedStack<>();
        String key = ""; //接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("s(show)：显示栈");
            System.out.println("e(exit)：退出栈");
            System.out.println("p(push)：压入栈");
            System.out.println("pop(pop)：弹出栈");
            key = scanner.next();
            switch (key) {
                case "s":
                    linkedStack.showList();
                    break;
                case "p":
                    System.out.println("输入一个数");
                    int value = scanner.nextInt();
                    linkedStack.push(value);
                    break;
                case "pop":
                    try {
                        int res = linkedStack.pop();
                        System.out.printf("弹出的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "e":
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

class LinkedStack<T> {

    private StackNode<T> top; // 头节点
    private int size = 0; // 记录栈的节点数


    public boolean isEmpty() {
        return top == null;
    }

    public void push(T value) {
        // 1.创建一个节点node
        // 2.node.next指向top
        // 3.top指向node
        StackNode<T> node = new StackNode<>(value);
        if (top == null) {
            top = node;
        } else {
            node.setNext(top);
            top = node;
        }
        ++size;
    }

    public T pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空～");
        }
        T value = top.getValue();
        top = top.getNext();
        --size;
        return value;
    }

    public void showList() {
        if (isEmpty()) {
            System.out.println("栈空~");
            return;
        }
        StackNode<T> tem = top;
        int count = 0;
        while (tem != null) {
            System.out.printf("stack[%d]=%s\n", count, tem.getValue().toString());
            tem = tem.getNext();
            ++count;
        }
    }

//    //返回运算符的优先级，优先级是程序员确定，优先级使用数字表示
//    //数字越大，则优先级越高
//    public int priority(int oper) {
//        if (oper == '*' || oper == '/') {
//            return 1;
//        } else if (oper == '+' || oper == '-') {
//            return 0;
//        } else {
//            return -1;// 假定目前表达式只有+，-，*，/
//        }
//    }
}

class StackNode<T> {
    private T value;
    private StackNode<T> next;

    public StackNode(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public StackNode<T> getNext() {
        return next;
    }

    public void setNext(StackNode<T> next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "StackNode{" +
                "value=" + value.toString() +
                '}';
    }
}