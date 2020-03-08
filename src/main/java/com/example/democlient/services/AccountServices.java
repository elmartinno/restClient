package com.example.democlient.services;

import com.example.democlient.Logs;
import com.example.democlient.model.Account;
import com.example.democlient.model.Balance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class AccountServices {
    @Autowired
    private Logs l;

    RestTemplate restTemplate = new RestTemplate();
    final String uri = "http://rest-server-rest11.192.168.42.59.nip.io/api/";
    //minishift: final String uri = "http://test-app-test00.192.168.42.137.nip.io/api/";

    public List<Account> getAllAccounts() {
        List<Account> result = restTemplate.getForObject(uri+"accounts", List.class);
        return result;
    }

    public Account findAccountByIban(String iban) {
        l.startLog();

        Account result = restTemplate.getForObject(uri+"findAccountByIban/?iban="+iban, Account.class);

        l.createLog("findAccountByIban","");

        return result;
    }

    public void addAccount(String iban) {
        Account account = new Account(iban,"00","00");
        account.setUpdate("00");
        account.setPublished("00");
        account.setBalance(new Balance("00","00","00"));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Account> requestBody = new HttpEntity<>(account, headers);

        l.startLog();

        restTemplate.postForObject(uri+"addAccount",requestBody,boolean.class);

        l.createLog("addAccount","");
    }

    public void updateAccount(String iban) {
        Account account = findAccountByIban(iban);

        SimpleDateFormat time_formatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss.SSS");

        account.setUpdate(time_formatter.format(System.currentTimeMillis()));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Account> requestBody = new HttpEntity<>(account, headers);

        l.startLog();

        restTemplate.put(uri+"updateAccount",requestBody,boolean.class);

        l.createLog("updateAccount","");
    }

    public void removeAccount(String iban) {
        l.startLog();

        restTemplate.delete(uri+"removeAccount/?iban="+iban, boolean.class);

        l.createLog("removeAccount","");
    }
}
