package ucb.edu.bo.SIS213.erPar.erPar;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ucb.edu.bo.SIS213.erPar.erPar.dao.UserDao;
import ucb.edu.bo.SIS213.erPar.erPar.entity.User;
import java.util.List;

@SpringBootTest
public class ApplicationTests {

		@Autowired
		private UserDao userDao;

		@Test
		public void testUserJPA() {
			List<User> usersList = userDao.findAll();
			for (User u : usersList) {
				System.out.println(u.getNombreUsuario());
			}
			assertNotEquals(0, usersList.size(), "No hay usuarios en la base de datos");
		}
}

