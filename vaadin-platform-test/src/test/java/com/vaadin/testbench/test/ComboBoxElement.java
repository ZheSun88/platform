package com.vaadin.testbench.test;

import java.util.List;
import java.util.stream.Stream;

import com.vaadin.testbench.TBMethods;
import com.vaadin.testbench.TestBenchElement;
import com.vaadin.testbench.elementsbase.Element;

@Element("vaadin-combo-box")
public class ComboBoxElement extends TestBenchElement {
    public void selectByText(String text) {
        setValue(text);
    }

    public void openPopup() {
        callFunction("open");
    }

    public void closePopup() {
        callFunction("close");
    }

    public List<String> getPopupSuggestions() {
        openPopup();
        return getOverlay().getItems();
    }

    public String getValue() {
        return getPropertyString("value");
    }

    public void setValue(String value) {
        setProperty("value", value);
    }

    public InputElement getInputElement() {
        return getTextField().$(InputElement.class).attribute("part", "value")
                .first();
    }

    protected TextFieldElement getTextField() {
        return TBMethods.getPropertyElement(this, "$", "input")
                .wrap(TextFieldElement.class);
    }

    protected ComboBoxOverlayElement getOverlay() {
        return TBMethods.getPropertyElement(this, "$", "overlay")
                .wrap(ComboBoxOverlayElement.class);
    }

    public boolean isPopupOpen() {
        return getPropertyBoolean("opened");
    }
}
