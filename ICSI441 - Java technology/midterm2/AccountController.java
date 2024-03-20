import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class Account {
    private String number;
    private long balance;
    private boolean isLocked;

    public Account(String number, long balance, boolean isLocked) {
        this.number = number;
        this.balance = balance;
        this.isLocked = isLocked;
    }

    public String getNumber() {
        return number;
    }

    public long getBalance() {
        return balance;
    }

    public boolean isLocked() {
        return isLocked;
    }

    @Override
    public String toString() {
        return "Account{" +
                "number='" + number + '\'' +
                ", balance=" + balance +
                ", isLocked=" + isLocked +
                '}';
    }
}

public class AccountController {

    public static void main(String[] args) {

        List<Account> accounts = new ArrayList<>(Arrays.asList(
                new Account("B123", 150_000_000, false),
                new Account("B456", 80_000_000, false),
                new Account("B789", 120_000_000, true), //tugjigdsen account tul active gej uzehgui
                new Account("B101", 200_000_000, false)
        ));

        //hooson bish dans
        List<Account> nonEmptyAccounts = accounts.stream()
                .filter(account -> !account.getNumber().isEmpty())
                .collect(Collectors.toList());

        //100k deesh active dans
        List<Account> activeAccountsWithLargestBalance = accounts.stream()
                .filter(account -> !account.isLocked() && account.getBalance() >= 100_000_000)
                .sorted(Comparator.comparingLong(Account::getBalance).reversed())
                .collect(Collectors.toList());

        System.out.println("Нийт данс: " + accounts);
        System.out.println("Хоосон биш Дансны жягсаалт: " + nonEmptyAccounts);
        System.out.println("100К-с дээш хадгаламжтай одоо ашиглагдаж байгаа данс: " + activeAccountsWithLargestBalance);
    }
}
