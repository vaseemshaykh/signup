//package com.nitya.assesment.client;
//
//import com.google.gwt.core.client.GWT;
//import com.google.gwt.event.dom.client.ClickEvent;
//import com.google.gwt.event.dom.client.ClickHandler;
//import com.google.gwt.event.dom.client.KeyCodes;
//import com.google.gwt.event.dom.client.KeyUpEvent;
//import com.google.gwt.event.dom.client.KeyUpHandler;
//import com.google.gwt.user.client.rpc.AsyncCallback;
//import com.google.gwt.user.client.ui.Button;
//import com.google.gwt.user.client.ui.Label;
//import com.google.gwt.user.client.ui.PasswordTextBox;
//import com.google.gwt.user.client.ui.RootPanel;
//import com.google.gwt.user.client.ui.TextBox;
//import com.google.gwt.user.client.ui.VerticalPanel;
//
//public class Login {
//	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);
//
//	public void onModuleLoad() {
//		final Button loginButton = new Button("Login");
//		final TextBox usernameField = new TextBox();
//		final PasswordTextBox passwordField = new PasswordTextBox();
//		final Label errorLabel = new Label();
//
//		loginButton.addStyleName("loginButton");
//
//		VerticalPanel panel = new VerticalPanel();
//		panel.add(new Label("Username:"));
//		panel.add(usernameField);
//		panel.add(new Label("Password:"));
//		panel.add(passwordField);
//		panel.add(loginButton);
//		panel.add(errorLabel);
//
//		RootPanel.get().add(panel);
//
//		usernameField.setFocus(true);
//
//		class LoginHandler implements ClickHandler, KeyUpHandler {
//			public void onClick(ClickEvent event) {
//				login();
//			}
//
//			public void onKeyUp(KeyUpEvent event) {
//				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
//					login();
//				}
//			}
//
//			private void login() {
//				errorLabel.setText("");
//				String username = usernameField.getText();
//				String password = passwordField.getText();
//
//				if (!isValidInput(username, password)) {
//					errorLabel.setText("Please fill in all fields");
//					return;
//				}
//
//				loginButton.setEnabled(false);
//
//				greetingService.login(username, password, new AsyncCallback<String>() {
//					public void onFailure(Throwable caught) {
//						errorLabel.setText("Login failed. Please try again.");
//						loginButton.setEnabled(true);
//					}
//
//					public void onSuccess(String result) {
//						if (result.equals("success")) {
//							errorLabel.setText("Login successful!");
//							// Redirect to another page or perform other actions upon successful login
//						} else {
//							errorLabel.setText("Login failed. Please try again.");
//						}
//						loginButton.setEnabled(true);
//					}
//				});
//			}
//
//			private boolean isValidInput(String username, String password) {
//				return !username.isEmpty() && !password.isEmpty();
//			}
//		}
//
//		LoginHandler handler = new LoginHandler();
//		loginButton.addClickHandler(handler);
//		usernameField.addKeyUpHandler(handler);
//		passwordField.addKeyUpHandler(handler);
//	}
//}
