/*
 * InfoDAO.java
 * 
 * All Rights Reserved.
 * Copyright (c) 2020 FPT University
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

/**
 * InfoDAO class.<br>
 * 
 * <pre>
 * Class chứa các method nhằm mục đích lấy dữ liệu cần từ database
 * Trong class này sẽ tiến hành các xử lí dưới đây.
 * 
 * ・getInfoFindPage()
 * 
 * </pre>
 * 
 * @author FU AnhTHPHE130193
 * @version 1.0
 */
public class InfoDAO {
    /**Store DBContext object*/
    DBContext db = new DBContext();
    
     /**
     * Xử lí lấy dữ liệu Info trong CSDL.<br>
     * 
     * <pre>
     * Method sẽ lấy dữ liệu từ database ra để trả về 1 Map chứa các đối tượng Info.
     * Trường hợp lấy dữ liệu thất bại thì thực hiện ném exception.
     * 
     * ♦ Trình tự xử lí.
     *   1.Viết câu query để lấy ra hết các dữ liệu thuộc Info
     *   2.Dùng PrepareStatement để truyền tham số và thực hiện câu truy vấn.
     *   3. Kết quả của quá trình trên được lưu vào 1 ResultSet.
     *      3.1 Nếu kết quả ResultSet trả về record, tạo 1 Map<String,String>,
     *       set key là name và value là content, add vào Map và return Map vừa tạo
     *      3.2 Nếu không có record nào trong ResultSet thì return về Map rỗng.
     *   4.Đóng kết nối database
     * ♦ Xử lí Exception
     *  ・Nếu không lấy được dữ liệu thì Exception sẽ được bắn ra
     * 
     * </pre>
     * 
     * @return Map 
     * @throws Exception
     */
    public Map<String,String> getInforFindPage() throws Exception {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        //List<Info> infoList = new ArrayList<>();
        Map<String,String> hash = new HashMap<>();
        try {
            String sQuery = "SELECT * FROM Info";
            con = db.getConnection();
    
            ps = con.prepareStatement(sQuery);
            rs = ps.executeQuery();
            while (rs.next()) {
                //int id = rs.getInt("id");
                String name = rs.getString("name");
                
                String content = "";
                if(name.contains("img")){
                    content = db.getImagePath() + rs.getString("content");
                }
                else{
                    content = rs.getString("content");
                }
                
                //Info i = new Info(id, name, content);
                hash.put(name, content);
                
            } 
            
        } catch (Exception e) {
            db.closeConnection(rs, ps, con);
            throw e;
        }
        return hash;
    }

    
    
}
