package cn.chenzw.toolkit.spring.domain;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * @author chenzw
 */
public class ContextBeans {

    private final Set<BeanDescriptor> beans;

    private final String parentId;

    public ContextBeans(Set<BeanDescriptor> beans, String parentId) {
        this.beans = beans;
        this.parentId = parentId;
    }

    public String getParentId() {
        return this.parentId;
    }

    public Set<BeanDescriptor> getBeans() {
        return this.beans;
    }

    public static ContextBeans describing(ConfigurableApplicationContext context) {
        if (context == null) {
            return null;
        }
        ConfigurableApplicationContext parent = getConfigurableParent(context);
        return new ContextBeans(describeBeans(context.getBeanFactory()), (parent != null) ? parent.getId() : null);
    }

    private static ConfigurableApplicationContext getConfigurableParent(ConfigurableApplicationContext context) {
        ApplicationContext parent = context.getParent();
        if (parent instanceof ConfigurableApplicationContext) {
            return (ConfigurableApplicationContext) parent;
        }
        return null;
    }

    private static Set<BeanDescriptor> describeBeans(ConfigurableListableBeanFactory beanFactory) {
        Set<BeanDescriptor> beans = new HashSet<>();
        for (String beanName : beanFactory.getBeanDefinitionNames()) {
            BeanDefinition definition = beanFactory.getBeanDefinition(beanName);
            if (isBeanEligible(beanName, definition, beanFactory)) {
                beans.add(describeBean(beanName, definition, beanFactory));
            }
        }
        return beans;
    }

    private static BeanDescriptor describeBean(String name, BeanDefinition definition,
                                               ConfigurableListableBeanFactory factory) {
        return new BeanDescriptor(name, factory.getAliases(name), definition.getScope(), factory.getType(name),
                definition.getResourceDescription(), factory.getDependenciesForBean(name));
    }

    private static boolean isBeanEligible(String beanName, BeanDefinition bd, ConfigurableBeanFactory bf) {
        return (bd.getRole() != BeanDefinition.ROLE_INFRASTRUCTURE
                && (!bd.isLazyInit() || bf.containsSingleton(beanName)));
    }

    public static final class BeanDescriptor {

        private final String name;

        private final String[] aliases;

        private final String scope;

        private final Class<?> type;

        private final String resource;

        private final String[] dependencies;

        private BeanDescriptor(String name, String[] aliases, String scope, Class<?> type, String resource, String[] dependencies) {
            this.name = name;
            this.aliases = aliases;
            this.scope = (StringUtils.hasText(scope) ? scope : BeanDefinition.SCOPE_SINGLETON);
            this.type = type;
            this.resource = resource;
            this.dependencies = dependencies;
        }

        public String[] getAliases() {
            return this.aliases;
        }

        public String getScope() {
            return this.scope;
        }

        public Class<?> getType() {
            return this.type;
        }

        public String getResource() {
            return this.resource;
        }

        public String[] getDependencies() {
            return this.dependencies;
        }

        public String getName() {
            return name;
        }
    }

}
