package com.training.databacemanager;

public enum tableName {
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

	public abstract String getTableName();

}
