package com.android.alex.robolectric;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

/**
 * Created by alexandrediguida on 22/12/2016.
 */


@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk=23)
public class UtilTest {

    private LoginActivity loginActivity;

    private String usernameValid = "Alex";
    private String usernameValid2 = "O'Neil";
    private String usernameValid3 = "O-Neil";
    private String usernameNotValid = "4l3x";

    private String passwordValid = "aQQ2$1234";
    private String passwordNotValid = "aswe123";
    private String passwordNotValid2 = "qwfefwgewgweb";


    @Before
    public void setUp() throws Exception {

        loginActivity = Robolectric.buildActivity(LoginActivity.class)
                .create()
                .resume()
                .get();
    }


    @Test
    public void checkActivity() throws Exception {
        assertNotNull(loginActivity);
    }



    // Regex
    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void checkUsernameValid() throws Exception {
        boolean isValid = Util.isUserValid(usernameValid);
        assertTrue(isValid);
    }
    @Test
    public void checkUsernameValid2() throws Exception {
        boolean isValid = Util.isUserValid(usernameValid2);
        assertTrue(isValid);
    }
    @Test
    public void checkUsernameValid3() throws Exception {
        boolean isValid = Util.isUserValid(usernameValid3);
        assertTrue(isValid);
    }

    @Test
    public void checkUsernameNotValid() throws Exception {
        boolean isValid = Util.isUserValid(usernameNotValid);
        assertFalse(isValid);
    }


    @Test
    public void checkPasswordValid() throws Exception {
        boolean isValid = Util.isPassValid(passwordValid);
        assertTrue(isValid);
    }

    @Test
    public void checkPasswordNotValid() throws Exception {
        boolean isValid = Util.isPassValid(passwordNotValid);
        assertFalse(isValid);
    }
    @Test
    public void checkPasswordNotValid2() throws Exception {
        boolean isValid = Util.isPassValid(passwordNotValid2);
        assertFalse(isValid);
    }


    // Keyboard
    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void keyboardTest() throws Exception {
        Util.hideSoftKeyboard(null);
    }
}
