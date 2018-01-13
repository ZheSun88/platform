package com.vaadin.testbench.test;

import java.time.LocalDate;

import com.vaadin.testbench.TestBenchElement;
import com.vaadin.testbench.elementsbase.Element;

@Element("vaadin-date-picker")
public class DatePickerElement extends TestBenchElement {
    public void clear() {
        setValue("");
    }

    public String getValue() {
        return getPropertyString("value");
    }

    public void setValue(String value) {
        setProperty("value", value);
    }

    public void openPopup() {
        callFunction("open");
    }

    public void setDate(LocalDate date) {
        setValue(date.toString());
    }

    public LocalDate getDate() {
        String value = getValue();
        if (value.isEmpty()) {
            return null;
        }
        return LocalDate.parse(value);
    }
}