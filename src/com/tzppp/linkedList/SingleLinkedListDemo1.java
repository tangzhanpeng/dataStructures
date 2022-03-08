package com.tzppp.linkedList;

import java.util.Stack;

public class SingleLinkedListDemo1 {
    public static void main(String[] args) {
        HeroNode heroNode = new HeroNode(1, "宋江", "及时雨");
        HeroNode heroNode1 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode heroNode2 = new HeroNode(3, "吴用", "智多星");
        HeroNode heroNode3 = new HeroNode(4, "林冲", "豹子头");
        HeroNode heroNode4 = new HeroNode(5, "林冲", "豹子头");
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.addNodeSerial(heroNode);
        singleLinkedList.addNodeSerial(heroNode1);
        singleLinkedList.addNodeSerial(heroNode2);

        SingleLinkedList singleLinkedList2 = new SingleLinkedList();
        singleLinkedList2.addNodeSerial(heroNode3);
        singleLinkedList2.addNodeSerial(heroNode4);

        System.out.println("list1:");
        singleLinkedList.list();
        System.out.println("list2:");
        singleLinkedList2.list();
        SingleLinkedList singleLinkedList3 = new SingleLinkedList();
        singleLinkedList3.setHead(SingleLinkedList.mergeList(singleLinkedList.getHead(), singleLinkedList2.getHead()));
        System.out.println("合并之后的node:");
        singleLinkedList3.list();


//        singleLinkedList.addNode(heroNode);
//        singleLinkedList.addNode(heroNode2);
//        singleLinkedList.addNode(heroNode1);
//        singleLinkedList.addNode(heroNode3);

//        singleLinkedList.addNodeSerial(heroNode);
//        singleLinkedList.addNodeSerial(heroNode2);
//        singleLinkedList.addNodeSerial(heroNode1);
//        singleLinkedList.addNodeSerial(heroNode3);
//        System.out.println("反转之前的list:");
//        singleLinkedList.list();
//        System.out.println("逆序打印:");
//        singleLinkedList.reversePrint(singleLinkedList.getHead());
//        System.out.println("递归逆序打印:");
//        singleLinkedList.reversePrintRecursion(singleLinkedList.getHead().getNext());
//        singleLinkedList.list();
//        System.out.println("反转之后的list:");
//        singleLinkedList.reverseList(singleLinkedList.getHead());
//        singleLinkedList.list();
//        singleLinkedList.updateNode(heroNode1);
//        singleLinkedList.updateNode(heroNode4);
//        singleLinkedList.list();
//        singleLinkedList.delNode(5);
//        singleLinkedList.delNode(4);
//        singleLinkedList.list();
    }
}

//定义SingleLinkedList管理我们的英雄
class SingleLinkedList {
    // 先初始化一个头节点，头节点不要动
    private HeroNode head = new HeroNode(0, "", "");//不存放具体的数据

    public HeroNode getHead() {
        return head;
    }

    public void setHead(HeroNode head) {
        this.head = head;
    }

    // 添加节点到单向链表
    // 思路，当不考虑编号的顺序时
    // 1.找到最后的节点
    // 2.将node指向最后的节点的next即可
    public void addNode(HeroNode node) {
        // 因为head节点不能动，因此需要一个临时node
        HeroNode temNode = head;
        while (true) {
            if (temNode.getNext() == null) {
                temNode.setNext(node);
                break;
            }
            temNode = temNode.getNext();
        }
    }

    /**
     * 单向链表的有序添加
     * @param node
     */
    public void addNodeSerial(HeroNode node) {
        HeroNode temNode = head;
        boolean flag = false;
        while (true) {
            if (temNode.getNext() == null) {
                break;
            }
            if (temNode.getNext().getNo() > node.getNo()) {
                break;
            } else if (temNode.getNext().getNo() == node.getNo()) {
                flag = true;
                break;
            }
            temNode = temNode.getNext();
        }
        if (flag) {
            System.out.printf("加入的英雄编号%d已存在\n", node.getNo());
        } else {
            node.setNext(temNode.getNext());
            temNode.setNext(node);
        }
    }

    /**
     * 修改单向链表的节点
     * @param node
     */
    public void updateNode(HeroNode node) {
        HeroNode temNode = head.getNext();
        boolean flag = false;
        while (temNode != null) {
            if (temNode.getNo() == node.getNo()) {
                flag = true;
                break;
            }
            temNode = temNode.getNext();
        }
        if (flag) {
            temNode.setName(node.getName());
            temNode.setNickName(node.getNickName());
        } else {
            System.out.printf("链表中没有对应的英雄编号%d\n", node.getNo());
        }
    }

