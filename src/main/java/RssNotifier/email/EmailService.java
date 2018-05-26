package RssNotifier.email;

import RssNotifier.data.Subscriber;

import java.util.List;

public class EmailService {
	private String name;
	private String emailAddress;
	private String password;
	private String smtpServer;
	private int smptPort;

	public EmailService() {

	}

	public void sendEmail(List<Subscriber> recipients, String title, String message) throws EmailException {

	}
}
