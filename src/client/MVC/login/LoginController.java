package client.MVC.login;

import java.util.Observable;

//import com.google.common.base.CharMatcher;

import client.MVC.base.*;
import client.MVC.misc.*;
import client.facade.Facade;

import javax.swing.*;


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
//        if (!username.matches(usernameRegex)) {
//            System.out.println("LoginController.java: invalid character(s) in username");
//        }
//        if (!password.matches(passwordRegex)) {
//            System.out.println("LoginController.java: password should only contain ASCII characters");
//        }

        // If log in succeeded
        if (verifyCharacters(username) && verifyCharacters(password))
        {//going to need some work
            if(Facade.getInstance().playerLogin(username, password)){
                getLoginView().closeModal();
                loginAction.execute();
            }
            else{
                JOptionPane.showMessageDialog(new JFrame(), "Server Error", "Inane error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        else
        {
            JOptionPane.showMessageDialog(new JFrame(), "Illegal Characters", "Inane error", JOptionPane.ERROR_MESSAGE);
            return;
        }

    }

    @Override
    public void register() {

        // TODO: register new user (which, if successful, also logs them in)
        String username;
        String password;
        String confirmPassword;

        username = getLoginView().getRegisterUsername();
        password = getLoginView().getRegisterPassword();
        confirmPassword = getLoginView().getRegisterPasswordRepeat();

        // If register succeeded
//        if (!username.matches(usernameRegex)) {
//            System.out.println("LoginController.java: username should only include alphbate");
//        }
//        if (!password.matches(passwordRegex)) {
//            System.out.println("LoginController.java: password include invalid charaters, such as unicode?");
//        }
//        if (!confirmPassword.matches(passwordRegex)) {
//            System.out.println("LoginController.java: password include invalid charaters, such as unicode?");
//        }
        System.out.println(confirmPassword + " "+  password);
        if (!confirmPassword.equals(password))
        {
            JOptionPane.showMessageDialog(new JFrame(), "Passwords do not match", "Inane error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(password.length() < 5)
        {
            JOptionPane.showMessageDialog(new JFrame(), "Passwords not long enough", "Inane error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(username.length() < 3 || username.length() > 7)
        {
            JOptionPane.showMessageDialog(new JFrame(), "Username must be between 3 and 7 characters long", "Inane error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(verifyCharacters(username) && verifyCharacters(password) && verifyCharacters(confirmPassword))
        {
            if(!Facade.getInstance().register(username, password))
            {
                JOptionPane.showMessageDialog(new JFrame(), "Server hated something.  Please try again", "Inane error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            else
            {
                System.out.println("success?");
                getLoginView().closeModal();
                loginAction.execute();
            }
        }
        else
        {
            JOptionPane.showMessageDialog(new JFrame(), "Illegal Characters", "Inane error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }

    public boolean verifyCharacters(String word)
    {
        for(int i = 0; i < word.length(); i++)
        {
            char curr = word.charAt(i);
            if(!Character.isAlphabetic(curr) && !Character.isDigit(curr))
            {
                if(curr != '-' && curr != '_')
                    return false;
            }
        }
        return true;
    }

    @Override
    public void update(Observable o, Object arg) {}
}

