package org.fundacionjala.coding.danielM.bank_ocr;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Administrator on 3/12/2017.
 */
public class BankOcrTest {
    /**
     * Test if scanned image recognized numbers between 0 and 9.
     */
    @Test
    public void testGetKeyWhenScannedNumbersAreBetween0And9() {
        // given:
        String[] scannedImage = {
                " _ "
                        + "| |"
                        + "|_|",

                "   "
                        + "  |"
                        + "  |",

                " _ "
                        + " _|"
                        + "|_ ",

                "__ "
                        + " _|"
                        + "__|",

                "   "
                        + "|_|"
                        + "  |",

                " _ "
                        + "|_ "
                        + " _|",

                " _ "
                        + "|_ "
                        + "|_|",

                "__ "
                        + "  |"
                        + "  |",

                " _ "
                        + "|_|"
                        + "|_|",

                " _ "
                        + "|_|"
                        + " _|"
        };

        // when:
        String actualResult = BankOcr.accountRepresentation(scannedImage);

        // then:
        String expectedResult = "0123456789";
        assertEquals(expectedResult, actualResult);
    }

    /**
     * Test that anything different from a number is recognized as '?'.
     */
    @Test
    public void testGetKeyWhenScannedImageHasDifferentValues() {
        // given:
        String[] scannedImage = {
                " _ "
                        + "| |"
                        + "|_|",

                " _ "
                        + "|_|"
                        + " _|",

                "Wrong number",
                "",
                " _ "
                        + "|_ "
                        + " _|"
        };

        // when:
        String actualResult = BankOcr.accountRepresentation(scannedImage);

        // then:
        String expectedResult = "09??5";
        assertEquals(expectedResult, actualResult);
    }

    /**
     * Test if the scanned account is a correct one.
     */
    @Test
    public void testValidateAccountWhenTheGivenAccountIsCorrect() {
        // given:
        String[] scannedImage = {
                "__ "
                        + " _|"
                        + "__|",

                "   "
                        + "|_|"
                        + "  |",

                " _ "
                        + "|_ "
                        + " _|",

                " _ "
                        + "|_|"
                        + "|_|",

                " _ "
                        + "|_|"
                        + "|_|",

                " _ "
                        + " _|"
                        + "|_ ",

                " _ "
                        + "|_|"
                        + "|_|",

                " _ "
                        + "|_ "
                        + "|_|",

                " _ "
                        + "|_ "
                        + " _|",
        };
        String correctAccount = BankOcr.accountRepresentation(scannedImage);

        // when:
        boolean actualResult = BankOcr.validateAccount(correctAccount);

        // then:
        assertTrue(actualResult);

    }

    /**
     * Test if the scanned account is an incorrect one.
     */
    @Test
    public void testValidateAccountWhenTheGivenAccountIsIncorrect() {
        // given:
        String[] scannedImage = {
                "   "
                        + "|_|"
                        + "  |",

                " _ "
                        + "|_ "
                        + " _|",

                " _ "
                        + "|_ "
                        + "|_|",

                "__ "
                        + "  |"
                        + "  |",

        };
        String incorrectAccount = BankOcr.accountRepresentation(scannedImage);

        // when:
        boolean actualResult = BankOcr.validateAccount(incorrectAccount);

        // then:
        assertTrue(!actualResult);
    }

    /**
     * Test if the status of a correct account is "".
     */
    @Test
    public void testGetAccountStatusWithACorrectAccount() {
        // given:
        String correctAccount = "345882865";

        // when:
        String actualResult = BankOcr.getAccountStatus(correctAccount);

        // then:
        String expectResult = "";
        assertEquals(expectResult, actualResult);
    }

    /**
     * Test if the status of an incorrect account is "ERR".
     */
    @Test
    public void testGetAccountStatusWithAnIncorrectAccount() {
        // given:
        String incorrectAccount = "021453789";

        // when:
        String actualResult = BankOcr.getAccountStatus(incorrectAccount);

        // then:
        String expectedResult = "ERR";
        assertEquals(expectedResult, actualResult);
    }

    /**
     * Test if the status of an illegible account is: "ILL".
     */
    @Test
    public void testGetAccountStatusWithAnIllegibleAccount() {
        // given:
        String illegibleAccount = "  560  |";

        // when:
        String actualResult = BankOcr.getAccountStatus(illegibleAccount);

        // then:
        String expectedResult = "ILL";
        assertEquals(expectedResult, actualResult);
    }

    /**
     * Test if a scanned account has the incorrect number of digits.
     */
    @Test
    public void testParseScannedNumberWithIncorrectNumberOfDigits() {
        //given:
        String scannedString =
                " _ "
                        + "| |"
                        + "|_|";
        String[] accountArray = BankOcr.parseScannedNumbers(scannedString);

        // when:
        String actualResult = BankOcr.accountRepresentation(accountArray);

        // then:
        String expectedResult = "??????????";
        assertEquals(expectedResult, actualResult);
    }

    /**
     * Test if a scanned account with nine digits is parsed correctly.
     */
    @Test
    public void testParseScannedNumbersWithNineDigits() {
        // given:
        String scannedString =
          "__     _  _  _  _  _  _  _ "
        + " _||_||_ |_||_| _||_||_ |_ "
        + "__|  | _||_||_||_ |_||_| _|";

        String[] accountArray = BankOcr.parseScannedNumbers(scannedString);

        // when:
        String actualResult = BankOcr.accountRepresentation(accountArray);

        // then:
        String expectedResult = "345882865";
        assertEquals(expectedResult, actualResult);
    }
}
