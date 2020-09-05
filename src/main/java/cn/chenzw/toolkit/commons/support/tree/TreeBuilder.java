package cn.chenzw.toolkit.commons.support.tree;

import java.util.*;
import java.util.function.Function;

/**
 * 树型构建器
 *
 * @param <T> 列表对象类型
 * @param <I> ID类型
 * @author chenzw
 */
public class TreeBuilder<T, I> {

    private Collection<T> list;

    private Collection<T> wholeTree;

    private Function<T, I> idCallbackFn;

    private Function<T, I> parentIdCallbackFn;

    private Function<T, String> labelCallbackFn;

    private Function<T, Object> extCallbackFn;

    private I startIdValue;

    public static <T, I> TreeBuilder<T, I> create(Collection<T> list) {
        return new TreeBuilder<>(list);
    }

    public TreeBuilder(Collection<T> list) {
        this.list = list;
    }

    /**
     * 配置id字段
     *
     * @param idCallbackFn
     * @return
     */
    public TreeBuilder<T, I> configIdField(Function<T, I> idCallbackFn) {
        this.idCallbackFn = idCallbackFn;
        return this;
    }

    /**
     * 配置parentId字段
     *
     * @param parentIdCallbackFn
     * @return
     */
    public TreeBuilder<T, I> configParentIdField(Function<T, I> parentIdCallbackFn) {
        this.parentIdCallbackFn = parentIdCallbackFn;
        return this;
    }

    /**
     * 配置label字段
     *
     * @param labelCallbackFn
     * @return
     */
    public TreeBuilder<T, I> configLabelField(Function<T, String> labelCallbackFn) {
        this.labelCallbackFn = labelCallbackFn;
        return this;
    }

    /**
     * 配置ext字段
     *
     * @param extCallbackFn
     * @return
     */
    public TreeBuilder<T, I> configExtField(Function<T, Object> extCallbackFn) {
        this.extCallbackFn = extCallbackFn;
        return this;
    }

    /**
     * 起始id值
     *
     * @param id
     * @return
     */
    public TreeBuilder<T, I> startWith(I id) {
        this.startIdValue = id;
        return this;
    }

    public List<TreeNode> build() {
        if (idCallbackFn == null) {
            throw new NullPointerException("Missing \"idField\" config!");
        }
        if (parentIdCallbackFn == null) {
            throw new NullPointerException("Missing \"parentId\" config!");
        }
        if (startIdValue == null) {
            throw new NullPointerException("Missing \"startWith\" config!");
        }
        if (labelCallbackFn == null) {
            throw new NullPointerException("Missing \"label\" field config!");
        }
        return findChilds(startIdValue);
    }

    /**
     * 补全根节点构建
     *
     * @param wholeTree
     * @return
     */
    public List<TreeNode> build(List<T> wholeTree) {
        this.wholeTree = wholeTree;

        Collection<T> nonExistingNodeParents = list;
        while (nonExistingNodeParents != null && !nonExistingNodeParents.isEmpty()) {
            nonExistingNodeParents = findNonExistingNodeParents(nonExistingNodeParents);
        }

        return build();
    }


    private List<TreeNode> findChilds(I id) {
        List<TreeNode> childNode = new ArrayList<>();
        for (T item : list) {
            I _parentId = parentIdCallbackFn.apply(item);
            if (Objects.equals(id, _parentId)) {
                TreeNode<Object> treeNode = new TreeNode<>();
                treeNode.setParentId(_parentId);
                treeNode.setLabel(labelCallbackFn.apply(item));

                I _id = idCallbackFn.apply(item);
                treeNode.setId(_id);
                treeNode.setChildrens(findChilds(_id));

                if (extCallbackFn != null) {
                    treeNode.setExt(extCallbackFn.apply(item));
                }
                treeNode.setLeaf(treeNode.getChildrens().isEmpty());
                childNode.add(treeNode);
            }
        }
        return childNode;
    }

    /**
     * 查找父层未存在的节点
     *
     * @param nodes
     * @return
     */
    private Collection<T> findNonExistingNodeParents(Collection<T> nodes) {
        Collection<T> parentNodes = new ArrayList<>();
        for (T node : nodes) {
            // 底层是否已存在父节点
            Optional<T> nodeOpt = findNode(list, parentIdCallbackFn.apply(node));
            Optional<T> nodeOpt2 = findNode(nodes, parentIdCallbackFn.apply(node));
            if (!nodeOpt.isPresent() && !nodeOpt2.isPresent()) {
                Optional<T> newNodeOpt = findNode(wholeTree, parentIdCallbackFn.apply(node));
                if (newNodeOpt.isPresent()) {
                    parentNodes.add(newNodeOpt.get());
                }
            }
        }
        list.addAll(parentNodes);
        return parentNodes;
    }

    private Optional<T> findNode(Collection<T> nodes, I id) {
        return nodes.stream().filter(item -> {
            I _id = idCallbackFn.apply(item);
            return Objects.equals(_id, id);
        }).findFirst();
    }
}
