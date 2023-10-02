package repositorys;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CrudRepository <T, localidad> {
    T save(T t) throws SQLException;

    T update(T t) throws SQLException;

    Optional<T> findByLocalidad(localidad localidad) throws SQLException;

    List<T> findAll() throws SQLException;

    boolean deleteByLocalidad(localidad localidad) throws SQLException;

    void deleteAll() throws SQLException;
}
