package com.dev.itFramework.utils;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.dev.itFramework.UrlMapping;
import com.dev.itFramework.utils.ClassMethod;

public class ListeControllerAnnote {
    public static List<Class<?>> getController(Class annotation, String nomPackage) throws  Exception{
        String packagePath = nomPackage.replace('.', '/');
        List<Class<?>> classes = new ArrayList<>();
        File packageDir = new File(Thread.currentThread().getContextClassLoader().getResource(packagePath).toURI());
        for (File file : packageDir.listFiles()) {
            if (file.isFile() && file.getName().endsWith(".class")) {
                String nomClass = nomPackage + "." + file.getName().replace(".class", "");
                Class<?> clazz = Class.forName(nomClass);
                if (clazz.isAnnotationPresent(annotation)) {
                    classes.add(clazz);
                }
            }
            else if (file.isDirectory()) {
                classes.addAll(getController(annotation, nomPackage + "." + file.getName()));
            }
        }
        return classes;
    }

    public static Map<String, ClassMethod> getMapping(List<Class<?>> controllers) {
        Map<String, ClassMethod> mappingUrls = new HashMap<>();

        for (Class<?> controller : controllers) {
            Method[] methods = controller.getDeclaredMethods();

            for (Method method : methods) {
                if (method.isAnnotationPresent(UrlMapping.class)) {
                    UrlMapping annotation =  method.getAnnotation(UrlMapping.class);
                    String url = annotation.value();
                    ClassMethod classMethod = new ClassMethod();
                    mappingUrls.put(url, classMethod);
                }
            }
        }
        return mappingUrls;
    }
}
