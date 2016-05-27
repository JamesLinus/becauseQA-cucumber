package com.framework.testcase.testrail;

import java.util.Date;

public class ReleaseSchedule {

	private Integer id;
	private String releaseName;
	private Date sprint1Date;
	private Date sprint2Date;
	private Date offcycleDate;
	private Date hardeningDate;
	private Date releaseDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getReleaseName() {
		return releaseName.trim();
	}

	public void setReleaseName(String releaseName) {
		this.releaseName = releaseName;
	}

	public Date getSprint1Date() {
		return sprint1Date;
	}

	public void setSprint1Date(Date sprint1Date) {
		this.sprint1Date = sprint1Date;
	}

	public Date getSprint2Date() {
		return sprint2Date;
	}

	public void setSprint2Date(Date sprint2Date) {
		this.sprint2Date = sprint2Date;
	}

	public Date getOffcycleDate() {
		return offcycleDate;
	}

	public void setOffcycleDate(Date offcycleDate) {
		this.offcycleDate = offcycleDate;
	}

	public Date getHardeningDate() {
		return hardeningDate;
	}

	public void setHardeningDate(Date hardeningDate) {
		this.hardeningDate = hardeningDate;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

}
