package io.github.vishalmysore.util;

import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
@Log
public class DuckDbInitializer implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;

    public DuckDbInitializer(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void run(String... args) throws Exception {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS hotels AS " +
                "SELECT * FROM read_csv_auto('src/main/resources/data/hotels.csv');";
        
        jdbcTemplate.execute(createTableQuery);
        log.info("DuckDB table created from CSV at startup!");
    }
}
