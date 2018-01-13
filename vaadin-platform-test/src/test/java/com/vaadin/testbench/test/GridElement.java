package com.vaadin.testbench.test;

import com.vaadin.testbench.TBMethods;
import com.vaadin.testbench.TestBenchElement;
import com.vaadin.testbench.elementsbase.Element;

@Element("vaadin-grid")
public class GridElement extends TestBenchElement {

    public void scrollToRow(int row) {
        callFunction("_scrollToIndex", row);
    }

    public long getFirstVisibleRowIndex() {
        return getPropertyDouble("_firstVisibleIndex").longValue();
    }

    public long getRowCount() {
        return getPropertyDouble("_virtualCount").longValue();
    }

    private TestBenchElement getHeader() {
        return TBMethods.getPropertyElement(this, "$", "header");
    }

    private TestBenchElement getFooter() {
        return TBMethods.getPropertyElement(this, "$", "footer");
    }
    
}