package co.istad.bankingapp.api.account.web;

import co.istad.bankingapp.api.accounttype.web.AccountTypeDto;
import lombok.Builder;

@Builder
public record AccountDto(
        String accountNo,
        String accountName,
        String profile,
        String phoneNumber,
        Integer transferLimit,
        Integer accountType
) {
}
