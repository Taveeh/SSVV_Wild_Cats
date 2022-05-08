package ssvv.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@DefaultUrl("https://www.booking.com/")
public class BookingPage extends PageObject {

    @FindBy(id = "ss")
    private WebElementFacade locationField;

    @FindBy(className = "xp__button")
    private WebElementFacade searchButton;
    public void enterLocation(String location) {
        locationField.type(location);
    }

    public void search() {
        searchButton.click();
    }
    public void acceptCookies() {
        find(By.id("onetrust-accept-btn-handler")).click();
    }

    public List<String> getAccommodationNames() {
        WebElementFacade hotelsList = find(By.id("right"));
        return hotelsList.findElements(By.tagName("h3")).stream()
                .map(element -> element.getText().toLowerCase(Locale.ROOT))
                .collect(Collectors.toList());
    }

    public void filterByRating(int rating) {
        find(By.name("review_score=" + rating)).click();
    }
    public String findNumberOfAccommodations() {
        WebElementFacade accommodations = find(By.id("right"));

        return accommodations.findElements(By.tagName("h1"))
                .stream()
                .map(WebElement::getText)
                .findFirst()
                .get();

    }

}
