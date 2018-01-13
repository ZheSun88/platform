package com.vaadin.testbench.test;

import java.io.File;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.WrapsElement;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebElement;

import com.vaadin.testbench.TBMethods;
import com.vaadin.testbench.TestBenchElement;
import com.vaadin.testbench.elementsbase.Element;

@Element("vaadin-upload")
public class UploadElement extends TestBenchElementWithStringValue {

    public void upload(File file) {
        if (isMaxFilesReached()) {
            removeFile(0);
        }
        WebElement uploadElement = setLocalFileDetector();
        uploadElement.sendKeys(file.getPath());
        // TODO Wait for upload to complete
    }

    private void removeFile(int i) {
        executeScript(
                "arguments[0]._removeFile(arguments[0].files[arguments[1]])",
                this, i);
    }

    public int getMaxFiles() {
        return getPropertyInteger("maxFiles");
    }

    public boolean isMaxFilesReached() {
        return getPropertyBoolean("maxFilesReached");
    }

    private TestBenchElement setLocalFileDetector() {
        TestBenchElement uploadElement = getUploadElement();

        WebElement realUploadElement = uploadElement;
        while (realUploadElement instanceof WrapsElement) {
            realUploadElement = ((WrapsElement) realUploadElement)
                    .getWrappedElement();
        }
        if (realUploadElement instanceof RemoteWebElement) {
            ((RemoteWebElement) realUploadElement)
                    .setFileDetector(new LocalFileDetector());
        } else {
            throw new IllegalArgumentException("Expected argument of type "
                    + RemoteWebElement.class.getName() + ", received "
                    + realUploadElement.getClass().getName());
        }

        return uploadElement;
    }

    private TestBenchElement getUploadElement() {
        return TBMethods.getPropertyElement(this, "$", "fileInput");
    }

}
