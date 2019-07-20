package cn.chenzw.toolkit.spring.domain;

import org.springframework.asm.Type;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.MediaTypeExpression;
import org.springframework.web.servlet.mvc.condition.NameValueExpression;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public final class ContextHandlerMappings {

    private final List<HandlerMappingDescription> mappings;

    private final String parentId;

    public ContextHandlerMappings(List<HandlerMappingDescription> mappings, String parentId) {
        this.mappings = mappings;
        this.parentId = parentId;
    }

    public String getParentId() {
        return this.parentId;
    }

    public List<HandlerMappingDescription> getMappings() {
        return mappings;
    }


    public static class HandlerMappingDescription {

        private String handler;

        private String predicate;

        private HandlerMethodDescription handlerMethod;

        private RequestMappingConditionsDescription requestMappingConditions;

        public HandlerMethodDescription getHandlerMethod() {
            return this.handlerMethod;
        }


        public String getHandler() {
            return handler;
        }

        public String getPredicate() {
            return predicate;
        }


        public RequestMappingConditionsDescription getRequestMappingConditions() {
            return this.requestMappingConditions;
        }


        public HandlerMappingDescription(String handler, String predicate, HandlerMethodDescription handlerMethod, RequestMappingConditionsDescription requestMappingConditions) {
            this.handler = handler;
            this.predicate = predicate;
            this.handlerMethod = handlerMethod;
            this.requestMappingConditions = requestMappingConditions;
        }


    }

    /**
     * A description of a {@link HandlerMethod}.
     */
    public static class HandlerMethodDescription {

        private final String className;

        private final String name;

        private final String descriptor;

        public HandlerMethodDescription(String className, String name, String descriptor) {
            this.className = className;
            this.name = name;
            this.descriptor = descriptor;
        }

        public HandlerMethodDescription(HandlerMethod handlerMethod) {
            this.name = handlerMethod.getMethod().getName();
            this.className = handlerMethod.getMethod().getDeclaringClass().getCanonicalName();
            this.descriptor = Type.getMethodDescriptor(handlerMethod.getMethod());
        }

        public String getName() {
            return this.name;
        }

        public String getDescriptor() {
            return this.descriptor;
        }

        public String getClassName() {
            return this.className;
        }

    }

    /**
     * Description of the conditions of a {@link RequestMappingInfo}.
     */
    public static class RequestMappingConditionsDescription {

        private final List<MediaTypeExpressionDescription> consumes;

        private final List<NameValueExpressionDescription> headers;

        private final Set<RequestMethod> methods;

        private final List<NameValueExpressionDescription> params;

        private final Set<String> patterns;

        private final List<MediaTypeExpressionDescription> produces;

        public RequestMappingConditionsDescription(List<MediaTypeExpressionDescription> consumes, List<NameValueExpressionDescription> headers, Set<RequestMethod> methods, List<NameValueExpressionDescription> params, Set<String> patterns, List<MediaTypeExpressionDescription> produces) {
            this.consumes = consumes;
            this.headers = headers;
            this.methods = methods;
            this.params = params;
            this.patterns = patterns;
            this.produces = produces;
        }

        public RequestMappingConditionsDescription(RequestMappingInfo requestMapping) {
            this.consumes = requestMapping.getConsumesCondition().getExpressions().stream()
                    .map(MediaTypeExpressionDescription::new).collect(Collectors.toList());
            this.headers = requestMapping.getHeadersCondition().getExpressions().stream()
                    .map(NameValueExpressionDescription::new).collect(Collectors.toList());
            this.methods = requestMapping.getMethodsCondition().getMethods();
            this.params = requestMapping.getParamsCondition().getExpressions().stream()
                    .map(NameValueExpressionDescription::new).collect(Collectors.toList());
            this.patterns = requestMapping.getPatternsCondition().getPatterns().stream()
                    // .map(PathPattern::getPatternString)
                    .collect(Collectors.toSet());
            this.produces = requestMapping.getProducesCondition().getExpressions().stream()
                    .map(MediaTypeExpressionDescription::new).collect(Collectors.toList());
        }

        public List<MediaTypeExpressionDescription> getConsumes() {
            return this.consumes;
        }

        public List<NameValueExpressionDescription> getHeaders() {
            return this.headers;
        }

        public Set<RequestMethod> getMethods() {
            return this.methods;
        }

        public List<NameValueExpressionDescription> getParams() {
            return this.params;
        }

        public Set<String> getPatterns() {
            return this.patterns;
        }

        public List<MediaTypeExpressionDescription> getProduces() {
            return this.produces;
        }

    }

    public static class MediaTypeExpressionDescription {

        private final String mediaType;

        private final boolean negated;

        MediaTypeExpressionDescription(MediaTypeExpression expression) {
            this.mediaType = expression.getMediaType().toString();
            this.negated = expression.isNegated();
        }

        public String getMediaType() {
            return this.mediaType;
        }

        public boolean isNegated() {
            return this.negated;
        }

    }

    public static class NameValueExpressionDescription {

        private final String name;

        private final Object value;

        private final boolean negated;

        NameValueExpressionDescription(NameValueExpression<?> expression) {
            this.name = expression.getName();
            this.value = expression.getValue();
            this.negated = expression.isNegated();
        }

        public String getName() {
            return this.name;
        }

        public Object getValue() {
            return this.value;
        }

        public boolean isNegated() {
            return this.negated;
        }


    }

}