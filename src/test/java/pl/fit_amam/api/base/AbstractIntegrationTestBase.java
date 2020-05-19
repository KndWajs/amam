package pl.fit_amam.api.base;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import pl.fit_amam.api.Application;
import pl.fit_amam.api.config.H2JpaConfig;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class, H2JpaConfig.class})
@ActiveProfiles("test")
public abstract class AbstractIntegrationTestBase {
}
