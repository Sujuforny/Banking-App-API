package co.istad.bankingapp.api.account.web;

import co.istad.bankingapp.api.accounttype.AccountType;
import lombok.Builder;


public record AccountDto(
        String accountNo,
        String accountName,
        String profile,
        String phoneNumber,
        Integer transferLimit,
        AccountType accountType
) {
}
