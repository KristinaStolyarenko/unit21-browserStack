package tests;

import io.appium.java_client.MobileBy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.qameta.allure.Allure.step;


@Tag("selenide_android")
public class BrowserStackAndroidSelenideTests extends TestBase {

    @Test
    @DisplayName("Successful search in wikipedia android app")
    void searchTest() {
        step("Type search", () -> {
            $(MobileBy.AccessibilityId("Search Wikipedia")).click();
            $(MobileBy.id("org.wikipedia.alpha:id/search_src_text")).val("BrowserStack");
        });
        step("Verify content found", () ->
                $$(MobileBy.id("org.wikipedia.alpha:id/page_list_item_container"))
                        .shouldHave(sizeGreaterThan(0)));
    }

    @Test
    @DisplayName("Check that bookmarks list is empty")
    void bookmarksTest() {
        step("Tap bookmarks list", () -> {
                    $(MobileBy.AccessibilityId("My lists")).click();
        });
        step("Check that list is empty", () -> {
            $$(MobileBy.id("org.wikipedia.alpha:id/empty_title"))
                    .shouldHave(texts("No reading lists yet"));
            $$(MobileBy.id("org.wikipedia.alpha:id/empty_message"))
                    .shouldHave(texts("Save articles to reading lists so you can view them later, even when you're offline."));
        });
    }
}