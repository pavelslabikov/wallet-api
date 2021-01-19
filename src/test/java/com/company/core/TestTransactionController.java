package com.company.core;

import com.company.dao.AccountDao;
import com.company.dao.TransactionDao;
import com.company.core.models.Account;
import com.company.core.models.Transaction;
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

import java.util.Date;

import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestTransactionController {
    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean(name = "sqlAccountDao")
    private AccountDao accountDao;

    @MockBean(name = "sqlTransactionDao")
    private TransactionDao transactionDao;

    @LocalServerPort
    private int port;

    private String resourceUrl;

    @Before
    public void setUpBeforeTest() {
        MockitoAnnotations.initMocks(this);
        resourceUrl = String.format("http://localhost:%d/v1/accounts/", port);
    }

    @Test
    public void testGettingTransactionsFromNonExistingAccount() {
        mockNonExistingAccount();
        resourceUrl += "1/transactions";
        var response = restTemplate.getForEntity(resourceUrl, String.class);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void testCreatingTransactionForNonExistingAccount() {
        var testTransaction = new Transaction("INCOME", new Date(), 1, "");
        mockNonExistingAccount();
        resourceUrl += "1/transactions";
        var response = restTemplate.postForEntity(resourceUrl, testTransaction, String.class);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void testGettingTransactions() {
        var testAccount = new Account(2, 2);
        mockExistingAccount(testAccount);
        var testTransactions = new Transaction[] {
                new Transaction("INCOME", new Date(), 1, "")
        };

        mockTransactionCollection(testAccount.getNumber(), testTransactions);
        resourceUrl += "2/transactions";
        var response = restTemplate.getForEntity(resourceUrl, Transaction[].class);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assert.assertArrayEquals(testTransactions, response.getBody());
    }

    private void mockNonExistingAccount() {
        when(accountDao.getByNumber(1)).thenReturn(null);
    }

    private void mockExistingAccount(Account account) {
        when(accountDao.getByNumber(account.getNumber())).thenReturn(account);
    }

    private void mockTransactionCollection(int number, Transaction[] transactions) {
        when(transactionDao.getAllTransactions(number)).thenReturn(transactions);
    }
}
