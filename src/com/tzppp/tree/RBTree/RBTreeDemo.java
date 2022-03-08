package com.tzppp.tree.RBTree;

/**
 * 红黑树，权值以int为例子，真正的红黑树只要修改权值为范型，并重写权值对象的compareTo方法即可
 */
public class RBTreeDemo {
    public static void main(String[] args) {
        int[] arr = {10, 9, 8, 6, 5, 4, 3};
        RBTree tree = new RBTree();
        for (int i : arr) {
            tree.add(i);
        }
        tree.infixOrder();
        tree.deleteNode(2);
        System.out.println("删除节点后");
        tree.infixOrder();
//        RBTree tree = new RBTree();
//        tree.add(11);
//        System.out.println("插入11后");
//        tree.infixOrder();
//        tree.add(2);
//        System.out.println("插入2后");
//        tree.infixOrder();
//        tree.add(14);
//        System.out.println("插入14后");
//        tree.infixOrder();
//        tree.add(1);
//        System.out.println("插入1后");
//        tree.infixOrder();
//        tree.add(7);
//        System.out.println("插入7后");
//        tree.infixOrder();
//        tree.add(15);
//        System.out.println("插入15后");
//        tree.infixOrder();
//        tree.add(5);
//        System.out.println("插入5后");
//        tree.infixOrder();
//        tree.add(8);
//        System.out.println("插入8后");
//        tree.infixOrder();
//        tree.add(4);
//        System.out.println("插入4后");
//        tree.infixOrder();
    }
}

class RBTree {

    private static final boolean RED = false;
    private static final boolean BLACK = true;

    private RBNode root;

    public void add(int v) {
        add(new RBNode(v));
    }

    /**
     * 添加节点
     *
     * @param rbNode 待添加的节点
     */
    private void add(RBNode rbNode) {
        // 插入节点的父节点
        RBNode preNode = null;
        RBNode tem = this.root;

        while (tem != null) {
            // 等于的往右边挂
            preNode = tem;
            if (rbNode.v < tem.v) {
                tem = tem.left;
            } else {
                tem = tem.right;
            }
        }

        if (preNode != null) {
            rbNode.parent = preNode;
            if (rbNode.v < preNode.v) {
                preNode.left = rbNode;
            } else {
                preNode.right = rbNode;
            }
        } else {
            this.root = rbNode;
        }
        // 调整节点
        addFix(rbNode);
    }

    /**
     * 调整红黑树
     * ①、插入节点的父节点和其叔叔节点（祖父节点的另一个子节点）均为红色。
     * ②、插入节点的父节点是红色的，叔叔节点是黑色的，且插入节点是其父节点的右子节点。
     * ③、插入节点的父节点是红色的，叔叔节点是黑色的，且插入节点是其父节点的左子节点。
     * n-儿子节点，p-爸爸节点，g-爷爷节点，u-叔叔节点
     *
     * @param n 儿子节点
     */
    private void addFix(RBNode n) {
        RBNode p, g, u;
        while ((p = n.parent) != null && p.color == RED) {
            g = p.parent;
            // 爸爸节点是爷爷节点的左子节点
            if (g.left == p) {
                u = g.right;
                // 叔叔节点为红色（意味左右黑色路径计算截止到爸爸层，就是爸爸没有其它孩子）
                if (u != null && u.color == RED) {
                    p.color = BLACK;
                    u.color = BLACK;
                    g.color = RED;
                    // g当作新节点继续判断
                    n = g;
                    continue;
                }
                // 叔叔节点为黑色（那么要么叔叔是null, 或者爸爸还有其它孩子）
                // 如果儿子节点是爸爸节点的右子节点,以儿子为支撑进行左旋
                if (p.right == n) {
                    p.leftRotate();
                    // 因为进行了左旋因此需要交换一下父子节点
                    RBNode tem = n;
                    n = p;
                    p = tem;
                }
                // 下面以爸爸节点为支撑进行右旋，当然先把父节点图黑，爷爷节点图红
                p.color = BLACK;
                g.color = RED;
                g.rightRotate();
                break;
            }
            // 爸爸节点是爷爷节点的右子节点
            else {
                u = g.left;
                // 叔叔节点为红色
                if (u != null && u.color == RED) {
                    p.color = BLACK;
                    u.color = BLACK;
                    g.color = RED;
                    n = g;
                    continue;
                }
                // 叔叔节点为黑色
                // 儿子节点是爸爸节点的左子节点，以儿子为支撑进行右旋
                if (p.left == n) {
                    p.rightRotate();
                    // 交换父子节点
                    RBNode tem = n;
                    n = p;
                    p = tem;
                }
                // 爸爸节点涂黑，爷爷节点涂红
                p.color = BLACK;
                g.color = RED;
                g.leftRotate();
                break;
            }
        }
        // 如果只进行了涂色或者插入的解释根节点那么要把根节点涂黑
        if (root == n) {
            n.color = BLACK;
        }
    }

