package cn.chenzw.toolkit.core.tree;

import java.util.List;

/**
 * @author chenzw
 */
@FunctionalInterface
public interface TreeNodePredicate {

    Boolean matches(TreeNode treeNode, List<TreeNode> treeNodes, Integer index, Integer level);
}
