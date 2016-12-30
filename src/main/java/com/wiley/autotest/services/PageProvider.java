package com.wiley.autotest.services;

import com.wiley.autotest.selenium.context.HelperRegistry;
import com.wiley.autotest.selenium.context.IPage;
import com.wiley.autotest.selenium.context.ScreenshotHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.wiley.autotest.selenium.SeleniumHolder.getWebDriver;

/**
 * Created by vefimov on 23.12.2016.
 */
@Component
public class PageProvider {

    @Autowired
    private HelperRegistry registry;

    public <E extends IPage> E getPage(final Class<E> helperClass, ScreenshotHelper screenshotHelper) {
        E helper = registry.getPageHelper(helperClass);
        helper.init(getWebDriver(), screenshotHelper);
        return helper;
    }

    public <E extends IPage> E getPage(final Class<E> helperClass) {
        E helper = registry.getPageHelper(helperClass);
        //TODO VE implement proper support of ScreenshotHelper. As for now it's only used for screenshot base tests passing it as null
        helper.init(getWebDriver(), null);
        return helper;
    }

    public <E extends IPage> E getPage(final Class<E> helperClass, ScreenshotHelper screenshotHelper, final String urlToOpen) {
        E helper = getPage(helperClass, screenshotHelper);
        helper.load(urlToOpen);
        return helper;
    }

    public <E extends IPage> E getPage(final Class<E> helperClass, final String urlToOpen) {
        E helper = getPage(helperClass);
        helper.load(urlToOpen);
        return helper;
    }
}
