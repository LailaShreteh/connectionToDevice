package com.training.deviceoperation.deviceconnection.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 * The ClassMap class represents each Class Map and its criteria to define a
 * traffic classification.
 * 
 * @author user
 */
@Entity
public class ClassMap {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id_classmap;
	@Column
	private String className;
	@Column
	private String classMapConfigurationMode;
	@Column
	private String description;
	@Column
	private String matchType;
	@Column
	private String matchTypeValue;
	

	public ClassMap() {
	}
	private Set<ACL> aclList = new HashSet<ACL>(0);


	public ClassMap(String className, String classMapConfigurationMode, String description, String matchType,
			String matchTypeValue) {
		this.className = className;
		this.classMapConfigurationMode = classMapConfigurationMode;
		this.description = description;
		this.matchType = matchType;
		this.matchTypeValue = matchTypeValue;
	}
	public ClassMap(String className, String classMapConfigurationMode, String description, String matchType,
			String matchTypeValue, Set<ACL> aclList) {
		this.className = className;
		this.classMapConfigurationMode = classMapConfigurationMode;
		this.description = description;
		this.matchType = matchType;
		this.matchTypeValue = matchTypeValue;
		this.aclList=aclList;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "aclList")
	public Set<ACL> getACL() {
		return this.aclList;
	}

	public String getMatchTypeValue() {
		return matchTypeValue;
	}

	public void setMatchTypeValue(String matchTypeValue) {
		this.matchTypeValue = matchTypeValue;
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
		return matchType;
	}

	public void setMatchType(String matchType) {
		this.matchType = matchType;
	}

	public void setACL(Set<ACL> aclList) {
		this.aclList = aclList;
	}

	@Override
	public String toString() {
		return ("**" + className + " || \t" + classMapConfigurationMode + " || \t" + description + " || \t" + matchType
				+ " || \t" + matchTypeValue);
	}
}
