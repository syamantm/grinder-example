package com.syamantakm.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Syamantak Mukhopadhyay
 */
public interface RowMapper<T> {
    T mapRow(ResultSet rs, int rowNum) throws SQLException;
}
