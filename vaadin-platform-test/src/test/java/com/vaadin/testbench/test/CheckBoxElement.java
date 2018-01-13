package com.vaadin.testbench.test;

import com.vaadin.testbench.TBMethods;
import com.vaadin.testbench.TestBenchElement;
import com.vaadin.testbench.elementsbase.Element;

@Element("vaadin-checkbox")
public class CheckBoxElement extends TestBenchElement {
    public boolean isChecked() {
        return getPropertyBoolean("checked");
    }

    public void setChecked(boolean checked) {
        setProperty("checked", checked);
    }

    public String getLabel() {
        // FIXME When there is no label
        return TBMethods.getPropertyString(this, "firstChild", "textContent");
    }

    protected LabelElement getLabelElement() {
        return TBMethods.getPropertyElement(this, "$", "label")
                .wrap(LabelElement.class);
    }

    protected InputElement getInputElement() {
        return $(InputElement.class).attribute("part", "native-checkbox")
                .first();
    }
}
