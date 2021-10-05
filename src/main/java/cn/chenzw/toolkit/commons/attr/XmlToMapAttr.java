package cn.chenzw.toolkit.commons.attr;

/**
 * @author chenzw
 */
public class XmlToMapAttr {

    private boolean useTextAttr = false;

    private String textKey = "#text";

    private boolean includeAttrs = true;

    private String attrKey = "#attrs";

    public boolean isUseTextAttr() {
        return useTextAttr;
    }

    public void setUseTextAttr(boolean useTextAttr) {
        this.useTextAttr = useTextAttr;
    }

    public String getTextKey() {
        return textKey;
    }

    public void setTextKey(String textKey) {
        this.textKey = textKey;
    }

    public boolean isIncludeAttrs() {
        return includeAttrs;
    }

    public void setIncludeAttrs(boolean includeAttrs) {
        this.includeAttrs = includeAttrs;
    }

    public String getAttrKey() {
        return attrKey;
    }

    public void setAttrKey(String attrKey) {
        this.attrKey = attrKey;
    }
}
