package com.yash.ota.rowmapper;

import com.yash.ota.domain.ReportItem;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * This class provides object relational mapping functionality.
 *
 * @author - Jay Shah
 */
@Component
public class IndividualTraineeTechSpecificTestWiseExtractor implements ResultSetExtractor<List<ReportItem>> {
    @Override
    public List<ReportItem> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        List<ReportItem> list = new ArrayList<>();
        while (resultSet.next()) {
            ReportItem reportItem = new ReportItem();
            reportItem.setId(resultSet.getInt("Result_Id"));
            reportItem.setName(resultSet.getString("Topic_Name"));
            reportItem.setMarks(resultSet.getFloat("Percentage_Score"));
            list.add(reportItem);
        }
        return list;
    }
}
