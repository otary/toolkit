package cn.chenzw.toolkit.spring.domain;

import javax.servlet.ServletRegistration;
import java.util.Collection;
import java.util.List;

public class ContextServletMappings {

    private List<ServletRegistrationMappingDescription> servlets;

    private String parentId;

    public ContextServletMappings(List<ServletRegistrationMappingDescription> servlets, String parentId) {
        this.servlets = servlets;
        this.parentId = parentId;
    }

    public List<ServletRegistrationMappingDescription> getServlets() {
        return servlets;
    }

    public String getParentId() {
        return parentId;
    }

    public static class ServletRegistrationMappingDescription {

        private ServletRegistration registration;

        public ServletRegistrationMappingDescription(ServletRegistration servletRegistration) {
            this.registration = registration;
        }

        public String getName() {
            return this.registration.getName();
        }


        public String getClassName() {
            return this.registration.getClassName();
        }


        protected final ServletRegistration getRegistration() {
            return this.registration;
        }


        public Collection<String> getMappings() {
            return getRegistration().getMappings();
        }

    }

}
