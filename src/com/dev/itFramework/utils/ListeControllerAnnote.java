package com.dev.itFramework.utils;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.io.File;

// import java.lang.Exception;
import java.net.URISyntaxException;

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
}
