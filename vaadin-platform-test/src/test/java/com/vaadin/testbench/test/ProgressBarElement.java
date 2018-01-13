package com.vaadin.testbench.test;

import com.vaadin.testbench.TestBenchElement;
import com.vaadin.testbench.elementsbase.Element;

@Element("vaadin-progress-bar")
public class ProgressBarElement extends TestBenchElement {

    public double getValue() {
        return getPropertyDouble("value");
    }
}
