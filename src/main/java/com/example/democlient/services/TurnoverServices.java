package com.example.democlient.services;

import com.example.democlient.Logs;
import com.example.democlient.model.Account;
import com.example.democlient.model.Amount;
import com.example.democlient.model.Balance;
import com.example.democlient.model.Turnover;
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
public class TurnoverServices {
    @Autowired
    private Logs l;
    RestTemplate restTemplate = new RestTemplate();
    final String uri = "http://rest-server-rest11.192.168.42.59.nip.io/api/";
    //minishift: final String uri = "http://test-app-test00.192.168.42.137.nip.io/api/";

    public List<Turnover> findTurnoversByIban(String iban) {
        l.startLog();

        List<Turnover> result = restTemplate.getForObject(uri+"findTurnoversByIban/?iban="+iban, List.class);

        l.createLog("findTurnoversByIban","");

        System.out.println(result);
        return result;
    }

    public void addTurnover(String iban) {
        Turnover turnover = new Turnover(iban,"lorem ipsum bla bla bal");
        turnover.setDescription("00");
        turnover.setVariable("00");
        turnover.setDate("00");
        turnover.setSpecific("00");
        turnover.setBalance(new Balance("00","00","00"));
        turnover.setAmount(new Amount("00","00","00"));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Turnover> requestBody = new HttpEntity<>(turnover, headers);

        l.startLog();

        restTemplate.postForObject(uri+"addTurnover",requestBody,boolean.class);

        l.createLog("addTurnover","");
    }

    public List<Turnover> getAllTurnovers() {
        List<Turnover> result = restTemplate.getForObject(uri+"turnovers", List.class);
        return result;
    }

    public void removeTurnovers(String iban) {
        restTemplate.delete(uri+"removeTurnovers/?iban="+iban, boolean.class);
    }

    public void updateTurnovers(String iban) {
        List<Turnover> turnovers = findTurnoversByIban(iban);

        SimpleDateFormat time_formatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss.SSS");

        //turnovers.forEach(t ->t.setSpecific(time_formatter.format(System.currentTimeMillis())) );
            //t.setSpecific(time_formatter.format(System.currentTimeMillis()));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<List<Turnover>> requestBody = new HttpEntity<>(turnovers, headers);

        restTemplate.put(uri+"updateTurnovers",requestBody,boolean.class);
    }
}
