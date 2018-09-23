package com.yash.ota.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.yash.ota.domain.ReportItem;

public class TechwiseReportExtractor implements ResultSetExtractor<List<ReportItem>> {

	@Override
	public List<ReportItem> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<ReportItem> list = new ArrayList<ReportItem>();
		while (rs.next()) {
			ReportItem reportItem = new ReportItem();
			reportItem.setName(rs.getString("first_name"));
			reportItem.setMarks(rs.getFloat("marks"));
			list.add(reportItem);
		}
		return list;
	}

}