package com.training.deviceoperation.deviceconnection.model;

public class class_map {
	private String className;
	private String classMapConfigurationMode;// match-all or match-any
	private String description;
	private String MatchType;// match access-group .......

	public String getClassName() {
		return className;
	}

	public class_map(String className, String classMapConfigurationMode, String description, String matchType) {
		super();
		this.className = className;
		this.classMapConfigurationMode = classMapConfigurationMode;
		this.description = description;
		MatchType = matchType;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getClassMapConfigurationMode() {
		return classMapConfigurationMode;
	}

	public void setClassMapConfigurationMode(String classMapConfigurationMode) {
		this.classMapConfigurationMode = classMapConfigurationMode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMatchType() {
		return MatchType;
	}

	public void setMatchType(String matchType) {
		MatchType = matchType;
	}
}
