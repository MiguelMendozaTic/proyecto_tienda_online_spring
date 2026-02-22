package com.tienda;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;  // ¡Agrega esta importación!

@SpringBootTest
@ActiveProfiles("test")  // ¡Agrega esta línea!
class TiendaApplicationTests {

	@Test
	void contextLoads() {
	}

}