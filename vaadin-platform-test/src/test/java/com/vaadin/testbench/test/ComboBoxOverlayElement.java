package com.vaadin.testbench.test;

import java.util.List;

import com.vaadin.testbench.TestBenchElement;
import com.vaadin.testbench.elementsbase.Element;

@Element("vaadin-combo-box-overlay")
public class ComboBoxOverlayElement extends TestBenchElement {

    public List<String> getItems() {
        return (List<String>) executeScript(
                "var overlay=arguments[0]; return overlay._items.map(function(item) { return overlay.getItemLabel(item);});",
                this);
    }

}
