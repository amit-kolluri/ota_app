package com.yash.ota.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCallbackHandler;

import com.yash.ota.domain.ReportItem;

/**
 * @author karl.roth
 */
public class IndividualTraineeTopicwiseExtractor implements ResultSetExtractor<List<ReportItem>> {

	@Override
	public List<ReportItem> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<ReportItem> list = new ArrayList<ReportItem>();
		while (rs.next()) {
			ReportItem reportItem = new ReportItem();
			reportItem.setId(rs.getInt("Technology_Id"));
			reportItem.setName(rs.getString("Topic_Name"));
			reportItem.setMarks(rs.getFloat("Percentage_Score"));
			list.add(reportItem);
		}
		return list;

	}

}
