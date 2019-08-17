package cn.chenzw.toolkit.freemarker.builder;

import freemarker.template.*;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

public class FreeMarkerBuilder {

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

    public static FreeMarkerBuilder create() {
        return new FreeMarkerBuilder();
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

    public Template build() throws IOException {
        this.version = ObjectUtils.defaultIfNull(version,
                Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);

        Configuration config = new Configuration(version);

        config.setDirectoryForTemplateLoading(new File(templatePath));

        // 设置包装器，并将对象包装为数据模型
        config.setObjectWrapper(new DefaultObjectWrapperBuilder(version).build());

        // 设置异常处理策略
        if (templateExceptionHanler != null) {
            config.setTemplateExceptionHandler(templateExceptionHanler);
        }

        if (locale != null) {
            config.setLocale(locale);
        }

        if (!StringUtils.isEmpty(encoding)) {
            config.setEncoding(config.getLocale(), encoding);
        }
        return config.getTemplate(templateName);
    }

}
