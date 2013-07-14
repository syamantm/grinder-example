package com.syamantakm.dao;

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
public class SimpleJdbcDao {
    public static final Logger LOGGER = LoggerFactory.getLogger(SimpleJdbcDao.class);

    public static final String SQL_SELECT_CACHE_ENTRY = "SELECT id FROM cache_entry LIMIT ?";

    @Resource
    private PropertyHolder propertyHolder;

    public List<Integer> getCacheEntryIds(int limit) {
        List<Integer> cacheEntryIds = new ArrayList<>();
        try {
            Connection conn = getConnection(
                    propertyHolder.getJdbcDriverClass(),
                    propertyHolder.getJdbcUrl(),
                    propertyHolder.getJdbcUsername(),
                    propertyHolder.getJdbcPassword()
            );
            PreparedStatement stmt = conn.prepareStatement(SQL_SELECT_CACHE_ENTRY);
            stmt.setInt(1, limit);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                cacheEntryIds.add(rs.getInt("id"));
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return cacheEntryIds;
    }

    private Connection getConnection(String driverClass, String jdbcUrl,
                                     String username, String pass) throws ClassNotFoundException, SQLException {
        Class.forName(driverClass);
        Connection conn = DriverManager.getConnection(jdbcUrl, username, pass);
        LOGGER.info("JDBC Connection created to : " + jdbcUrl);
        return conn;
    }
}
