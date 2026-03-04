package Final;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
public class crudmain {
	static private final String dburl = "jdbc:mysql://localhost:3306/add_a5";
	static private final String user = "root";
	static private final String password = "171298";
	public static void main(String[] args) {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch(ClassNotFoundException e) {
			System.out.println(e.toString());
		}
		try {
			 con = DriverManager.getConnection(dburl,user,password);
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		Scanner sc = new Scanner(System.in);
		System.out.println("Please Select Any One Choice From Below");
		System.out.println("_______________________________________");
		while(true) {
			System.out.println("1.Insert 2.Update 3.Delete 4.Read 5.Read By Id 6.Exit");
			System.out.println("Enter Choice : ");
			int choice = sc.nextInt();
			if(choice==6) {
				System.out.println("Thank you !! 🙏🙏🙏🏻");
				break;
			}
			switch(choice) {
				case 1 : Crudlogics.insert(con, sc);
						break;
				case 2 : Crudlogics.update(con, sc);
						break;
				case 3 : Crudlogics.delete(con, sc);
						break;
				case 4 : Crudlogics.read(con);
						break;
				case 5 : Crudlogics.readByItem_No(con, sc);
						break;
				default : System.out.println("Invalid choice!!!!");
						break;
			}
		}
		
	}
}
