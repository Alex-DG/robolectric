package com.android.alex.robolectric;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertNotNull;

/**
 * Created by alexandrediguida on 22/12/2016.
 */


@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk=23)
public class UserActivityTest {

    private UserActivity userActivity;

    @Before
    public void setUp() throws Exception {

        userActivity = Robolectric.buildActivity(UserActivity.class)
                .create()
                .resume()
                .get();
    }

    @Test
    public void checkActivity() throws Exception {
        assertNotNull(userActivity);
    }

    @Test
    public void logoutTest() throws Exception {
        userActivity.logoutBtn.performLongClick();
    }

}
