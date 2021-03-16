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
        assertEquals("10", acc.getKey());
        assertEquals("TestUser", acc.getUsername());
        assertEquals("Cam", acc.getFirstName());
        assertEquals("Gallagher", acc.getLastName());
        assertEquals("test@HRapp.org", acc.getEmail());
        System.out.println(acc.toString());
    }

    @Test
    public void addAnAccount() {
        HomeUserAccount acc = new HomeUserAccount();
        acc.setUsername("AA");
        acc.setPassword("AA");
        acc.setFirstName("AA");
        acc.setLastName("AA");
        acc.setEmail("AA");
        String result = HomeUserAccount.registerAccount(acc);
        System.out.println(result);
        assertNull(result);
        HomeUserAccount resultAcc = HomeUserAccount.retrieveAccountData("AA", "AA");
        assertNotNull(resultAcc);
        System.out.println(resultAcc.toString());
    }
}