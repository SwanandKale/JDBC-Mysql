package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pojo.Product;

public class ProductManager {

	private Connection con;
	private Statement stmt1;
	private Statement stmt2;
	private PreparedStatement pstmt3;
	private PreparedStatement pstmt4;
	private PreparedStatement pstmt5;

	public ProductManager() throws SQLException {
		// established connection
		con = DbConnector.openConnection();

		stmt1 = con.createStatement();
		stmt2 = con.createStatement();
		pstmt3 = con.prepareStatement("insert into product values(?,?,?)");
		pstmt4 = con.prepareStatement("update product set pname=?, price=? where pid=?");
		pstmt5 = con.prepareStatement("delete from product where pid=?");
	}
	
	public List<Product> getAllProducts() throws SQLException
	{
		List<Product> allProducts=new ArrayList<Product>();
		
		String query="select* from product";
		ResultSet result= stmt1.executeQuery(query);
		
		while(result.next())
		{
			
			Product product=new Product(result.getInt(1), result.getString(2), result.getDouble(3));
			allProducts.add(product);
			
		}
		return allProducts;
	}
	
	public Product getProductById(int id) throws SQLException
	{
		Product product=null;
		String query="select * from product where pid="+id;
		ResultSet result=stmt2.executeQuery(query);
		
		if(result.next())
		{
			product=new Product(result.getInt(1),result.getString(2),result.getDouble(3));
		}
		return product;
	
	}
	
	public boolean addProduct(Product product) throws SQLException
	{
		pstmt3.setInt(1, product.getPid());
		pstmt3.setString(2, product.getPname());
		pstmt3.setDouble(3, product.getPrice());
		
		int i= pstmt3.executeUpdate();
		
		if(i>0)
		{
			return true;
		}
		return false;
	
	}
	
	public boolean updateProduct(Product product) throws SQLException 
	{
		pstmt4.setInt(3, product.getPid());
		pstmt4.setString(1, product.getPname());
		pstmt4.setDouble(2, product.getPrice());
		
		int i=pstmt4.executeUpdate();
		if(i>0)
		{
			return true;
		}
		return false;
		
	}
	
	public boolean deleteProduct(int id) throws SQLException
	{
		pstmt5.setInt(1,id);
		int i=pstmt5.executeUpdate();
		
		if(i>0)
		{
			return true;
		}
		return false;
		
	}
	
	public void closeConnection() throws SQLException
	{
		con.close();
	}
 
}
//Statement is also interface
//createstatement -->creates a statement object for sending SQL statement to database
//ExecuteQuery it is the method  it will fire the querry and return ResultSet as optput
//Execute update method used for DML like update delete insert and return count rows deleted/updated etc