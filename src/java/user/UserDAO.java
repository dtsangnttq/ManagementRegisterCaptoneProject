/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

/**
 *
 * @author mac
 */
public class UserDAO {

    public UserDTO checkLogin(String gmail, String password) throws SQLException {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT name, roleID, userID "
                        + "FROM tblUser "
                        + "WHERE  gmail=? AND password=? ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, gmail);
                stm.setString(2, password);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String username = rs.getString("name");
                    String roleID = rs.getString("roleID");
                    String userID = rs.getString("userID");
                    user = new UserDTO(userID, username, password, roleID, gmail, "", "", "", "", "");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return user;
    }

    public UserDTO checkLoginGG(String gmail) throws SQLException {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT name, password, roleID, userID "
                        + "FROM tblUser "
                        + "WHERE  gmail=? ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, gmail);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String username = rs.getString("name");
                    String password = rs.getString("password");
                    String roleID = rs.getString("roleID");
                    String userID = rs.getString("userID");
                    user = new UserDTO(userID, username, password, roleID, gmail, "", "", "", "", "");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return user;
    }

    public List<UserDTO> getListStudent() throws SQLException {
        List<UserDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " SELECT userID, name, gmail, phone, photoUrl, capstoneID, groupID, statusID "
                        + " FROM tblUser "
                        + " WHERE [roleID] LIKE 'US' ";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("userID");
                    String username = rs.getString("name");
                    String gmail = rs.getString("gmail");
                    String phone = rs.getString("phone");
                    String photoUrl = rs.getString("photoUrl");
                    String capstoneID = rs.getString("capstoneID");
                    String groupID = rs.getString("groupID");
                    String statusID = rs.getString("statusID");
                    list.add(new UserDTO(userID, username, "", "US", gmail, phone, capstoneID, groupID, statusID, photoUrl));
                }
                System.out.println(list);
            }

        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public List<UserDTO> getListSupervisor() throws SQLException {
        List<UserDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " SELECT userID, name, gmail, phone, photoUrl, capstoneID, groupID, statusID "
                        + " FROM tblUser "
                        + " WHERE [roleID] LIKE 'MT' ";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("userID");
                    String username = rs.getString("name");
                    String gmail = rs.getString("gmail");
                    String phone = rs.getString("phone");
                    String photoUrl = rs.getString("photoUrl");
                    String capstoneID = rs.getString("capstoneID");
                    String groupID = rs.getString("groupID");
                    String statusID = rs.getString("statusID");
                    list.add(new UserDTO(userID, username, "", "MT", gmail, phone, capstoneID, groupID, statusID, photoUrl));
                }
            }

        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public UserDTO getInforUser(String userIDValue) throws SQLException {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " SELECT tblUser.userID, tblUser.name, tblUser.gmail, tblUser.phone,"
                        + " tblUser.photoUrl, tblSemester.semesterName,tblCapstone.capstoneName"
                        + " FROM tblUser Inner Join tblUserCapstone ON tblUser.userID = tblUserCapstone.userID "
                        + "Inner Join tblCapstone ON tblUserCapstone.capstoneID = tblCapstone.capstoneID  "
                        + "Inner Join tblSemester ON tblUser.semesterID = tblSemester.semesterID where tblUser.userID = ? ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, userIDValue);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String userID = rs.getString("userID");
                    String name = rs.getString("name");
                    String gmail = rs.getString("gmail");
                    String phone = rs.getString("phone");
                    String photoUrl = rs.getString("photoUrl");
                    String semesterName = rs.getString("semesterName");
                    String capstoneName = rs.getString("capstoneName");
                    user = new UserDTO(userID, name, gmail, phone, photoUrl, semesterName, capstoneName);  
                    if (user != null) {
                        return user;
                    }
                }

            }

        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return null;

    }

    public boolean updateInfor(String name, String phone, String photoUrl, String userID) throws SQLException {
        int row = 0;
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "UPDATE tblUser \n"
                    + "SET name = ? , phone = ?, photoUrl = ? "
                    + "WHERE userID = ? ";
            stm = conn.prepareStatement(sql);
            stm.setString(1, name);
            stm.setString(2, phone);
            stm.setString(3, photoUrl);
            stm.setString(4, userID);
            row = stm.executeUpdate();
            if (row > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return false;
    }
}
