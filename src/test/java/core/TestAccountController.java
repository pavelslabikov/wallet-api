import core.controllers.AccountController;
import core.dao.AccountDao;
import core.dao.simple.SimpleAccountDao;
import core.models.Account;
import org.checkerframework.checker.units.qual.A;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.HashSet;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestAccountController {
    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean(name = "sqlAccountDao")
    private AccountDao accountDao;

    @Test
    public void testReceivingNotFoundResponse() {
        MockitoAnnotations.initMocks(this);
        var testAccount = new Account(1, 2);
        when(accountDao.getByNumber(2)).thenReturn(testAccount);
        var actualAccount = restTemplate.getForEntity("http://localhost:8080/v1/accounts/2", Account.class);
        assert actualAccount.getStatusCode() == HttpStatus.OK;

    }
}
