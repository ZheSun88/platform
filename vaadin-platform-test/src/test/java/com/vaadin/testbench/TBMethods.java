package com.vaadin.testbench;

import java.util.List;
import java.util.stream.Stream;

public class TBMethods {

    public static TestBenchElement getPropertyElement(TestBenchElement element,
            String... name) {
        return (TestBenchElement) getProperty(element, name);
    }

    public static Object getProperty(TestBenchElement element, String[] name) {
        String script = "var value = arguments[0]";
        script += createPropertyChain(name);
        script += ";";

        Stream<Object> parameters = Stream.concat(Stream.of(element),
                Stream.of(name));
        return element.executeScript(script + "return value;",
                parameters.toArray());
    }

    private static String createPropertyChain(String[] name) {
        String result = "";
        for (int i = 0; i < name.length; i++) {
            result += "[arguments[" + (i + 1) + "]]";
        }
        return result;
    }

    public static String getPropertyString(TestBenchElement element,
            String... name) {
        return (String) getProperty(element, name);
    }

    public static List<TestBenchElement> getChildren(TestBenchElement element) {
        return (List<TestBenchElement>) element
                .executeScript("return arguments[0].children", element);
    }

}
