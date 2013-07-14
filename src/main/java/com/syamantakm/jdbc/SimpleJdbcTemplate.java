package com.syamantakm.jdbc;

import com.syamantakm.annotation.Resource;
import com.syamantakm.util.PropertyHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Syamantak Mukhopadhyay
 */
public class SimpleJdbcTemplate implements JdbcTemplate {
    public static final Logger LOGGER = LoggerFactory.getLogger(SimpleJdbcTemplate.class);

    @Resource
    private PropertyHolder propertyHolder;

    @Override
    public <T> T queryForObject(String sql, RowMapper<T> mapper, Object... params) {
        T result = null;
        try {
            Connection conn = getConnection(
                    propertyHolder.getJdbcDriverClass(),
                    propertyHolder.getJdbcUrl(),
                    propertyHolder.getJdbcUsername(),
                    propertyHolder.getJdbcPassword()
            );
            PreparedStatement stmt = conn.prepareStatement(sql);
            int idx = 1;
            for(Object obj : params) {
                stmt.setObject(idx++, obj);
            }
            ResultSet rs = stmt.executeQuery();
            rs.next();
            result = mapper.mapRow(rs, 1);

            rs.close();
            stmt.close();
            conn.close();

        } catch (ClassNotFoundException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public <T> List<T> queryForList(String sql, RowMapper<T> mapper, Object... params) {
        List<T> result = new ArrayList<>();
        try {
            Connection conn = getConnection(
                    propertyHolder.getJdbcDriverClass(),
                    propertyHolder.getJdbcUrl(),
                    propertyHolder.getJdbcUsername(),
                    propertyHolder.getJdbcPassword()
            );
            PreparedStatement stmt = conn.prepareStatement(sql);
            int idx = 1;
            for(Object obj : params) {
                stmt.setObject(idx++, obj);
            }
            ResultSet rs = stmt.executeQuery();
            idx = 1;
            while(rs.next()) {
                T row = mapper.mapRow(rs, idx++);
                result.add(row);
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (ClassNotFoundException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
        return result;
    }

    private Connection getConnection(String driverClass, String jdbcUrl,
                                     String username, String pass) throws ClassNotFoundException, SQLException {
        Class.forName(driverClass);
        Connection conn = DriverManager.getConnection(jdbcUrl, username, pass);
        LOGGER.info("JDBC Connection created to : " + jdbcUrl);
        return conn;
    }
}
