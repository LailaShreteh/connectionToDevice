package com.training.deviceoperation.deviceconnection.model;

/**
 * The ClassMap class represents each Class Map and its criteria to define a
 * traffic classification.
 * 
 */
public class ClassMap {
	private String className;
	private String classMapConfigurationMode;
	private String description;
	private String MatchType;

	public ClassMap(String className, String classMapConfigurationMode, String description, String matchType) {
		this.className = className;
		this.classMapConfigurationMode = classMapConfigurationMode;
		this.description = description;
		this.MatchType = matchType;
	}

	public String getClassName() {
		return className;
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
