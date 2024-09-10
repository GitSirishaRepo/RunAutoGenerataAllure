package steps;

import factory.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.LoginPage;
import utils.DataUtility;
import utils.ElementUtil;
import utils.EqBrikOTP;

import java.util.Properties;

public class LoginInvestorSteps {


    private final LoginPage loginPage;
    private Hooks hooks;
    private final ElementUtil eleUtil;
    private final DataUtility dataUtil;
    private final EqBrikOTP eqBrikOTP;
    protected String OTP;
    protected DriverFactory df;
    public String email;




    public LoginInvestorSteps(Hooks hooks) {
        this.loginPage=hooks.getLoginPage();
        this.eleUtil= hooks.getElementUtil();
        this.eqBrikOTP= hooks.getEbOTP();
        this.dataUtil= hooks.getDataUtil();
        this.df=hooks.getDriverFactory();
    }

    @Given("user is on Investor Login page")
    public void user_is_on_investor_login_page() {
    loginPage.waitForPageToLoad();
    }
    @Given("wait for page to load")
    public void wait_for_page_to_load() throws InterruptedException {
        loginPage.waitForPageLoadNEW();
//       Thread.sleep(5000);
    }
    @When("enter username {string} and password {string}")
    public void enter_username_and_password(String userName, String passWord) {
      Properties prop = df.initProp();
      loginPage.enterCredentials(userName, passWord);

    }
    @When("click on Sign in button")
    public void click_on_sign_in_button() {
        loginPage.clickSignInButton();
    }


    @Then("page title should be {string}")
    public void pageTitleShouldBe(String arg0) {
        String ActualTitle= loginPage.getPageTitle();
        System.out.println("Actual title"+ ActualTitle);
        Assert.assertEquals("Log-in", ActualTitle);
//        Assert.assertEquals("Log-in", ActualTitle);
    }

    @When("user clicks on Sign in link")
    public void userClicksOnSignInLink() {
        loginPage.clickSignIn();
    }
    @And("enters email clicks send verification button")
    public void entersEmailClicksSendVerificationButton() throws InterruptedException {
         email= dataUtil.randomEmailGenerator();
        OTP= loginPage.getVerificationCode(email);

    }


    /*@And("enters {string} clicks send verification button")
    public void entersEmailClicksSendVerificationButton(String ff) throws InterruptedException {

        String email= dataUtil.randomEmailGenerator();
       OTP= loginPage.getVerificationCode(email);

    }*/

    @And("waits for some time")
    public void waitsForSomeTime() throws InterruptedException {
       // Wait<LoginPage> newWait= new FluentWait<LoginPage>(loginPage).withTimeout(Duration.ofMinutes(2));
        loginPage.waitsForSomeTime();
    }



    @When("opt is received and enters otp")
    public void optIsReceivedAndEntersOtp() {
        System.out.println("What is sent ---"+OTP);
        loginPage.enterOTPAndCredentials(OTP);
    }

    @And("clicks verify button")
    public void clicksVerifyButton() {

    }

    @Then("wait for Verify Identity")
    public void waitForVerifyIdentity() {
    loginPage.waitForVerifyIdentity();
    }



    @And("upload and VerifyIdentity")
    public void uploadAndVerifyIdentity() {
        loginPage.uploadAndVerifyIdentity();
    }

    @And("go to main page")
    public void goToMainPage() {
    }
}
