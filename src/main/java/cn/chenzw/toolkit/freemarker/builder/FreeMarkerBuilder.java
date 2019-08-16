package cn.chenzw.toolkit.freemarker.builder;

import freemarker.template.*;
import org.apache.commons.lang3.ObjectUtils;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

public class FreeMarkerBuilder {

    /**
     * freemarker版本
     * @see {@link freemarker.template.Configuration#VERSION_2_3_28}
     */
    private Version version;

    private String templatePath;

    /**
     * 异常处理策略
     * @see {@link freemarker.template.TemplateExceptionHandler}
     */
    private TemplateExceptionHandler templateExceptionHanler;

    private Locale locale;

    private String encoding;

    public static FreeMarkerBuilder create() {
        return new FreeMarkerBuilder();
    }

    public FreeMarkerBuilder setVersion(Version version) {
        this.version = version;
        return this;
    }

    public FreeMarkerBuilder setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
        return this;
    }

    public FreeMarkerBuilder setTemplateExceptionHanler(TemplateExceptionHandler templateExceptionHanler) {
        this.templateExceptionHanler = templateExceptionHanler;
        return this;
    }

    public FreeMarkerBuilder setLocale(Locale locale) {
        this.locale = locale;
        return this;
    }

    public FreeMarkerBuilder setEncoding(String encoding) {
        this.encoding = encoding;
        return this;
    }

    public Template build() throws IOException {

        version = ObjectUtils.defaultIfNull(version, Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        Configuration config = new Configuration(version);

        config.setDirectoryForTemplateLoading(new File(templatePath));

        // 设置包装器，并将对象包装为数据模型
        config.setObjectWrapper(new DefaultObjectWrapperBuilder(version).build());

        // 设置异常处理策略
        if (templateExceptionHanler != null) {
            config.setTemplateExceptionHandler(templateExceptionHanler);
        }


        return config.getTemplate();
    }

}
