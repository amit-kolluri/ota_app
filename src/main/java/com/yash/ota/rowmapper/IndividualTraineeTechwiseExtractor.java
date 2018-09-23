/*
 * IndividualTraineeTechwiseExtractor
 *
 * Version 1.0
 *
 * Date: 08/22/2018
 *
 * Author: YASH Trainees
 *
 * Copyright YASH Technologies Inc.
 */
package com.yash.ota.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.yash.ota.domain.ReportItem;

/**
 * Result set extractor for the Individual trainee techwise charts
 * @author karl.roth
 */
public class IndividualTraineeTechwiseExtractor implements ResultSetExtractor<List<ReportItem>> {
	
	@Override
	public List<ReportItem> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<ReportItem> list = new ArrayList<ReportItem>();
		while (rs.next()) {
			ReportItem reportItem = new ReportItem();
			reportItem.setId(rs.getInt("Technology_Id"));
			reportItem.setName(rs.getString("Technology_Name"));
			reportItem.setMarks(rs.getFloat("Average_Marks"));
			list.add(reportItem);
		}
		return list;
	}

}
