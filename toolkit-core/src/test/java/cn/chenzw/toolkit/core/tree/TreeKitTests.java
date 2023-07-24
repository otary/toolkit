package cn.chenzw.toolkit.core.tree;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author chenzw
 */
@Slf4j
@RunWith(JUnit4.class)
public class TreeKitTests {

    private List<TreeNode> buildTree() {
        List<TreeNode> treeNodes = new ArrayList<>();

        TreeNode rootNode1 = new TreeNode<>();
        rootNode1.setLabel("根节点1");
        treeNodes.add(rootNode1);

        TreeNode rootNode2 = new TreeNode<>();
        rootNode2.setLabel("根节点2");
        treeNodes.add(rootNode2);

        TreeNode treeNode1 = new TreeNode<>();
        treeNode1.setLabel("子节点1");
        TreeNode treeNode2 = new TreeNode<>();
        treeNode2.setLabel("子节点2");
        rootNode1.setChildren(Arrays.asList(treeNode1, treeNode2));

        TreeNode treeNode3 = new TreeNode<>();
        treeNode3.setLabel("子节点3");
        TreeNode treeNode4 = new TreeNode<>();
        treeNode4.setLabel("子节点4");
        rootNode2.setChildren(Arrays.asList(treeNode3, treeNode4));

        return treeNodes;
    }

    @Test
    public void testIterate() {
        List<TreeNode> treeNodes = buildTree();

        TreeKit.iterate(treeNodes,
                (treeNode, treeNodes1, index, level) ->
                        log.info("treeNode => {}", treeNode.getLabel())
        );
    }

    @Test
    public void testFindNode() {
        List<TreeNode> treeNodes = buildTree();
        TreeNodeContext treeNodeContext = TreeKit.findNode(treeNodes, (treeNode, treeNodes1, index, level) ->
                Objects.equals(treeNode.getLabel(), "子节点3")
        );

        log.info("found => {}", treeNodeContext);
    }
}
