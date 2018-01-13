package com.vaadin.testbench.test;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import com.vaadin.testbench.TestBenchElement;
import com.vaadin.testbench.elementsbase.Element;

@Element("vaadin-radio-group")
public class RadioButtonGroupElement extends TestBenchElement {
    public List<String> getOptions() {
        return getRadioButtons().stream().map(RadioButtonElement::getItem)
                .collect(Collectors.toList());
    }

    private List<RadioButtonElement> getRadioButtons() {
        return $(RadioButtonElement.class).all();
    }

    public void selectByText(String text) {
        Optional<RadioButtonElement> radioButton = getRadioButtonByText(text);
        if (!radioButton.isPresent()) {
            throw new NoSuchElementException(
                    "No item with text '" + text + "' found");
        }
        radioButton.get().click();
    }

    public String getSelectedText() {
        Optional<RadioButtonElement> button = getSelectedRadioButton();
        return button.map(RadioButtonElement::getItem).orElse(null);
    }

    private Optional<RadioButtonElement> getSelectedRadioButton() {
        return getRadioButtonByValue(getValue());
    }

    private Optional<RadioButtonElement> getRadioButtonByText(String text) {
        if (text == null) {
            return Optional.empty();
        }
        return getRadioButtons().stream()
                .filter(radioButton -> text.equals(radioButton.getItem()))
                .findFirst();
    }

    private Optional<RadioButtonElement> getRadioButtonByValue(String value) {
        if (value == null) {
            return Optional.empty();
        }
        return getRadioButtons().stream()
                .filter(radioButton -> value.equals(radioButton.getValue()))
                .findFirst();
    }

    public void setValue(String value) {
        setProperty("value", value);
    }

    public String getValue() {
        return getPropertyString("value");
    }
}
