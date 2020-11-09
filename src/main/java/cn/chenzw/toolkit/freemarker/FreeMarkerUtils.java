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

    public static String processToString(String templateContent, Object dataModel) throws IOException, TemplateException {
        return processToString(toTemplate(templateContent, null), dataModel);
    }

    /**
     * @param templateContent
     * @param dataModel
     * @param freeMarkerBuilder
     * @return
     */
    public static String processToString(String templateContent, Object dataModel, FreeMarkerBuilder freeMarkerBuilder) throws IOException, TemplateException {
        return processToString(toTemplate(templateContent, freeMarkerBuilder), dataModel);
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

    public static void processToFile(Template template, Object dataModel, File outFile) {
        Validate.notNull(outFile, "Freemarker out file is null !");

        if (outFile.getParentFile() != null) {
            if (!outFile.getParentFile().exists()) {
                outFile.getParentFile().mkdirs();
            }
        }

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

        if (freeMarkerBuilder == null) {
            return FreeMarkerBuilder.create().templatePath(templatePath).templateName(templateName).build();
        } else {
            return freeMarkerBuilder.templatePath(templatePath).templateName(templateName).build();
        }
    }

    private static Template toTemplate(String templateContent, FreeMarkerBuilder freeMarkerBuilder) throws IOException {
        Validate.notEmpty(templateContent, "Freemarker templateContent is null!");

        if (freeMarkerBuilder == null) {
            freeMarkerBuilder = FreeMarkerBuilder.create();
        }
        return freeMarkerBuilder.templateContent(templateContent).templateName("stringTemplate").build();

    }


}
