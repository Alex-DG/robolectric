package com.android.alex.robolectric;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.widget.Button;
import android.widget.EditText;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowToast;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by alexandrediguida on 22/12/2016.
 *
 * /!\ Information:
 * There is a bug 'open' about Robolectric not supporting sdk 24, this is a workaround for now : sdk = 23
 * If you don't want to write this above every file/method then you can add a file src/test/resources/robolectric.properties containing only: sdk=23
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk=23)
public class LoginActivityTest {

    private LoginActivity loginActivity;
    private Button loginBtn;

    @Before
    public void setUp() throws Exception {

        loginActivity = Robolectric.buildActivity(LoginActivity.class)
                .create()
                .resume()
                .get();

        loginBtn = (Button) loginActivity.findViewById(R.id.login_btn);
    }


    // Activity
    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void checkActivity() throws Exception {
        assertNotNull(loginActivity);
    }


    // Buttons
    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void loginButtonClick() throws Exception {
        assertNotNull(loginBtn);
        loginBtn.performClick();
        assertThat(ShadowToast.getTextOfLatestToast(), equalTo("SUCCESS !"));
    }

    @Test
    public void loginButtonClickShouldStartNewActivity() throws Exception {
        loginBtn.performClick();
        Intent intent = Shadows.shadowOf(loginActivity).peekNextStartedActivity();
        assertEquals(UserActivity.class.getCanonicalName(), intent.getComponent().getClassName());
    }


    // Drawable
    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void shouldHaveMustacheLogo() throws Exception {
        Drawable d = ContextCompat.getDrawable(loginActivity, R.drawable.custom_edit_valid);
        assertNotNull(d);
    }
    @Test
    public void shouldHaveMustacheLogoVisible() throws Exception {
        Drawable d = ContextCompat.getDrawable(loginActivity, R.drawable.custom_edit_valid);
        assertTrue(d.isVisible());
    }

    @Test
    public void userEditValidTest() throws Exception {
        loginActivity.username.setText("Alex");
        assertTrue(loginActivity.username.getBackground().equals(ContextCompat.getDrawable(loginActivity, R.drawable.custom_edit_valid)));
    }
    @Test
    public void userEditNotValidTest() throws Exception {
        loginActivity.username.setText("Alex123");
        assertTrue(loginActivity.username.getBackground().equals(ContextCompat.getDrawable(loginActivity, R.drawable.custom_edit_warning)));
    }

    @Test
    public void passEditValidTest() throws Exception {
        loginActivity.password.setText("aQQ2$1234");
        assertTrue(loginActivity.password.getBackground().equals(ContextCompat.getDrawable(loginActivity, R.drawable.custom_edit_valid)));
    }
    @Test
    public void passEditNotValidTest() throws Exception {
        loginActivity.password.setText("qwerty12");
        assertTrue(loginActivity.password.getBackground().equals(ContextCompat.getDrawable(loginActivity, R.drawable.custom_edit_warning)));
    }

    @Test
    public void loginButtonEnable() throws Exception {
        loginActivity.password.setText("qwerty12");
        assertTrue(loginActivity.password.getBackground().equals(ContextCompat.getDrawable(loginActivity, R.drawable.custom_edit_warning)));
    }
    @Test
    public void loginButtonDisabled() throws Exception {
        loginActivity.username.setText("Alex");
        loginActivity.password.setText("aQQ2$1234");

        assertTrue(loginActivity.loginBtn.getBackground().equals(ContextCompat.getDrawable(loginActivity, R.drawable.custom_button_valid)));
    }

}
