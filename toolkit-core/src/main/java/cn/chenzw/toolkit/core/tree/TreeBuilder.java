package cn.chenzw.toolkit.core.tree;

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

    private Function<T, I> idCallback;

    private Function<T, I> parentIdCallback;

    private Function<T, String> labelCallback;

    private Function<T, Object> extCallback;

    private Function<T, Object> ext2Callback;

    private Function<T, Object> ext3Callback;

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
     * @param idCallback
     * @return
     */
    public TreeBuilder<T, I> configIdField(Function<T, I> idCallback) {
        this.idCallback = idCallback;
        return this;
    }

    /**
     * 配置parentId字段
     *
     * @param parentIdCallback
     * @return
     */
    public TreeBuilder<T, I> configParentIdField(Function<T, I> parentIdCallback) {
        this.parentIdCallback = parentIdCallback;
        return this;
    }

    /**
     * 配置label字段
     *
     * @param labelCallback
     * @return
     */
    public TreeBuilder<T, I> configLabelField(Function<T, String> labelCallback) {
        this.labelCallback = labelCallback;
        return this;
    }

    /**
     * 配置ext字段
     *
     * @param extCallback
     * @return
     */
    public TreeBuilder<T, I> configExtField(Function<T, Object> extCallback) {
        this.extCallback = extCallback;
        return this;
    }

    public TreeBuilder<T, I> configExt2Field(Function<T, Object> ext2Callback) {
        this.ext2Callback = ext2Callback;
        return this;
    }

    public TreeBuilder<T, I> configExt3Field(Function<T, Object> ext3Callback) {
        this.ext3Callback = ext3Callback;
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
        if (idCallback == null) {
            throw new NullPointerException("Missing \"idField\" config!");
        }
        if (parentIdCallback == null) {
            throw new NullPointerException("Missing \"parentId\" config!");
        }
        if (startIdValue == null) {
            throw new NullPointerException("Missing \"startWith\" config!");
        }
        if (labelCallback == null) {
            throw new NullPointerException("Missing \"label\" field config!");
        }
        return findChild(startIdValue);
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


    private List<TreeNode> findChild(I id) {
        List<TreeNode> childNode = new ArrayList<>();
        for (T item : list) {
            I parentId = parentIdCallback.apply(item);
            if (Objects.equals(id, parentId)) {
                TreeNode<Object> treeNode = new TreeNode<>();
                treeNode.setParentId(parentId);
                treeNode.setLabel(labelCallback.apply(item));

                I _id = idCallback.apply(item);
                treeNode.setId(_id);
                treeNode.setChildren(findChild(_id));

                if (extCallback != null) {
                    treeNode.setExt(extCallback.apply(item));
                }
                if (ext2Callback != null) {
                    treeNode.setExt2(ext2Callback.apply(item));
                }
                if (ext3Callback != null) {
                    treeNode.setExt3(ext3Callback.apply(item));
                }
                treeNode.setLeaf(treeNode.getChildren().isEmpty());
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
            Optional<T> nodeOpt = findNode(list, parentIdCallback.apply(node));
            Optional<T> nodeOpt2 = findNode(nodes, parentIdCallback.apply(node));
            if (!nodeOpt.isPresent() && !nodeOpt2.isPresent()) {
                Optional<T> newNodeOpt = findNode(wholeTree, parentIdCallback.apply(node));
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
            I _id = idCallback.apply(item);
            return Objects.equals(_id, id);
        }).findFirst();
    }
}
