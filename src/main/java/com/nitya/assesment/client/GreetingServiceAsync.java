package com.nitya.assesment.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {

	void signUp(String username, String email, String password, AsyncCallback<String> callback);

	void login(String username, String password, AsyncCallback<String> callback);

}
