package com.tzppp.linkedList;

/**
 * 题目：
 * 1.有m个小孩，围成圈圈坐，m > 0
 * 2.从编号为K的小孩开始数数，数到n的时候该小孩出列，下一个小孩从1开始数数，直到所有的小孩均出列为止
 * 3.请给出所有小孩的出列顺序
 */
public class AnnularSingleLinkedListDemo {
    public static void main(String[] args) {
        JNode node1 = new JNode(1, "宋江");
        JNode node2 = new JNode(2, "卢俊义");
        JNode node3 = new JNode(3, "鲁智深");
        JNode node4 = new JNode(4, "史迁");
        JNode node5 = new JNode(5, "林冲");
        AnnularSingleLinkedList linkedList = new AnnularSingleLinkedList();
        linkedList.addNode(node1);
        linkedList.addNode(node2);
        linkedList.addNode(node3);
        linkedList.addNode(node4);
        linkedList.addNode(node5);
        linkedList.addNode(node1);
        linkedList.list();
        System.out.println(linkedList.getListSize(linkedList.getFirst()));
        linkedList.joseph(linkedList.getFirst(), 3);
    }
}

class AnnularSingleLinkedList {
    private JNode first;

    public JNode getFirst() {
        return first;
    }

    public void setFirst(JNode first) {
        this.first = first;
    }

    public void list() {
        if (first == null) {
            System.out.println("链表为空");
            return;
        }
        // 因为头节点不能动，因此需要一个辅助变量来遍历
        JNode temNode = first;
        int loopCount = 0;
        while (temNode != null && loopCount < 2) {
            System.out.println(temNode);
            temNode = temNode.next;
            if (temNode == first) {
                ++loopCount;
            }
        }
    }

    public void addNode(JNode node) {
        if (first == null) {
            first = node;
            return;
        }
        JNode temNode = first;
        while (temNode.next != null) {
            temNode = temNode.next;
        }
        temNode.next = node;
    }

    /**
     * 获取环形链表的有效节点数
     *
     * @param start
     * @return
     */
    public int getListSize(JNode start) {
        if (start == null) {
            return 0;
        }
        int size = 0;
        JNode temNode = first;
        int loopCount = 0;
        while (temNode != null && loopCount < 1) {
            temNode = temNode.next;
            ++size;
            if (temNode == first) {
                ++loopCount;
            }
        }
        return size;
    }

    public void joseph(JNode start, int k) {
        if (start == null || k < 1) {
            return;
        }
        //1.首先获取到环形链表的有效节点数
        JNode tem = start;
        //做法一、破坏掉环形链表，每次得到出列队员，则将此节点删除，直到环形链表的长度等于1时，输出并退出循环
        for (int size = getListSize(start); size > 0; --size) {
            if (size == 1) {
                System.out.println(tem);
            } else {
                int steps = k % size;
                for (int i = 1; i < steps; ++i) {
                    tem = tem.next;
                }
                System.out.println(tem.next);
                // 删除掉tem的下个节点
                tem.next = tem.next.next;
            }
        }
    }
}

class JNode {
    int no;
    String name;
    JNode next; //指向下一个节点

    public JNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return "JNode{" +
                "no=" + no +
                ", name='" + name +
                '}';
    }
}