package cn.chenzw.toolkit.commons.support.tree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
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

    private Function<T, I> idCallbackFn;

    private Function<T, I> parentIdCallbackFn;

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
        return findChilds(startIdValue);
    }


    private List<TreeNode> findChilds(I id) {
        List<TreeNode> childNode = new ArrayList<>();
        for (T item : list) {
            I _id = idCallbackFn.apply(item);
            I _parentId = parentIdCallbackFn.apply(item);
            if (Objects.equals(id, _parentId)) {
                TreeNode<Object> treeNode = new TreeNode<>();
                treeNode.setParentId(_parentId);
                treeNode.setId(_id);
                treeNode.setChildrens(findChilds(_id));
                treeNode.setLeaf(treeNode.getChildrens().isEmpty());
                childNode.add(treeNode);
            }
        }
        return childNode;
    }
}
