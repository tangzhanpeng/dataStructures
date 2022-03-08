package com.tzppp.stack;

/**
 * 计算器
 */
public class Calculator {
    public static void main(String[] args) {
        //根据老师和前面的思路，完成表达式的运算
        String expression = "-30-2*6-2";
        //创建两个栈，数栈，符号栈
        LinkedStack_<Integer> numStack = new LinkedStack_<>();
        LinkedStack_<Character> operStack = new LinkedStack_<>();
        //定义需要的相关变量
        int index = 0;//用于扫描
        char ch = ' ';//将每次扫描得到的char保存到ch
        StringBuilder tem = new StringBuilder();
        //开始while循环扫描expression
        //顺便解决一下如果第一个符号为负数的时候，压入一个0
        if (expression.startsWith("-")) {
            numStack.push(0);
        }
        do {
            //依次得到expression的每一个字符
            ch = expression.substring(index, index + 1).charAt(0);
            //判断ch是什么，然后做相应的处理
            if (LinkedStack_.isOper(ch)) {// 如果是运算符
                if (tem.length() > 0) {
                    numStack.push(Integer.valueOf(tem.toString()));
                    tem = new StringBuilder();
                }
                //判断当前的符号栈是否为空
                while (!operStack.isEmpty() && LinkedStack_.priority(operStack.peek()) >= LinkedStack_.priority(ch)) {
                    // 如果符号栈有操作符，酒浸洗比较，如果当前的操作符的优先级小于或等于栈中的操作符，就需要从数栈中pop出两个数，
                    // 再从符号栈中pop出一个符号，进行运算，将得到结果入数栈，然后将当前的操作符入符号栈
                    pushRes(numStack, operStack);
                }
                operStack.push(ch);
            } else {
                //如果是数，则直接入数栈
//                numStack.push(ch - 48);
                // 如果是数的话则暂时保存数字；
                tem.append(ch - 48);
            }
            //让index + 1,并判断是否扫描到expression到最后
        } while (++index < expression.length());

        if (tem.length() > 0) {
            numStack.push(Integer.valueOf(tem.toString()));
        }

        //符号栈为空则说明结束运算
        while (!operStack.isEmpty()) {
            pushRes(numStack, operStack);
        }
        // 将数栈的最后数，pop出，就是结果
        System.out.printf("表达式%s=%d", expression, numStack.pop());
    }

    public static void pushRes(LinkedStack_<Integer> numStack, LinkedStack_<Character> operStack) {
        int num1, num2, res;
        char oper;
        num1 = numStack.pop();
        num2 = numStack.pop();
        oper = operStack.pop();
        res = LinkedStack_.cal(num1, num2, oper);
        numStack.push(res);
    }
}


class LinkedStack_<T> {

    private StackNode_<T> top; // 头节点
    private int size = 0; // 记录栈的节点数

    public T peek() {
        if (top == null) {
            return null;
        }
        return top.getValue();
    }

    public boolean isEmpty() {
        return top == null;
    }

    public void push(T value) {
        // 1.创建一个节点node
        // 2.node.next指向top
        // 3.top指向node
        StackNode_<T> node = new StackNode_<>(value);
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
        StackNode_<T> tem = top;
        int count = 0;
        while (tem != null) {
            System.out.printf("stack[%d]=%s\n", count, tem.getValue().toString());
            tem = tem.getNext();
            ++count;
        }
    }

    //返回运算符的优先级，优先级是程序员确定，优先级使用数字表示
    //数字越大，则优先级越高
    public static int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;// 假定目前表达式只有+，-，*，/
        }
    }

    //判断是不是一个运算符
    public static boolean isOper(char val) {
        return val == '*' || val == '/' || val == '+' || val == '-';
    }

    //计算方法
    public static int cal(int num1, int num2, char oper) {
        int res = 0;//res 用于存放计算的结果
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;//注意顺序
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }
}

class StackNode_<T> {
    private T value;
    private StackNode_<T> next;

    public StackNode_(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public StackNode_<T> getNext() {
        return next;
    }

    public void setNext(StackNode_<T> next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "StackNode{" +
                "value=" + value.toString() +
                '}';
    }
}