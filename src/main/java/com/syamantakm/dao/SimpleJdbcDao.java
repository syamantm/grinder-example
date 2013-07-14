package com.syamantakm.dao;

import com.syamantakm.annotation.Resource;
import com.syamantakm.jdbc.DataAccessException;
import com.syamantakm.jdbc.JdbcTemplate;
import com.syamantakm.jdbc.RowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Syamantak Mukhopadhyay
 */
public class SimpleJdbcDao {
        public static final Logger LOGGER = LoggerFactory.getLogger(SimpleJdbcDao.class);

    public static final String SQL_SELECT_CACHE_ENTRY = "SELECT id FROM cache_entry LIMIT ?";

    @Resource
    private JdbcTemplate jdbcTemplate;

    public List<Integer> getCacheEntryIds(int limit) {
        List<Integer> cacheEntryIds = null;
        try {
           cacheEntryIds = jdbcTemplate.queryForList(SQL_SELECT_CACHE_ENTRY, new RowMapper<Integer>() {
               @Override
               public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                   return rs.getInt("id");
               }
           }, limit);
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return cacheEntryIds;
    }
}
