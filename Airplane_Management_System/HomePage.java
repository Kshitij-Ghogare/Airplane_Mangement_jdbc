package lab2.date15;

import java.sql.*;
import java.util.Scanner;

public class HomePage {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("welcome");
		Scanner sc=new Scanner(System.in);
		System.out.println("Select if you are Admin:1 or Customer:2");
		int choice=sc.nextInt();
		
		switch(choice){
			case 1:
				try {
					Connection con1= DriverManager.getConnection("jdbc:mysql://localhost:3306/db2","root","root");
					Statement stmt=con1.createStatement();
					
					ResultSet rs=stmt.executeQuery("select * from login_detail");
				
					System.out.println("Enter Username:");
					String a=sc.next();
					
					System.out.println("Enter Password:");
					String b=sc.next();
					
					while(rs.next()) {
					if(rs.getString("user_name").equals(a) && rs.getString("password").equals(b)) 
					{
						System.out.println("You Can Proceed");
						
						Admin admin=new Admin();
						admin.getConnection();
						
						System.out.println("Select Option to perform a task");
						System.out.println("1: Insert Flight Details\n2: Update Flight Details\n3:Delete Flight Details\n4: View Flight Details");
						int adm_choice=sc.nextInt();
						switch(adm_choice)
						{
						case 1:
							admin.insert_fight_details();
							break;
						case 2:
							admin.update_flight_details();
							break;
						case 3:
							admin.delete_flight_record();
							break;
						case 4:
							admin.view_flight_details();
							break;
						default:
							System.out.println("Enter valid choice");
						}
					}
					else {
						System.err.println("Wrong Username or Password");
					}}
					sc.close();
					stmt.close();
					con1.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 2:
				System.out.println("You Can Proceed");
				System.out.println("Select Option to perform a task");
				
				Customer cus=new Customer();
				cus.getConnection();
				
				System.out.println("Press\n1: Search Flight\n2: Book Flight");
				int cus_choice=sc.nextInt();
				
				switch(cus_choice) {
				
				case 1:
					cus.search_flight_details();
					break;
				case 2:
					cus.Book_flight_ticket();
					break;
				default:
					System.out.println("Enter valid choice");
				}
				
			}
	}

}
