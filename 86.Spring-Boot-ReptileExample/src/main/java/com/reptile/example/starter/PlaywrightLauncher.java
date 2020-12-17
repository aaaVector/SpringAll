package com.reptile.example.starter;

import com.microsoft.playwright.*;

import java.nio.file.Paths;

/**
 * @Author: xy
 * @Date: 2020/12/17 23:00
 */
public class PlaywrightLauncher {


    public static void main(String[] args) {
        final Playwright playwright = Playwright.create();
        final BrowserType chromium = playwright.chromium();
        try {
            Browser browser = chromium.launch(new BrowserType.LaunchOptions().withHeadless(true));
            BrowserContext context = browser.newContext(
                    new Browser.NewContextOptions().withViewport(800, 600));
            Page page = context.newPage();
            page.navigate("http://www.baidu.com/");
            Thread.sleep(10000);
            browser.close();
            playwright.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
