package com.tienda;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class ApplicationTest {
    
    @Test
    void contextLoads() {
        System.out.println("=== Prueba de contexto ===");
        assertTrue(true, "El contexto debe cargar");
    }
    
    @Test
    void testSuma() {
        System.out.println("=== Prueba matemática ===");
        assertEquals(4, 2 + 2, "2 + 2 debe ser 4");
    }
    
    @Test
    void testNoNulo() {
        System.out.println("=== Prueba de objeto no nulo ===");
        String mensaje = "Hola Jenkins";
        assertNotNull(mensaje, "El mensaje no debe ser nulo");
    }
}
