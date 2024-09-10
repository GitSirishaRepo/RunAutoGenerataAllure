package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utils.ElementUtil;
import utils.EqBrikOTP;
import utils.TimeWait;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.sql.Time;

public class LoginPage {


    private final WebDriver driver;
    private final ElementUtil eleUtil;
    private final EqBrikOTP eqbrikOtp;
    protected By singInTitle= By.xpath("//h2[@aria-level='1']");
//            "//h1[@role='heading']");
    protected By verifyIdentityButton= By.xpath("//button[text()='Verify Identity']");
    protected By FormLabel =By.xpath("//form[@aria-label='Sign in with your email address']");
    protected By Email= By.xpath("//input[@id='signInName']");
//            "//input[@id='email']");
    protected By Password= By.xpath("//input[@id='password']");
    protected By Submit= By.xpath("//button[@id='next']");
    protected By forgotPassword = By.id("forgotPassword");
    protected By signUpNowLink= By.xpath("//a[@id='createAccount']");
    protected By emailID= By.id("email");
    protected By sendOTPButton=By.id("email_ver_but_send");
//            "emailVerificationControl_but_send_code");
    protected By emailVerificationCode=By.id("email_ver_input");
//            "emailVerificationCode");
    protected By buttonVerifyCode=By.id("emailVerificationControl_but_verify_code");//
    protected By buttonChangeEmail=By.id("emailVerificationControl_but_change_claims");
    protected String OTP;
    protected By chooseDocument= By.xpath("//button[text()='Choose document']");
    protected By countrySearchList=By.xpath("//input[@id='country-search']");
    protected By clickPassport=By.xpath("//button[@data-onfido-qa='passport']");
    protected By uploadScanPhotos=By.xpath("//button[@data-onfido-qa='uploaderButtonLink']");
    protected By uploadPhoto=By.xpath( "//button[@data-onfido-qa='image-guide-doc-upload-btn']");
    protected By uploadPhotoFile=By.xpath("//input[@class='onfido-sdk-ui-CustomFileInput-input']");
    protected By uploadPhotoFileConfirm=By.xpath("//button[@data-onfido-qa='confirm-action-btn']");



    public LoginPage(WebDriver driver) {
        this.driver =driver;
        eleUtil = new ElementUtil(driver);
        eqbrikOtp= new EqBrikOTP(driver);
    }

    public String getPageTitle(){
        WebElement title;
        System.out.println(eleUtil.getElement(singInTitle).getText());
        return eleUtil.getElement(singInTitle).getText();
    }


    public void enterCredentials(String userName, String pwd) {
        eleUtil.doSendKeys(Email, userName, TimeWait.WAIT5SECONDS);
        eleUtil.doSendKeys(Password, pwd, TimeWait.WAIT5SECONDS);
    }

    public void clickSignInButton(){
        eleUtil.doClick(Submit);
    }

    public void waitForPageToLoad(){
        eleUtil.waitForElementVisible(singInTitle, 5000);
    }

    public void clickSignIn() {
        eleUtil.doClick(signUpNowLink);
    }

    public String getVerificationCode(String email) throws InterruptedException {
        eleUtil.doSendKeys(emailID, email, TimeWait.WAIT30SECONDS );
        eleUtil.doClick(sendOTPButton,TimeWait.WAIT30SECONDS);
       // eleUtil.waitForElementPresence(buttonVerifyCode, TimeWait.WAIT30SECONDS);
       waitsForSomeTime();
        OTP = eqbrikOtp.getOTP();
        return OTP;


    }

    public void waitForVerifyIdentity(){
        eleUtil.waitForElementVisible(verifyIdentityButton, 50000);
    }

    public void waitsForSomeTime() throws InterruptedException {
       // Wait<ElementUtil> newWait= new FluentWait<ElementUtil>(eleUtil).withTimeout(Duration.ofMinutes(2));
        Thread.sleep(10000);
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10000));
    }
    public void enterOTPAndCredentials(String OTPRec) {
        System.out.println("Im sending"+OTPRec);
        eleUtil.doSendKeys(emailVerificationCode, OTPRec, TimeWait.WAIT30SECONDS);
    }

    public void uploadAndVerifyIdentity() {

        eleUtil.doClick(verifyIdentityButton, TimeWait.WAIT30SECONDS);
        eleUtil.doClick(chooseDocument,TimeWait.WAIT30SECONDS);
//        Actions act = new Actions(driver)
//        act.moveToElement()
        eleUtil.doClick(countrySearchList, TimeWait.WAIT5SECONDS);
        eleUtil.doSendKeys(countrySearchList,"United States of America", TimeWait.WAIT5SECONDS);
        WebElement selectElement=eleUtil.getElement(countrySearchList);
        selectElement.sendKeys(Keys.ARROW_DOWN);
        selectElement.sendKeys(Keys.ENTER);
        eleUtil.doClick(clickPassport, TimeWait.WAIT5SECONDS);
        eleUtil.doClick(uploadScanPhotos, TimeWait.WAIT5SECONDS);

       WebElement popupload= eleUtil.getElement(uploadPhotoFile);
       String projectPath = System.getProperty("user.dir");
       System.out.println("The Path is#####"+projectPath);
       String filePath="\\passport.jpg";
        popupload.sendKeys(projectPath+filePath);
    eleUtil.doClick(uploadPhotoFileConfirm,TimeWait.WAIT5SECONDS);

    }

    public void waitForPageLoadNEW() {
    System.out.println("IN NEW WAIT");
        eleUtil.waitForPageLoadNEW();
    }
}
