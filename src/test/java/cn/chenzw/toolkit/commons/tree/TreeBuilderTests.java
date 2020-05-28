package cn.chenzw.toolkit.commons.tree;

import cn.chenzw.toolkit.commons.support.tree.TreeBuilder;
import cn.chenzw.toolkit.commons.support.tree.TreeNode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

@RunWith(JUnit4.class)
public class TreeBuilderTests {

    @Test
    public void test() throws JsonProcessingException {
        List<Organization> orgs = new ArrayList<>();

        Organization o1 = new Organization();
        o1.setOrgId(1);
        o1.setParentOrgId(0);
        orgs.add(o1);

        Organization o2 = new Organization();
        o2.setOrgId(2);
        o2.setParentOrgId(1);
        orgs.add(o2);

        Organization o3 = new Organization();
        o3.setOrgId(3);
        o3.setParentOrgId(1);
        orgs.add(o3);

        Organization o4 = new Organization();
        o4.setOrgId(4);
        o4.setParentOrgId(2);
        orgs.add(o4);

        Organization o5 = new Organization();
        o5.setOrgId(5);
        o5.setParentOrgId(2);
        orgs.add(o5);

        Organization o6 = new Organization();
        o6.setOrgId(6);
        o6.setParentOrgId(1);
        orgs.add(o6);

        List<TreeNode> treeNodes = TreeBuilder.create(orgs)
                .configIdField(Organization::getOrgId)
                .configParentIdField(Organization::getParentOrgId)
                .startWith(0)
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(treeNodes);

        Assert.assertEquals("[{\"id\":1,\"parentId\":0,\"childrens\":[{\"id\":2,\"parentId\":1,\"childrens\":[{\"id\":4,\"parentId\":2,\"childrens\":[],\"leaf\":true},{\"id\":5,\"parentId\":2,\"childrens\":[],\"leaf\":true}],\"leaf\":false},{\"id\":3,\"parentId\":1,\"childrens\":[],\"leaf\":true},{\"id\":6,\"parentId\":1,\"childrens\":[],\"leaf\":true}],\"leaf\":false}]", json);
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
