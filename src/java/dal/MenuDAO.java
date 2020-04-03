/*
 * MenuDAO.java
 * 
 * All Rights Reserved.
 * Copyright (c) 2020 FPT University
 */
package dal;

import entity.Menu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * MenuDAO class.<br>
 * 
 * <pre>
 * Class chứa các method nhằm mục đích lấy dữ liệu cần từ database
 * Trong class này sẽ tiến hành các xử lí dưới đây.
 * 
 * ・getListMenu()
 * ・getTotalPages()
 * 
 * 
 * </pre>
 * 
 * @author FU AnhTHPHE130193
 * @version 1.0
 */
public class MenuDAO {
    /**Store DBContext object*/
    DBContext db = new DBContext();
    int check = 0;
    
    /**
     * Xử lí lấy dữ liệu Menu trong CSDL.<br>
     * 
     * <pre>
     * Method sẽ lấy dữ liệu database ra
     *    để trả về 1 list các đối tượng Menu tại vị trí tương ứng
     * Trường hợp lấy dữ liệu thất bại thì thực hiện ném exception.
     * 
     * ♦ Trình tự xử lí.
     *   1.Viết câu query để lấy ra hết các dữ liệu thuộc Menu
     *   2.Dùng PrepareStatement để truyền tham số và thực hiện câu truy vấn.
     *   3. Kết quả của quá trình trên được lưu vào 1 ResultSet.
     *      3.1 Nếu kết quả ResultSet trả về record, tạo 1 list đối tượng Menu,
     *       set các giá trị cho từng Menu, add vào list và return list vừa tạo
     *      3.2 Nếu không có record nào trong ResultSet thì return về list rỗng.
     *   4.Đóng kết nối database
     * ♦ Xử lí Exception
     *  ・Nếu không lấy được dữ liệu thì Exception sẽ được bắn ra
     * 
     * </pre>
     * 
     * @param currentPage int
     * @throws SQLException
     * @throws ClassNotFoundException
     * @return Menu list object
     */
    public List<Menu> getListMenus(int currentPage) throws Exception{
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        List<Menu> returnList = new ArrayList<>();
        
        int start = (currentPage - 1) * db.getPageSize() + 1;
        int end = start + (db.getPageSize() - 1);
        
        String select = "Select * from ("
                    + " Select *,ROW_NUMBER() OVER (Order by id)"
                    + " as rn from Menu"
                    + " ) as H "
                    + " where H.rn >= ? and H.rn <= ? ";
        
        try {
            con = db.getConnection();
            ps = con.prepareStatement(select);
            ps.setInt(1, start);
            ps.setInt(2, end);
            rs = ps.executeQuery();
            
            while(rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String content = rs.getString(3);
                double price = rs.getFloat(4);
                Menu m = new Menu(id, name, content, price);
                returnList.add(m);
            }
            
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MenuDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        } finally{
            db.closeConnection(rs, ps, con);
        }
        return returnList;
    }
    
    
    /**
     * Xử lí lấy tổng số trang<br>
     * 
     * <pre>
     * Method sẽ lấy dữ liệu từ CSDL để lấy ra tổng số trang cần phân trang ở trang Menu
     * Trường hợp lấy dữ liệu thất bại thì thực hiện ném exception.
     * 
     * ♦ Trình tự xử lí.
     *   1.Viết câu query để lấy total record trong CSDL
     *   2.Dùng PrepareStatement để truyền tham số và thực hiện câu truy vấn.
     *   3. Kết quả của quá trình trên được lưu vào 1 ResultSet.
     *     3.1 Nếu kết quả ResultSet trả về Record, tạo 1 biến int nhận giá trị đó
     *          , sau đó gọi nó chia cho pageSize mà mình đã phân từ trước,
     *          kết quả nhận được sẽ được (totalPage) sẽ đc trả về.     
     *      3.2 Nếu không có record nào trong ResultSet thì return về 0.
     *   4.Đóng kết nối database
     * ♦ Xử lí Exception
     *  ・Nếu không lấy được dữ liệu thì Exception sẽ được bắn ra
     * 
     * </pre>
     * 
     * @throws SQLException
     * @throws ClassNotFoundException
     * @return int
     */
    public int getTotalPages() throws Exception {
        int totalPage = 0;
        int totalRecord = 0;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        
        try{
            String query = "select count(*) as totalRecord from Menu";
            con = db.getConnection();
            ps = con.prepareStatement(query);

            rs = ps.executeQuery();
            while(rs.next()){
                totalRecord = rs.getInt("totalRecord");
            }

            if(totalRecord % db.getPageSize()==0){
                totalPage = totalRecord / db.getPageSize();
            }else{
                totalPage = totalRecord / db.getPageSize() + 1; 
            }
        }catch(ClassNotFoundException | SQLException e){
            throw e;
        }finally{
            db.closeConnection(rs, ps, con);
        }

        return totalPage;
    }
 
}
