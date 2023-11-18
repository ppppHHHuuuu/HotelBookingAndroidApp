package com.example.mobdev_nhom7;

import static org.junit.Assert.assertEquals;

import com.example.mobdev_nhom7.activity.NewPassword;

import org.junit.Before;
import org.junit.Test;

public class NewPasswordTest {
    private String password;
    private int totalCount;
    private int uppercaseCount;
    @Before
    public void setUp() {
        password = "";
        totalCount = 0;
        uppercaseCount = 0;
    }

    @Test
    public void newPasswordUnitTest() {
        password = "1231";
        assertEquals(4, NewPassword.totalCharacterCount(password));
        assertEquals(0, NewPassword.uppercaseCount(password));
        password = "1231A";
        assertEquals(5, NewPassword.totalCharacterCount(password));
        assertEquals(1, NewPassword.uppercaseCount(password));

        totalCount = 5;
        uppercaseCount = 0;
        assertEquals(false, NewPassword.passwordCheck(totalCount, uppercaseCount));
        totalCount = 5;
        uppercaseCount = 1;
        assertEquals(false, NewPassword.passwordCheck(totalCount, uppercaseCount));
        totalCount = 10;
        uppercaseCount = 0;
        assertEquals(false, NewPassword.passwordCheck(totalCount, uppercaseCount));
        totalCount = 10;
        uppercaseCount = 1;
        assertEquals(true, NewPassword.passwordCheck(totalCount, uppercaseCount));
    }
}
