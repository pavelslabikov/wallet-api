package com.company.core;

import com.company.dao.AccountDao;
import com.company.core.models.Account;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestAccountController {
    @LocalServerPort
    private int port;

    private String resourceUrl;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean(name = "sqlAccountDao")
    private AccountDao accountDao;

    @Before
    public void setUpBeforeTest() {
        MockitoAnnotations.initMocks(this);
        resourceUrl = String.format("http://localhost:%d/v1/accounts/", port);
    }

    @Test
    public void testGettingNonExistingAccount() {
        mockNonExistingAccount();
        var response = restTemplate.getForEntity(resourceUrl + "1", Object.class);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void testGettingExistingAccount() {
        var testAccount = new Account(2, 2);
        mockExistingAccount(testAccount);
        var response = restTemplate.getForEntity(resourceUrl + "2", Account.class);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assert.assertEquals(testAccount, response.getBody());
    }

    @Test
    public void testGettingAllAccounts() {
        var testAccounts = new Account[] {
                new Account(1, 1),
                new Account(2, 2)
        };

        mockAccountCollection(testAccounts);
        var response = restTemplate.getForEntity(resourceUrl, Account[].class);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assert.assertArrayEquals(testAccounts, response.getBody());
    }

    @Test
    public void testCreatingAlreadyExistingAccount() {
        var testAccount = new Account(2, 2);
        mockExistingAccount(testAccount);
        var response = restTemplate.postForEntity(resourceUrl, testAccount, String.class);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.METHOD_NOT_ALLOWED);
    }

    private void mockNonExistingAccount() {
        when(accountDao.getByNumber(1)).thenReturn(null);
    }

    private void mockExistingAccount(Account account) {
        when(accountDao.getByNumber(account.getNumber())).thenReturn(account);
    }

    private void mockAccountCollection(Account[] accounts) {
        when(accountDao.getAllAccounts()).thenReturn(accounts);
    }
}
