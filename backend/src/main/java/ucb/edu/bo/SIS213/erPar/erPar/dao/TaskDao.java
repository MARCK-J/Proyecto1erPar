package ucb.edu.bo.SIS213.erPar.erPar.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ucb.edu.bo.SIS213.erPar.erPar.entity.Task;

import java.util.List;

@Repository
public interface TaskDao extends JpaRepository<Task, Long> {

    @Query(value = "SELECT * FROM tasks WHERE ID_etiqueta = ?1",nativeQuery = true)
    List<Task> findByEtiquetaId(@Param("etiquetaId") Long etiquetaId);

    @Query(value = "SELECT * FROM tasks WHERE estado = ?1",nativeQuery = true)
    List<Task> findByEstado(@Param("estado") String estado);
}

