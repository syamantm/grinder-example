package com.syamantakm.jdbc;

import java.sql.ResultSet;
import java.util.List;

/**
 * @author Syamantak Mukhopadhyay
 */
public interface JdbcTemplate {
    <T> T queryForObject(String sql,  RowMapper<T> mapper, Object... params);
    <T> List<T>  queryForList(String sql,  RowMapper<T> mapper, Object... params);
}
