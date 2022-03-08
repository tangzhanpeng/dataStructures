package com.tzppp.tree.ThreaderBinaryTree;

public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        ThreadedBinaryTree tree = new ThreadedBinaryTree();
        HeroNode node1 = new HeroNode(1, "1");
        HeroNode node2 = new HeroNode(2, "2");
        HeroNode node3 = new HeroNode(3, "3");
        HeroNode node4 = new HeroNode(4, "4");
        HeroNode node5 = new HeroNode(5, "5");
        HeroNode node6 = new HeroNode(6, "6");
        HeroNode node7 = new HeroNode(7, "7");
        node1.setLeft(node2);
        node1.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);
        node3.setRight(node7);
        tree.setRoot(node1);
        // 排序前
        System.out.println("排序前");
        tree.preOrder();
        System.out.println("排序后");
        tree.threaderHeroNode();
        tree.infixOrder();
    }
}

/**
 * 线索化二叉树
 */
class ThreadedBinaryTree {
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    // 进行二叉树线索化的方法
    private HeroNode tem;
    public void threaderHeroNode() {
        //node == null,不能线索化
        if (root == null) {
            return;
        }
        //一、先线索化左子树
        //二、线索化当前节点
        //三、线索化右子树
        tem = null;
        infixThreaded2(root);
        tem = null;
    }

    private synchronized void infixThreaded2(HeroNode node) {
        if (node.getLeft() != null) {
            infixThreaded2(node.getLeft());
        }
        if (tem != null) {
            if (tem.getRight() == null) {
                tem.setRight(node);
                tem.setRightType(1);
            }
            if (node.getLeft() == null) {
                node.setLeft(tem);
                node.setLeftType(1);
            }
        }
        tem = node;
        if (node.getRight() != null) {
            infixThreaded2(node.getRight());
        }
    }

    // 线索化遍历
    public void infixOrder() {
        HeroNode node = root;
        while (node != null) {
            while (node.getLeft() != null && node.getLeftType() == 0) {
                node = node.getLeft();
            }
            System.out.println(node.toString2());
            while (node.getRightType() == 1 && node.getRight() != null) {
                node = node.getRight();
                System.out.println(node.toString2());
            }
            node = node.getRight();
        }
    }

    private void infixOrder(HeroNode node) {
        if (node.getRightType() == 1 && node.getRight() != null) {
            System.out.println();
        }
    }

    // 前序遍历
    public void preOrder() {
        if (this.root != null) {
            root.preOrder();
        } else {
            System.out.println("二叉树为空不能遍历");
        }
    }
}

class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    //说明：
    //1.leftType = 0表示指向的是左子数，如果是1表示前驱节点
    //2.leftType = 0表示指向的是右子数，如果是1表示后继节点
    private int leftType;
    private int rightType;

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
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

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    public String toString2() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", left=" + (left == null ? "null" : left.toString2()) +
                ", right=" + (right == null ? "null" : right.toString2()) +
                ", leftType=" + leftType +
                ", rightType=" + rightType +
                '}';
    }

    // 前序
    public void preOrder() {
        System.out.println(this);
        //递归左子树
        if (this.left != null && this.leftType == 0) {
            this.left.preOrder();
        }
        // 递归向右子数前序遍历
        if (this.right != null && this.rightType == 0) {
            this.right.preOrder();
        }
    }

}
