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
    private String label;
    private boolean isLeaf;
    private List<TreeNode> childrens;

    /**
     * 自定义扩展字段
     */
    private Object ext;

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

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Object getExt() {
        return ext;
    }

    public void setExt(Object ext) {
        this.ext = ext;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", label='" + label + '\'' +
                ", isLeaf=" + isLeaf +
                ", childrens=" + childrens +
                ", ext=" + ext +
                '}';
    }
}
