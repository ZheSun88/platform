package com.vaadin.testbench.test;

import com.vaadin.testbench.TestBenchElement;
import com.vaadin.testbench.elementsbase.Element;

@Element("vaadin-notification")
public class NotificationElement extends TestBenchElement {
    public void close() {
        callFunction("close");
    }

    public boolean isOpen() {
        return getPropertyBoolean("opened");
    }
}