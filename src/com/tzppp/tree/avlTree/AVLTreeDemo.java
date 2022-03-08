package com.tzppp.tree.avlTree;

/**
 * 平衡二叉排序树
 * 思考删除的时候如何保证还是一颗平衡二叉排序树
 */
public class AVLTreeDemo {
    public static void main(String[] args) {
        int[] arr = {9,2,1,7,8,4,3,6,5};
        AVLTree tree = new AVLTree(arr);
        tree.infixOrder();
        tree.deleteNode(9);
        System.out.println("删除节点后");
        tree.infixOrder();
    }
}

/**
 * 定义删除节点的时候使用前驱节点
 */
class AVLTree {
    private Node root;

    public Node getRoot() {
        return root;
    }

    /**
     * 生成搜索二叉树
     *
     * @param arr
     */
    public AVLTree(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        for (int i = 0; i < arr.length; ++i) {
            insertNode(new Node(arr[i]));
        }
    }

    /**
     * 搜索二叉树插入新的节点
     *
     * @param insertNode
     */
    public void insertNode(Node insertNode) {
        if (this.root == null) {
            this.root = insertNode;
            return;
        }
        Node tem = this.root;
        while (tem != null) {
            // 1）比父节点小
            if (insertNode.getVal() < tem.getVal()) {
                if (tem.getLeft() == null) {
                    tem.setLeft(insertNode);
                    break;
                } else {
                    tem = tem.getLeft();
                }
            }
            // 2）比父节点大
            else if (insertNode.getVal() > tem.getVal()) {
                if (tem.getRight() == null) {
                    tem.setRight(insertNode);
                    break;
                } else {
                    tem = tem.getRight();
                }
            }
            // 3)等于父节点直接挂在左子节点
            else {
                insertNode.setLeft(tem.getLeft());
                tem.setLeft(insertNode);
                break;
            }
        }
        // 进行AVL树的创建
        // 若右子节点高度超过左子节点高度1
        if (root.getRightHeight() - root.getLeftHeight() > 1) {
            //如果右子节点的左子节点高度大于右子节点的高度要先对右子树进行右旋
            if (root.getRight().getLeftHeight() > root.getRight().getRightHeight()) {
                root.getRight().dextral();
            }
            root.sinistral();
        } else if (root.getLeftHeight() - root.getRightHeight() > 1) {
            if (root.getLeft().getRightHeight() > root.getLeft().getLeftHeight()) {
                root.getLeft().sinistral();
            }
            root.dextral();
        }
    }

    /**
     * 搜索二叉树前序遍历
     */
    public void infixOrder() {
        if (root == null) {
            System.out.println("搜索二叉树为空～");
            return;
        }
        root.infixOrder();
    }

    /**
     * 删除搜索二叉树节点
     *
     * @param val
     */
    public boolean deleteNode(int val) {
        if (root == null) {
            return false;
        }
        Node tem = root;
        if (root.getVal() == val) {
            deleteRoot();
            return true;
        }
        while (tem != null) {
            if (tem.getVal() > val) {
                if (tem.getLeft() != null && tem.getLeft().getVal() == val) {
                    deleteNode(tem, 0);
                    return true;
                }
                tem = tem.getLeft();
            } else if (tem.getVal() < val) {
                if (tem.getRight() != null && tem.getRight().getVal() == val) {
                    deleteNode(tem, 1);
                    return true;
                }
                tem = tem.getRight();
            }
        }
        System.out.println("没有对应的节点");
        return false;
    }

