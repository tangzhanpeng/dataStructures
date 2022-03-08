package com.tzppp.stack;

import java.util.*;

/**
 * 后缀表达式（逆波兰表达式）计算器
 * 可以优化：
 * 1）支持小数
 * 2）表达式合法性检测
 * 3）表达式空格支持
 * 4）。。。
 */
public class PolandNotation {

    public static void main(String[] args) {
        String expression = "10+((2+3)*4)-5";
        System.out.println("中缀表达式为=" + expression);
        PolandNotation polandNotation = new PolandNotation();
        List<String> infillList = polandNotation.getInfillList(expression);
        System.out.println("中缀表达式的数组=" + infillList);
        List<String> polandList = polandNotation.getPolandExpression(infillList);
        System.out.println("后缀表达式为=" + polandList);
        int res = polandNotation.polandByList(polandList);
        System.out.println("计算结果=" + res);
    }

    private static final Map<String, Integer> operatorMap;

    static {
        operatorMap = new HashMap<>();
        operatorMap.put("*", 1);
        operatorMap.put("/", 1);
        operatorMap.put("+", 0);
        operatorMap.put("-", 0);
        operatorMap.put("(", -1);
        operatorMap.put(")", -1);
    }

    private int polandByList(List<String> polandList) {
        Stack<Integer> polandStack = new Stack<>();
        int res = 0;
        for (String s : polandList) {
            if (s.matches("\\d+")) {
                polandStack.push(Integer.parseInt(s));
            } else {
                int num1 = polandStack.pop();
                int num2 = polandStack.pop();
                if ("*".equals(s)) {
                    res = num1 * num2;
                } else if ("/".equals(s)) {
                    res = num2 / num1;
                } else if ("+".equals(s)) {
                    res = num1 + num2;
                } else if ("-".equals(s)) {
                    res = num2 - num1;
                } else {
                    throw new RuntimeException("符号错误～");
                }
                polandStack.push(res);
            }
        }
        return polandStack.pop();
    }

    /**
     * 将中缀表达式转换为后缀表达数
     *
     * @param infillList
     * @return
     */
    private List<String> getPolandExpression(List<String> infillList) {
        List<String> resList = new Stack<>(); // 结果栈
        Stack<String> operStack = new Stack<>(); // 符号栈
        for (String val : infillList) {
            // 1）如果是数字直接入res
            if (val.matches("\\d+")) {
                resList.add(val);
            } else if ("(".equals(val)) {
                // 2）如果是左括号则入符号栈
                operStack.push(val);
            } else if (")".equals(val)) {
                // 3) 如果是右括号，则依次弹出符号栈的运算符，并压入结果栈，直到遇到左括号为止，此时将一堆括号丢弃
                String oper = operStack.pop();
                while (!"(".equals(oper)) {
                    resList.add(oper);
                    oper = operStack.pop();
                }
            } else {
                //当val运算优先级小于等于符号栈的栈顶符号运算优先级，则将符号栈栈顶符号弹出压入到结果栈，直到栈顶符号优先级小于或者栈顶为空为止
                while (!operStack.isEmpty() && priority(val) <= priority(operStack.peek())) {
                    resList.add(operStack.pop());
                }
                operStack.push(val);
            }
        }
        while (!operStack.isEmpty()) {
            resList.add(operStack.pop());
        }
        return resList;
    }

    /**
     * 将中缀表达式转换为数组
     *
     * @param expression
     * @return
     */
    private List<String> getInfillList(String expression) {
        char[] chars = expression.toCharArray();
        StringBuilder numStr = new StringBuilder();
        List<String> infillList = new ArrayList<>();
        for (char aChar : chars) {
            if (aChar >= 48 && aChar <= 57) {
                numStr.append(aChar - 48);
            } else {
                if (numStr.length() > 0) {
                    infillList.add(numStr.toString());
                    numStr = new StringBuilder();
                }
                infillList.add(String.valueOf(aChar));
            }
        }
        if (numStr.length() > 0) {
            infillList.add(numStr.toString());
        }
        return infillList;
    }

    private int priority(String oper) {
        return operatorMap.get(oper);
    }
}