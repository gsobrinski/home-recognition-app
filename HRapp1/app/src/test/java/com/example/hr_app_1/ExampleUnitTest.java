package com.example.hr_app_1;

import com.example.hr_app_1.phpAPI.HomeUserAccount;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void retrieveAccountSucceeds() {
        HomeUserAccount acc = HomeUserAccount.retrieveAccountData("TestUser", "hashedPassword");
        assertNotNull(acc);
    }

    @Test
    public void retrieveAccountMatchesTestUser() {
        HomeUserAccount acc = HomeUserAccount.retrieveAccountData("TestUser", "hashedPassword");
        assertEquals("1", acc.getKey());
        assertEquals("TestUser", acc.getUsername());
        assertEquals("Cam", acc.getFirstName());
        assertEquals("Gallagher", acc.getLastName());
        assertEquals("test@HRapp.org", acc.getEmail());
    }
}