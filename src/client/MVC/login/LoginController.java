package client.MVC.login;

import java.util.Observable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import com.google.common.base.CharMatcher;

import client.MVC.base.*;
import client.MVC.misc.*;
import client.facade.Facade;


/**
 * Implementation for the login controller
 */
public class LoginController extends Controller implements ILoginController {

    private IMessageView messageView;
    private IAction loginAction;
    //added by Mike Lo
    private String usernameRegex = "[0-9a-zA-Z]+";
    private String passwordRegex = "[\u0000-\u00FF]+";

    /**
     * LoginController constructor
     *
     * @param view        Login view
     * @param messageView Message view (used to display error messages that occur during the login process)
     */
    public LoginController(ILoginView view, IMessageView messageView) {

        super(view);

        this.messageView = messageView;
    }

    public ILoginView getLoginView() {

        return (ILoginView) super.getView();
    }

    public IMessageView getMessageView() {

        return messageView;
    }

    /**
     * Sets the action to be executed when the user logs in
     *
     * @param value The action to be executed when the user logs in
     */
    public void setLoginAction(IAction value) {

        loginAction = value;
    }

    /**
     * Returns the action to be executed when the user logs in
     *
     * @return The action to be executed when the user logs in
     */
    public IAction getLoginAction() {

        return loginAction;
    }

    @Override
    public void start() {

        getLoginView().showModal();
    }

    @Override
    public void signIn() {

        // TODO: log in user
    	//CharMatcher unicodeChecker;
    	
    	String username;
    	String password;
    	username = getLoginView().getLoginUsername();
    	password = getLoginView().getLoginPassword();
    	if(!username.matches(usernameRegex))
    	{
    		System.out.println("LoginController.java: invalid character(s) in username");
    	}
    	if(!password.matches(passwordRegex))
    	{
    		System.out.println("LoginController.java: password should only contain ASCII characters");
    	}
    	
        // If log in succeeded
    	if (username.matches(usernameRegex)&&password.matches(passwordRegex))
    	{
    		getLoginView().closeModal();
			Facade.getInstance().Login(password, username);
			loginAction.execute();
    	}
        
    }

    @Override
    public void register() {

        // TODO: register new user (which, if successful, also logs them in)
    	String username;
    	String password;
    	String confirmPassword;
    	
    	username = getLoginView().getRegisterUsername();
    	password = getLoginView().getRegisterPasswordRepeat();
    	confirmPassword = getLoginView().getRegisterPasswordRepeat();
    	
        // If register succeeded
    	if (!username.matches(usernameRegex))
    	{
    		System.out.println("LoginController.java: username should only include alphbate");
    	}
    	if (!password.matches(passwordRegex))
    	{
    		System.out.println("LoginController.java: password include invalid charaters, such as unicode?");
    	}
    	if (!confirmPassword.matches(passwordRegex))
    	{
    		System.out.println("LoginController.java: password include invalid charaters, such as unicode?");
    	}
    	if (!confirmPassword.equals(password))
    	{
    		System.out.println("LoginController.java: Password does not match");
    	}
    	if (username.matches(usernameRegex)&&password.matches(passwordRegex)&&confirmPassword.matches(passwordRegex) && confirmPassword.equals(password))
    	{
    		getLoginView().closeModal();
			Facade.getInstance().register(username, password);
			loginAction.execute();
		}
        
    }

	@Override
	public void update(Observable o, Object arg) {

	}
}

