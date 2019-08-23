package cn.chenzw.toolkit.compiler;

import cn.chenzw.toolkit.commons.ProjectUtils;

import javax.tools.*;
import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

public class DynamicCompilerTool {

    /**
     * 编译java文件,使用StandardJavaFileManager编译Java源程式
     *
     * @param encoding    编译编码
     * @param filePath    文件或者目录（若为目录，自动递归编译）
     * @param diagnostics 存放编译过程中的错误信息
     * @return
     * @throws Exception
     */
    public boolean compiler(String encoding, String filePath, String outputFile, DiagnosticCollector<JavaFileObject> diagnostics) throws Exception {

        String sourceDir = filePath;//java源文件存放目录
        String targetDir = "";//编译后class类文件存放目录

        // 获取编译器实例
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        // 获取标准文件管理器实例
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, Charset.forName(encoding));
        try {
            //得到编译关联的jar
           // List<File> jars = new ArrayList<File>();
            //getJarFiles(new File(filePath), jars);

            List<File> jars = ProjectUtils.getDependentJarFiles();
            // 得到filePath目录下的所有java源文件
            File sourceFile = new File(sourceDir);
            List<File> sourceFileList = new ArrayList<File>();
            getSourceFiles(sourceFile, sourceFileList);
            // 没有java文件，直接返回
            if (sourceFileList.size() == 0) {
                System.out.println(sourceDir + "目录下查找不到任何java文件");
                return false;
            }
            //加载依赖的jar文件和依赖的class文件
            List<File> dependencies = new ArrayList<File>();
            dependencies.addAll(jars);
            //dependencies.addAll(sourceFileList);
            fileManager.setLocation(StandardLocation.CLASS_PATH, dependencies);
            fileManager.setLocation(StandardLocation.SOURCE_PATH, sourceFileList);
            //编译后输出的地址
            fileManager.setLocation(StandardLocation.CLASS_OUTPUT, Arrays.asList(new File[]{new File(outputFile)}));

            // 获取要编译的编译单元
            Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromFiles(sourceFileList);
            /**
             * 编译选项，在编译java文件时，编译程序会自动的去寻找java文件引用的其他的java源文件或者class。
             * -sourcepath选项就是定义java源文件的查找目录，有时我们编译一个Java源程式文件，而这个源程式文件需要另几个Java文件，
             *            而这些Java文件又在另外一个目录，那么这就需要为编译器指定这些文件所在的目录。
             * -classpath选项就是定义class文件的查找目录。
             * -d 是用来指定存放编译生成的.class文件的路径
             */
            //Iterable<String> options = Arrays.asList("-encoding", encoding, "-classpath", jars.toString(), "-d", targetDir, "-sourcepath", sourceDir);
            Iterable<String> options = Arrays.asList("-encoding", encoding, "-source", "1.8");
            JavaCompiler.CompilationTask compilationTask = compiler.getTask(null, fileManager, diagnostics, options, null, compilationUnits);
            //运行编译任务
            return compilationTask.call();
        } finally {
            fileManager.close();
        }
    }

    private void getSourceFiles(File sourceFile, List<File> sourceFileList) throws Exception {
        if (sourceFile.exists() && sourceFileList != null) {//文件或者目录必须存在
            if (sourceFile.isDirectory()) {// 若file对象为目录
                File[] childrenFiles = sourceFile.listFiles((pathname -> {
                    if (pathname.isDirectory()) {
                        return true;
                    } else {
                        String name = pathname.getName();
                        if (name.endsWith(".java") ? true : false) {
                            return true;
                        }
                        return false;
                    }
                }));
                // 递归调用
                for (File childFile : childrenFiles) {
                    getSourceFiles(childFile, sourceFileList);
                }
            } else {
                //若file对象为文件
                String name = sourceFile.getName();
                if (name.endsWith(".java") ? true : false) {
                    //System.out.println(sourceFile.getAbsolutePath());
                    sourceFileList.add(sourceFile);
                }
            }
        }
    }

    private void getJarFiles(File jarFile, List<File> jars) throws Exception {
        if (jarFile.exists() && jarFile != null) {//文件或者目录必须存在
            if (jarFile.isDirectory()) {// 若file对象为目录
                File[] childrenFiles = jarFile.listFiles((pathname) -> {
                    if (pathname.isDirectory()) {
                        return true;
                    } else {
                        String name = pathname.getName();
                        if (name.endsWith(".jar") ? true : false) {
                            return true;
                        }
                        return false;
                    }
                });
                // 递归调用
                for (File childFile : childrenFiles) {
                    getJarFiles(childFile, jars);
                }
            } else {
                String name = jarFile.getName();
                if (name.endsWith(".jar") ? true : false) {
                    // System.out.println(jarFile.getAbsolutePath());
                    jars.add(jarFile);
                }
            }
        }
    }

    public static void main(String[] objs) throws Exception {
        DynamicCompilerTool tool = new DynamicCompilerTool();
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
        String outputFile = ProjectUtils.getRootPath();
        File cls = new File(outputFile);
        if (!cls.exists()) {
            cls.mkdir();
        }
        String encoding = "utf-8";
        File javaFile = new File(ProjectUtils.getRootPath() + "/src/main/java/cn/chenzw/toolkit/commons/UriExtUtils.java");
        boolean b = tool.compiler(encoding, javaFile.getAbsolutePath(), outputFile, diagnostics);
        if (!b) {
            StringJoiner rs = new StringJoiner(System.getProperty("line.separator"));
            for (Diagnostic diagnostic : diagnostics.getDiagnostics()) {
                rs.add(String.format("%s:%s[line %d column %d]-->%s%n", diagnostic.getKind(), diagnostic.getSource(), diagnostic.getLineNumber(),
                        diagnostic.getColumnNumber(),
                        diagnostic.getMessage(null)));
                System.out.println("编译失败，原因：" + rs.toString());
            }
        } else {
            System.out.println("编译成功");
        }
    }


}
