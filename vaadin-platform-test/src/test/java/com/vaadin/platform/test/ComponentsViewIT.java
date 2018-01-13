package com.vaadin.platform.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import com.vaadin.testbench.TestBenchTestCase;
import com.vaadin.testbench.test.ButtonElement;
import com.vaadin.testbench.test.CheckBoxElement;
import com.vaadin.testbench.test.ComboBoxElement;
import com.vaadin.testbench.test.DatePickerElement;
import com.vaadin.testbench.test.DialogElement;
import com.vaadin.testbench.test.GridElement;
import com.vaadin.testbench.test.NotificationElement;
import com.vaadin.testbench.test.PasswordFieldElement;
import com.vaadin.testbench.test.ProgressBarElement;
import com.vaadin.testbench.test.RadioButtonGroupElement;
import com.vaadin.testbench.test.TabsElement;
import com.vaadin.testbench.test.TextAreaElement;
import com.vaadin.testbench.test.TextFieldElement;
import com.vaadin.testbench.test.UploadElement;

public class ComponentsViewIT extends TestBenchTestCase {

    private ComboBoxElement comboBox;
    private DialogElement dialog;
    private ButtonElement button;
    private CheckBoxElement checkBox;
    private DatePickerElement datePicker;
    private GridElement grid;
    private NotificationElement notification;
    private ProgressBarElement progressBar;
    private RadioButtonGroupElement radioButtonGroup;
    private TextFieldElement textField;
    private PasswordFieldElement passwordField;
    private TextAreaElement textArea;
    private UploadElement upload;
    private TabsElement tabs;

    @Before
    public void setup() {
        setDriver(new ChromeDriver());
        getDriver().get("http://localhost:8080/");
        comboBox = $(ComboBoxElement.class).first();
        button = $(ButtonElement.class).first();
        checkBox = $(CheckBoxElement.class).first();
        datePicker = $(DatePickerElement.class).first();
        progressBar = $(ProgressBarElement.class).first();
        notification = $(NotificationElement.class).first();
        radioButtonGroup = $(RadioButtonGroupElement.class).first();
        textField = $(TextFieldElement.class).first();
        textArea = $(TextAreaElement.class).first();
        passwordField = $(PasswordFieldElement.class).first();
        upload = $(UploadElement.class).first();
        tabs = $(TabsElement.class).first();
        grid = $(GridElement.class).first();

        dialog = $(DialogElement.class).first();
        dialog.close();
    }

    @After
    public void teardown() {
        getDriver().quit();
    }

    @Test
    public void combobox() {
        Assert.assertEquals("", comboBox.getValue());
        comboBox.setValue("First");
        Assert.assertEquals("First", comboBox.getValue());
        Assert.assertEquals("First",
                comboBox.getInputElement().getPropertyString("value"));
        comboBox.selectByText("Second");
        Assert.assertEquals("Second", comboBox.getValue());
        comboBox.setValue("nonexistent");
        Assert.assertEquals("", comboBox.getValue());

        Assert.assertFalse(comboBox.isPopupOpen());
        comboBox.openPopup();
        Assert.assertTrue(comboBox.isPopupOpen());
        comboBox.closePopup();
        Assert.assertFalse(comboBox.isPopupOpen());

        List<String> items = comboBox.getPopupSuggestions();
        Assert.assertArrayEquals(
                new String[] { "First", "Second", "Second", "Third" },
                items.toArray());
    }

    @Test
    public void button() {
        String row = getLogRow(0);
        button.click();
        String row2 = getLogRow(0);
        Assert.assertNotEquals(row, row2);
        Assert.assertTrue(row2.endsWith(". Clicked button"));

        Assert.assertEquals("Button text", button.getText());
    }

    @Test
    public void checkBox() {
        Assert.assertFalse(checkBox.isChecked());
        checkBox.setChecked(true);
        Assert.assertTrue(checkBox.isChecked());
        checkBox.setChecked(false);
        Assert.assertFalse(checkBox.isChecked());

        Assert.assertEquals("Checkbox label", checkBox.getLabel());
    }

    @Test
    public void datePicker() {
        Assert.assertEquals("", datePicker.getValue());
        Assert.assertEquals(null, datePicker.getDate());
        datePicker.setValue("2017-05-06");
        Assert.assertEquals("2017-05-06", datePicker.getValue());
        Assert.assertEquals(LocalDate.of(2017, 5, 6), datePicker.getDate());
    }

