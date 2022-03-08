package com.tzppp.tree.huffManCode;

import java.io.*;
import java.util.*;

/**
 * 霍夫曼压缩、解压文件
 */
public class HuffmanCodeDemo {
    public static void main(String[] args) throws FileNotFoundException {
//        String text = "hello world";
//        byte[] bytes = text.getBytes();
//        HuffmanZip huffmanTree = new HuffmanZip(bytes);
//        System.out.println(huffmanTree.getHuffmanCode());
//        System.out.println(huffmanTree.getHuffmanMap());
//        System.out.println(Arrays.toString(huffmanTree.getHuffmanByte()));
//
//        HuffmanReZip huffmanReZip = new HuffmanReZip(huffmanTree.getHuffmanByte(), huffmanTree.getHuffmanMap(), huffmanTree.getLastIndex());
//        System.out.println(huffmanReZip.getHuffmanCode());
//        System.out.println(new String(huffmanReZip.getSourceBytes()));

        //测试文件压缩
//        String srcFile = "/Users/sqmall/Desktop/IMG_0026.jpg";
//        String detFile = "/Users/sqmall/Desktop/dst.zip";
//        zipFile(srcFile, detFile);
//        System.out.println("压缩完毕");

        String zipFile = "/Users/sqmall/Desktop/dst.zip";
        String dstFile  = "/Users/sqmall/Desktop/new.jpg";
        unZip(zipFile, dstFile);
        System.out.println("解压成功");
    }

    /**
     * 压缩文件
     * @param srcFile
     * @param dstFile
     */
    public static void zipFile(String srcFile, String dstFile) {
        FileInputStream is = null;
        OutputStream os = null;
        ObjectOutputStream oos = null;
        try {
            is = new FileInputStream(srcFile);
            byte[] b = new byte[is.available()];
            // 读取文件
            is.read(b);
            //压缩
            HuffmanZip huffmanTree = new HuffmanZip(b);
            // 创建文件的输出流，存放压缩文件
            os = new FileOutputStream(dstFile);
            // 创建一个和文件输出关联的ObjectOutputStream
            oos = new ObjectOutputStream(os);
            // 对象流写入霍夫曼编码，是为了以后恢复源文件时使用
            oos.writeObject(huffmanTree.getHuffmanByte());
            // 把 霍夫曼编码后的字节数组写入压缩文件
            oos.writeObject(huffmanTree.getHuffmanMap());
            // 把最后的index传入
            oos.writeObject(huffmanTree.getLastIndex());
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }finally {
            try {
                is.close();
                os.close();
                oos.close();
            }catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }

    /**
     * 解压
     * @param zipFile
     * @param dstFile
     */
    public static void unZip(String zipFile, String dstFile) {
        InputStream is = null;
        ObjectInputStream oos = null;
        OutputStream os = null;

        try {
            is = new FileInputStream(zipFile);
            oos = new ObjectInputStream(is);
            byte[] huffmanBytes = (byte[]) oos.readObject();
            Map<Byte, String> huffmanMap = (Map<Byte, String>) oos.readObject();
            int lastIndex = (int) oos.readObject();

            HuffmanReZip huffmanReZip = new HuffmanReZip(huffmanBytes, huffmanMap, lastIndex);
            os = new FileOutputStream(dstFile);
            os.write(huffmanReZip.getSourceBytes());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                os.close();
                oos.close();
                is.close();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}

class HuffmanReZip {
    private byte[] huffmanBytes;
    private String huffmanCode;
    private int lastIndex;
    private Map<Byte, String> huffmanMap;
    private byte[] sourceBytes;

    public String getHuffmanCode() {
        return huffmanCode;
    }

    public byte[] getSourceBytes() {
        return sourceBytes;
    }

    public HuffmanReZip(byte[] huffmanBytes, Map<Byte, String> huffmanMap, int lastIndex) {
        this.huffmanBytes = huffmanBytes;
        this.huffmanMap = huffmanMap;
        this.lastIndex = lastIndex;
        this.decode();
    }

    /**
     * 解码
     */
    private void decode() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < huffmanBytes.length; i++) {
            stringBuilder.append(byteToBitString(huffmanBytes[i], i != huffmanBytes.length - 1));
        }
        this.huffmanCode = stringBuilder.toString();
        Map<String, Byte> reHuffManMap = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanMap.entrySet()) {
            reHuffManMap.put(entry.getValue(), entry.getKey());
        }
        stringBuilder = new StringBuilder();
        String tem;
        List<Byte> byteList = new ArrayList<>();
        for (int i = 0, j = 1; i < huffmanCode.length() && j <= huffmanCode.length(); ++j) {
            tem = huffmanCode.substring(i, j);
            if (reHuffManMap.containsKey(tem)) {
                Byte c = reHuffManMap.get(tem);
                stringBuilder.append(c);
                byteList.add(c);
                i = j;
            }
        }
        sourceBytes = new byte[byteList.size()];
        for (int i = 0; i < byteList.size(); i++) {
            sourceBytes[i] = byteList.get(i);
        }
    }

