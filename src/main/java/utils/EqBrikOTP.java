package utils;


/*
 * EqBrikOTP.java
 *
 * Description:
 * This program fetches the OTP that is sent to gmail account from Microsoft and returns 6 digit OTP
 *
 * Usage:
 * 1) Logins to the gmail account
 * 2) account used
 * String username = "lsnsponsor@gmail.com";
 *	String password = "xavr iyox cied mxqj";
 * 3) If you need to use a different gmail account change values of the above variable username and password
 * 4) program goes to Inbox and reads the latest  email with Subject //"Microsoft on behalf of EquityBrix"
 * 5) trims the 6 digit OTP from body and stores in the OTP variable
 * 6) returns the OTP variable.
 *
 *
 *
 */

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import pages.LoginPage;

import javax.mail.*;
import javax.mail.search.SubjectTerm;
import java.time.Duration;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class EqBrikOTP {

	private final WebDriver driver;
	String host = "imap.gmail.com";
	String username = "lsnsponsor@gmail.com";
	String password = "xavr iyox cied mxqj";
	Session session;
	Store store;
	Folder inbox;
	String OTP;

	public EqBrikOTP(WebDriver driver) {
		this.driver =driver;
	}

	public String getOTP() {
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50000));
		try {
			store= ConnectEmail();
			store.connect(host, username, password);

			Folder inbox = store.getFolder("inbox");

			inbox.open(Folder.READ_ONLY);
			if(inbox.getUnreadMessageCount()>0) {
				Message[] messages = inbox.getMessages();
				if (messages.length > 0) {

					Message latestMessage = messages[messages.length-1];
					String messageContent="Microsoft on behalf of EquityBrix";
					Object content = latestMessage.getContent();
					messageContent = (String) content;
					String regex="Your code is: .*([0-9]{6}).*";
					Pattern pattern = Pattern.compile(regex);
					Matcher matcher = pattern.matcher(messageContent);
					if (matcher.find()) {
						OTP = matcher.group(1);
						System.out.println("OTP found: " + OTP);
					} else {
						System.out.println("No OTP found in the email.");
					}
				} else {
					System.out.println("No messages in inbox.");
				}
				inbox.close(false);
				store.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return OTP;
	}


	public Store ConnectEmail() throws MessagingException {
		Properties props = new Properties(); props.setProperty("mail.store.protocol",
				"imaps"); props.setProperty("mail.imap.ssl.enable", "true");
		props.setProperty("mail.imap.host", host);
		props.setProperty("mail.imap.port", "993");
		session = Session.getDefaultInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
			final Session session = Session.getInstance(props, null);

		});
		 store = session.getStore("imaps");


		return store;

	}

	public void deleteEmail() throws MessagingException {
		String subjectToDelete = "EquityBrix Demo account email verification code";
		store= ConnectEmail();
		try {
			store.connect(host, username, password);
			// Open the inbox folder
			inbox = store.getFolder("INBOX");
			inbox.open(Folder.READ_WRITE);
			// Fetch messages from the inbox
			// Message[] messages = inbox.getMessages();
			Message[] messages = inbox.search(new SubjectTerm(subjectToDelete));
			// Delete the first message as an example
			if (messages.length > 0) {
				Message messageToDelete = messages[0];
				System.out.println("Deleting message: " + messageToDelete.getSubject());
				// Mark the message for deletion
				messageToDelete.setFlag(Flags.Flag.DELETED, true);
				// Close the folder and expunge the deleted message
				inbox.close(true); // true to expunge deleted messages
			} else {
				System.out.println("No messages found in the inbox.");
			}
			// Close the store
			store.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				// Ensure folder is closed and messages are expunged
				if (inbox != null && inbox.isOpen()) {
					inbox.close(true); // true to expunge deleted messages
				}
				if (store != null) {
					store.close();
				}

			} catch (MessagingException e) {
				e.printStackTrace();

			}

		}}}



	

