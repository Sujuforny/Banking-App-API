package co.istad.bankingapp.api.account.web;

import lombok.Builder;

@Builder
public record CreateNewAccountDto(

        String accountNo,
        String accountName,
        String profile,
        Integer pin,
        String password,
        String phoneNumber,
        Integer transferLimit,
        Integer accountType) {
}
