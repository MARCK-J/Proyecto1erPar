package ucb.edu.bo.SIS213.erPar.erPar;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ucb.edu.bo.SIS213.erPar.erPar.bl.LabelBl;
import ucb.edu.bo.SIS213.erPar.erPar.entity.Label;

@SpringBootTest
class LabelTests {

	private long Uid = 1;

	@Autowired 
	LabelBl labelBL;

	@Test
	void testgetAllLabels() {
		List<Label> labellist = labelBL.getAllLabels();
		for (Label l : labellist) {
			System.out.println(l.getName());
		}
		assertNotEquals(0, labellist.size(), "No hay etiquetas en la base de datos");
	}

	@Test
	void testgetLabelsByUserId() {
		List<Label> labellist = labelBL.getLabelsByUserId(Uid);
		for (Label l : labellist) {
			System.out.println(l.getName());
		}
		assertNotEquals(0, labellist.size(), "No hay etiquetas en la base de datos");
	}

	@Test
	void testcreateLabel() {
		Label label = new Label();
		label.setName("Test2");
		label.setUserId(Uid);
		Label createdLabel = labelBL.createLabel(label);
		assertNotNull(createdLabel, "La etiqueta no debe ser nula");
		assertEquals("Test2", createdLabel.getName(), "El nombre de la etiqueta debe ser Test2");
		assertEquals(Uid, createdLabel.getUserId(), "El id del usuario debe ser 1");
	}

	@Test
	void testupdateLabel() {
		
	}

	@Test
	void testdeleteLabelById() {
		labelBL.deleteLabelById(10L);
		List<Label> labellist = labelBL.getAllLabels();
		for (Label l : labellist) {
			System.out.println(l.getName());
		}
		assertNotEquals(0, labellist.size(), "No hay etiquetas en la base de datos");
	}
}
