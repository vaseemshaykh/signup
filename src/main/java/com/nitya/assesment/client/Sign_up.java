package com.nitya.assesment.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Sign_up implements EntryPoint {

	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

	public void onModuleLoad() {
		final Button signUpButton = new Button("Sign Up");
		final TextBox usernameField = new TextBox();
		final TextBox emailField = new TextBox();
		final PasswordTextBox passwordField = new PasswordTextBox();
		final Label errorLabel = new Label();

		signUpButton.addStyleName("signUpButton");

		VerticalPanel panel = new VerticalPanel();
		panel.add(new Label("Username:"));
		panel.add(usernameField);
		panel.add(new Label("Email:"));
		panel.add(emailField);
		panel.add(new Label("Password:"));
		panel.add(passwordField);
		panel.add(signUpButton);
		panel.add(errorLabel);

		RootPanel.get().add(panel);

		usernameField.setFocus(true);

		class SignUpHandler implements ClickHandler, KeyUpHandler {
			public void onClick(ClickEvent event) {
				signUp();
			}

			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					signUp();
				}
			}

			private void signUp() {
				errorLabel.setText("");
				String username = usernameField.getText();
				String email = emailField.getText();
				String password = passwordField.getText();

				if (!isValidInput(username, email, password)) {
					errorLabel.setText("Please fill in all fields");
					return;
				}

				signUpButton.setEnabled(false);

				greetingService.signUp(username, email, password, new AsyncCallback<String>() {
					public void onFailure(Throwable caught) {
						errorLabel.setText("Sign-up failed. Please try again.");
						signUpButton.setEnabled(true);
					}

					public void onSuccess(String result) {
						if (result.equals("success")) {
							errorLabel.setText("Sign-up successful!");
						} else {
							errorLabel.setText("Sign-up failed. Please try again.");
						}
						signUpButton.setEnabled(true);
					}
				});
			}

			private boolean isValidInput(String username, String email, String password) {
				return !username.isEmpty() && !email.isEmpty() && !password.isEmpty();
			}
		}

		SignUpHandler handler = new SignUpHandler();
		signUpButton.addClickHandler(handler);
		usernameField.addKeyUpHandler(handler);
		emailField.addKeyUpHandler(handler);
		passwordField.addKeyUpHandler(handler);

		// Create the Login Button
		final Button loginButton = new Button("Login");

		// Add a ClickHandler to the Login Button
		loginButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				String username = usernameField.getText();
				String password = passwordField.getText();

				greetingService.login(username, password, new AsyncCallback<String>() {
					@Override
					public void onFailure(Throwable caught) {
						// Handle login failure
						errorLabel.setText("Login failed. Please try again.");
					}

					@Override
					public void onSuccess(String result) {
						if (result.equals("success")) {
							// Redirect to dashboard page
							Window.Location.assign("Dashboard.html");
						} else {
							// Show error message
							errorLabel.setText("Invalid username or password");
						}
					}
				});
			}
		});

		// Add the Login Button to the panel
		panel.add(loginButton);
	}
}
