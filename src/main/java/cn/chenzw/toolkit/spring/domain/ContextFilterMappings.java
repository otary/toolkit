package cn.chenzw.toolkit.spring.domain;

import javax.servlet.FilterRegistration;
import javax.servlet.Registration;
import java.util.Collection;
import java.util.List;

/**
 * @author chenzw
 */
public class ContextFilterMappings {

    private List<FilterRegistrationMappingDescription> servletFilters;

    private String parentId;

    public ContextFilterMappings(List<FilterRegistrationMappingDescription> servletFilters, String parentId) {
        this.servletFilters = servletFilters;
        this.parentId = parentId;
    }

    public List<FilterRegistrationMappingDescription> getServletFilters() {
        return servletFilters;
    }

    public String getParentId() {
        return parentId;
    }

    public static class FilterRegistrationMappingDescription extends RegistrationMappingDescription<FilterRegistration> {

        public FilterRegistrationMappingDescription(FilterRegistration filterRegistration) {
            super(filterRegistration);
        }

        public Collection<String> getServletNameMappings() {
            return this.getRegistration().getServletNameMappings();
        }


        public Collection<String> getUrlPatternMappings() {
            return this.getRegistration().getUrlPatternMappings();
        }

    }

    public static class RegistrationMappingDescription<T extends Registration> {

        private final T registration;

        public RegistrationMappingDescription(T registration) {
            this.registration = registration;
        }

        public String getName() {
            return this.registration.getName();
        }

        public String getClassName() {
            return this.registration.getClassName();
        }

        protected final T getRegistration() {
            return this.registration;
        }

    }

}
