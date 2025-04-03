package pizzashop.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;
import pizzashop.validator.PaymentValidator;
import pizzashop.model.PaymentType;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class PaymentServiceTest {

    private PaymentService service;
    private PaymentRepository repository;
    private PaymentValidator validator;

    @BeforeEach
    void setUp() throws IOException {
        MenuRepository repoMenu = new MenuRepository();
        this.repository = new PaymentRepository();
        this.validator = new PaymentValidator();
        this.service = new PaymentService(repoMenu, repository, validator);

        repository.getAll().clear();
        repository.writeAll();
    }

    @Test
    @Order(1)
    @DisplayName("EC Test 1")
        // @Disabled
    void addValidPaymentTest() throws IOException {
        service.addPayment(1, PaymentType.Card, 200.5);

        assertEquals(1, service.getPayments().size());
    }

    @Test
    @Order(2)
    @DisplayName("EC Test 2")
    void addValidPaymentTest2() throws IOException {
        service.addPayment(4, PaymentType.Card, 100.75);

        assertEquals(1, service.getPayments().size());
    }

    @Test
    @Order(3)
    @DisplayName("EC Test 3")
    void addValidPaymentTest3() throws IOException {
        service.addPayment(6, PaymentType.Card, 45.7);

        assertEquals(1, service.getPayments().size());
    }

    @Test
    @Order(4)
    @DisplayName("EC Test 4")
    void addValidPaymentTest4() throws IOException {
        service.addPayment(8, PaymentType.Card, 28.9);

        assertEquals(1, service.getPayments().size());
    }

    @Test
    @Order(5)
    @DisplayName("EC Test 5")
    void addInvalidPayment() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            service.addPayment(-10, PaymentType.Card, 800.4);
        }, "Expected addPayment to throw, but it didn't");

        assertTrue(thrown.getMessage().contains("Table number must be between 1 and 8"), "Exception message does not match expected text.");
    }


    @Test
    @Order(6)
    @DisplayName("EC Test 6")
    void addInvalidPayment2() {

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            service.addPayment(121, PaymentType.Card, 455);
        }, "Expected addPayment to throw, but it didn't");

        assertTrue(thrown.getMessage().contains("Table number must be between 1 and 8"), "Exception message does not match expected text.");
    }

    @Test
    @Order(7)
    @DisplayName("EC Test 7")
    void addInvalidPayment3() {

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            service.addPayment(4, PaymentType.Card, 25000);
        }, "Expected addPayment to throw, but it didn't");

        assertTrue(thrown.getMessage().contains("Amount must be between 0 and 1000."), "Exception message does not match expected text.");
    }

    @Test
    @Order(8)
    @DisplayName("EC Test 8")
    void addInvalidPayment4() {

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            service.addPayment(7, PaymentType.Card, -7.5);
        }, "Expected addPayment to throw, but it didn't");

        assertTrue(thrown.getMessage().contains("Amount must be between 0 and 1000."), "Exception message does not match expected text.");

    }


    @Test
    @Order(9)
    @Tag("BVA")
    @DisplayName("BVA Test 1")
    void addValidPaymentTestBVA() throws IOException {
        service.addPayment(3, PaymentType.Card, 33.3);

        assertEquals(1, service.getPayments().size());
    }

    @Test
    @Order(10)
    @DisplayName("BVA Test 2")
    void addInvalidPaymentBVA() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            service.addPayment(9, PaymentType.Card, 33.3);
        }, "Expected addPayment to throw, but it didn't");

        assertTrue(thrown.getMessage().contains("Table number must be between 1 and 8"), "Exception message does not match expected text.");
    }

    @Test
    @Order(11)
    @DisplayName("BVA Test 3")
    void addInvalidPaymentBVA2() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            service.addPayment(0, PaymentType.Card, 33.3);
        }, "Expected addPayment to throw, but it didn't");

        assertTrue(thrown.getMessage().contains("Table number must be between 1 and 8"), "Exception message does not match expected text.");
    }

    @Test
    @Order(12)
    @DisplayName("BVA Test 4")
    @EnabledOnOs(OS.WINDOWS)
    void addValidPaymentTestBVA2() throws IOException {
        service.addPayment(5, PaymentType.Card, 450.65);

        assertEquals(1, service.getPayments().size());
    }

    @Test
    @Order(13)
    @DisplayName("BVA Test 5")
    @EnabledOnOs(OS.WINDOWS)
    void addInvalidPaymentBVA3() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            service.addPayment(5, PaymentType.Card, 0);
        }, "Expected addPayment to throw, but it didn't");

        assertTrue(thrown.getMessage().contains("Amount must be between 0 and 1000."), "Exception message does not match expected text.");
    }


    @Test
    @Order(14)
    @DisplayName("BVA Test 6")
    @EnabledOnOs(OS.WINDOWS)
    void addInvalidPaymentBVA4() {

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            service.addPayment(5, PaymentType.Card, 1000.1);
        }, "Expected addPayment to throw, but it didn't");

        assertTrue(thrown.getMessage().contains("Amount must be between 0 and 1000."), "Exception message does not match expected text.");
    }




}