package com.intellij.plugins.haxe.util;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CompilationUtilTest extends Assert {

    @DataProvider
    public Object[][] getClassNameByPathData() {
        return new Object[][]{
                {"Main", "C:\\Program Files (x86)\\JetBrains\\Main.hx"},
                {"Main", "/home/fedor/JetBrains/Main.hx"}
        };
    }

    @Test(dataProvider = "getClassNameByPathData")
    public void testGetClassNameByPath(String expectedClassName, String classPath) {
        String className = CompilationUtil.getClassNameByPath(classPath);
        assertEquals(className, expectedClassName);
    }
}
