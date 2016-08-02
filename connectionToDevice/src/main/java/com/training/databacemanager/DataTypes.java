package com.training.databacemanager;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import com.training.deviceoperation.deviceconnection.model.*;

public enum DataTypes {
	interfaces {

		@Override
		public boolean insert(Object object) {
			EthernetProtocolEndpoint ePE = (EthernetProtocolEndpoint) object;
			Connection conn = null;
			String sql = "INSERT INTO interfaces" + " VALUES ('" + ePE.getName() + "' , '" + ePE.getAdminStatus()
					+ "', '" + ePE.getOperStatus() + "', " + ePE.getMtu() + ", '" + ePE.getDuplexMode() + "', '"
					+ ePE.getIfSpeed() + "', '" + ePE.getMacAddress() + "')";
			try {
				Statement stmt = null;
				stmt = conn.createStatement();
				stmt.executeUpdate(sql);
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;

		}
	},

	policymap {
		@Override
		public boolean insert(Object object) {
			PolicyMap policyMap = (PolicyMap) object;
			Connection conn = null;
			String sql = "INSERT INTO policymap" + " VALUES ('" + policyMap.getPolicyName() + "' , '"
					+ policyMap.getTrafficClass() + "')";
			try {
				Statement stmt = null;
				stmt = conn.createStatement();
				stmt.executeUpdate(sql);
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;

		}
	},
	classmap {
		@Override
		public boolean insert(Object object) {
			ClassMap classMap = (ClassMap) object;
			Connection conn = null;
			String sql = "INSERT INTO classmap" + " VALUES ('" + classMap.getClassName() + "' , '"
					+ classMap.getClassMapConfigurationMode() + "', '" + classMap.getDescription() + "', "
					+ classMap.getMatchType() + ", '" + classMap.getMatchTypeValue() + "')";
			try {
				Statement stmt = null;
				stmt = conn.createStatement();
				stmt.executeUpdate(sql);
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;

		}
	},
	acl {
		@Override
		public boolean insert(Object object) {
			ACL acl = (ACL) object;
			Connection conn = null;
			Statement stmt = null;
			String sql = "INSERT INTO acl" + " VALUES ('" + acl.getAccessListModeNumber() + "' , '" + acl.getAccessNum()
					+ "', '" + acl.getDesIP() + "', " + acl.getIPAccessListNum() + ", '" + acl.getIPAccessListType()
					+ "', '" + acl.getSourceIP() + "', '" + acl.getWildCardDesIP() + "', '" + acl.getWildCardSourceIP()
					+ "')";
			try {

				stmt = conn.createStatement();
				stmt.executeUpdate(sql);
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;

		}
	},
	interface_acl {
		@Override
		public boolean insert(Object object) {
			Interface_ACL interfaceACL = (Interface_ACL) object;
			Connection conn = null;
			String sql = "INSERT INTO interface_acl" + " VALUES ('" + interfaceACL.getDir() + "' , '"
					+ interfaceACL.getAcl() + "', '" + interfaceACL.getInterface() + "')";
			try {
				Statement stmt = null;
				stmt = conn.createStatement();
				stmt.executeUpdate(sql);
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;

		}
	},
	interface_policy {
		@Override
		public boolean insert(Object object) {
			Interface_Policy interface_policy = (Interface_Policy) object;
			Connection conn = null;
			String sql = "INSERT INTO interface_policy" + " VALUES ('" + interface_policy.getDirection() + "' , '"
					+ interface_policy.getPolicy() + "' , '" + interface_policy.getInterfaceID() + "')";
			try {
				Statement stmt = null;
				stmt = conn.createStatement();
				stmt.executeUpdate(sql);
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;

		}
	},
	transaction {
		@Override
		public boolean insert(Object object) {
			Transaction transaction = (Transaction) object;
			Connection conn = null;
			String sql = "INSERT INTO transaction" + " VALUES ('" + transaction.getCommand() + "' , '"
					+ transaction.getPolicy_map() + "' , '" + transaction.getClass_map() + "')";
			try {
				Statement stmt = null;
				stmt = conn.createStatement();
				stmt.executeUpdate(sql);
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;

		}
	};
	public abstract boolean insert(Object object);
}