    /**
     * 删除节点
     * 1.找到待删除节点
     * 2.判断是否为根节点
     * 1.如果是根节点：
     * 1.只有一个根节点
     * 2.根节点只有一个子节点
     * 3.根节点右2个子节点
     * 2. 如果不是根节点：
     * 1.叶子节点
     * 2.只有一个子节点
     * 3.有两个子节点
     *
     * @param v 节点值
     */
    public void deleteNode(int v) {
        if (this.root == null) {
            System.out.println("红黑树为空");
            return;
        }
        // 创建一个搜索指针
        RBNode rbNode = searchNode(v);
        if (rbNode == null) {
            System.out.println("节点" + v + "不存在");
            return;
        }
        deleteNode(rbNode);
    }

    private void deleteNode(RBNode node) {
        RBNode p = node.parent;
        if (node.left != null && node.right != null) {
            // 当前节点换为前驱节点，删除前驱节点，即左子树最大的节点preNode, preNode必然为叶子节点或者只有一个子节点
            RBNode preNode = node.left;
            int max = preNode.v;
            while (preNode.right != null) {
                preNode = preNode.right;
                max = preNode.v;
            }
            node.v = max;
            // 删除前驱节点
            deleteNode(preNode);
        } else {
            RBNode nextNode = (node.left != null ? node.left : node.right);
            if (p == null) {
                this.root = nextNode;
            } else {
                if (p.left == node) {
                    p.left = nextNode;
                } else {
                    p.right = nextNode;
                }
            }
            if (nextNode != null) {
                nextNode.parent = p;
            }
            // 实际删除节点的颜色为黑色才需要进行调整，因为如果是红色并不会改变黑色节点的数量，
            // nextNode为null则意味root也为null
            if (node.color == BLACK) {
                deleteFix(nextNode, p);
            }
        }
    }

