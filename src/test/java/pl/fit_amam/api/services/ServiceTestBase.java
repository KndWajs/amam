package pl.fit_amam.api.services;

import org.junit.Before;
import pl.fit_amam.api.base.AbstractIntegrationTestBase;
import pl.fit_amam.api.base.LoginTestHelper;


public abstract class ServiceTestBase extends AbstractIntegrationTestBase {

    @Before
    public void setUpTest() {
        LoginTestHelper.loginAsRegularUser();
    }

}
