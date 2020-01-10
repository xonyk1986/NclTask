package definitions;

import cucumber.api.java.bs.A;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java8.Ar;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.server.handler.DeleteSession;
import org.w3c.dom.Text;

import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static support.TestContext.getDriver;
import static support.TestContext.teardown;

public class NclStepDefs {
    @Given("A Guest")
    public void aGuest() {
        getDriver().get("https://www.ncl.com/");
    }

    @And("I am on Homepage")
    public void iAmOnHomepage() {
        String web = getDriver().getCurrentUrl();
        assertThat(web).containsIgnoringCase("https://www.ncl.com/");
    }

    @And("I navigated to {string} page")
    public void iNavigatedToPage(String tab) {
        WebElement element = getDriver().findElement(By.xpath("//a[@class='linkNav'][contains(text(),'Explore')]"));
        new Actions(getDriver()).moveToElement(element).perform();

        WebElement arg = getDriver().findElement(By.xpath("//a[@class='linkItem'][contains(text(),'" + tab + "')]"));
        JavascriptExecutor executor = (JavascriptExecutor) getDriver();
        executor.executeScript("arguments[0].click();", arg);

    }

    @When("I search for destination {string}")
    public void iSearchForDestination(String dest) {
        getDriver().findElement(By.xpath("//span[contains(text(),'Destination')]")).click();
        getDriver().findElement(By.xpath("//li[contains(text(),'" + dest + "')]")).click();
        getDriver().findElement(By.xpath("//button[text()='FIND EXCURSIONS']")).click();


    }

    @Then("Shore Excursions page is present")
    public void shoreExcursionsPageIsPresent() {
        String page = getDriver().findElement(By.xpath("//div[@class='shore-explore']//h2[text()='Shore Excursions']")).getText();
        assertThat(page).containsIgnoringCase("Shore Excursions");
    }

    @And("Results are filtered by {string}")
    public void resultsAreFilteredBy(String filter) {
        String page = getDriver().findElement(By.xpath("//a[@title='Alaska Cruises']")).getText();
        assertThat(page).containsIgnoringCase(filter);
    }


    @And("Filter By Ports are only belong to {string}  or {string}")
    public void filterByPortsAreOnlyBelongToOr(String Al, String Br) {
        getDriver().findElement(By.xpath("//span[contains(text(),'Port')]")).click();

        List<WebElement> allPorts = getDriver().findElements(By.xpath("//ul[@id='ports']//li//label"));


        for (WebElement element : allPorts) {


            if (element.getText().contains(Al)) {


                System.out.println(element.getText() + " -> " + Al);
            } else if (element.getText().contains(Br)) {


                System.out.println(element.getText() + " -> " + Br);
            } else {

                assertThat(allPorts).extracting(WebElement::getText).contains(Al, Br);





            }
        }

    }

}

