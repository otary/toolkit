package cn.chenzw.toolkit.freemarker;

import cn.chenzw.toolkit.freemarker.builder.FreeMarkerBuilder;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.lang3.Validate;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

public abstract class FreeMarkerUtils {

    private FreeMarkerUtils() {
    }

    public static String processToString(File templateFile, Object dataModel) throws IOException, TemplateException {
        return processToString(templateFile, dataModel, null);
    }

    public static String processToString(File templateFile, Object dataModel, FreeMarkerBuilder freeMarkerBuilder) throws IOException, TemplateException {
        Validate.notNull(templateFile, "Freemarker template file is null !");
        Validate.isTrue(templateFile.exists(), "Freemarker template file [ " + templateFile.getAbsolutePath() + "] is not exists!");

        String templatePath = templateFile.getParentFile().getAbsolutePath();
        String templateName = templateFile.getName();

        Template template = null;
        if (freeMarkerBuilder == null) {
            template = FreeMarkerBuilder.create().templatePath(templatePath).templateName(templateName).build();
        } else {
            template = freeMarkerBuilder.templatePath(templatePath).templateName(templateName).build();
        }
        return processToString(template, dataModel);
    }

    public static String processToString(Template template, Object dataModel) throws IOException, TemplateException {
        StringWriter writer = new StringWriter();
        template.process(dataModel, writer);
        return writer.toString();
    }
}
