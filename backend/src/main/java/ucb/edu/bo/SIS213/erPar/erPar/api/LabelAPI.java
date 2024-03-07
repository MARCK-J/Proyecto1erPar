package ucb.edu.bo.SIS213.erPar.erPar.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ucb.edu.bo.SIS213.erPar.erPar.bl.LabelBl;
import ucb.edu.bo.SIS213.erPar.erPar.dto.ResponseDTO;
import ucb.edu.bo.SIS213.erPar.erPar.entity.Label;


@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
@RequestMapping("/api/v1/label")
public class LabelAPI {

    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(LabelAPI.class);

    LabelBl labelbl;

    @Autowired
    public LabelAPI(LabelBl labelbl) {
        this.labelbl = labelbl;
    }

    // Endpoint para obtener todas las etiquetas
    @GetMapping
    public ResponseDTO getAllLabels()  {
        List<Label> labellist;
        try {
            labellist = labelbl.getAllLabels();
            LOGGER.info("Se obtuvieron las etiquetas exitosamente");
        }catch(RuntimeException e) {
            LOGGER.error("Error al obtener las etiquetas", e);
            return new ResponseDTO("TASK-1000", e.getMessage());
        }
        return new ResponseDTO(labellist);
    }

    @GetMapping("/User/{userId}")
    public ResponseDTO getLabelsByUserId(@PathVariable Long userId) {
        List<Label> labellist;
        try {
            labellist = labelbl.getLabelsByUserId(userId);
            LOGGER.info("Se obtuvieron las etiquetas exitosamente");
        } catch (RuntimeException e) {
            LOGGER.error("Error al obtener las etiquetas", e);
            return new ResponseDTO("TASK-1000", e.getMessage());
        }
        return new ResponseDTO(labellist);
    }

    @PostMapping("/User/{userId}/Create")
    public ResponseDTO createLabel(@PathVariable Long userId, @RequestBody Label label) {
        label.setUserId(userId);
        Label createdLabel;
        try {
            createdLabel = labelbl.createLabel(label);
            LOGGER.info("Se crearon las etiquetas exitosamente");
        } catch (RuntimeException e) {
            LOGGER.error("Error al crear las etiquetas", e);
            return new ResponseDTO("TASK-1000", e.getMessage());
        }
        return new ResponseDTO(createdLabel);
    }

    @PutMapping("/Update/{labelId}")
    public ResponseDTO updateLabel(@PathVariable Long labelId, @RequestBody Label label) {
        Label updatedLabel;
        try {
            updatedLabel = labelbl.updateLabel(labelId, label);
            LOGGER.info("Se logro actualizar las etiquetas exitosamente");
        } catch (RuntimeException e) {
            LOGGER.error("Error al actualizar las etiquetas", e);
            return new ResponseDTO("TASK-1000", "La etiqueta no existe");
        }
        return new ResponseDTO(updatedLabel);
    }

    @DeleteMapping("/Delete/{labelId}")
    public ResponseDTO deleteLabelById(@PathVariable Long labelId) {
        try {
            labelbl.deleteLabelById(labelId);
            LOGGER.info("Se logro eliminar las etiquetas exitosamente");
        } catch (RuntimeException e) {
            LOGGER.error("Error al eliminar las etiquetas", e);
            return new ResponseDTO("TASK-1000", e.getMessage());
        }
        return new ResponseDTO("Etiqueta eliminada exitosamente");
    }
}

