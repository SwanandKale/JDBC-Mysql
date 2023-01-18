package tester;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import dal.ProductManager;
import pojo.Product;

public class Tester {

	public static void main(String[] args) {
		try {
			Scanner sc = new Scanner(System.in);
			// connection established
			ProductManager prod = new ProductManager();

			boolean flag = false;

			System.out.println(
					"1.Display Products 2.Display Product By Id 3.Add Product 4.Update Product 5. Delete Product 6.Exit");
			System.out.println("Enter the choice");
			while (!flag) {
				switch (sc.nextInt()) {
				case 1:
					prod.getAllProducts().forEach(product -> {
						System.out.println(product);
					});
					break;

				case 2:
					System.out.println("Enter the Product Id");
					System.out.println(prod.getProductById(sc.nextInt()));
					break;

				case 3:
					System.out.println("Enter Product Id , Name , Price");
					if (prod.addProduct(new Product(sc.nextInt(), sc.next(), sc.nextDouble()))) {
						System.out.println("Product Added succesfully !");
					}
					break;
				case 4:
					System.out.println("Enter Product Id -> Enter Updated Name and Price ");
					if (prod.updateProduct(new Product(sc.nextInt(), sc.next(), sc.nextDouble()))) {
						System.out.println("Product Updated succesfully !");
					}
					break;
				case 5:
					System.out.println("Enter the Product Id to delete");
					if (prod.deleteProduct(sc.nextInt())) {
						System.out.println("Product deleted");
					}
					break;
				case 6:
					System.out.println("Exit");
					prod.closeConnection();
					flag = true;
					break;

				default:
					System.out.println("Enter valid choice");

				}// switch end
			} // while end

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

}
