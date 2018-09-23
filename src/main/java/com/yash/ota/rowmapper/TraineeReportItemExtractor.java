package com.yash.ota.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.yash.ota.domain.TraineeReportItem;

public class TraineeReportItemExtractor implements ResultSetExtractor<List<TraineeReportItem>> {

	@Override
	public List<TraineeReportItem> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<TraineeReportItem> traineeReportItems = new ArrayList<>();
		while(rs.next()) {
			TraineeReportItem tri = new TraineeReportItem();
			tri.setTest(rs.getString("test"));
			tri.setScore(rs.getFloat("score"));
			tri.setAverage(rs.getFloat("average"));
			tri.setDataClass("data");
			traineeReportItems.add(tri);
		}
		return traineeReportItems;
	}

}
