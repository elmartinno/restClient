package com.example.democlient.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class AccountData {

    private Account account;
    private List<Turnover> turnovers;

    public AccountData(Account account,List<Turnover> turnovers){
        this.account=account;
        this.turnovers=turnovers;
    }
}
