package co.istad.bankingapp.api.account;

import co.istad.bankingapp.api.accounttype.AccountType;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
public class Account {
    private Integer id;
    private String accountNo;
    private String accountName;
    private String profile;
    private Integer pin;
    private String password;
    private String phoneNumber;
    private Integer transferLimit;
    private AccountType accountType;
}
