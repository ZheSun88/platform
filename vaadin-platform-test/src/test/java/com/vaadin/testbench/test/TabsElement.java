package com.vaadin.testbench.test;

import java.util.List;

import com.vaadin.testbench.TBMethods;
import com.vaadin.testbench.TestBenchElement;
import com.vaadin.testbench.elementsbase.Element;

@Element("vaadin-tabs")
public class TabsElement extends TestBenchElement {

    public void setSelectedTabIndex(int selectedTab) {
        setProperty("selected", selectedTab);
    }

    public int getSelectedTabIndex() {
        return getPropertyInteger("selected");
    }

    public TabElement getSelectedTab() {
        return ((TestBenchElement) executeScript(
                "return arguments[0].children[arguments[0].selected];", this))
                        .wrap(TabElement.class);
    }

    public int getTab(String text) {
        List<TestBenchElement> children = TBMethods.getChildren(this);
        for (int i = 0; i < children.size(); i++) {
            String tabText = children.get(i).wrap(TabElement.class).getText();
            if (text.equals(tabText))
                return i;
        }
        return -1;
    }

}
