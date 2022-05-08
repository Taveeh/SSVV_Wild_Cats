package ssvv.steps.serenity;

import net.thucydides.core.annotations.Step;
import ssvv.pages.BookingPage;

import java.util.Locale;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.hasItem;

public class BookingUserSteps {

    BookingPage bookingPage;

    @Step
    public void searchLocation(String location) {
        bookingPage.enterLocation(location);
    }

    @Step
    public void clickSearch() {
        bookingPage.search();
    }

    @Step
    public void toHomePage() {
        bookingPage.open();
    }

    @Step
    public void shouldExistOnPage(String value) {
        assertThat(
                bookingPage.getAccommodationNames(),
                hasItem(
                        containsString(
                                value.toLowerCase(Locale.ROOT)
                        )
                )
        );
    }

    @Step
    public void shouldNotExistOnPage(String value) {
        assertThat(
                bookingPage.getAccommodationNames(),
                not(
                        hasItem(
                                containsString(
                                        value.toLowerCase(Locale.ROOT)
                                )
                        )
                )
        );
    }

    @Step
    public void shouldExistNumberOfAccommodations(int count) {
        assertThat(
                bookingPage.findNumberOfAccommodations(),
                containsString(
                        String.valueOf(count)
                )
        );
    }

    @Step
    public void acceptCookies() {
        bookingPage.acceptCookies();
    }

    @Step
    public void filterByRating(Integer rating) {
        bookingPage.filterByRating(rating);
    }

    @Step
    public void filterVeryGoodRating() {
        bookingPage.filterByRating(80);
    }

    @Step
    public void filterSuperbRating() {
        bookingPage.filterByRating(90);
    }

}