    /**
     * 删除节点进行红黑调整
     *
     * @param n 替换删除节点的新节点
     * @param p 新节点的父节点不需要再取一遍比较方便
     */
    private void deleteFix(RBNode n, RBNode p) {
        /**
         * 说明：
         * 1.被删除的节点为黑色
         * 2.如果n也为黑色，则此路径上已经至少有2个黑色节点
         * 3.说明p必然存在，且p必然有两个子节点
         */
        RBNode u; //叔叔节点
        while (n != root && n.color == BLACK) {
            // 子节点为左子节点
            if (n == p.left) {
                u = p.right;
                /**
                 * case1: n为"红+黑" 叔叔节点为红色，则爸爸节点为黑色，叔叔节点的两个子节点均为黑色
                 * (01) 将叔叔节点设为“黑色”。
                 * (02) 将爸爸节点设为“红色”。
                 * (03) 对爸爸节点进行左旋。
                 * (04) 左旋后，n指向没有改变，修改p和u的指向
                 */
                if (u.color == RED) {
                    u.color = BLACK;
                    p.color = RED;
                    p.leftRotate();
                    p = u.parent;
//                    u = p.right;
                } else if (u.color == BLACK) {
                    /**
                     * case2: n是“黑+黑”节点，叔叔节点是黑色，叔叔节点的两个孩子都是黑色。
                     * (01) 将叔叔节点设为“红色”。
                     * (02) 设置爸爸节点为新的n节点。
                     */
                    if (u.left.color == BLACK && u.right.color == BLACK) {
                        u.color = RED;
                        n = p;
                        p = n.parent;
                    }
                    /**
                     * case3: n是“黑+黑”节点，叔叔节点是黑色；u的左孩子是红色，u右孩子是黑色的
                     * (01) 将u的左孩子设为“黑色”。
                     * (02) 将u设为“红色”。
                     * (03) 对u进行右旋。
                     * (04) 右旋后，重新设置n的兄弟节点。
                     * 我们处理“Case 3”的目的是为了将“Case 3”进行转换，转换成“Case 4”,从而进行进一步的处理。
                     * 转换的方式是对叔叔节点点进行右旋；为了保证右旋后，它仍然是红黑树，
                     * 就需要在右旋前“叔叔节点的左孩子设为黑色”，同时“叔叔节点设为红色”；
                     * 右旋后，由于叔叔节点发生了变化，需要更新u，从而进行后续处理。
                     */
                    else if (u.left.color == RED && u.right.color == BLACK) {
                        u.left.color = BLACK;
                        u.color = RED;
                        u.rightRotate();
                        // u的指向就是右旋后的叔叔节点不需要重写指向（和旋转方法实现有关）
                    }
                    /**
                     * case4: n是“黑+黑”节点，叔叔节点是黑色；叔叔节点的右孩子是红色的，叔叔的左孩子任意颜色。
                     * (01) 将爸爸颜色赋值给叔叔。
                     * (02) 将爸爸设为“黑色”。
                     * (03) 将叔叔的右孩子设为“黑色”。
                     * (04) 对爸爸进行左旋。
                     * (05) 设置“n”为“根节点”。跳出循环
                     * 我们处理“Case 4”的目的是：去掉n中额外的黑色，将n变成单独的黑色。处理的方式是“：进行颜色修改，然后对爸爸进行左旋。下面，我们来分析是如何实现的。
                     *       为了便于说明，我们设置“当前节点”为S(Original Son)，“兄弟节点”为B(Brother)，“兄弟节点的左孩子”为BLS(Brother's Left Son)，“兄弟节点的右孩子”为BRS(Brother's Right Son)，“父节点”为F(Father)。
                     *       我们要对F进行左旋。但在左旋前，我们需要调换F和B的颜色，并设置BRS为黑色。为什么需要这里处理呢？因为左旋后，F和BLS是父子关系，而我们已知BL是红色，如果F是红色，则违背了“特性(4)”；为了解决这一问题，我们将“F设置为黑色”。 但是，F设置为黑色之后，为了保证满足“特性(5)”，即为了保证左旋之后：
                     *       第一，“同时经过根节点和S的分支的黑色节点个数不变”。
                     *              若满足“第一”，只需要S丢弃它多余的颜色即可。因为S的颜色是“黑+黑”，而左旋后“同时经过根节点和S的分支的黑色节点个数”增加了1；现在，只需将S由“黑+黑”变成单独的“黑”节点，即可满足“第一”。
                     *       第二，“同时经过根节点和BLS的分支的黑色节点数不变”。
                     *              若满足“第二”，只需要将“F的原始颜色”赋值给B即可。之前，我们已经将“F设置为黑色”(即，将B的颜色"黑色"，赋值给了F)。至此，我们算是调换了F和B的颜色。
                     *       第三，“同时经过根节点和BRS的分支的黑色节点数不变”。
                     *              在“第二”已经满足的情况下，若要满足“第三”，只需要将BRS设置为“黑色”即可。
                     */
                    else if (u.right.color == RED) {
                        u.color = p.color;
                        p.color = BLACK;
                        u.right.color = BLACK;
                        p.leftRotate();
                        break;
                    }
                }
            }
            // 子节点为右子节点则一切逻辑和上述左右相反
            else {
                u = p.left;
                if (u.color == RED) {
                    u.color = BLACK;
                    p.color = RED;
                    p.rightRotate();
                    p = u.parent;
//                    u = p.left;
                } else if (u.color == BLACK) {
                    if (u.right.color == BLACK && u.left.color == BLACK) {
                        u.color = RED;
                        n = p;
                        p = n.parent;
                    } else if (u.right.color == RED && u.left.color == BLACK) {
                        u.right.color = BLACK;
                        u.color = RED;
                        u.leftRotate();
                    } else if (u.left.color == RED) {
                        u.color = p.color;
                        p.color = BLACK;
                        u.left.color = BLACK;
                        p.rightRotate();
                        break;
                    }
                }
            }
        }
    }

//    private void deleteNode2(RBNode rbNode) {
//        RBNode parentNode = rbNode.parent;
//        boolean isLeft = isLeft(parentNode, rbNode);
//        // 叶子节点，不需要进行
//        if (rbNode.left == null && rbNode.right == null) {
//            // 根节点
//            if (parentNode == null) {
//                this.root = null;
//            } else {
//                if (isLeft) {
//                    parentNode.left = null;
//                } else {
//                    parentNode.right = null;
//                }
//            }
//        }
//        // 两个子节点
//        else if (rbNode.left != null && rbNode.right != null) {
//            // 当前节点换为前驱节点，删除前驱节点，即左字数最大的节点newNode, newNode必然为叶子节点或者只有一个子节点
//            RBNode preNode = rbNode.left;
//            int max = preNode.v;
//            while (preNode.right != null) {
//                preNode = preNode.right;
//                max = preNode.v;
//            }
//            rbNode.v = max;
//            // 删除前驱节点
//            deleteNode(preNode);
//        }
//        // 一个子节点
//        else {
//            RBNode nextNode = (rbNode.left != null ? rbNode.left : rbNode.right);
//            // 根节点
//            if (parentNode == null) {
//                this.root = nextNode;
//            } else {
//                if (isLeft) {
//                    parentNode.left = nextNode;
//                } else {
//                    parentNode.right = nextNode;
//                }
//                nextNode.parent = parentNode;
//            }
//        }
//
//    }

//    private boolean isLeft(RBNode p, RBNode n) {
//        return p != null && p.left != null && p.left.v == n.v;
//    }