    /**
     * 删除节点
     * 1.待删除的节点为叶子节点
     * 2.待删除的节点只有一个子节点
     * 3.待删除的节点有2个子节点
     * @param node     带删除的节点的父节点
     * @param direction 删除节点的方向，0表示左节点，1表示右节点
     */
    private void deleteNode(Node node, int direction) {
        Node tem = (direction == 0 ? node.getLeft() : node.getRight());
        // 1.删除叶子节点，直接删除叶子结点
        if (tem.getLeft() == null && tem.getRight() == null) {
            if (direction == 0) {
                node.setLeft(null);
            } else {
                node.setRight(null);
            }

        }
        // 3.左右节点都存在，则将待删除的节点的值设置为前驱节点的值，并将前驱节点的值删除（前驱节点有左子树就向上提）
        else if (tem.getLeft() != null && tem.getRight() != null) {
            deleteFullNode(tem);
        }
        // 2.只有一个节点，将子节点往上提
        else {
            Node nextNode = tem.getLeft() != null ? tem.getLeft() : tem.getRight();
            if (direction == 0) {
                node.setLeft(nextNode);
            } else {
                node.setRight(nextNode);
            }
        }
    }

    /**
     * 删除根节点
     */
    private void deleteRoot() {
        // 只有根节点
        if (root.getLeft() == null && root.getRight() == null) {
            root = null;
        }
        // 有2个子节点，将前驱节点提到root节点
        else if (root.getLeft() != null && root.getRight() != null) {
            deleteFullNode(root);
        }
        // 只有一个子节点
        else {
            root = (root.getLeft() != null ? root.getLeft() : root.getRight());
        }
    }

    /**
     * 删除满非叶子节点（有两个子节点的非叶子节点）
     * @param delNode
     */
    private void deleteFullNode(Node delNode) {
        Node preNode = delNode;
        Node maxNode = delNode.getLeft();
        int direction = 0;
        int max = maxNode.getVal();
        if (maxNode.getRight() != null) {
            direction = 1;
            do {
                preNode = maxNode;
                maxNode = maxNode.getRight();
                max = maxNode.getVal();
            }while (maxNode.getRight() != null);
        }
        deleteNode(preNode, direction);
        delNode.setVal(max);
    }

    class Node {
        private int val;
        private Node left;
        private Node right;

        public Node(int val) {
            this.val = val;
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public String toString2() {
            return "Node{" +
                    "val=" + val +
                    ", left=" + (left == null ? "null" : left.toString2()) +
                    ", right=" + (right == null ? "null" : right.toString2()) +
                    '}';
        }

        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    '}';
        }

        public void infixOrder() {
            if (this.getLeft() != null) {
                this.getLeft().infixOrder();
            }
            System.out.println(this);
            if (this.getRight() != null) {
                this.getRight().infixOrder();
            }
        }

        /**
         * 获取左子节点的高度
         * @return
         */
        public int getLeftHeight() {
            if (this.left == null) {
                return 0;
            } else {
                return this.left.getHeight();
            }
        }

        public int getRightHeight() {
            if (this.right == null) {
                return 0;
            } else {
                return this.right.getHeight();
            }
        }

        /**
         * 获取以当前节点为根节点的树的高度
         * @return
         */
        public int getHeight() {
            return Math.max(this.left == null ? 0 : this.left.getHeight(), this.right == null ? 0 : this.right.getHeight()) + 1;
        }

        /**
         * 以当前节点为根节点进行左旋
         * 前提，右子节点高度 - 左子节点高度 > 1 ，所以右子节点高度至少为2
         * 1.新建node值设置为当前节点
         * 2.node.left = this.node.left
         * 3.node.right = this.node.right.left
         * 4.当前节点的值设置为右节点的值
         * 5.this.left = node
         * 6.this.right = this.right.right
         */
        public void sinistral() {
            Node newNode = new Node(this.val);
            newNode.left = this.left;
            newNode.right = this.right.left;
            this.val = this.right.val;
            this.left = newNode;
            this.right = this.right.right;
        }

        /**
         * 以当前节点为根节点进行左旋
         * 前提，左子节点高度 - 右子节点高度 > 1 ，所以左子节点高度至少为2
         */
        public void dextral() {
            Node newNode = new Node(this.val);
            newNode.right = this.right;
            newNode.left = this.left.right;
            this.val = this.left.val;
            this.left = this.left.left;
            this.right = newNode;
        }
    }
}
