package lab2.date15;

import java.sql.*;
import java.util.Scanner;

public class Admin {
	Connection con=null;
	Statement stmt=null;
	PreparedStatement pstmt=null;
	Scanner sc=new Scanner(System.in);
	public  void getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db2","root","root");
			 stmt = con.createStatement();
			
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void insert_fight_details() {
	
	try {
		
		 System.out.println("Enter Source,Destination,Service_Provider,Ticket_price,Available_Ticket");
		 System.out.println("Enter no of records to insert");
		 int records=sc.nextInt();
		 
		 for(int i=1;i<=records;i++) {
			  
		 System.out.println("Source: "); 
		 String a=sc.next();
		 System.out.println("Destination: "); 
		 String b=sc.next();
		 System.out.println("Service_Provider: "); 
		 String c=sc.next();
		 System.out.println("Ticket_Price: "); 
		 int d=sc.nextInt();
		 System.out.println("Available_Ticket: "); 
		 int e=sc.nextInt(); 
		
		 String quiry="Insert into flight_details (Source,Destination,Service_Provider,Ticket_price,Available_Ticket) values('"+a+"','"+b+"','"+c+"',"+d+","+e+")";
		 
		 int row=stmt.executeUpdate(quiry);
		 System.out.println(row+"Row inserted successfully..."); 
		 
		 }
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	

	}
	
	public void update_flight_details() {
		
		try {
			System.out.println("Source: "); 
			 String a=sc.next();
			 System.out.println("Destination: "); 
			 String b=sc.next();
			 System.out.println("Service_Provider: "); 
			 String c=sc.next();
			 System.out.println("Ticket_Price: "); 
			 int d=sc.nextInt();
			 System.out.println("Available_Ticket: "); 
			 int e=sc.nextInt();
			PreparedStatement pstmt=con.prepareStatement("update flight_details set source=?,destination=?,Service_provider=?,Ticket_Price=?,Available_Ticket=? where Flight_ID=?");
			pstmt.setString(1, a);
			pstmt.setString(2, b);
			pstmt.setString(1, c);
			pstmt.setInt(1, d);
			pstmt.setInt(1, e);
			
			int row = pstmt.executeUpdate();
			System.out.println(row+"row deleted...");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void delete_flight_record() {
		try {
			PreparedStatement pstmt=con.prepareStatement("delete from flight_details where Flight_id=?");
			System.out.println("enter id");
			int id= sc.nextInt();
			pstmt.setInt(1, id);
				
			int row1 = pstmt.executeUpdate();
			System.out.println(row1+"row deleted...");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void view_flight_details() {
		try {
			PreparedStatement pstmt=con.prepareStatement("select * from Flight_details");
			ResultSet rs=pstmt.executeQuery();
			System.out.println("ID\t\tSource\t\tDestination\t\tService_Provider\tTicket_Price\tAvailable_Ticket");
			while(rs.next()) {
			System.out.println(rs.getInt(1)+"\t\t"+rs.getString(2)+"\t\t"+rs.getString(3)+"\t\t"+rs.getString(4)+"\t\t"+rs.getInt(5)+"\t\t\t"+rs.getInt(6));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void freeresources() {
	try {
		if(con != null) {
			con.close();}
		if(stmt != null) {
			stmt.close();}
		if(pstmt != null) {
			pstmt.close();}	
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
}
