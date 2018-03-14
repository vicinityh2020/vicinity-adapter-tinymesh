package com.tinymesh.vicinity.adapter.dao.impl;

import com.tinymesh.vicinity.adapter.dao.DeviceDao;
import com.tinymesh.vicinity.adapter.model.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public class DeviceDaoImpl  extends JdbcDaoSupport implements DeviceDao{
    
    @Autowired
    DataSource dataSource;
    
    @PostConstruct
    private void initialize(){
        setDataSource(dataSource);
    }

    @Override
    public void insert(Device device) {

        String sql = "INSERT INTO device " +
                "(DEVICETYPE, UUID, DATE, STATE, URL) VALUES(?, ?, ?, ?, ?";
                getJdbcTemplate().update(sql, new Object[]{
                        device.getDeviceType(), device.getUuid(), device.getDateTime(), device.getUrl()

        });


    }

    @Override
    public void insertBatch(List<Device> devices) {
        String sql = "INSERT INTO device " +
                "(DEVICETYPE, UUID, DATE, STATE, URL) VALUES(?, ?, ?, ?, ?";
        getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                Device device = devices.get(i);
                preparedStatement.setString(1, device.getDeviceType());
                preparedStatement.setObject(2, device.getUuid());
                preparedStatement.setObject(3, device.getDateTime());
                preparedStatement.setBoolean(4, device.isState());
                preparedStatement.setString(5, device.getUrl());
            }

            @Override
            public int getBatchSize() {
                return devices.size();
            }
        });

    }

    @Override
    public List<Device> loadAllDevice() {
        String sql = "SELECT * FROM device";
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);

        List<Device> result = new ArrayList<>();
        for(Map<String, Object> row:rows){
            Device device = new Device();
            device.setDeviceType((String)row.get("deviceType"));
            device.setUuid((UUID)row.get("uuid"));
            device.setDateTime((LocalDateTime) row.get("dateTime"));
            device.setState((Boolean) row.get("state"));
            device.setUrl((String)row.get("url"));



        }
        return result;
    }

    @Override
    public Device findDeviceByType(String deviceType) {
        String sql = "SELECT * FROM device WHERE deviceType = ?";
        return (Device)getJdbcTemplate().queryForObject(sql, new Object[]{deviceType}, new RowMapper<Device>() {

            @Override
            public Device mapRow(ResultSet resultSet, int i) throws SQLException {
                Device device = new Device();
                device.setDeviceType(resultSet.getString("deviceType"));
                device.setUuid((UUID) resultSet.getObject("uuid"));
                device.setDateTime((LocalDateTime) resultSet.getObject("dateTime"));
                device.setState(resultSet.getBoolean("state"));
                device.setUrl(resultSet.getString("url"));
                return device;
            }
        });
    }

    @Override
    public String findDevByType(String deviceType) {
        String sql = "SELECT * FROM device WHERE deviceType = ?";
        return getJdbcTemplate().queryForObject(sql, new Object[]{deviceType}, String.class);
    }

    @Override
    public int getTotalNumberDevice() {
        String sql = "SELECT Count(*) FROM device";
        int total = getJdbcTemplate().queryForObject(sql, Integer.class);
        return total;
    }
}
