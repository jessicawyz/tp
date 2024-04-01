package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class PaymentTest {

    @Test
    public void constructor_validAmount_createsPayment() {
        assertEquals(100.0, new Payment(100.0).getAmount());
        assertEquals(0.0, new Payment("0").getAmount());
    }

    @Test
    public void constructor_invalidAmount_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> new Payment(-1.0));
        assertThrows(IllegalArgumentException.class, () -> new Payment("invalid"));
    }

    @Test
    public void isValidPayment_validInput_returnsTrue() {
        assertTrue(Payment.isValidPayment(0.0));
        assertTrue(Payment.isValidPayment("100.00"));
    }

    @Test
    public void isValidPayment_invalidInput_returnsFalse() {
        assertFalse(Payment.isValidPayment(-1.0));
        assertFalse(Payment.isValidPayment("invalid"));
    }

    @Test
    public void toString_returnsCorrectFormat() {
        Payment payment = new Payment(1234.56);
        assertEquals("$1234.56", payment.toString());
    }

    @Test
    public void equals_consistentWithDoubleEquality() {
        Payment payment1 = new Payment(100.0);
        Payment payment2 = new Payment(100.00);
        Payment payment3 = new Payment(100.01);

        assertEquals(payment1, payment2);
        assertNotEquals(payment1, payment3);
    }

    @Test
    public void hashCode_consistentWithEquals() {
        Payment payment1 = new Payment(100.0);
        Payment payment2 = new Payment(100.00);

        assertEquals(payment1.hashCode(), payment2.hashCode());
    }
}
