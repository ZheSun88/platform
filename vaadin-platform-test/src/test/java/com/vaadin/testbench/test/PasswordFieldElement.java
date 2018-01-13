package com.vaadin.testbench.test;

import com.vaadin.testbench.elementsbase.Element;

@Element("vaadin-password-field")
public class PasswordFieldElement extends TestBenchElementWithStringValue {

    public boolean isPasswordVisible() {
        return getPropertyBoolean("passwordVisible");
    }

    public void setPasswordVisible(boolean passwordVisible) {
        if (isPasswordVisible() != passwordVisible) {
            callFunction("_togglePasswordVisibility");
        }
    }

}
