package ssvv.features.booking.filter;

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
@UseTestDataFrom(value = "src/test/resources/bookingFeatures/ratingFilter.csv")
public class FilterLocationStory {
    @Managed(uniqueSession = true)
    public WebDriver webdriver;

    public String location;
    public Integer rating;
    public String hotel;
    public Boolean exists;

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public Boolean getExists() {
        return exists;
    }

    public void setExists(Boolean exists) {
        this.exists = exists;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Steps
    public BookingUserSteps bookingUserSteps;

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

    @Qualifier
    public String getQualifier() {
        return String.valueOf(rating / 10) + "+";
    }

    public void searchOradea() {
        openPageAndAcceptCookies();
        bookingUserSteps.searchLocation(getLocation());
        bookingUserSteps.clickSearch();
    }

    @Issue("#BOOKING-Filter")
    @Test
    public void filterHotelsByRating() {
        searchOradea();
        bookingUserSteps.filterByRating(getRating());
        if (getExists()) {
            bookingUserSteps.shouldExistOnPage(getHotel());
        } else {
            bookingUserSteps.shouldNotExistOnPage(getHotel());
        }
    }
}