    /**
     * byte转字符串，最后不足8位的要特殊判断
     * @param b
     * @param flag
     * @return
     */
    private String byteToBitString(byte b, boolean flag) {
        if (flag || lastIndex == 8) {
            return Integer.toBinaryString((b & 0xFF) + 0x100).substring(1);
        } else {
            return Integer.toBinaryString(b + 0x100).substring(9 - lastIndex);
        }
    }
}

class HuffmanZip {

    private Node node;
    private byte[] sourceBytes;
    private Map<Byte, String> huffmanMap;
    private String huffmanCode;
    private byte[] huffmanByte;
    private int lastIndex;

    /**
     * 初始化霍夫曼树
     *
     * @param sourceBytes
     */
    public HuffmanZip(byte[] sourceBytes) {
        if (sourceBytes == null || sourceBytes.length == 0) {
            return;
        }
        this.sourceBytes = sourceBytes;
        node = getHuffManTree();
        huffmanMap = new HashMap<>();
        getHuffmanMap(node, new StringBuilder());
        createHuffmanCode();
        createHuffmanByte();
    }

    /**
     * 创建霍夫曼树
     *
     * @param
     * @return
     */
    private Node getHuffManTree() {
        Map<Byte, Integer> cMap = new HashMap<>();
        for (byte c : sourceBytes) {
            Integer count = cMap.get(c);
            if (count != null) {
                cMap.put(c, count + 1);
            } else {
                cMap.put(c, 1);
            }
        }
        Queue<Node> nodeQueue = new PriorityQueue<>();
        for (Map.Entry<Byte, Integer> entry : cMap.entrySet()) {
            nodeQueue.add(new Node(entry.getValue(), entry.getKey()));
        }

        while (nodeQueue.size() > 1) {
            Node leftNode = nodeQueue.poll();
            Node rightNode = nodeQueue.poll();
            Node parentNode = new Node(leftNode.getVal() + rightNode.getVal());
            parentNode.setLeft(leftNode);
            parentNode.setRight(rightNode);
            nodeQueue.add(parentNode);
        }
        return nodeQueue.poll();
    }

    /**
     * 生成霍夫曼编码表
     *
     * @param node
     * @param tem
     */
    private void getHuffmanMap(Node node, StringBuilder tem) {
        if (node.getLeft() == null && node.getRight() == null) {
            huffmanMap.put(node.getC(), tem.toString());
        }
        if (node.getLeft() != null) {
            getHuffmanMap(node.getLeft(), tem.append("0"));
            tem.deleteCharAt(tem.length() - 1);
        }
        if (node.getRight() != null) {
            getHuffmanMap(node.getRight(), tem.append("1"));
            tem.deleteCharAt(tem.length() - 1);
        }
    }

    /**
     * 获取霍夫曼byte数组
     */
    private void createHuffmanByte() {
        int len = (huffmanCode.length() + 7) / 8;
        huffmanByte = new byte[len];
        for (int i = 0; i < len; ++i) {
            String tem;
            if (i == len - 1) {
                tem = huffmanCode.substring(i * 8);
                lastIndex = tem.length();
            } else {
                tem = huffmanCode.substring(i * 8, i * 8 + 8);
            }
            huffmanByte[i] = (byte) Integer.parseInt(tem, 2);
        }
    }

    /**
     * 生成霍夫曼编码
     */
    private void createHuffmanCode() {
        StringBuilder tem = new StringBuilder();
        for (byte c : sourceBytes) {
            tem.append(huffmanMap.get(c));
        }
        this.huffmanCode = tem.toString();
    }

    /**
     * 获取最后一个元素的二进制长度
     * @return
     */
    public int getLastIndex() {
        return lastIndex;
    }

    /**
     * 获取霍夫曼压缩字节数组
     * @return
     */
    public byte[] getHuffmanByte() {
        return huffmanByte;
    }

    /**
     * 取霍夫曼编码
     *
     * @return
     */
    public String getHuffmanCode() {
        return huffmanCode;
    }


    /**
     * 获得霍夫曼编码表
     *
     * @return
     */
    public Map<Byte, String> getHuffmanMap() {
        return huffmanMap;
    }


    /**
     * 前序遍历霍夫曼树
     */
    public void preOrder() {
        if (this.node == null) {
            System.out.println("霍夫曼树为空～");
        }
        this.node.preOrder();
    }
}

class Node implements Comparable<Node> {
    private int val;
    private byte c;
    private Node left;
    private Node right;

    public Node(int val, byte c) {
        this(val);
        this.c = c;
    }

    public Node(int val) {
        this.val = val;
    }

    public byte getC() {
        return c;
    }

    public void setC(byte c) {
        this.c = c;
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

    @Override
    public String toString() {
        return "Node{" +
                "val=" + val +
                ", c=" + c +
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

    @Override
    public int compareTo(Node o) {
        return this.val - o.val;
    }
}