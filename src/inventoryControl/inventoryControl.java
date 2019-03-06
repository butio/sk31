package inventoryControl;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.Dao;

/**
 * Servlet implementation class inventoryControl
 */
@WebServlet("/inventoryControl")
public class inventoryControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public inventoryControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		ArrayList<inventoryControlDB> arrayList = new ArrayList<inventoryControlDB>();
		String sql = "";

		Dao dao = null;
		ResultSet rs = null;
		try{
			sql = "SELECT vending_id, vending.place, stock.count, product.id, product.name, stock.stock, stock.price, stock.state, stock.receiptdate " +
					"FROM vending " +
					"INNER JOIN area ON area.id = vending.area_id " +
					"INNER JOIN stock ON stock.vending_id = vending.id " +
					"INNER JOIN product ON stock.product_id = product.id " +
					"INNER JOIN category ON category.id = product.category_id;";
			System.out.println(sql);
			dao = new Dao();
			System.out.println("aaa");
			rs = dao.execute(sql);
			System.out.println("bbb");
			while(rs.next()){
				inventoryControlDB s = new inventoryControlDB();
				s.setVendingId(rs.getString("vending_id"));
				s.setPlace(rs.getString("vending.place"));
				s.setCount(rs.getString("stock.count"));
				s.setProductId(rs.getString("product.id"));
				s.setProduct(rs.getString("product/name"));
				s.setStock(rs.getInt("stock.stock"));
				s.setPrice(rs.getInt("stock.price"));
				s.setState(rs.getString("stock.state"));
				s.setReceiptDate(rs.getString("stock.receiptdate"));
				arrayList.add(s);
			}
			System.out.println("ccc");
			request.setAttribute("RESULT",arrayList);
			RequestDispatcher rd=request.getRequestDispatcher("/inventoryControl.jsp");
			rd.forward(request, response);

		}catch(Exception e){
		}finally{
			try{
				if(rs != null){
					rs.close();
				}
				dao.close();
			}
			catch(Exception e){

			}
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
