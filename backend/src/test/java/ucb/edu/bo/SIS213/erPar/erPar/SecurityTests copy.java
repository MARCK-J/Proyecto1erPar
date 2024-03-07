package ucb.edu.bo.SIS213.erPar.erPar;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ucb.edu.bo.SIS213.erPar.erPar.bl.SecurityBl;
import ucb.edu.bo.SIS213.erPar.erPar.entity.User;

@SpringBootTest
class SecurityTests {

	@Autowired 
	SecurityBl securityBL;

	@Test
	void testSuccessLogin() {
		User user = securityBL.login("Marco Reynolds", "12345");
		assertNotNull(user, 
		"El usuario no debe ser nulo");
	}

	@Test
	void testErrorLogin() {
		try {
			securityBL.login("Marco Reynolds", "1234576");
		} catch (RuntimeException e) {
			assertEquals("", e.getMessage(), "El mensaje de error no debe estar vacío");
		}
		
	}
}
