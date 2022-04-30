package DAO;

import java.sql.*;
import java.util.ArrayList;
import DTO.*;

//member table db ����
public class MemberDAO {
	String dirver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/managementdb";
	String userid="root";
	String passwd = "1234";
	Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt = null; 
	ResultSet rs = null;
	
	public MemberDAO() {
		try {
			Class.forName(dirver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} //����̹� �ε���
	}
	
	//1. �˻��ϱ�
	public ArrayList<MemberDTO> select() {
		ArrayList<MemberDTO> list = new ArrayList<MemberDTO>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(url, userid, passwd);
			String query = "select * from member";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MemberDTO mdto = new MemberDTO();
				mdto.setmCode(rs.getInt("mCode"));
				mdto.setProCode(rs.getString("proCode"));
				mdto.setmName(rs.getString("mName"));
				mdto.setmGender(rs.getString("mGender"));
				mdto.setmAge(rs.getInt("mAge"));
				mdto.setmPhone(rs.getString("mPhone"));
				mdto.setmPro(rs.getString("mPro"));
				mdto.setmLocker(rs.getString("mLocker"));
				mdto.setmVaccine(rs.getString("mVaccine"));
				list.add(mdto);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				//�����ͺ��̽� �ڿ��� �ݵ�� �ݾ� �־�� �Ѵ�. �׷��� finally�� �� ��
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return list;
	}//end select()
	
	
	//2. �����ϱ�
	public void insert(String mCode, String proCode, String mName, String mGender, String mAge, String mPhone, String mLocker, String mVaccine,String mPro) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DriverManager.getConnection(url, userid, passwd);
			String query = "insert into member(mCode, proCode, mName, mGender, mAge, mPhone, mPro, mLocker, mVaccine) values(?,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(mCode));
			pstmt.setString(2, proCode);
			pstmt.setString(3, mName);
			pstmt.setString(4, mGender);
			pstmt.setInt(5, Integer.parseInt(mAge));
			pstmt.setString(6, mPhone);
			pstmt.setString(8, mLocker);
			pstmt.setString(9, mVaccine);
			pstmt.setString(7, mPro);
				
			int n = pstmt.executeUpdate();
			if (n==1) System.out.println("���� ������");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}//end insert()
	
	
	    //3. �����ϱ�
		public void update(String mCode, String proCode, String mName, String mGender, String mAge, String mPhone, String mPro, String mLocker, String mVaccine) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			
			try {
				conn = DriverManager.getConnection(url, userid, passwd);
				String query = "update member set mCode=?, proCode=?, mGender=?, mAge=?, mPhone=?, mPro=?, mLocker=?, mVaccine=? where mName=?";
				pstmt=conn.prepareStatement(query);
				pstmt.setInt(1, Integer.parseInt(mCode));
				pstmt.setString(2, proCode);
				pstmt.setString(9, mName);
				pstmt.setString(3, mGender);
				pstmt.setInt(4, Integer.parseInt(mAge));
				pstmt.setString(5, mPhone);
				pstmt.setString(6, mPro);
				pstmt.setString(7, mLocker);
				pstmt.setString(8, mVaccine);
				
				
				int n = pstmt.executeUpdate();
				if(n==1) System.out.println("������ ������");
			
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					pstmt.close();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		//4. �����ϱ�
		public void delete(String mName) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			
			try {
				conn = DriverManager.getConnection(url, userid, passwd);
				String query = "delete from member where mName = ? ";
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, mName);
				int n = pstmt.executeUpdate();
				if(n == 1) System.out.println("������");
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					pstmt.close();
					conn.close();
				}catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		//ȸ������ ��ȸ
		public ArrayList<MemberDTO> search(String mName) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			//MemberDTO mdto = new MemberDTO();  //���� �̸�������� �ٸ� ���������� �ִ°� �߰� �Ϸ��� while�� ������ �־���� 
			ArrayList<MemberDTO> a = new ArrayList<MemberDTO>();
			
			try {
				conn = DriverManager.getConnection(url, userid, passwd);
				String query = "select * from member where mName = ? ";
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, mName);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					MemberDTO mdto = new MemberDTO();
					mdto.setmCode(rs.getInt("mCode"));
					mdto.setProCode(rs.getString("proCode"));
					mdto.setmName(rs.getString("mName"));
					mdto.setmGender(rs.getString("mGender"));
					mdto.setmAge(rs.getInt("mAge"));
					mdto.setmPhone(rs.getString("mPhone"));
					mdto.setmPro(rs.getString("mPro"));
					mdto.setmLocker(rs.getString("mLocker"));
					mdto.setmVaccine(rs.getString("mVaccine"));
					a.add(mdto);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
					try {
						if(rs != null)
						if(pstmt != null) pstmt.close();
					    if(conn != null) conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
			return a;
		}

	}
