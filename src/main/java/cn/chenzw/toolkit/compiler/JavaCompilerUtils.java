package cn.chenzw.toolkit.compiler;

import java.io.File;
import java.io.IOException;

public class JavaCompilerUtils {

    private JavaCompilerUtils() {
    }

    public static void compile(File javaSourceFile) {


    }

    public static void main(String[] args) throws IOException {
        /*
        File javaFile = new File(ProjectUtils.getRootPath() + "/src/main/java/cn/chenzw/toolkit/commons/UriExtUtils.java");

        System.out.println(javaFile.exists());
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
        fileManager.setLocation(StandardLocation.CLASS_OUTPUT,
                Arrays.asList(new File[] { new File(ProjectUtils.getRootPath()) }));

        Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjects(javaFile);
        JavaCompiler.CompilationTask task = compiler.getTask(new FileWriter(new File("UriExtUtils.class")), fileManager, null, null, null, compilationUnits);


        task.call();
        fileManager.close();
        */


       /* JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

        // 建立DiagnosticCollector对象
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);


        // 建立用于保存被编译文件名的对象
        // 每个文件被保存在一个从JavaFileObject继承的类中
        Iterable<? extends JavaFileObject> compilationUnits = fileManager
                .getJavaFileObjectsFromStrings(Arrays.asList("test3.java"));
        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager,
                diagnostics, null, null, compilationUnits);
        // 编译源程式
        boolean success = task.call();
        fileManager.close();
        System.out.println((success) ? "编译成功" : "编译失败");*/


       /* List<String> paths = Arrays.asList("");

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(
                diagnostics, null, null);
        fileManager.setLocation(StandardLocation.CLASS_OUTPUT,
                Arrays.asList(new File[]{new File("")}));
        Iterable<? extends JavaFileObject> compilationUnits = fileManager
                .getJavaFileObjectsFromStrings(paths);
        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager,
                diagnostics, null, null, compilationUnits);
        boolean result = task.call();
        fileManager.close();*/

        String path = System.getProperty("java.class.path");
        System.out.println(path);

    }
}
