package cn.chenzw.toolkit.core.tree;

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
    private List<TreeNode> children;

    /**
     * 自定义扩展字段
     */
    private Object ext;
    private Object ext2;
    private Object ext3;

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

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
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

    public Object getExt2() {
        return ext2;
    }

    public void setExt2(Object ext2) {
        this.ext2 = ext2;
    }

    public Object getExt3() {
        return ext3;
    }

    public void setExt3(Object ext3) {
        this.ext3 = ext3;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TreeNode{");
        sb.append("id=").append(id);
        sb.append(", parentId=").append(parentId);
        sb.append(", label='").append(label).append('\'');
        sb.append(", isLeaf=").append(isLeaf);
        sb.append(", children=").append(children);
        sb.append(", ext=").append(ext);
        sb.append(", ext2=").append(ext2);
        sb.append(", ext3=").append(ext3);
        sb.append('}');
        return sb.toString();
    }
}
