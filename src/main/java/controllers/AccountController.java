package controllers;

import models.Account;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/accounts")
public class AccountController {
    @GetMapping("{number}")
    public Account getAccount(@PathVariable Integer number) {
        return null;
    }

    @GetMapping("/")
    public Account[] getAllAccounts() {
        return null;
    }

    @PostMapping("/")
    public void createAccount(@RequestBody Account account) {

    }

    @DeleteMapping("{number}")
    public void deleteAccount(@PathVariable int number) {

    }
}
