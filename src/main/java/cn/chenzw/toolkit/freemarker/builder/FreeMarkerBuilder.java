package cn.chenzw.toolkit.freemarker.builder;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

public class FreeMarkerBuilder {

    private Configuration configuration;

    /**
     * freemarker版本
     *
     * @see {@link freemarker.template.Configuration#VERSION_2_3_28}
     */
    private Version version;

    private String templatePath;

    private String templateName;


    /**
     * 异常处理策略
     *
     * @see {@link freemarker.template.TemplateExceptionHandler}
     */
    private TemplateExceptionHandler templateExceptionHanler;

    private Locale locale;

    private String encoding;

    /**
     * 模版内容
     */
    private String templateContent;

    private FreeMarkerBuilder() {
    }

    private FreeMarkerBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    public static FreeMarkerBuilder create() {
        return new FreeMarkerBuilder();
    }

    public static FreeMarkerBuilder createByConfiguration(Configuration configuration) {
        return new FreeMarkerBuilder(configuration);
    }

    public FreeMarkerBuilder version(Version version) {
        this.version = version;
        return this;
    }

    public FreeMarkerBuilder templatePath(String templatePath) {
        this.templatePath = templatePath;
        return this;
    }

    public FreeMarkerBuilder templateExceptionHanler(TemplateExceptionHandler templateExceptionHanler) {
        this.templateExceptionHanler = templateExceptionHanler;
        return this;
    }

    public FreeMarkerBuilder locale(Locale locale) {
        this.locale = locale;
        return this;
    }

    public FreeMarkerBuilder encoding(String encoding) {
        this.encoding = encoding;
        return this;
    }

    public FreeMarkerBuilder templateName(String templateName) {
        this.templateName = templateName;
        return this;
    }

    public String getTemplateName() {
        return this.templateName;
    }

    public FreeMarkerBuilder templateContent(String templateContent) {
        this.templateContent = templateContent;
        return this;
    }

    public Template build() throws IOException {
        if (configuration == null) {
            configuration = new Configuration(ObjectUtils.defaultIfNull(version,
                    Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS));
        }

        if (!StringUtils.isEmpty(templatePath)) {
            configuration.setDirectoryForTemplateLoading(new File(templatePath));
        }

        if (!StringUtils.isEmpty(templateContent)) {
            StringTemplateLoader stringTemplateLoader = new StringTemplateLoader();
            stringTemplateLoader.putTemplate(templateName, templateContent);
            configuration.setTemplateLoader(stringTemplateLoader);
        }

        // 设置异常处理策略
        if (templateExceptionHanler != null) {
            configuration.setTemplateExceptionHandler(templateExceptionHanler);
        }

        if (locale != null) {
            configuration.setLocale(locale);
        }

        if (!StringUtils.isEmpty(encoding)) {
            configuration.setEncoding(configuration.getLocale(), encoding);
        }

        return configuration.getTemplate(templateName);
    }

}
