package services;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import org.apache.ibatis.jdbc.ScriptRunner;

public class DatabaseManager {
    private static DatabaseManager instance;
    private boolean databaseInitTables = false;
    public String databaseUrl;
    public String databaseDriver;
    private String databaseInitScript;
    public String databaseUsername;
    public String databasePassword;
    private Connection conn;

    public DatabaseManager() {
        loadProperties();
        try {
            openConnection();
            if (databaseInitTables) {
                initTables();
            }
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public static synchronized DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    private void loadProperties() {
        try {
            var props = new Properties();
            props.load(DatabaseManager.class.getClassLoader().getResourceAsStream("database.properties"));
            databaseUrl = props.getProperty("database.url", "jdbc:h2:C:/Users/Raul Rodriguez Luna/IdeaProjects/Medicion");
            databaseDriver = props.getProperty("database.driver", "org.h2.Driver");
            databaseInitTables = Boolean.parseBoolean(props.getProperty("database.initTables", "false"));
            databaseInitScript = props.getProperty("database.initScript", "init.sql");
            databaseUsername = props.getProperty("database.username", "pokemon");
            databasePassword = props.getProperty("database.password", "pokemon");

        } catch (IOException e) {
            e.getMessage();
        }
    }

    private void openConnection() throws SQLException {
        conn = DriverManager.getConnection(databaseUrl);
    }

    private void closeConnection() throws SQLException {
        conn.close();
    }

    private void initTables() {
        try {
            executeScript(databaseInitScript, true);
        } catch (FileNotFoundException e) {
            e.getMessage();
        }
    }


    public void executeScript(String scriptSqlFile, boolean logWriter) throws FileNotFoundException {
        ScriptRunner sr = new ScriptRunner(conn);
        var file = ClassLoader.getSystemResource(scriptSqlFile).getFile();
        Reader reader = new BufferedReader(new FileReader(file));
        sr.runScript(reader);
    }


    public Connection getConnection() throws SQLException {
        if (conn == null || conn.isClosed()) {
            try {
                openConnection();
            } catch (SQLException e) {
                e.getMessage();
                throw e;
            }
        }
        return conn;
    }

    public void close() throws Exception {
        closeConnection();
    }
}

