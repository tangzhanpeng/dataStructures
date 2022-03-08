package com.tzppp.tree;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        // 创建二叉树
        BinaryTree binaryTree = new BinaryTree();
        // 创建节点
        HeroNode node1 = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关圣");
        HeroNode node6 = new HeroNode(6, "鲁智深");
        HeroNode node7 = new HeroNode(7, "燕三");
        // 手动创建该二叉树，后边再递归创建
        node1.setLeft(node2);
        node1.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);
        node3.setRight(node7);

        binaryTree.setRoot(node1);

        System.out.println("前序遍历");
        binaryTree.preOrder();
        System.out.println("中序遍历");
        binaryTree.infixOrder();
        System.out.println("后序遍历");
        binaryTree.postOrder();
//        HeroNode heroNode = binaryTree.postSearch(5);
//        if (heroNode == null) {
//            System.out.println("未找到");
//        } else {
//            System.out.println(heroNode);
//        }

//        if (binaryTree.delNode2(3)) {
//            System.out.println("删除成功");
//        } else {
//            System.out.println("删除失败");
//        }
//        System.out.println("前序遍历删除后");
//        binaryTree.preOrder();
    }
}

class BinaryTree {
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    // 前序遍历
    public void preOrder() {
        if (this.root != null) {
            root.preOrder();
        } else {
            System.out.println("二叉树为空不能遍历");
        }
    }

    public HeroNode preSearch(int no) {
        if (this.root != null) {
            return this.root.preSearch(no);
        } else {
            return null;
        }
    }

    // 中序遍历
    public void infixOrder() {
        if (this.root != null) {
            root.infixOrder();
        } else {
            System.out.println("二叉树为空不能遍历");
        }
    }

    public HeroNode infixSearch(int no) {
        if (this.root != null) {
            return this.root.infixSearch(no);
        } else {
            return null;
        }
    }

    // 后序遍历
    public void postOrder() {
        if (this.root != null) {
            root.postOrder();
        } else {
            System.out.println("二叉树为空不能遍历");
        }
    }

    public HeroNode postSearch(int no) {
        if (this.root != null) {
            return this.root.postSearch(no);
        } else {
            return null;
        }
    }

    public boolean delNode(int no) {
        if (root == null) {
            return false;
        }
        if (root.getNo() == no) {
            root = null;
            return true;
        }
        return root.delNode(no);
    }

    public boolean delNode2(int no) {
        if (root == null) {
            return false;
        }
        if (root.getNo() == no) {
            root = null;
            return true;
        }
        return root.delNode2(no);
    }

    private boolean delNode(int no, HeroNode node) {
        if (node.getLeft() != null) {
            if (node.getLeft().getNo() == no) {
                node.setLeft(null);
                return true;
            } else {
                if (delNode(no, node.getLeft())) {
                    return true;
                }
            }
        }
        if (node.getRight() != null) {
            if (node.getRight().getNo() == no) {
                node.setRight(null);
                return true;
            } else {
                if (delNode(no, node.getRight())) {
                    return true;
                }
            }
        }
        return false;
    }
}

class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

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

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    // 前序
    public void preOrder() {
        System.out.println(this);
        //递归左子树
        if (this.left != null) {
            this.left.preOrder();
        }
        // 递归向右子数前序遍历
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    // 前序查找
    public HeroNode preSearch(int no) {
        System.out.println("前序遍历～～～");
        if (this.no == no) {
            return this;
        }
        HeroNode heroNode = null;
        if (this.left != null) {
            heroNode = this.left.preSearch(no);
        }
        if (heroNode != null) {
            return heroNode;
        }
        if (this.right != null) {
            heroNode = this.right.preSearch(no);
        }

        return heroNode;
    }

    // 中序
    public void infixOrder() {
        // 递归向左子树中序遍历
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        // 递归向右子数种序遍历
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    // 中序查找
    public HeroNode infixSearch(int no) {
        HeroNode heroNode = null;
        if (this.left != null) {
            heroNode = this.left.infixSearch(no);
        }
        if (heroNode != null) {
            return heroNode;
        }
        System.out.println("中序遍历～～～");
        if (this.no == no) {
            return this;
        }
        if (this.right != null) {
            heroNode = this.right.infixSearch(no);
        }

        return heroNode;
    }

    // 后序
    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }

    // 后序查找
    public HeroNode postSearch(int no) {
        HeroNode heroNode = null;
        if (this.left != null) {
            heroNode = this.left.postSearch(no);
        }
        if (heroNode != null) {
            return heroNode;
        }
        if (this.right != null) {
            heroNode = this.right.postSearch(no);
        }
        if (heroNode != null) {
            return heroNode;
        }
        System.out.println("后序遍历～～～");
        if (this.no == no) {
            return this;
        }
        return null;
    }

    public boolean delNode(int no) {
        if (this.left != null) {
            if (this.left.no == no) {
                this.left = null;
                return true;
            } else {
                if (this.left.delNode(no)) {
                    return true;
                }
            }
        }
        if (this.right != null) {
            if (this.right.no == no) {
                this.right = null;
                return true;
            } else {
                return this.right.delNode(no);
            }
        }
        return false;
    }

    /**
     * 如果删除的是非叶子节点，如果有左子节点则提上来，否则右子节点提上来
     * @param no
     * @return
     */
    public boolean delNode2(int no) {
        if (this.left != null) {
            if (this.left.no == no) {
                setNodeNull(this, this.left, true);
                return true;
            } else {
                if (this.left.delNode(no)) {
                    return true;
                }
            }
        }
        if (this.right != null) {
            if (this.right.no == no) {
                setNodeNull(this, this.right, false);
                return true;
            } else {
                return this.right.delNode(no);
            }
        }
        return false;
    }

    private void setNodeNull(HeroNode f, HeroNode c, boolean left) {
        if (c.left != null) {
            if (left) {
                f.left = c.left;
            } else {
                f.right = c.left;
            }
        } else if (c.right != null) {
            if (left) {
                f.left = c.right;
            } else {
                f.right = c.right;
            }
        }
    }
}