package com.CK;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode node1 = new TreeNode(9);
        TreeNode node2 = new TreeNode(20);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(5);
        TreeNode node5 = new TreeNode(15);
        TreeNode node6 = new TreeNode(7);
//        TreeNode node6 = new TreeNode(4);
//        TreeNode node6 = new TreeNode(4);
//        TreeNode node9 = new TreeNode(8);
//        TreeNode node10 = new TreeNode(9);
//        TreeNode node6 = new TreeNode(4);
//        TreeNode node6 = new TreeNode(4);
//        TreeNode node13 = new TreeNode(9);
//        TreeNode node14 = new TreeNode(8);

        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.left = node5;
        node2.right = node6;
//        node4.left = node9;
//        node4.right = node10;
//        node6.left = node13;
//        node6.right = node14;

        root.printTree();
        Solution solution = new Solution();
        solution.zigzagLevelOrder(root);
    }
}

class Solution {
    List<TreeNode> bfArray = new ArrayList<>();
    boolean toRight = true;

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) return new ArrayList<>();
        List<List<Integer>> resList = new ArrayList<>();
        bfArray.add(root);
        bfArray.add(null);
        traverseBF(bfArray, resList);
        return resList;
    }

    public void traverseBF(List<TreeNode> bfArray, List<List<Integer>> resList) {
        while (bfArray.size() > 1) {
            TreeNode node = this.shiftArray(bfArray);
            if (node == null) {
                Collections.reverse(bfArray);
                resList.add(new ArrayList<>());
                toRight = !toRight;
                bfArray.add(null);
            } else {
                if (resList.isEmpty()) {
                    resList.add(new ArrayList<>());
                    resList.get(0).add(node.val);
                } else resList.get(resList.size() - 1).add(node.val);
                if (!toRight) {
                    if (node.right != null) bfArray.add(node.right);
                    if (node.left != null) bfArray.add(node.left);
                } else {
                    if (node.left != null) bfArray.add(node.left);
                    if (node.right != null) bfArray.add(node.right);
                }
            }
            traverseBF(bfArray, resList);
        }

    }

    private TreeNode shiftArray(List<TreeNode> arr) {
        TreeNode res = null;
        if (!arr.isEmpty()) {
            res = arr.get(0);
            arr.remove(0);
        }
        return res;
    }
}

// 用Queue and iterator
class Solution2 {
    public List<List> zigzagLevelOrder(TreeNode root) {
        List<List> res = new ArrayList<>();
        if (root == null) return res;

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        boolean order = true;
        int size = 1;

        while (!q.isEmpty()) {
            List<Integer> tmp = new ArrayList<>();
            for (int i = 0; i < size; ++i) {
                TreeNode n = q.poll();
                if (order) {
                    tmp.add(n.val);
                } else {
                    tmp.add(0, n.val);
                }
                if (n.left != null) q.add(n.left);
                if (n.right != null) q.add(n.right);
            }
            res.add(tmp);
            size = q.size();
            order = !order;
        }
        return res;
    }
}