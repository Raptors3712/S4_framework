package com.dev.itFramework.utils;

import java.lang.reflect.Method;

public class ClassMethod {
    private Class<?> clazz;
    private Method methode;

    public ClassMethod() {}

    public Class<?> getClazz() {
        return this.clazz;
    }

    public Method getMethode() {
        return this.methode;
    }

    public void setCLazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public void setMethode(Method methode) {
        this.methode = methode;
    }
}
