package ucb.edu.bo.SIS213.erPar.erPar.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ucb.edu.bo.SIS213.erPar.erPar.entity.Label;

import java.util.List;

@Repository
public interface LabelDao extends JpaRepository<Label, Long> {

    @Query(value = "SELECT * FROM labels WHERE ID_usuario = ?1",
    nativeQuery = true)
    List<Label> findLabelsByUserId(Long userId);
}


