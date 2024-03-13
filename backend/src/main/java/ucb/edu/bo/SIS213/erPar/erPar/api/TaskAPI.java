package ucb.edu.bo.SIS213.erPar.erPar.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ucb.edu.bo.SIS213.erPar.erPar.bl.TaskBl;
import ucb.edu.bo.SIS213.erPar.erPar.dto.ResponseDTO;
import ucb.edu.bo.SIS213.erPar.erPar.entity.Task;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
@RequestMapping("/api/v1/task")
public class TaskAPI {

    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(LabelAPI.class);

    TaskBl taskBl;

    @Autowired
    public TaskAPI(TaskBl taskBl) {
        this.taskBl = taskBl;
    }

    @GetMapping
    public ResponseDTO getAllTasks() {
        List<Task> tasks;
        try {
            tasks = taskBl.getAllTasks();
            LOGGER.info("Se obtuvieron las tareas exitosamente");
        } catch (RuntimeException e) {
            LOGGER.error("Error al obtener las tareas", e);
            return new ResponseDTO("TASK-1000", e.getMessage());
        } 
        return new ResponseDTO(tasks);
    }

    @GetMapping("/etiqueta/{etiquetaId}")
    public ResponseDTO getTasksByEtiquetaId(@PathVariable Long etiquetaId) {
        List<Task> tasks;
        try {
            tasks = taskBl.getTasksByEtiquetaId(etiquetaId);
            LOGGER.info("Se obtuvieron las tareas exitosamente");
        } catch (RuntimeException e) {
            LOGGER.error("Error al obtener las tareas", e);
            return new ResponseDTO("TASK-1000", e.getMessage());
        }
        return new ResponseDTO(tasks);
    }

    @GetMapping("/estado/{estado}")
    public ResponseDTO getTasksByEstado(@PathVariable String estado) {
        List<Task> tasks;
        try {
            tasks = taskBl.getTasksByEstado(estado);
            LOGGER.info("Se obtuvieron las tareas exitosamente");
        } catch (RuntimeException e) {
            LOGGER.error("Error al obtener las tareas", e);
            return new ResponseDTO("TASK-1000", e.getMessage());
        }
        return new ResponseDTO(tasks); 
    }

    @PostMapping("/Label/{etiquetaId}/Create")
    public ResponseDTO createTask(@PathVariable Long etiquetaId, @RequestBody Task task) {
        task.setEtiquetaId(etiquetaId);
        task.setFechaCreacion(LocalDateTime.now());
        Task createdTask;
        try {
            createdTask = taskBl.createTask(task);
            LOGGER.info("Se crearon las tareas exitosamente");
        } catch (RuntimeException e) {
            LOGGER.error("Error al crear las tareas", e);
            return new ResponseDTO("TASK-1000", "No se puede crear la tarea");
        }
        return new ResponseDTO(createdTask);
    }

    @PutMapping("/Update/{taskId}")
    public ResponseDTO updateTask(@PathVariable Long taskId, @RequestBody Task task) {
        task.setFechaCreacion(task.getFechaCreacion());
        task.setFechaModificacion(LocalDateTime.now());
        Task updatedTask;
        try {
            updatedTask = taskBl.updateTask(taskId, task);
            LOGGER.info("Se logro actualizar las tareas exitosamente");
        } catch (Exception e) {
            LOGGER.error("Error al actualizar las tareas", e);
            return new ResponseDTO("TASK-1000", e.getMessage());
        }
        return new ResponseDTO(updatedTask);
    }

    @DeleteMapping("/Delete/{taskId}")
    public ResponseDTO deleteTask(@PathVariable Long taskId) {
        try {
            taskBl.deleteTask(taskId);
            LOGGER.info("Se logro eliminar las tareas exitosamente");
        } catch (Exception e) {
            LOGGER.error("Error al eliminar las tareas", e);
            return new ResponseDTO("TASK-1000", e.getMessage());
        }
        return new ResponseDTO("Tarea eliminada exitosamente");
    }
}