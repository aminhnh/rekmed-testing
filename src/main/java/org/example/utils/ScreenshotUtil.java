package org.example.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ScreenshotUtil {
    public static String takeScreenshot(WebDriver driver, String screenshotName) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String destination = "E:/Kuliah/Semester 4/screenshots/" + screenshotName + ".png";
        try {
            Files.createDirectories(Paths.get("E:/Kuliah/Semester 4/screenshots/"));
            Files.copy(source.toPath(), Paths.get(destination));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return destination;
    }
}