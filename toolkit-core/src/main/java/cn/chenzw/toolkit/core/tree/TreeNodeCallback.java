package cn.chenzw.toolkit.core.tree;

import java.util.List;

/**
 * @author chenzw
 */
@FunctionalInterface
public interface TreeNodeCallback {

    void callback(TreeNode treeNode, List<TreeNode> treeNodes, Integer index, Integer level);

}
