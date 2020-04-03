/*
 * DetailDAO.java
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

/**
 * DetailDAO class.<br>
 *
 * <pre>
 * Class chứa các method nhằm mục đích lấy dữ liệu cần từ database
 * Trong class này sẽ tiến hành các xử lí dưới đây.
 *
 * ・getDetailNews()
 * ・checkInt()
 *
 * </pre>
 *
 * @author FU AnhTHPHE130193
 * @version 1.0
 */
public class DetailDAO {

    /**
     * Store DBContext object
     */
    DBContext db = new DBContext();

    /**
     * Xử lí lấy 1 đối tượng News thông qua Id.<br>
     *
     * <pre>
     * Method sẽ lấy dữ liệu từ database ra để trả về 1 object News hoàn chỉnh.
     * Trường hợp lấy dữ liệu thất bại thì thực hiện ném exception.
     *
     * ♦ Trình tự xử lí.
     *   1.Viết câu query để lấy ra Record có Id bằng với Id ghi trong tham số truyền vào.
     *   2.Dùng PrepareStatement để truyền tham số và thực hiện câu truy vấn.
     *   3. Kết quả của quá trình trên được lưu vào 1 ResultSet.
     *      3.1 Nếu kết quả ResultSet trả về ít nhất 1 Record, tạo 1 đối tượng News mới,
     *          set các giá trị cho đối tượng đó sau đó return về chính đối tượng đó.
     *      3.2 Nếu không có record nào trong ResultSet thì return về null.
     *   4.Đóng kết nối database
     * ♦ Xử lí Exception
     *  ・Nếu không lấy được dữ liệu thì Exception sẽ được bắn ra
     * </pre>
     *
     * @param id int
     * @throws Exception
     * @return News object
     */
    public News getDetailNews(int id) throws Exception {
        String query = "select * from News where id = ?";
        News m;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = db.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                m = new News();
                m.setId(rs.getInt(1));
                m.setTitle(rs.getString(2));
                m.setImgLink(db.getImagePath() + rs.getString(3));
                m.setContent(rs.getString(4));
                m.setFullContent(rs.getString(5));
                return m;
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw ex;
        } finally {
            db.closeConnection(rs, ps, conn);
        }
        return null;

    }

    /**
     * Check valid integer.<br>
     *
     * <pre>
     * Method sẽ kiểm tra tham số truyền vào có phải là số nguyên không
     *
     * ♦ Trình tự xử lí.
     *
     *   1.parse biến tham số truyền vào
     *   2.Nếu parse thành công thì trả về true, và ngược lại
     *
     * </pre>
     *
     * @param tmp string
     * @return boolean
     */
    public boolean checkInt(String tmp) {
        int a;
        try {
            a = Integer.parseInt(tmp);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }

    }

}
