package ucb.edu.bo.SIS213.erPar.erPar.bl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ucb.edu.bo.SIS213.erPar.erPar.dao.TaskDao;
import ucb.edu.bo.SIS213.erPar.erPar.entity.Task;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskBl {

    private TaskDao taskDao;

    @Autowired
    public TaskBl(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    // Métodos GET
    public List<Task> getAllTasks() {
        return taskDao.findAll();
    }

    public List<Task> getTasksByEtiquetaId(Long etiquetaId) {
        Task task = taskDao.findById(etiquetaId).orElse(null);
        if (task == null) {
            throw new RuntimeException("No se encontró la etiqueta");
        }
        return taskDao.findByEtiquetaId(etiquetaId);
    }

    public List<Task> getTasksByEstado(String estado) {
        return taskDao.findByEstado(estado);
    }

    // Método POST
    public Task createTask(Task task) {
        if (task.getTitulo().isEmpty() || task.getDescripcion().isEmpty() || task.getEstado().isEmpty() || task.getFechaLimite() == null) {
            throw new RuntimeException("No se puede crear la tarea");
        }
        return taskDao.save(task);
    }

    // Método PUT
    public Task updateTask(Long taskId, Task task) {
        Task existingTask = taskDao.findById(taskId).orElseThrow(() -> new RuntimeException("Tarea no encontrada"));
        existingTask.setEtiquetaId(task.getEtiquetaId());
        existingTask.setTitulo(task.getTitulo());
        existingTask.setDescripcion(task.getDescripcion());
        existingTask.setEstado(task.getEstado());
        existingTask.setFechaLimite(task.getFechaLimite());
        existingTask.setFechaModificacion(LocalDateTime.now());
        return taskDao.save(existingTask);
    }

    // Método DELETE
    public void deleteTask(Long id) {
        if (!taskDao.existsById(id)) {
            throw new RuntimeException("No se encontró la tarea");
        }
        taskDao.deleteById(id);
    }
}

