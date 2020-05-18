package pl.fit_amam.api.services;

import org.junit.Before;
import pl.fit_amam.api.base.LoginTestHelper;

public class BasicServiceTest {

    @Before
    public void setUpTest() {
        LoginTestHelper.loginAsRegularUser();
    }

}
