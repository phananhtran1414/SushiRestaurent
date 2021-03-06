/*
 * News.java
 * 
 * All Rights Reserved.
 * Copyright (c) 2020 FPT University
 */
package dal;

import entity.News;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * NewsDAO class.<br>
 * 
 * <pre>
 * Class chứa các method nhằm mục đích lấy dữ liệu cần từ database
 * Trong class này sẽ tiến hành các xử lí dưới đây.
 * 
 * ・getListMenu()
 * ・getTotalPages()
 * 
 * </pre>
 * 
 * @author FU AnhTHPHE130193
 * @version 1.0
 */
public class NewsDAO {
    /**Store DBContext object*/
    DBContext db = new DBContext();
    

    /**
     * Xử lí lấy tổng số trang<br>
     * 
     * <pre>
     * Method sẽ lấy dữ liệu từ CSDL để lấy ra tổng số trang News cần phân trang ở trang Home
     * Trường hợp lấy dữ liệu thất bại thì thực hiện ném exception.
     * 
     * ♦ Trình tự xử lí.
     *   1.Viết câu query để lấy lấy total record trong CSDL
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

        try {
            String squery = "select count(*) as TotalRecord from News";
            con = db.getConnection();
            ps = con.prepareStatement(squery);
            rs = ps.executeQuery();
            while (rs.next()) {
                totalRecord = rs.getInt("TotalRecord");
            }
            if (totalRecord % db.getPageSize() != 0) {
                totalPage = (totalRecord / db.getPageSize()) + 1;
            } else {
                totalPage = totalRecord / db.getPageSize();
            }

        } catch (ClassNotFoundException | SQLException ex) {
            throw ex;
        } finally {
            db.closeConnection(rs, ps, con);
        }
        return totalPage;

    }

    
    /**
     * Xử lí lấy dữ liệu News trong CSDL.<br>
     * 
     * <pre>
     * Method sẽ lấy dữ liệu database ra
     *    để trả về 1 list các đối tượng News tại vị trí tương ứng
     * Trường hợp lấy dữ liệu thất bại thì thực hiện ném exception.
     * 
     * ♦ Trình tự xử lí.
     *   1.Viết câu query để lấy ra hết các dữ liệu thuộc News
     *   2.Dùng PrepareStatement để truyền tham số và thực hiện câu truy vấn.
     *   3. Kết quả của quá trình trên được lưu vào 1 ResultSet.
     *      3.1 Nếu kết quả ResultSet trả về record, tạo 1 list đối tượng News,
     *       set các giá trị cho từng News, add vào list và return list vừa tạo
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
     * @return News list object
     */
    public List<News> getDataList(int currentPage) throws Exception {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        List<News> returnList = new ArrayList<>();
        int stat = (currentPage - 1) * db.getPageSize() + 1;
        int end = stat + (db.getPageSize() - 1);
        News news;
        try {
            String sQuery = "SELECT * FROM ("
                    + "SELECT *, ROW_NUMBER() OVER (ORDER BY id) AS RN FROM News"
                    + ") AS H "
                    + "where H.RN >= ? and H.RN <=?";
            con = db.getConnection();
            ps = con.prepareStatement(sQuery);
            ps.setInt(1, stat);
            ps.setInt(2, end);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String title = rs.getString(2);
                String imgLink = db.getImagePath() + rs.getString(3);
                String content = rs.getString(4);
                String fullContent = rs.getString(5);
                news = new News(id, title, imgLink, content, fullContent);
                returnList.add(news);
            }

        } catch (ClassNotFoundException | SQLException ex) {
            throw ex;
        } finally {
            db.closeConnection(rs, ps, con);
        }
        return returnList;

    }
    
    
    
}
