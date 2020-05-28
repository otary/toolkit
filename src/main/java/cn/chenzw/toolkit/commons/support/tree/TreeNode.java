package cn.chenzw.toolkit.commons.support.tree;

import java.io.Serializable;
import java.util.List;

/**
 * 树节点
 *
 * @param <I> ID类型
 * @author chenzw
 */
public class TreeNode<I> implements Serializable {

    private static final long serialVersionUID = -8455502881581876500L;

    private I id;
    private I parentId;
    private boolean isLeaf;
    private List<TreeNode> childrens;

    public I getId() {
        return id;
    }

    public void setId(I id) {
        this.id = id;
    }

    public I getParentId() {
        return parentId;
    }

    public void setParentId(I parentId) {
        this.parentId = parentId;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }

    public List<TreeNode> getChildrens() {
        return childrens;
    }

    public void setChildrens(List<TreeNode> childrens) {
        this.childrens = childrens;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", isLeaf=" + isLeaf +
                ", childrens=" + childrens +
                '}';
    }
}
