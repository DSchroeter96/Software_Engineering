import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class AppTest {

  @Test
  void onePlusOneIsTwo() {
    assertThat(1 + 1, is(2));
  }

  @Test
  public void currencyServiceImplConvertUSD_GBPCorrect() {
      CurrencyService impl = new CurrencyServiceImpl();

      assertThat(impl.convert(CurrencyType.DOLLAR, CurrencyType.POUND, 113), is(closeTo(86.25, 1e-2)));
  }

    @Test
    public void currencyServiceImplConvertGBP_USDCorrect() {
        CurrencyService impl = new CurrencyServiceImpl();

        assertThat(impl.convert(CurrencyType.POUND, CurrencyType.DOLLAR, 113), is(closeTo(148.03, 1e-2)));
    }

    @Test
    public void currencyServiceImplConvertUSD_EURCorrect() {
        CurrencyService impl = new CurrencyServiceImpl();

        assertThat(impl.convert(CurrencyType.DOLLAR, CurrencyType.EURO, 113), is(closeTo(98.26, 1e-2)));
    }

    @Test
    public void currencyServiceImplConvertEUR_USDCorrect() {
        CurrencyService impl = new CurrencyServiceImpl();

        assertThat(impl.convert(CurrencyType.EURO, CurrencyType.DOLLAR, 113), is(closeTo(129.95, 1e-2)));
    }

    @Test
    public void currencyServiceImplConvertGBP_EURCorrect() {
        CurrencyService impl = new CurrencyServiceImpl();

        assertThat(impl.convert(CurrencyType.POUND, CurrencyType.EURO, 113), is(closeTo(128.40, 1e-2)));
    }

    @Test
    public void currencyServiceImplConvertEUR_GBPCorrect() {
        CurrencyService impl = new CurrencyServiceImpl();

        assertThat(impl.convert(CurrencyType.EURO, CurrencyType.POUND, 113), is(closeTo(99.44, 1e-2)));
    }

    @Test
    public void currencyServiceImplConvertEUR_EURCorrect() {
        CurrencyService impl = new CurrencyServiceImpl();

        assertThat(impl.convert(CurrencyType.EURO, CurrencyType.EURO, 113), is(closeTo(113, 1e-2)));
    }

    @Test
    public void currencyServiceImplConvertUSD_USDCorrect() {
        CurrencyService impl = new CurrencyServiceImpl();

        assertThat(impl.convert(CurrencyType.DOLLAR, CurrencyType.DOLLAR, 113), is(closeTo(113, 1e-2)));
    }

    @Test
    public void currencyServiceImplConvertGBP_GBPCorrect() {
        CurrencyService impl = new CurrencyServiceImpl();

        assertThat(impl.convert(CurrencyType.POUND, CurrencyType.POUND, 113), is(closeTo(113, 1e-2)));
    }



}
