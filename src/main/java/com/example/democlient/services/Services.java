package com.example.democlient.services;

import com.example.democlient.model.Account;
import com.example.democlient.model.AccountData;
import com.example.democlient.model.Turnover;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class Services {

    RestTemplate restTemplate = new RestTemplate(/*new OkHttp3ClientHttpRequestFactory()*/);
    final String uri = "http://localhost:8080/api/";
    //minishift: final String uri = "http://test-app-test00.192.168.42.137.nip.io/api/";

    public AccountData getAccountData(String iban) {


        List<Turnover> turnovers = restTemplate.getForObject(uri+"findTurnoversByIban/?iban="+iban, List.class);
        //System.out.println(turnovers);


        Account account = restTemplate.getForObject(uri+"findAccountByIban/?iban="+iban, Account.class);
        //System.out.println(account);

        AccountData accountData = new AccountData(account, turnovers);
        //System.out.println(accountData);
        return accountData;
    }


}
