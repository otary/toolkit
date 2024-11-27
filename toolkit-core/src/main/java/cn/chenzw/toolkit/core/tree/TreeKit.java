package cn.chenzw.toolkit.core.tree;

import java.util.List;

/**
 * @author chenzw
 */
public class TreeKit {

    /**
     * 遍历树
     *
     * @param treeNodes
     * @param nodeCallback
     */
    public static void iterate(List<TreeNode> treeNodes, TreeNodeCallback nodeCallback) {
        for (int i = 0; i < treeNodes.size(); i++) {
            recursion(treeNodes, null, i, 0, (treeNode, parentNode, index, level) -> {
                nodeCallback.callback(treeNode, parentNode, index, level);
                return false;
            });
        }
    }

    /**
     * 查找树节点
     *
     * @param treeNodes
     * @param nodePredicate
     * @return
     */
    public static TreeNodeContext findNode(List<TreeNode> treeNodes, TreeNodePredicate nodePredicate) {
        for (int i = 0; i < treeNodes.size(); i++) {
            TreeNodeContext treeNodeContext = recursion(treeNodes, null, i, 0, nodePredicate);
            if (treeNodeContext != null) {
                return treeNodeContext;
            }
        }
        return null;
    }

    private static TreeNodeContext recursion(List<TreeNode> treeNodes, TreeNode parentNode, Integer index, Integer level, TreeNodePredicate nodePredicate) {
        TreeNode treeNode = treeNodes.get(index);

        if (nodePredicate.matches(treeNode, parentNode, index, level)) {
            TreeNodeContext treeNodeContext = new TreeNodeContext();
            treeNodeContext.setTreeNode(treeNode);
            treeNodeContext.setIndex(index);
            treeNodeContext.setLevel(level);
            return treeNodeContext;
        }

        level++;
        List children = treeNode.getChildren();
        if (children != null) {
            for (int i = 0; i < children.size(); i++) {
                TreeNodeContext treeNodeContext = recursion(children, treeNode, i, level, nodePredicate);
                if (treeNodeContext != null) {
                    return treeNodeContext;
                }
            }
        }
        return null;
    }
}
