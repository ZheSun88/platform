package com.vaadin.testbench.test;

import com.vaadin.testbench.TBMethods;
import com.vaadin.testbench.TestBenchElement;
import com.vaadin.testbench.elementsbase.Element;

@Element("vaadin-radio-button")
public class RadioButtonElement extends TestBenchElement {
    public String getItem() {
        return TBMethods.getPropertyString(this, "firstChild", "textContent");
    }

    public String getValue() {
        return getPropertyString("value");
    }
}
