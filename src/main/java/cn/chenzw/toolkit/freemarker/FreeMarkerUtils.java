package cn.chenzw.toolkit.freemarker;

import cn.chenzw.toolkit.freemarker.builder.FreeMarkerBuilder;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.lang3.Validate;

import java.io.*;

/**
 * @author
 */
public abstract class FreeMarkerUtils {

    private FreeMarkerUtils() {
    }

    public static String processToString(File templateFile, Object dataModel) throws IOException, TemplateException {
        return processToString(templateFile, dataModel, null);
    }

    /**
     * 根据模版生成字符串
     *
     * @param templateFile
     * @param dataModel
     * @param freeMarkerBuilder
     * @return
     * @throws IOException
     * @throws TemplateException
     */
    public static String processToString(File templateFile, Object dataModel, FreeMarkerBuilder freeMarkerBuilder) throws IOException, TemplateException {
        return processToString(toTemplate(templateFile, freeMarkerBuilder), dataModel);
    }

    public static String processToString(Template template, Object dataModel) throws IOException, TemplateException {
        StringWriter writer = new StringWriter();
        template.process(dataModel, writer);
        return writer.toString();
    }


    public static void processToFile(File templateFile, Object dataModel, File outFile) throws IOException, TemplateException {
        processToFile(templateFile, dataModel, outFile, null);
    }

    /**
     * 根据模版生成文件
     *
     * @param templateFile
     * @param dataModel
     * @param outFile
     * @param freeMarkerBuilder
     * @throws IOException
     * @throws TemplateException
     */
    public static void processToFile(File templateFile, Object dataModel, File outFile, FreeMarkerBuilder freeMarkerBuilder) throws IOException, TemplateException {
        processToFile(toTemplate(templateFile, freeMarkerBuilder), dataModel, outFile);
    }

    public static void processToFile(Template template, Object dataModel, File outFile) throws IOException, TemplateException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile)))) {
            template.process(dataModel, bufferedWriter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Template toTemplate(File templateFile, FreeMarkerBuilder freeMarkerBuilder) throws IOException {
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
        return template;
    }
}
