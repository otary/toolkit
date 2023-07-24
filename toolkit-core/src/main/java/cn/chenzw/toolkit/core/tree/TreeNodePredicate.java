package cn.chenzw.toolkit.core.tree;

import java.util.List;

/**
 * @author chenzw
 */
@FunctionalInterface
public interface TreeNodePredicate {

    Boolean matches(TreeNode treeNode, TreeNode parentNode, Integer index, Integer level);
}
