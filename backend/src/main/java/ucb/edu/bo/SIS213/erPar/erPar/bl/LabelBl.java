package ucb.edu.bo.SIS213.erPar.erPar.bl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ucb.edu.bo.SIS213.erPar.erPar.dao.LabelDao;
import ucb.edu.bo.SIS213.erPar.erPar.entity.Label;

import java.util.List;

@Service
public class LabelBl {

    private LabelDao labeldao;

    @Autowired
    public LabelBl(LabelDao labeldao) {
        this.labeldao = labeldao;
    }

    public List<Label> getAllLabels() {
        return labeldao.findAll();
    }

    public List<Label> getLabelsByUserId(Long userId) {
        Label label = labeldao.findById(userId).orElse(null);
        if (label == null) {
            throw new RuntimeException("No se encontró el usuario");
        }
        return labeldao.findLabelsByUserId(userId);
    }

    public Label createLabel(Label label) {
        return labeldao.save(label);
    }

    public Label updateLabel(Long labelId, Label label) {
        Label existingLabel = labeldao.findById(labelId).orElseThrow();
        existingLabel.setName(label.getName());
        return labeldao.save(existingLabel);
    }

    public void deleteLabelById(Long labelId) {
        if (!labeldao.existsById(labelId)) {
            throw new RuntimeException("No se encontró la etiqueta");
        }
        labeldao.deleteById(labelId);
    }
}

