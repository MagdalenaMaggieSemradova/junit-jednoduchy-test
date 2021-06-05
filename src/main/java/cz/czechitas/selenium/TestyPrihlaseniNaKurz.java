package cz.czechitas.selenium;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestyPrihlaseniNaKurz {

    private static final String URL_APLIKACE = "https://cz-test-jedna.herokuapp.com/";


    WebDriver prohlizec;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "C:\\Java-Training\\Selenium\\geckodriver.exe");
        prohlizec = new FirefoxDriver();
        prohlizec.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void rodicSePrihlasiDoWebovéAplikace() {
        prohlizec.navigate().to(URL_APLIKACE);
        WebElement odkazPrihlasit = prohlizec.findElement(By.linkText("Prihlasit"));
        odkazPrihlasit.click();

        WebElement polickoEmail = prohlizec.findElement(By.id("email"));
        WebElement polickoHeslo = prohlizec.findElement(By.id("password"));
        WebElement tlacitkoPrihlasit = prohlizec.findElement(By.xpath("//a/i[text() = 'Přihlásit']"));
        polickoEmail.sendKeys("semradovamagdalena@gmail.com");
        polickoHeslo.sendKeys("Milacek88");
        tlacitkoPrihlasit.click();


        Assertions.assertTrue(prohlizec.getCurrentUrl().endsWith("/zaci"));
        WebElement nadpisStranky = prohlizec.findElement(By.xpath("//header//h1"));
        Assertions.assertEquals("Přihlášky", nadpisStranky.getText());
    }

    @Test
    public void rodicVybereKurzAPrihlasiDiteNaKurz() {
        String urlPrihlaskaZaka = URL_APLIKACE + "zaci";
        prohlizec.navigate().to(urlPrihlaskaZaka);
        WebElement polickoEmail = prohlizec.findElement(By.id("email"));
        WebElement polickoHeslo = prohlizec.findElement(By.id("password"));
        WebElement tlacitkoPrihlasit = prohlizec.findElement(By.id("Prihlasit"));
        polickoEmail.sendKeys("semradovamagdalena@gmail.com");
        polickoHeslo.sendKeys("Milacek88");
        tlacitkoPrihlasit.click();

        WebElement tlacitkoVytvoritNovouPrihlasku = prohlizec.findElement(By.linkText("Vytvořit novou přihlášku"));
        tlacitkoVytvoritNovouPrihlasku.click();

        List<WebElement> tlacitkoKurzViceInformaci = prohlizec.findElements(By.xpath("//div[@class = 'card']//a[text() = 'Více informací']"));
        WebElement tlacitkoViceInformaci = tlacitkoKurzViceInformaci.get(2);
        tlacitkoViceInformaci.click();

        List<WebElement> tlacitkoKurzVytvoritPrihlasku = prohlizec.findElements(By.xpath("//div[@class = 'card']//a[text() = 'Vytvořit přihlášku']"));
        WebElement tlacitkoVytvoritPrihlasku = tlacitkoKurzVytvoritPrihlasku.get(0);
        tlacitkoVytvoritPrihlasku.click();


        WebElement menuVyberteTermin = prohlizec.findElement(By.xpath("//div[text()='Vyberte termín...']"));
        menuVyberteTermin.click();

        WebElement polickoTermin = prohlizec.findElement(By.xpath("//div[@class='bs-searchbox']//input"));
        polickoTermin.sendKeys("21\n");

        WebElement krestniJmenoZaka = prohlizec.findElement(By.id("forename"));
        krestniJmenoZaka.sendKeys("Petr");

        WebElement prijmeniZaka = prohlizec.findElement(By.id("surname"));
        prijmeniZaka.sendKeys("Semrád");

        WebElement datumNarozeniZaka = prohlizec.findElement(By.id("birthday"));
        datumNarozeniZaka.sendKeys("10.11.2014");

        WebElement zaplatitKartou = prohlizec.findElement(By.xpath("//a/i[text() ='payment_byCard']"));
        zaplatitKartou.click();

        WebElement zatrhnoutSouhlas = prohlizec.findElement(By.xpath("//a/i[text() ='Zatrhnout souhlas']"));
        zatrhnoutSouhlas.click();

        WebElement tlacitkoOdeslat = prohlizec.findElement(By.xpath("//a/i[text() = Odeslat']"));
        tlacitkoOdeslat.click();

        WebElement potvrzeniPrihlasky = prohlizec.findElement(By.xpath("//*[text()='Stáhnout potvrzení o přihlášení']"));
        Assertions.assertNotNull(potvrzeniPrihlasky);

    }

    @Test
    public void rodicSeMusiPrihlasitAVybratKurzADitePrihlasit() {
        prohlizec.navigate().to(URL_APLIKACE);

        List<WebElement> tlacitkoKurzViceInformaci = prohlizec.findElements(By.xpath("//div[@class = 'card']//a[text() = 'Více informací']"));
        WebElement tlacitkoViceInformaci = tlacitkoKurzViceInformaci.get(2);
        tlacitkoViceInformaci.click();


        List<WebElement> tlacitkoKurzVytvoritPrihlasku = prohlizec.findElements(By.linkText("Vytvořit přihlášku"));
        WebElement tlacitkoVytvoritPrihlasku = tlacitkoKurzVytvoritPrihlasku.get(0);
        tlacitkoVytvoritPrihlasku.click();

        WebElement nadpisStranky = prohlizec.findElement(By.xpath("//header//h1"));
        Assertions.assertEquals("Přihlášení", nadpisStranky.getText());

        WebElement polickoEmail = prohlizec.findElement(By.id("email"));
        WebElement polickoHeslo = prohlizec.findElement(By.id("password"));
        WebElement tlacitkoPrihlasit = prohlizec.findElement(By.xpath("//form//button[contains(text(), 'Přihlásit')]"));
        polickoEmail.sendKeys("semradovamagdalena@gmail.com");
        polickoHeslo.sendKeys("milacek88");
        tlacitkoPrihlasit.click();

        WebElement nadpisStranky2 = prohlizec.findElement(By.xpath("//header//h1"));
        Assertions.assertEquals("Nová přihláška", nadpisStranky2.getText());

        WebElement menuVyberteTermin = prohlizec.findElement(By.xpath("//div[text()='Vyberte termín...']"));
        menuVyberteTermin.click();

        WebElement polickoTerminu = prohlizec.findElement(By.xpath("//div[@class='bs-searchbox']//input"));
        polickoTerminu.sendKeys("28\n");

        WebElement krestniJmenoZaka = prohlizec.findElement(By.id("forename"));
        krestniJmenoZaka.sendKeys("Petr");

        WebElement prijmeniZaka = prohlizec.findElement(By.id("surname"));
        prijmeniZaka.sendKeys("Semrád");

        WebElement datumNarozeniZaka = prohlizec.findElement(By.id("birthday"));
        datumNarozeniZaka.sendKeys("10.11.2014");

        WebElement zaplatitKartou = prohlizec.findElement(By.xpath("//label[@for='payment_card']"));
        zaplatitKartou.click();

        WebElement zatrhnoutSouhlas = prohlizec.findElement(By.xpath("//label[@for='terms_conditions']"));
        zatrhnoutSouhlas.click();

        WebElement tlacitkoOdeslat = prohlizec.findElement(By.xpath("//input[@type='submit']"));
        tlacitkoOdeslat.click();

        WebElement potvrzeniPrihlasky = prohlizec.findElement(By.xpath("//*[text()='Stáhnout potvrzení o přihlášení']"));
        Assertions.assertNotNull(potvrzeniPrihlasky);
    }

    @Test
    public void kliknoutNaTlacitkoHistorieEmailu() {
        prohlizec.navigate().to("https://cz-test-jedna.herokuapp.com/");
        WebElement tlacitkoHistorieEmailu = prohlizec.findElement(By.linkText("HistorieEmailu"));
        tlacitkoHistorieEmailu.click();

        WebElement polickoEmail = prohlizec.findElement(By.id("email"));
        WebElement polickoHeslo = prohlizec.findElement(By.id("password"));
        WebElement tlacitkoPrihlasit = prohlizec.findElement(By.xpath("//a/i[text() = 'Přihlásit']"));
        polickoEmail.sendKeys("semradovamagdalena@gmail.com");
        polickoHeslo.sendKeys("Milacek88");
        tlacitkoPrihlasit.click();

        Assertions.assertTrue(prohlizec.getCurrentUrl().endsWith("/HistorieEmailu"));
        WebElement nadpisStranky = prohlizec.findElement(By.xpath("//a/i[text() = 'HistorieEmailu']"));
        Assertions.assertEquals("HistorieEmailu", nadpisStranky.getText());
    }

    @AfterEach
    public void tearDown() {
        prohlizec.close();

    }
}
