enum CurrencyType {
  DOLLAR,
  EURO,
  POUND
}

interface CurrencyService {
  double convert(CurrencyType from, CurrencyType to, double amount);
}

class CurrencyServiceImpl implements CurrencyService {
  private static double getRate(CurrencyType from, CurrencyType to) {
    if (from == to) return 1.0;
    return switch (from) {
      case EURO -> switch (to) {
        case DOLLAR -> 1.15;
        case POUND -> 0.88;
        default -> throw new IllegalStateException("Unexpected value: " + to);
      };
      case POUND -> to == CurrencyType.DOLLAR ? 1.31 : 1/getRate(to, from);
      case DOLLAR -> 1/getRate(to, from);
    };
  }
  @Override
  public double convert(CurrencyType from, CurrencyType to, double amount) {
    return amount * getRate(from, to);
  }
}
class BankAccount {

  private static final double EUR_USD_RATE = 1.15;
  private static final double GBP_USD_RATE = 1.31;
  private static final double USD_EUR_RATE = 0.87;
  private static final double GBP_EUR_RATE = 1.14;
  private static final double USD_GBP_RATE = 0.76;

  private static final double EUR_GBP_RATE = 0.88;

  private final CurrencyType currencyType;
  private final CurrencyService currencyService;
  private double amount;

  public BankAccount(CurrencyType currencyType, double amount, CurrencyService currencyService) {
    this.currencyService = currencyService;
    this.currencyType = currencyType;
    this.amount = amount;
  }

  public boolean add(CurrencyType addedType, double addedAmount) {
    if (addedAmount < 0)
      return false;
    amount += currencyService.convert(this.currencyType, addedType, addedAmount);
    return true;
  }

  public boolean remove(CurrencyType removedType, double removedAmount) {
    double inCurrency = this.currencyService.convert(this.currencyType, removedType, removedAmount);
    if (inCurrency > amount) {
      return false;
    }
    amount -= inCurrency;
    return true;
  }

  public CurrencyType getCurrencyType() {
    return currencyType;
  }

  public double getAmount() {
    return amount;
  }
}

public class App {

  public static void main(String[] args) {
    BankAccount account = new BankAccount(CurrencyType.EURO, 9001, new CurrencyServiceImpl());
    account.add(CurrencyType.DOLLAR, 100);
    account.remove(CurrencyType.POUND, 10);
    System.out.println("Balance: " + account.getAmount());
  }
}