    /**
     * 搜索节点
     *
     * @param v
     * @return
     */
    private RBNode searchNode(int v) {
        RBNode rbNode = this.root;
        while (rbNode != null) {
            if (rbNode.v == v) {
                break;
            }
            rbNode = rbNode.v > v ? rbNode.left : rbNode.right;
        }
        return rbNode;
    }

    public void infixOrder() {
        if (root == null) {
            System.out.println("红黑树为空～");
        }
        root.infixOrder();
    }

    class RBNode {
        int v; //权值
        boolean color;// 颜色false - 红， true
        RBNode left; // 左子节点
        RBNode right; // 右子节点
        RBNode parent; // 父节点

        public RBNode(int v) {
            this.v = v;
        }

        @Override
        public String toString() {
            return "" + v + (this.color == RED ? "R" : "B");
        }

        /**
         * 中序遍历
         */
        public void infixOrder() {
            if (this.left != null) {
                this.left.infixOrder();
            }
            System.out.println(this);
            if (this.right != null) {
                this.right.infixOrder();
            }
        }

        /**
         * 左旋
         */
        public void leftRotate() {
            if (this.right == null) {
                return;
            }
            RBNode rbNode = new RBNode(this.v);
            rbNode.left = this.left;
            if (rbNode.left != null) {
                rbNode.left.parent = rbNode;
            }

            rbNode.right = this.right.left;
            if (this.right.left != null) {
                this.right.left.parent = rbNode;
            }

            this.v = this.right.v;
            this.color = this.right.color;
            this.left = rbNode;
            rbNode.parent = this;

            this.right = this.right.right;
            if (this.right.right != null) {
                this.right.right.parent = this;
            }
        }

        /**
         * 右旋
         */
        public void rightRotate() {
            if (this.left == null) {
                return;
            }

            RBNode rbNode = new RBNode(this.v);
            rbNode.right = this.right;
            if (this.right != null) {
                this.right.parent = rbNode;
            }

            rbNode.left = this.left.right;
            if (this.left.right != null) {
                this.left.right.parent = rbNode;
            }

            this.v = this.left.v;
            this.color = this.left.color;
            this.right = rbNode;
            rbNode.parent = this;

            this.left = this.left.left;
            if (this.left.left != null) {
                this.left.left.parent = this;
            }

        }
    }
}