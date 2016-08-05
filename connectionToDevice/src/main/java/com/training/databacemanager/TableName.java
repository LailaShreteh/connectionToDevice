package com.training.databacemanager;

/**
 * TableName is enum class of classes model Name.
 * 
 * @author user
 *
 */
public enum TableName {
	EthernetProtocolEndpoint {

		@Override
		public String getTableName() {
			// TODO Auto-generated method stub
			return "interfaces";
		}
	},

	PolicyMap {

		@Override
		public String getTableName() {
			// TODO Auto-generated method stub
			return "policymap";
		}
	},

	ClassMap {

		@Override
		public String getTableName() {
			// TODO Auto-generated method stub
			return "classmap";
		}
	},

	ACL {

		@Override
		public String getTableName() {
			// TODO Auto-generated method stub
			return "acl";
		}
	},

	Interface_ACL {

		@Override
		public String getTableName() {
			// TODO Auto-generated method stub
			return "interface_acl";
		}
	},

	Interface_Policy {

		@Override
		public String getTableName() {
			// TODO Auto-generated method stub
			return "interface_policy";
		}
	},

	Transaction {

		@Override
		public String getTableName() {
			return "transaction";
		}
	};

	/**
	 * getTableName method to get the name of each model class as in the
	 * database tables.
	 * 
	 * @return - the name of the model class as in the database tables.
	 */
	public abstract String getTableName();

}
