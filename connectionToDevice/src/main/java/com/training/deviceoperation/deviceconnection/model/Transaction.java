package com.training.deviceoperation.deviceconnection.model;

/**
 * 
 * @author user
 *
 */
public class Transaction {
	private Action command;
	private PolicyMap policyMap;
	private ClassMap classMap;

	public Transaction(Action command, PolicyMap policyMap, ClassMap classMap) {
		this.command = command;
		this.policyMap = policyMap;
		this.classMap = classMap;
	}

	public Action getCommand() {
		return command;
	}

	public void setCommand(Action command) {
		this.command = command;
	}

	public PolicyMap getPolicyMap() {
		return policyMap;
	}

	public void setPolicyMap(PolicyMap policyMap) {
		this.policyMap = policyMap;
	}

	public ClassMap getClassMap() {
		return classMap;
	}

	public void setClass_map(ClassMap classMap) {
		this.classMap = classMap;
	}

}