    @Test
    public void grid() {
        Assert.assertEquals(500, grid.getRowCount());

        Assert.assertEquals(0, grid.getFirstVisibleRowIndex());
        // Assert.assertEquals("Item 0", grid.getCell(0, 0).getText());
        // Assert.assertEquals("Column 2 data for 0",
        // grid.getCell(0, 1).getText());

        grid.scrollToRow(123);
        Assert.assertEquals(123, grid.getFirstVisibleRowIndex());
        // Assert.assertEquals("Item 123", grid.getCell(123, 0).getText());
        // Assert.assertEquals("Column 2 data for 123",
        // grid.getCell(123, 1).getText());
    }

    @Test
    public void notification() {
        // TODO
        // Assert.assertEquals("Hello", notification.getText());
        notification.close();
        Assert.assertFalse(notification.isOpen());
        // Closing a notification does not remove it from the DOM
        Assert.assertEquals(1, $(NotificationElement.class).all().size());
    }

    @Test
    public void progressBar() {
        Assert.assertEquals(0.75, progressBar.getValue(), 0.01);
    }

    @Test
    public void radioButtonGroup() {
        Assert.assertNull(radioButtonGroup.getSelectedText());
        Assert.assertNull(radioButtonGroup.getValue());

        radioButtonGroup.setValue("3");
        Assert.assertEquals("3", radioButtonGroup.getValue());
        Assert.assertEquals("Item 2", radioButtonGroup.getSelectedText());

        radioButtonGroup.selectByText("Item 3");
        Assert.assertEquals("4", radioButtonGroup.getValue());
        Assert.assertEquals("Item 3", radioButtonGroup.getSelectedText());

    }

    @Test
    public void textField() {
        Assert.assertEquals("", textField.getValue());
        textField.setValue("foo");
        Assert.assertEquals("foo", textField.getValue());
        textField.setValue("bar");
        Assert.assertEquals("bar", textField.getValue());
        textField.clear();
        Assert.assertEquals("", textField.getValue());
    }

    @Test
    public void textArea() {
        Assert.assertEquals("", textArea.getValue());
        textArea.setValue("foo");
        Assert.assertEquals("foo", textArea.getValue());
        textArea.setValue("bar");
        Assert.assertEquals("bar", textArea.getValue());
        textArea.clear();
        Assert.assertEquals("", textArea.getValue());
    }

    @Test
    public void passwordField() {
        Assert.assertEquals("", passwordField.getValue());
        passwordField.setValue("foo");
        Assert.assertEquals("foo", passwordField.getValue());
        passwordField.setValue("bar");
        Assert.assertEquals("bar", passwordField.getValue());
        passwordField.clear();
        Assert.assertEquals("", passwordField.getValue());

        Assert.assertFalse(passwordField.isPasswordVisible());
        passwordField.setPasswordVisible(true);
        Assert.assertTrue(passwordField.isPasswordVisible());
        passwordField.setPasswordVisible(true);
        Assert.assertTrue(passwordField.isPasswordVisible());
        passwordField.setPasswordVisible(false);
        Assert.assertFalse(passwordField.isPasswordVisible());
    }

    @Test
    public void tabs() throws Exception {
        Assert.assertEquals(0, tabs.getSelectedTabIndex());
        Assert.assertEquals(0, tabs.getTab("foo"));
        Assert.assertEquals(1, tabs.getTab("bar"));

        Assert.assertEquals("foo", tabs.getSelectedTab().getText());
        tabs.setSelectedTabIndex(1);
        Assert.assertEquals(1, tabs.getSelectedTabIndex());
        Assert.assertEquals("bar", tabs.getSelectedTab().getText());
    }

    @Test
    public void upload() throws Exception {
        byte[] file1Contents = "This is file 1"
                .getBytes(StandardCharsets.UTF_8);
        byte[] file2Contents = "This is another åäö file"
                .getBytes(StandardCharsets.UTF_8);

        File file1 = createTempFile(file1Contents);

        upload.upload(file1);
        String logRow = getLogRow(0);
        Assert.assertTrue(logRow.endsWith("File " + file1.getName()
                + " of size " + file1Contents.length + " received"));

        File file2 = createTempFile(file2Contents);
        upload.upload(file2);
        logRow = getLogRow(0);
        Assert.assertTrue(logRow.endsWith("File " + file2.getName()
                + " of size " + file2Contents.length + " received"));
    }

    private File createTempFile(byte[] contents) throws IOException {
        File tempFile = File.createTempFile("TestFileUpload", ".txt");
        try (FileOutputStream out = new FileOutputStream(tempFile)) {
            out.write(contents);
        }
        tempFile.deleteOnExit();
        return tempFile;
    }

    private String getLogRow(int i) {
        return findElement(By.id("log")).findElements(By.tagName("div")).get(i)
                .getText();
    }
}
