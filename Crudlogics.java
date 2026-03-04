package Final;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
public class Crudlogics {
	public static void insert(Connection con,Scanner sc) {
		System.out.print("Enter Item_No : ");
		int item_no = sc.nextInt();
		sc.nextLine();
		System.out.print("Enter Item_Name : ");
		String item_name = sc.nextLine();
		System.out.print("Enter Item_Quantity : ");
		String item_quantity = sc.nextLine();
		System.out.print("Enter Item_Price : ");
		String item_price = sc.nextLine();
		System.out.print("Enter Item_Expiry_Date : ");
		String item_ed = sc.nextLine();
		try {
			String query ="INSERT INTO STORE(ITEM_NO,ITEM_NAME,ITEM_QUANTITY,ITEM_PRICE,ITEM_ED) VALUES("
				       +"?,?,?,?,?)"; /* Issue the Query */
			PreparedStatement psmt = con.prepareStatement(query); /* create statement */ 
			psmt.setInt(1,item_no);
			psmt.setString(2,item_name);
			psmt.setString(3,item_quantity);
			psmt.setString(4,item_price);
			psmt.setString(5,item_ed);
			int status = psmt.executeUpdate(); /* execute query */
			if(status>0) {  /* process the query */
				System.out.println("Data inserted!!! 👌👌");
				System.out.println("Thank you !! 🙏🙏🙏🏻");
			} else {
				System.out.println("try again!! 👎👎");
			}
			con.close();
		} catch(Exception e) {
			System.out.println(e.toString());
		}
	}
	public static void update(Connection con,Scanner sc) {
		System.out.println("Enter Item Name : ");
		sc.nextLine();
		String item_name = sc.nextLine();
		System.out.println("Enter Item Price");
		String item_price = sc.nextLine();
		System.out.println("Enter Item Expiry Date : ");
		String item_ed = sc.nextLine();
		System.out.println("Enter Item Number to Update : ");
		int item_no = sc.nextInt();
		try{
			String query ="UPDATE STORE SET ITEM_NAME=?,ITEM_PRICE=?,ITEM_ED=? WHERE ITEM_NO=?"; 
			PreparedStatement psmt = con.prepareStatement(query); /* create statement */
			psmt.setString(1,item_name); /* setting values */
			psmt.setString(2,item_price);
			psmt.setString(3,item_ed);
			psmt.setInt(4, item_no);
			int status = psmt.executeUpdate(); /* execute query */
			if(status!=0) { /* process the query */
				System.out.println("update completed!!!!");
				System.out.println("Thank you !! 🙏🙏🙏🏻");
			}else {
				System.out.println("invalid item_no : "+item_no);
			}
			con.close();
		}catch(Exception e) {
			System.out.println(e.toString());
		}
	} 
	public static void delete(Connection con,Scanner sc) {
		System.out.println("Enter Item_No : ");
		int item_no = sc.nextInt();
		try {
			String query = "DELETE FROM STORE WHERE ITEM_NO=?"; 
			PreparedStatement psmt = con.prepareStatement(query); 
			psmt.setInt(1,item_no);
			int status = psmt.executeUpdate();
			if(status>0) { 
				System.out.println("Data Deleted!!!");
				System.out.println("Thank you !! 🙏🙏🙏🏻");
			} else {
				System.out.println("try again!!!");
			}
		}catch(Exception e) {
			System.out.println(e.toString());
		}
	}
	public static void read(Connection con) {
		try {
			String query = "SELECT * FROM STORE"; /* issue the query */
			Statement stmt = con.createStatement(); /* create statement */
			ResultSet rs = stmt.executeQuery(query); /* execute query */
			System.out.println("Item_No\tItem_Name   Item_Quantity  Item_price   Item_ED ");
			System.out.println("<---------------------------------------------------------->");
			while(rs.next()) { /* process the query */
				int no = rs.getInt(1);   /* returns item_no */
				String item_name = rs.getString(2); /* returns item_name */
				String item_quantity = rs.getString(3); /* returns item_quantity */
				String item_price = rs.getString(4); /* returns item_price */
				String item_ed = rs.getString(5); /* returns item_ed */
				System.out.println(no+"\t "+item_name+"\t\t"+item_quantity+"\t "+item_price+"\t\t"+item_ed);
			}
			System.out.println("<---------------------------------------------------------->");
		} catch(Exception e) {
			System.out.println(e.toString());
		}
	}
	public static void readByItem_No(Connection con,Scanner sc) {
		System.out.println("Enter Item_no : ");
		int item_no = sc.nextInt();
		try {
			String query = "SELECT * FROM STORE WHERE ITEM_NO = ? "; /* Issue Query */
			PreparedStatement psmt = con.prepareStatement(query); /* create Statement */
			psmt.setInt(1,item_no); /* setting value for place holders */
			ResultSet rs = psmt.executeQuery(); /* execute query */
			System.out.println("Item_No\t Item_Name \t Item_Quantity\t Item_Price\tItem_ED");
			System.out.println("-------------------------------------------------------------------");
			if(rs.next()) { /* process the query */
				int item = rs.getInt(1);
				String item_name = rs.getString(2);
				String item_quantity = rs.getString(3);
				String item_price = rs.getString(4);
				String item_ed = rs.getString(5);
				System.out.println(item+"\t"+item_name+"\t"+item_quantity+"\t"+item_price+"\t"+item_ed);
			} else {
				System.out.println("Invalid Item_No : "+item_no);
			}
		}catch(Exception e) {
			System.out.println(e.toString());
		}
	}
}
