package com.training.databacemanager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.training.databacemanager.exception.CRUDException;
import com.training.deviceoperation.deviceconnection.model.*;

public enum DataTypes {
	interfaces {

		@Override
		public void insert(Object object) throws CRUDException {
			EthernetProtocolEndpoint ePE = (EthernetProtocolEndpoint) object;
			Connection conn = null;
			JDBC JDBCConnection = new JDBC();
			conn = JDBCConnection.connectToDatabase();
			Statement stmt = null;
			try {
				stmt = conn.createStatement();
				
			String sql = "INSERT INTO interfaces (name,adminStatus,operationalStatus,MTU,speed,duplex,macAddress)" + " VALUES ('" + ePE.getName() + "' , '"
					+ ePE.getAdminStatus() + "', '" + ePE.getOperStatus() + "', " + ePE.getMtu() + ", '"
					+ ePE.getDuplexMode() + "', '" + ePE.getIfSpeed() + "', '" + ePE.getMacAddress() + "')";
			//System.out.println(sql);
				stmt.executeUpdate(sql);
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new CRUDException(e.getMessage());
			}

		}

		@Override
		public int getID(String name) {
			Connection conn = null;
			JDBC JDBCConnection = new JDBC();
			conn = JDBCConnection.connectToDatabase();
			Statement stmt = null;
			String query = "SELECT id_interface from interfaces where name = '"+name+"'";
			int id_interface = 0;
			try {
				stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				while (rs.next()) {
					id_interface = rs.getInt("id_interface");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (conn != null) {
						try {
							conn.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}
			}
			return id_interface;
		}

	},

	policymap {

		@Override
		public void insert(Object object) throws CRUDException {
			PolicyMap policyMap = (PolicyMap) object;
			Connection conn = null;
			JDBC JDBCConnection = new JDBC();
			conn = JDBCConnection.connectToDatabase();
			String sql = "INSERT INTO policymap (policyName,trafficClass)" + " VALUES ('" + policyMap.getPolicyName() + "' , '"
					+ policyMap.getTrafficClass() + "')";
			//System.out.println(sql);
			try {
				Statement stmt = null;
				stmt = conn.createStatement();
				stmt.executeUpdate(sql);
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new CRUDException(e.getMessage());
			}
		}

		@Override
		public int getID(String name) {
			Connection conn = null;
			JDBC JDBCConnection = new JDBC();
			conn = JDBCConnection.connectToDatabase();
			Statement stmt = null;
			String query = "SELECT id_policymap from policymap where policyName = '"+name+"'";
			
			int id_policymap = 0;
			try {
				stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				while (rs.next()) {
					id_policymap = rs.getInt("id_policymap");
					//System.out.println(id_policymap);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						
					}
				}
			}
			return id_policymap;
		}

	},
	classmap {

		@Override
		public void insert(Object object) throws CRUDException {
			ClassMap classMap = (ClassMap) object;
			Connection conn = null;
			JDBC JDBCConnection = new JDBC();
			conn = JDBCConnection.connectToDatabase();
			String sql = "INSERT INTO classmap (className,classMapConfigurationMode,description,MatchType,matchTypeValue)" + " VALUES ('"+ classMap.getClassName() + "' , '"
					+ classMap.getClassMapConfigurationMode() + "', '" + classMap.getDescription() + "', '"
					+ classMap.getMatchType() + "', '" + classMap.getMatchTypeValue() + "')";
			

			try {
				Statement stmt = null;
				stmt = conn.createStatement();
				stmt.executeUpdate(sql);
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new CRUDException(e.getMessage());
			}

		}

		@Override
		public int getID(String name) {
			Connection conn = null;
			JDBC JDBCConnection = new JDBC();
			conn = JDBCConnection.connectToDatabase();
			Statement stmt = null;
			String query = "SELECT id_classmap from classmap where className = '"+name+"'";
			int id_classmap = 0;
			try {
				stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				while (rs.next()) {
					id_classmap = rs.getInt("id_classmap");
					//System.out.println(id_classmap);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			return id_classmap;
		}

	},
	acl {

		@Override
		public void insert(Object object) throws CRUDException {
			ACL acl = (ACL) object;
			Connection conn = null;
			Statement stmt = null;
			JDBC JDBCConnection = new JDBC();
			conn = JDBCConnection.connectToDatabase();
			String sql = "INSERT INTO acl (iPAccessListType,iPAccessListNum,accessListModeNumber,sourceIP,WildCardSourceIP,desIP,WildCardDesIP)" + " VALUES ('"+ acl.getIPAccessListType() + "' , '" + acl.getIPAccessListNum()
					+ "' , '" + acl.getAccessListModeNumber()+ "' , '" + acl.getSourceIP() 
					+ "', '" + acl.getWildCardSourceIP() + "', '" + acl.getDesIP() + "', '" + acl.getWildCardDesIP()
					+ "')";
			try {

				stmt = conn.createStatement();
				stmt.executeUpdate(sql);
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new CRUDException(e.getMessage());
			}

		}

		@Override
		public int getID(String name) {
			Connection conn = null;
			JDBC JDBCConnection = new JDBC();
			conn = JDBCConnection.connectToDatabase();
			Statement stmt = null;
			String query = "SELECT id_ACL from acl where iPAccessListNum = '"+name+"'";
			int id_ACL = 0;
			try {
				stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				while (rs.next()) {
					id_ACL = rs.getInt("id_ACL");
				//	System.out.println(id_ACL);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			return id_ACL;		
		}

	},
	interface_acl {

		@Override
		public void insert(Object object)throws CRUDException  {
			Interface_ACL interfaceACL = (Interface_ACL) object;
			Connection conn = null;
			JDBC JDBCConnection = new JDBC();
			conn = JDBCConnection.connectToDatabase();
			// select interface_id from interfaces where
			// interfaceACL.getInterface() = ''
			int id_interface = DataTypes.interfaces.getID(interfaceACL.getInterface());
			int id_acl = DataTypes.acl.getID(interfaceACL.getAclName());

			String sql = "INSERT INTO interface_acl" + " VALUES ('" + interfaceACL.getDir() + "' , '"
					+ id_acl + "', '" + id_interface + "')";
			//System.out.println(sql);
			

			try {
				Statement stmt = null;
				stmt = conn.createStatement();
				stmt.executeUpdate(sql);
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new CRUDException(e.getMessage());
			}

		}
// some thing wrong here !!
		@Override
		public int getID(String name) {
			// TODO Auto-generated method stub
			return 0;
		}

	},
	interface_policy {

		@Override
		public void insert(Object object) throws CRUDException {
			Interface_Policy interface_policy = (Interface_Policy) object;
			Connection conn = null;
			JDBC JDBCConnection = new JDBC();
			conn = JDBCConnection.connectToDatabase();
			int id_interface = DataTypes.interfaces.getID(interface_policy.getInterfaceName());
			int id_policy = DataTypes.policymap.getID(interface_policy.getPolicyName());
			
			String sql = "INSERT INTO interface_policy" + " VALUES ('" + interface_policy.getDirection() + "' , '"
					+ id_policy + "' , '" + id_interface  + "')";
			//System.out.println(sql);
			try {
				Statement stmt = null;
				stmt = conn.createStatement();
				stmt.executeUpdate(sql);
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new CRUDException(e.getMessage());
			}

		}

		@Override
		public int getID(String name) {
			// TODO Auto-generated method stub
			return 0;
		}

	},
	transaction {

		@Override
		public void insert(Object object) throws CRUDException {
			Transaction transaction = (Transaction) object;
			Connection conn = null;
			JDBC JDBCConnection = new JDBC();
			conn = JDBCConnection.connectToDatabase();
			int id_policy = DataTypes.policymap.getID(transaction.getPolicy_map());
			int id_class = DataTypes.classmap.getID(transaction.getClass_map());
			
			String sql = "INSERT INTO transaction" + " VALUES ('" + transaction.getCommand() + "' , '"
					+ id_policy + "' , '" + id_class + "')";
			try {
				Statement stmt = null;
				stmt = conn.createStatement();
				stmt.executeUpdate(sql);
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new CRUDException(e.getMessage());
			}

		}

		@Override
		public int getID(String name) {
			// TODO Auto-generated method stub
			return 0;
		}

	};

	public abstract void insert(Object object)throws CRUDException;

	public abstract int getID(String name);

}
