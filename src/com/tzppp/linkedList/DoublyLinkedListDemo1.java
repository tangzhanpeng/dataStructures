package com.tzppp.linkedList;

public class DoublyLinkedListDemo1 {
    public static void main(String[] args) {

        DoublyNode node1 = new DoublyNode(1, "宋江");
        DoublyNode node2 = new DoublyNode(2, "卢俊义");
        DoublyNode node3 = new DoublyNode(3, "鲁智深");
        DoublyNode node4 = new DoublyNode(4, "史迁");
        DoublyNode node5 = new DoublyNode(2, "林冲");

        DoublyLinkedList list = new DoublyLinkedList();
//        list.addNode(node1);
//        list.addNode(node3);
//        list.addNode(node2);
//        list.addNode(node4);
        list.addNodeSerial(node1);
        list.addNodeSerial(node3);
        list.addNodeSerial(node2);
        list.addNodeSerial(node4);
        System.out.println("最初的数据");
        list.list();
        System.out.println("修改后的数据");
        list.updateNode(node5);
        list.list();
        System.out.println("删除后的数据");
        list.delNode(2);
        list.list();
    }
}

class DoublyLinkedList {

    private DoublyNode head = new DoublyNode(-1, "");

    public void list() {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        // 因为头节点不能动，因此需要一个辅助变量来遍历
        DoublyNode temNode = head.next;
        while (temNode != null) {
            System.out.println(temNode);
            temNode = temNode.next;
        }
    }

    public void addNode(DoublyNode node) {
        DoublyNode temNode = head;
        while (temNode.next != null) {
            temNode = temNode.next;
        }
        temNode.next = node;
        node.pre = temNode;
    }

    public void addNodeSerial(DoublyNode node) {
        DoublyNode temNode = head;
        boolean flag = false;
        while (true) {
            if (temNode.next == null) {
                break;
            }
            if (temNode.next.no > node.no) {
                break;
            } else if (temNode.next.no == node.no) {
                flag = true;
                break;
            }
            temNode = temNode.next;
        }
        if (flag) {
            System.out.printf("加入的英雄编号%d已存在\n", node.no);
        } else {
            node.next = temNode.next;
            if (temNode.next != null) {
                temNode.next.pre = node;
            }
            node.pre = temNode;
            temNode.next = node;
        }
    }

    public void updateNode(DoublyNode node) {
        DoublyNode temNode = head.next;
        boolean flag = false;
        while (temNode != null) {
            if (temNode.no == node.no) {
                flag = true;
                break;
            }
            temNode = temNode.next;
        }
        if (flag) {
            temNode.name = node.name;
        } else {
            System.out.printf("链表中没有对应的英雄编号%d\n", node.no);
        }
    }

    public void delNode(int no) {
        DoublyNode temNode = head.next;
        boolean flag = false;
        while (temNode != null) {
            if (temNode.no == no) {
                flag = true;
                break;
            }
            temNode = temNode.next;
        }
        if (flag) {
            if (temNode.next != null) {
                temNode.next.pre = temNode.pre;
                temNode.pre.next = temNode.next;
            } else {
                temNode.pre.next = null;
            }
            temNode.pre = null;
            temNode.next = null; // gc回收
        } else {
            System.out.printf("链表中没有对应的英雄编号%d\n", no);
        }

    }

}

class DoublyNode {
    int no;
    String name;
    DoublyNode pre; //指向前一个节点
    DoublyNode next; //指向下一个节点

    public DoublyNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return "DoublyNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}