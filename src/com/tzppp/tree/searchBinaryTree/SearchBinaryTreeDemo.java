package com.tzppp.tree.searchBinaryTree;

/**
 * 搜索二叉树
 */
public class SearchBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {9,2,1,7,8,4,3,6,5};
        SearchBinaryTree tree = new SearchBinaryTree(arr);
        tree.infixOrder();
        tree.deleteNode(7);
        System.out.println("删除节点后");
        tree.infixOrder();
    }
}

/**
 * 定义删除节点的时候使用前驱节点
 */
class SearchBinaryTree {
    private Node root;

    public Node getRoot() {
        return root;
    }

    /**
     * 生成搜索二叉树
     *
     * @param arr
     */
    public SearchBinaryTree(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        root = new Node(arr[0]);
        for (int i = 1; i < arr.length; ++i) {
            insertNode(new Node(arr[i]));
        }
    }

    /**
     * 搜索二叉树插入新的节点
     *
     * @param insertNode
     */
    public void insertNode(Node insertNode) {
        Node tem = root;
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
            if (tem.getLeft().getRight() == null) {
                tem.getLeft().setRight(tem.getRight());
                tem.setRight(null);
                if (direction == 0) {
                    node.setLeft(tem.getLeft());
                } else {
                    node.setRight(tem.getLeft());
                }
            } else {
                deleteFullNode(tem);
            }
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
            if (root.getLeft().getRight() == null) {
                root.getLeft().setRight(root.getRight());
                root.setRight(null);
                root = root.getLeft();
            } else {
                deleteFullNode(root);
            }
        }
        // 只有一个子节点
        else {
            root = (root.getLeft() != null ? root.getLeft() : root.getRight());
        }
    }

    /**
     * 删除满非叶子节点（有两个子节点的非叶子节点）
     * @param node
     */
    private void deleteFullNode(Node node) {
        Node preNode = node.getLeft();
        int max;
        while (true) {
            max = preNode.getRight().getVal();
            if (preNode.getRight().getRight() != null) {
                preNode = preNode.getRight();
            } else {
                break;
            }
        }
        preNode.setRight(preNode.getRight().getLeft());
        node.setVal(max);
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
    }
}

