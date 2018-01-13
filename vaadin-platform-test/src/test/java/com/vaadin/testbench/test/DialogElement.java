package com.vaadin.testbench.test;

import com.vaadin.testbench.TestBenchElement;
import com.vaadin.testbench.elementsbase.Element;

@Element("vaadin-dialog")
public class DialogElement extends TestBenchElement {

    public void open() {
        setProperty("opened", true);
    }

    public void close() {
        setProperty("opened", false);
    }

}
