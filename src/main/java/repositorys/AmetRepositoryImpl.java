package repositorys;

import models.Dia29;
import services.DatabaseManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AmetRepositoryImpl implements AmetRepository{

    private static AmetRepositoryImpl instance;

    private final DatabaseManager db;

    private AmetRepositoryImpl(DatabaseManager db) {
        this.db = db;
    }

    public static AmetRepositoryImpl getInstance(DatabaseManager db) {
        if (instance == null) {
            instance = new AmetRepositoryImpl(db);
        }
        return instance;
    }



    @Override
    public Dia29 save(Dia29 dia29) throws SQLException {
        String query = "INSERT INTO AMET (localidad) VALUES (?)";
        try (var connection = db.getConnection();
             var stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)
        ) {
            stmt.setString(1, dia29.getLocalidad());
            var res = stmt.executeUpdate();
            if (res > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                while (rs.next()) {
                    dia29.setLocalidad(rs.getString(1));
                }
                rs.close();
            }
        }
        return dia29;
    }

    @Override
    public Dia29 update(Dia29 dia29) throws SQLException {
        String query = "UPDATE AMET SET provincia =? WHERE localidad =?";
        try (var connection = db.getConnection();
             var stmt = connection.prepareStatement(query)
        ) {
            stmt.setString(1, dia29.getProvincia());
            stmt.setString(2, dia29.getLocalidad());
            stmt.executeUpdate();
        }
        return dia29;    }

    @Override
    public Optional<Dia29> findByLocalidad(String s) throws SQLException {
        String query = "SELECT * FROM AMET WHERE localidad = ?";
        try (var connection = db.getConnection();
             var stmt = connection.prepareStatement(query)
        ) {
            stmt.setString(1, s);
            var rs = stmt.executeQuery();
            Optional<Dia29> dia = Optional.empty();
            while (rs.next()) {
                dia = Optional.of(new Dia29(
                        rs.getString("localidad"),
                        rs.getString("provincia"),
                        rs.getDouble("temp_max"),
                        rs.getTimestamp("h_temp_max"),
                        rs.getDouble("temp_min"),
                        rs.getTimestamp("h_temp_min"),
                        rs.getInt("precipitacion")
                ));
            }
            rs.close();
            return dia;
        }    }

    @Override
    public List<Dia29> findAll() throws SQLException {
        String query = "SELECT * FROM AMET";
        var lista = new ArrayList<Dia29>();
        try (var connection = db.getConnection();
             var stmt = connection.prepareStatement(query)
        ) {
            var rs = stmt.executeQuery();
            while (rs.next()) {
                var alumno = new Dia29(
                        rs.getString("localidad"),
                        rs.getString("provincia"),
                        rs.getDouble("temp_max"),
                        rs.getTimestamp("localidad"),
                        rs.getDouble("temp_min"),
                        rs.getTimestamp("localidad"),
                        rs.getInt("localidad")
                        );
                lista.add(alumno);
            }
            rs.close();

        }
        return lista;
    }

    @Override
    public boolean deleteByLocalidad(String s) throws SQLException {
        String query = "DELETE FROM AMET WHERE localidad =?";
        try (var connection = db.getConnection();
             var stmt = connection.prepareStatement(query)
        ) {
            stmt.setString(1, s);
            var res = stmt.executeUpdate();
            return res > 0;
        }
    }

    @Override
    public void deleteAll() throws SQLException {
        String query = "DELETE FROM AMET";
        try (var connection = db.getConnection();
             var stmt = connection.prepareStatement(query)
        ) {
            stmt.executeUpdate();
        }
    }
}
