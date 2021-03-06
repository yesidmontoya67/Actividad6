package co.edu.ff.orders.product.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductIdTest {
    @Test
    @DisplayName("No debería crear id para casos inválidos")
    void isShouldNotPass()  {
        Long p1 = -1L;
        Long p2= null;

        assertAll(
                () -> assertThrows(NullPointerException.class, () -> ProductId.of(p2)),
                () -> assertThrows(IllegalArgumentException.class, () -> ProductId.of(p1))
        );
    }

    @Test
    @DisplayName("Debería crear inventario validas")
    void isShouldPass() {
        int p1 =0;
        int p2 = 234234;

        assertAll(
                () -> assertDoesNotThrow(() -> InventoryQueantity.of(p1)),
                () -> assertNotNull(InventoryQueantity.of(p1).getValue()),
                () -> assertDoesNotThrow(() -> InventoryQueantity.of(p2)),
                () -> assertNotNull(InventoryQueantity.of(p2).getValue())

        );
    }


    @Test
    @DisplayName("retorna el mismo valor ingresado para inventario")
    void valueOfSameValue() {
        int inventario = 1233324;

        InventoryQueantity inventarioInstance = InventoryQueantity.of(inventario);
        String message = String.format("Se esperaba retornar el valor %s para una instancia creada con %s", inventario, inventario);
        assertEquals(inventarioInstance.valueOf(), inventario, message);
    }


}