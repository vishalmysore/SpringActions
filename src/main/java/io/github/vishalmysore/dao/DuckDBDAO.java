package io.github.vishalmysore.dao;

import lombok.extern.java.Log;
import org.jooq.Record;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
@Log
public class DuckDBDAO implements IhotelsDAO {

    private final DSLContext dslContext;

    public DuckDBDAO(DataSource dataSource) {
        this.dslContext = DSL.using(dataSource, SQLDialect.DEFAULT);
    }

    @Override
    public String findHotels(String country, String city) {
        log.info("INSIDE DUCKDB REPO");
        String sql = "SELECT * FROM hotels WHERE country_name = ? AND city_name = ?";

        Result<Record> records = dslContext.fetch(sql, country, city);
        JSONArray jsonArray = new JSONArray();
        for (Record record : records) {
            JSONObject jsonObject = new JSONObject();
            for (Field<?> field : record.fields()) {
                jsonObject.put(field.getName(), record.getValue(field));
            }
            jsonArray.put(jsonObject);
        }

        return jsonArray.toString();
    }
}
