package repositorys;

import models.Dia29;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface AmetRepository extends CrudRepository<Dia29, String>{
    Optional<Dia29> findByLocalidad(String s) throws SQLException;
}
