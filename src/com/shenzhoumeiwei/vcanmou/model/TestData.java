package com.shenzhoumeiwei.vcanmou.model;

import java.util.List;

public class TestData {

	private String date;
	private List<TestGridData> testGridData;

	public static class TestGridData {
		private String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<TestGridData> getTestGridData() {
		return testGridData;
	}

	public void setTestGridData(List testGridData) {
		this.testGridData = testGridData;
	}

}
