package com.example.mobdev_nhom7;

import com.example.mobdev_nhom7.activity.Login;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class LoginUnitTest {
    @Before
    public void setUp() {
    }

    @Test
    public void validEmailTest0() {
        assertEquals(true, Login.isValidEmail("quangh2019@gmail.com"));
    }

    @Test
    public void invalidEmailTest1() {
        assertEquals(false, Login.isValidEmail("qqqqq"));
    }

    @Test
    public void invalidEmailTest2() {
        assertEquals(false, Login.isValidEmail("qqqqq@"));
    }

    @Test
    public void invalidEmailTest3() {
        assertEquals(false, Login.isValidEmail("qqqqq@gmail"));
    }

    @Test
    public void invalidEmailTest4() {
        assertEquals(false, Login.isValidEmail("qqqqq@gmail."));
    }

    @Test
    public void invalidEmailTest5() {
        assertEquals(false, Login.isValidEmail("qqqqq@."));
    }

    @Test
    public void invalidEmailTest6() {
        assertEquals(false, Login.isValidEmail("qqqqq@.com"));
    }

    @Test
    public void invalidEmailTest7() {
        assertEquals(false, Login.isValidEmail("qqqqq@com"));
    }

    @Test
    public void invalidEmailTest8() {
        assertEquals(false, Login.isValidEmail("qqqqq."));
    }

    @Test
    public void invalidEmailTest9() {
        assertEquals(false, Login.isValidEmail("qqqqq.com"));
    }
}
