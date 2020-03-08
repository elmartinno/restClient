package com.example.democlient.controller;

import com.example.democlient.model.Account;
import com.example.democlient.model.AccountData;
import com.example.democlient.model.Turnover;
import com.example.democlient.services.AccountServices;
import com.example.democlient.services.Services;
import com.example.democlient.services.TurnoverServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.xml.ws.Service;
import java.util.List;

@RestController
@RequestMapping("client")
public class TestClientController {
    @Autowired
    AccountServices accountServices;
    @Autowired
    TurnoverServices turnoverServices;
    @Autowired
    Services services;

    //http://localhost:8090/client/getData?iban=SK5509000000005165056080
    @GetMapping("/getData")
    private ResponseEntity<AccountData> getData(String iban) {
        ResponseEntity<AccountData> re = ResponseEntity.status(200).body(services.getAccountData(iban));
        return re;
    }

    //http://localhost:8090/client/updateAccount?iban=123456789
    @GetMapping("/updateAccount")
    private void updateAccount(String iban) {
        accountServices.updateAccount(iban);
    }

    //http://localhost:8090/client/accounts
    @GetMapping("/accounts")
    private List<Account> getAllAccounts() {
        return accountServices.getAllAccounts();
    }

    //http://localhost:8090/client/findAccountByIban/?iban=123456789
    @GetMapping("/findAccountByIban")
    private Account findAccountByIban(String iban) {
        return accountServices.findAccountByIban(iban);
    }

    //http://localhost:8090/client/removeAccount/?iban=123456789
    @GetMapping("/removeAccount")
    private void removeAccount(String iban) {
        accountServices.removeAccount(iban);
    }

    //http://localhost:8090/client/addAccount/?iban=123456789
    @GetMapping("/addAccount")
    private void addAccount(String iban) {
        accountServices.addAccount(iban);
    }

    //localhost:8090/client/findTurnoversByIban/?iban=SK8609000000005155063746
    @GetMapping("/findTurnoversByIban")
    private List<Turnover> findTurnoversByIban(String iban) {
        return turnoverServices.findTurnoversByIban(iban);
    }

    //http://localhost:8090/client/addTurnover
    @GetMapping("/addTurnover")
    private void addTurnover(String iban) {
        turnoverServices.addTurnover(iban);
    }

    //http://localhost:8090/client/turnovers
    @GetMapping("/turnovers")
    private List<Turnover> getAllTurnovers() {
        return turnoverServices.getAllTurnovers();
    }

    //http://localhost:8090/client/removeTurnovers/?iban=SK6009000000005133019509
    @GetMapping("/removeTurnovers")
    private void removeTurnovers(String iban) {
        turnoverServices.removeTurnovers(iban);
    }

    //http://localhost:8090/client/updateTurnovers?iban=SK8609000000005155063746
    @GetMapping("/updateTurnovers")
    private void updateTurnovers(String iban) {
        turnoverServices.updateTurnovers(iban);
    }

}
