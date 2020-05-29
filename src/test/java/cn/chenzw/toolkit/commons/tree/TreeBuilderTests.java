package cn.chenzw.toolkit.commons.tree;

import cn.chenzw.toolkit.commons.support.tree.TreeBuilder;
import cn.chenzw.toolkit.commons.support.tree.TreeNode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(JUnit4.class)
public class TreeBuilderTests {

    private List<Organization> orgs = new ArrayList<>();

    @Before
    public void setUp(){
        Organization o1 = new Organization();
        o1.setOrgId(1);
        o1.setParentOrgId(0);
        o1.setOrgName("o1");
        orgs.add(o1);

        Organization o2 = new Organization();
        o2.setOrgId(2);
        o2.setParentOrgId(1);
        o2.setOrgName("o2");
        orgs.add(o2);

        Organization o3 = new Organization();
        o3.setOrgId(3);
        o3.setParentOrgId(1);
        o3.setOrgName("o3");
        orgs.add(o3);

        Organization o4 = new Organization();
        o4.setOrgId(4);
        o4.setParentOrgId(2);
        o4.setOrgName("o4");
        orgs.add(o4);

        Organization o5 = new Organization();
        o5.setOrgId(5);
        o5.setParentOrgId(2);
        o5.setOrgName("o5");
        orgs.add(o5);

        Organization o6 = new Organization();
        o6.setOrgId(6);
        o6.setParentOrgId(1);
        o6.setOrgName("o6");
        orgs.add(o6);
    }

    @Test
    public void test() throws JsonProcessingException {

        List<TreeNode> treeNodes = TreeBuilder.create(orgs)
                .configIdField(Organization::getOrgId)
                .configParentIdField(Organization::getParentOrgId)
                .configLabelField(Organization::getOrgName)
                .startWith(0)
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(treeNodes);

        Assert.assertEquals("[{\"id\":1,\"parentId\":0,\"label\":\"o1\",\"childrens\":[{\"id\":2,\"parentId\":1,\"label\":\"o2\",\"childrens\":[{\"id\":4,\"parentId\":2,\"label\":\"o4\",\"childrens\":[],\"ext\":null,\"leaf\":true},{\"id\":5,\"parentId\":2,\"label\":\"o5\",\"childrens\":[],\"ext\":null,\"leaf\":true}],\"ext\":null,\"leaf\":false},{\"id\":3,\"parentId\":1,\"label\":\"o3\",\"childrens\":[],\"ext\":null,\"leaf\":true},{\"id\":6,\"parentId\":1,\"label\":\"o6\",\"childrens\":[],\"ext\":null,\"leaf\":true}],\"ext\":null,\"leaf\":false}]", json);
    }

    /**
     * 返回ext扩展字段
     */
    @Test
    public void testWithExt() throws JsonProcessingException {

        List<TreeNode> treeNodes = TreeBuilder.create(orgs)
                .configIdField(Organization::getOrgId)
                .configParentIdField(Organization::getParentOrgId)
                .configLabelField(Organization::getOrgName)
                .configExtField((item)->{
                    Map<String, Object> extMap = new HashMap<>();
                    extMap.put("e", item.orgId);
                    extMap.put("x", item.getOrgName());
                    return extMap;
                })
                .startWith(0)
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(treeNodes);

        Assert.assertEquals("[{\"id\":1,\"parentId\":0,\"label\":\"o1\",\"childrens\":[{\"id\":2,\"parentId\":1,\"label\":\"o2\",\"childrens\":[{\"id\":4,\"parentId\":2,\"label\":\"o4\",\"childrens\":[],\"ext\":{\"e\":4,\"x\":\"o4\"},\"leaf\":true},{\"id\":5,\"parentId\":2,\"label\":\"o5\",\"childrens\":[],\"ext\":{\"e\":5,\"x\":\"o5\"},\"leaf\":true}],\"ext\":{\"e\":2,\"x\":\"o2\"},\"leaf\":false},{\"id\":3,\"parentId\":1,\"label\":\"o3\",\"childrens\":[],\"ext\":{\"e\":3,\"x\":\"o3\"},\"leaf\":true},{\"id\":6,\"parentId\":1,\"label\":\"o6\",\"childrens\":[],\"ext\":{\"e\":6,\"x\":\"o6\"},\"leaf\":true}],\"ext\":{\"e\":1,\"x\":\"o1\"},\"leaf\":false}]", json);

    }

    public static class Organization {

        private Integer orgId;
        private Integer parentOrgId;
        private String orgName;

        public Integer getOrgId() {
            return orgId;
        }

        public void setOrgId(Integer orgId) {
            this.orgId = orgId;
        }

        public Integer getParentOrgId() {
            return parentOrgId;
        }

        public void setParentOrgId(Integer parentOrgId) {
            this.parentOrgId = parentOrgId;
        }

        public String getOrgName() {
            return orgName;
        }

        public void setOrgName(String orgName) {
            this.orgName = orgName;
        }

        @Override
        public String toString() {
            return "Organization{" +
                    "orgId=" + orgId +
                    ", parentOrgId=" + parentOrgId +
                    ", orgName='" + orgName + '\'' +
                    '}';
        }
    }


}
