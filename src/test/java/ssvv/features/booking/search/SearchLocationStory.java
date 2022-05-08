package ssvv.features.booking.search;

import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Issue;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.thucydides.junit.annotations.Qualifier;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ssvv.steps.serenity.BookingUserSteps;

@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom(value = "src/test/resources/bookingFeatures/locationSearch.csv")
public class SearchLocationStory {
    @Managed(uniqueSession = true)
    public WebDriver webdriver;

    @Steps
    public BookingUserSteps bookingUserSteps;

    public String location;

    public String hotel;

    public Integer count;

    @Qualifier
    public String getQualifier() {
        return location;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void openPageAndAcceptCookies() {
        bookingUserSteps.toHomePage();

        WebDriverWait wait = new WebDriverWait(
                webdriver,
                5
        );
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.id("onetrust-accept-btn-handler")
                )
        );
        bookingUserSteps.acceptCookies();

    }

    @Issue("#BOOKING-Search-1")
    @Test
    public void searchLocation() {
        openPageAndAcceptCookies();
        bookingUserSteps.searchLocation(getLocation());
        bookingUserSteps.clickSearch();

        bookingUserSteps.shouldExistOnPage(getHotel());
    }

    @Issue("#BOOKING-Search-2")
    @Test
    public void searchLocationCount() {
        openPageAndAcceptCookies();
        bookingUserSteps.searchLocation(getLocation());
        bookingUserSteps.clickSearch();

        bookingUserSteps.shouldExistNumberOfAccommodations(getCount());
    }

}
