package com.framework.testcase.testrail;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.framework.utilities.DBUtils;

public class TestRailDB {

	public static ReleaseSchedule getReleaseByDate(String date) {
		String driver = "net.sourceforge.jtds.jdbc.Driver";
		String url = "jdbc:jtds:sqlserver://GDCQAAUTOSQL201/automation";
		String user = "qa_automation";
		String password = "Gr33nDot!";
		Connection connection = DBUtils.getConnection(driver, url, user,
				password);
		String sql = "SELECT * from automation..ReleaseSchedule where '" + date
				+ "' between Sprint1 and ReleaseDate";
		ResultSet rs = DBUtils.selectRecord(connection, sql);
		ReleaseSchedule releaseSchedule = new ReleaseSchedule();
		try {
			while (rs.next()) {
				releaseSchedule.setReleaseName(rs.getString("Release"));
				releaseSchedule.setSprint1Date(rs.getDate("Sprint1"));
				releaseSchedule.setSprint2Date(rs.getDate("Sprint2"));
				releaseSchedule.setOffcycleDate(rs.getDate("OffCycle"));
				releaseSchedule.setHardeningDate(rs.getDate("Hardening"));
				releaseSchedule.setReleaseDate(rs.getDate("ReleaseDate"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtils.closeAllConnections(connection, rs);
		}

		return releaseSchedule;

	}

	public static void main(String[] args) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT-08:00"));
		String date = simpleDateFormat.format(new Date());
		ReleaseSchedule releaseByDate = getReleaseByDate(date);
		String releaseName = releaseByDate.getReleaseName();
		System.out.println(releaseName);

	}

}