    /**
     * 删除单向链表的节点
     * @param no
     */
    public void delNode(int no) {
        HeroNode temNode = head;
        boolean flag = false;
        while (true) {
            if (temNode.getNext() == null) {
                break;
            }
            if (temNode.getNext().getNo() == no) {
                flag = true;
                break;
            }
            temNode = temNode.getNext();
        }
        if (flag) {
            HeroNode delNode = temNode.getNext();
            temNode.setNext(delNode.getNext());
            delNode.setNext(null); // gc回收
        } else {
            System.out.printf("链表中没有对应的英雄编号%d\n", no);
        }

    }

    /**
     * 单向链表的打印
     */
    public void list() {
        if (head.getNext() == null) {
            System.out.println("链表为空");
            return;
        }
        // 因为头节点不能动，因此需要一个辅助变量来遍历
        HeroNode temNode = head.getNext();
        while (temNode != null) {
            System.out.println(temNode);
            temNode = temNode.getNext();
        }
    }

    /**
     * 获取单向链表的有效节点
     * @param head
     * @return
     */
    public int getListSize(HeroNode head) {
        if (head == null || head.getNext() == null) {
            return 0;
        }
        int size = 0;
        HeroNode temNode = head.getNext();
        while (temNode != null) {
            ++size;
            temNode = temNode.getNext();
        }
        return size;
    }

    /**
     * 获取倒数lastIndex的节点
     * @param head
     * @param lastIndex
     * @return
     */
    public HeroNode getNodeByLastIndex(HeroNode head, int lastIndex) {
        if (head == null || head.getNext() == null) {
            return null;
        }
        int length = getListSize(head);
        if (length <= 0 || lastIndex > length) {
            return null;
        }
        HeroNode temNode = head.getNext();
        for (int i = 0; i < length - lastIndex; ++i) {
            temNode = temNode.getNext();
        }
        return temNode;
    }

    /**
     * 反转单向链表
     * @param head
     */
    public void reverseList(HeroNode head) {
        if (head == null || head.getNext() == null || head.getNext().getNext() == null) {
            return;
        }
        HeroNode temNode = head.getNext();
        HeroNode newNode = new HeroNode(-1, "", "");
        HeroNode next;
        while (temNode != null) {
            next = temNode.getNext();
            temNode.setNext(newNode.getNext());
            newNode.setNext(temNode);
            temNode = next;
        }
        head.setNext(newNode.getNext());
    }

    /**
     * 逆序打印单向链表
     * @param head
     */
    public void reversePrint(HeroNode head) {
        if (head == null || head.getNext() == null) {
            return;
        }
        Stack<HeroNode> heroNodeStack = new Stack<>();
        HeroNode next = head.getNext();
        while (next != null) {
            heroNodeStack.push(next);
            next = next.getNext();
        }
        while (heroNodeStack.size() > 0){
            System.out.println(heroNodeStack.pop());
        }
    }

    /**
     * 使用递归的方式逆向打印单向链表
     * @param node
     */
    public void reversePrintRecursion (HeroNode node) {
        if (node == null) {
            return;
        }
        if (node.getNext() == null) {
            System.out.println(node);
        } else {
            reversePrintRecursion(node.getNext());
            System.out.println(node);
        }
    }

    /**
     * 合并两个有序的单向链表
     * @param head1
     * @param head2
     * @return
     */
    public static HeroNode mergeList(HeroNode head1, HeroNode head2) {
        if (head1 == null || head1.getNext() == null || head2 == null || head2.getNext() == null) {
            return null;
        }

        HeroNode tem1 = head1.getNext();
        HeroNode tem2 = head2.getNext();
        HeroNode newNode = new HeroNode(-1, "", "");
        HeroNode tem3 = newNode;
        while (tem1 != null || tem2 != null) {
            if (tem1 != null && (tem2 == null || tem1.getNo() < tem2.getNo())) {
                tem3.setNext(tem1);
                tem1 = tem1.getNext();
            } else {
                tem3.setNext(tem2);
                tem2 = tem2.getNext();
            }
            tem3 = tem3.getNext();
        }
        return newNode;
    }
}

//定义HeroNode，每个HeroNode对象就是一个节点
class HeroNode {

    private int no;
    private String name;
    private String nickName;
    private HeroNode next; //指向下一个节点

    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public HeroNode getNext() {
        return next;
    }

    public void setNext(HeroNode next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName +
                '}';
    }
}