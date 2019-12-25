/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSTCopyright.IDCS.utils;

import CSTCopyright.IDCS.controller.PortModel;
import CSTCopyright.IDCS.controller.ScanModel;
import CSTCopyright.IDCS.controller.ServiceModel;
import CSTCopyright.IDCS.controller.UserAccount;
import CSTCopyright.IDCS.controller.VultModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mac
 */
public class DBUtils {

    //find user info with username
    public static UserAccount findUser(Connection conn, String userName) {

        String sql = "Select * from User a INNER JOIN UserInfo t"
                + " on a.username = t.username"
                + " where a.username = ? ";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, userName);

            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                //get Value from DB
                String password = rs.getString("Password");
                boolean gender = rs.getBoolean("Gender");
                String email = rs.getString("Email");
                String fName = rs.getString("FullName");
                String phone = rs.getString("PhoneNumber");
                Date exp = rs.getDate("EXPDate");
                int uType = rs.getInt("UserType");
                int useCount = rs.getInt("UseCount");
                String address = rs.getString("Address");
                //set User value
                UserAccount user = new UserAccount();
                user.setUserName(userName);
                user.setPassword(password);
                user.setGender(gender);
                user.setEmailAddress(email);
                user.setFullName(fName);
                user.setPhoneNumber(phone);
                user.setExpiryDate(exp);
                user.setUserType(uType);
                user.setUseCount(useCount);
                user.setAddress(address);
                return user;
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return null;
        }

        return null;
    }

    public static List<UserAccount> getUsers(Connection conn) throws SQLException {
        String sql = "Select * from User a INNER JOIN UserInfo t"
                + " on a.username = t.username"
                + " where a.username = ? ";

        List<UserAccount> list = null;
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                //get Value from DB
                String userName = rs.getString("UserName");
                String password = rs.getString("Password");
                boolean gender = rs.getBoolean("Gender");
                String email = rs.getString("Email");
                String fName = rs.getString("FullName");
                String phone = rs.getString("PhoneNumber");
                Date exp = rs.getDate("EXPDate");
                int uType = rs.getInt("UserType");
                int useCount = rs.getInt("UseCount");
                String address = rs.getString("Address");
                //set User value
                UserAccount user = new UserAccount();
                user.setUserName(userName);
                user.setPassword(password);
                user.setGender(gender);
                user.setEmailAddress(email);
                user.setFullName(fName);
                user.setPhoneNumber(phone);
                user.setExpiryDate(exp);
                user.setUserType(uType);
                user.setUseCount(useCount);
                list.add(user);
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return null;
        }
        return list;
    }

    public static List<VultModel> GetVultData(Connection conn, String vultObj) {
        String sql = "SELECT * FROM VultTbl v WHERE v.VultObj = ? ";
        List<VultModel> list = new ArrayList<>();
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, vultObj);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String vultDes = rs.getString("VultDes");
                String vultRem = rs.getString("VultRem");
                String vultNote = rs.getString("VultNote");
                int vultID = rs.getInt("VultID");
                list.add(new VultModel(vultID, vultObj, vultDes, vultRem, vultNote));
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return null;
        }
        return list;
    }

    public static boolean updateUser(Connection conn, UserAccount user) {

        String sql = "Update UserInfo u"
                + " set u.Gender = ?, u.Email = ?, u.FullName = ?,"
                + " u.PhoneNumber = ?, u.EXPDate = ?, u.UserType = ?,"
                + " u.Address = ? WHERE u.UserName = ?";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setBoolean(1, user.isGender());
            pstm.setString(2, user.getEmailAddress());
            pstm.setString(3, user.getFullName());
            pstm.setString(4, user.getPhoneNumber());
            pstm.setDate(5, user.getExpiryDate());
            pstm.setInt(6, user.getUserType());
            pstm.setString(7, user.getAddress());
            pstm.setString(8, user.getUserName());

            pstm.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return false;
        }

        return true;
    }

    public static boolean updateUserPassword(Connection conn, UserAccount user) {

        String sql = "Update User u"
                + " set u.Password = ? WHERE u.UserName = ?";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, user.getPassword());
            pstm.setString(2, user.getUserName());

            pstm.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return false;
        }

        return true;
    }

    public static boolean addPort(Connection conn, PortModel port) {
        String sql = "INSERT INTO PortsTbl (SID, PortNum, Name, Version, CPE, Product, ExtraInfo)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, Integer.parseInt(port.getS_ID()));
            pstm.setInt(2, Integer.parseInt(port.getPORTNUM()));
            pstm.setString(3, port.getNAME());
            pstm.setString(4, port.getVERSION());
            pstm.setString(5, port.getCPE());
            pstm.setString(6, port.getPRODUCT());
            pstm.setString(7, port.getEXTRALINFO());
            pstm.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return false;
        }
        return true;
    }

    public static boolean addScan(Connection conn, ScanModel scan) {
        String sql = "INSERT INTO ScanTbl (UserName, Target, IPV4, DDNS, NetName, Descr, Country, Person, Email, DateScan)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);

            pstm.setString(1, scan.getU_ID());
            pstm.setString(2, scan.getTARGET());
            pstm.setString(3, scan.getIPv4());
            pstm.setString(4, scan.getDDNS());
            pstm.setString(5, scan.getNETNAME());
            pstm.setString(6, scan.getDESCR());
            pstm.setString(7, scan.getCOUNTRY());
            pstm.setString(8, scan.getPERSON());
            pstm.setString(9, scan.getEMAIL());
            pstm.setDate(10, scan.getDATESCAN());
            pstm.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return false;
        }
        return true;
    }

    public static boolean addService(Connection conn, ServiceModel serv) {
        String sql = "INSERT INTO ServicesTbl (SID, PortNum, Script)"
                + "VALUES (?, ?, ?)";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, Integer.parseInt(serv.getS_ID()));
            pstm.setInt(2, Integer.parseInt(serv.getPORTNUM()));
            pstm.setString(3, serv.getSCRIPT());
            pstm.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return false;
        }
        return true;
    }

    public static ScanModel getLatestScanRecord(Connection conn, String userName) {
        ScanModel scan = new ScanModel();
        String sql = "SELECT * FROM ScanTbl s WHERE s.UserName = ? "
                + "ORDER BY s.DateScan DESC "
                + "LIMIT 1";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, userName);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                scan.setS_ID(rs.getString("SID"));
                scan.setTARGET(rs.getString("Target"));
                scan.setIPv4(rs.getString("IPV4"));
                scan.setDDNS(rs.getString("DDNS"));
                scan.setNETNAME(rs.getString("NetName"));
                scan.setDESCR(rs.getString("Descr"));
                scan.setCOUNTRY(rs.getString("Country"));
                scan.setPERSON(rs.getString("Person"));
                scan.setEMAIL(rs.getString("Email"));
                scan.setDATESCAN(rs.getDate("DateScan"));
                scan.setU_ID(userName);
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        return scan;
    }

    public static ScanModel getMostScanRecord(Connection conn, String userName) {
        ScanModel scan = new ScanModel();
        String sql = "SELECT * FROM   ScanTbl, (SELECT Target, count(*) as counter "
                + "FROM   ScanTbl GROUP BY Target Order by counter DESC Limit 1 ) "
                + "AS SortedTbl WHERE  ScanTbl.Target = SortedTbl.Target "
                + "AND ScanTbl.UserName = ? Order by DateScan LIMIT 1";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, userName);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                scan.setS_ID(rs.getString("SID"));
                scan.setTARGET(rs.getString("Target"));
                scan.setIPv4(rs.getString("IPV4"));
                scan.setDDNS(rs.getString("DDNS"));
                scan.setNETNAME(rs.getString("NetName"));
                scan.setDESCR(rs.getString("Descr"));
                scan.setCOUNTRY(rs.getString("Country"));
                scan.setPERSON(rs.getString("Person"));
                scan.setEMAIL(rs.getString("Email"));
                scan.setDATESCAN(rs.getDate("DateScan"));
                scan.setU_ID(userName);
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        return scan;
    }

    public static ScanModel findScanByDateScan(Connection conn, ScanModel scan) {
        String sql = "SELECT * FROM ScanTbl s "
                + "WHERE s.UserName = ? AND s.Target = ? AND s.DateScan = ?";
        ScanModel record = new ScanModel();
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, scan.getU_ID());
            pstm.setString(2, scan.getTARGET());
            pstm.setDate(3, scan.getDATESCAN());
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                record.setS_ID(rs.getString("SID"));
                record.setU_ID(rs.getString("UserName"));
                record.setTARGET(rs.getString("Target"));
                record.setIPv4(rs.getString("IPV4"));
                record.setDDNS(rs.getString("DDNS"));
                record.setNETNAME(rs.getString("NetName"));
                record.setDESCR(rs.getString("Descr"));
                record.setCOUNTRY(rs.getString("Country"));
                record.setPERSON(rs.getString("Person"));
                record.setEMAIL(rs.getString("Email"));
                record.setDATESCAN(rs.getDate("DateScan"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        return record;
    }
    
    public static ScanModel findLatestScanByTarget(Connection conn, ScanModel scan) {
        String sql = "SELECT * FROM ScanTbl s "
                + "WHERE s.UserName = ? AND s.Target = ?"
                + "ORDER BY s.DateScan DESC "
                + "LIMIT 1";
        ScanModel record = new ScanModel();
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, scan.getU_ID());
            pstm.setString(2, scan.getTARGET());
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                record.setS_ID(rs.getString("SID"));
                record.setU_ID(rs.getString("UserName"));
                record.setTARGET(rs.getString("Target"));
                record.setIPv4(rs.getString("IPV4"));
                record.setDDNS(rs.getString("DDNS"));
                record.setNETNAME(rs.getString("NetName"));
                record.setDESCR(rs.getString("Descr"));
                record.setCOUNTRY(rs.getString("Country"));
                record.setPERSON(rs.getString("Person"));
                record.setEMAIL(rs.getString("Email"));
                record.setDATESCAN(rs.getDate("DateScan"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        return record;
    }

    public static boolean updateScan(Connection conn, ScanModel scan) {
        String sql = "Update ScanTbl s SET "
                + "s.UserName = ?, s.Target =?, s.IPV4 = ?, "
                + "s.DDNS = ?, s.NetName = ?, s.Descr = ?, s.Country = ?, "
                + "s.Person = ?, s.Email = ?"
                + "WHERE s.UserName = ? AND s.Target = ? AND s.Date = ?";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, scan.getU_ID());
            pstm.setString(2, scan.getTARGET());
            pstm.setString(3, scan.getIPv4());
            pstm.setString(4, scan.getDDNS());
            pstm.setString(5, scan.getNETNAME());
            pstm.setString(6, scan.getDESCR());
            pstm.setString(7, scan.getCOUNTRY());
            pstm.setString(8, scan.getPERSON());
            pstm.setString(9, scan.getEMAIL());
            pstm.setDate(10, scan.getDATESCAN());
            pstm.setString(11, scan.getU_ID());
            pstm.setString(12, scan.getTARGET());
            pstm.setDate(13, scan.getDATESCAN());
            int rs = pstm.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return false;
        }
        return true;
    }

    public static PortModel findPort(Connection conn, PortModel port) {
        String sql = "SELECT * FROM PortsTbl p "
                + "WHERE p.SID = ? AND p.PortNum = ?";
        PortModel record = new PortModel();
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, Integer.parseInt(port.getS_ID()));
            pstm.setString(2, port.getPORTNUM());
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                record.setS_ID(rs.getString("SID"));
                record.setPORTNUM(rs.getString("PortNum"));
                record.setNAME(rs.getString("Name"));
                record.setVERSION(rs.getString("Version"));
                record.setCPE(rs.getString("CPE"));
                record.setPRODUCT(rs.getString("Product"));
                record.setEXTRALINFO(rs.getString("ExtraInfo"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        return record;
    }

    public static List<PortModel> findListPorts(Connection conn, String SID) {
        String sql = "SELECT * FROM PortsTbl p "
                + "WHERE p.SID = ?";
        List<PortModel> list = new ArrayList<>();
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, Integer.parseInt(SID));
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                PortModel record = new PortModel();
                record.setS_ID(rs.getString("SID"));
                record.setPORTNUM(rs.getString("PortNum"));
                record.setNAME(rs.getString("Name"));
                record.setVERSION(rs.getString("Version"));
                record.setCPE(rs.getString("CPE"));
                record.setPRODUCT(rs.getString("Product"));
                record.setEXTRALINFO(rs.getString("ExtraInfo"));
                if (record.getS_ID() != null) {
                    list.add(record);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        return list;
    }

    public static ServiceModel findService(Connection conn, ServiceModel serv) {
        String sql = "SELECT * FROM ServicesTbl s "
                + "WHERE s.SID = ? AND s.PortNum = ?";
        ServiceModel record = new ServiceModel();
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, Integer.parseInt(serv.getS_ID()));
            pstm.setString(2, serv.getPORTNUM());
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                record.setSCRIPT(rs.getString("Script"));
                record.setS_ID(rs.getString("SID"));
                record.setPORTNUM(rs.getString("PortNum"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        return record;
    }

    public static List<ServiceModel> findListService(Connection conn, String SID) {
        String sql = "SELECT * FROM ServicesTbl s "
                + "WHERE s.SID = ?";
        List<ServiceModel> list = new ArrayList<>();
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, Integer.parseInt(SID));
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                ServiceModel record = new ServiceModel();
                record.setSCRIPT(rs.getString("Script"));
                record.setS_ID(rs.getString("SID"));
                record.setPORTNUM(rs.getString("PortNum"));
                if (record.getS_ID() != null) {
                    list.add(record);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        return list;
    }

    public static List<ScanModel> findScanByTarget(Connection conn, ScanModel scan) {
        String sql = "SELECT * FROM ScanTbl s "
                + "WHERE s.UserName = ? AND s.Target LIKE ?"
                + "ORDER BY s.DateScan DESC";
        
        List<ScanModel> list = new ArrayList<>();
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, Integer.parseInt(scan.getU_ID()));
            pstm.setString(2, scan.getTARGET());
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                ScanModel record = new ScanModel();
                record.setS_ID(rs.getString("SID"));
                record.setU_ID(rs.getString("UserName"));
                record.setTARGET(rs.getString("Target"));
                record.setIPv4(rs.getString("IPV4"));
                record.setDDNS(rs.getString("DDNS"));
                record.setNETNAME(rs.getString("NetName"));
                record.setDESCR(rs.getString("Descr"));
                record.setCOUNTRY(rs.getString("Country"));
                record.setPERSON(rs.getString("Person"));
                record.setEMAIL(rs.getString("Email"));
                record.setDATESCAN(rs.getDate("DateScan"));
                if(record.getS_ID() != null){
                    list.add(record);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        return list;
    }
}
