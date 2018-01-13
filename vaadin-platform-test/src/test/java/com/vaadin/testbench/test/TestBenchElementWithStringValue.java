package com.vaadin.testbench.test;

import com.vaadin.testbench.TestBenchElement;

public class TestBenchElementWithStringValue extends TestBenchElement {
    public String getValue() {
        return getPropertyString("value");
    }

    public void setValue(String string) {
        setProperty("value", string);
    }

    @Override
    public void clear() {
        setValue("");
    }

}
