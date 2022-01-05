package lab2.date15;

import java.sql.*;
import java.util.Scanner;

public class Customer {
	Connection con=null;
	Statement stmt=null;
	PreparedStatement pstmt=null;
	Scanner sc=new Scanner(System.in);
	
	public void getConnection(){
		try {
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db2","root","root");
			stmt=con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void search_flight_details() {
		try {
			System.out.println("Enter source and destination:");
			System.out.println("Enter source: ");
			String a=sc.next();
			
			System.out.println("Enter Destination: ");
			String b=sc.next();
			
			String query="select * from flight_details where source='"+a+"' and destination='"+b+"'";
			ResultSet rs=stmt.executeQuery(query);
			System.out.println("ID\t\tSource\t\tDestination\t\tService_Provider\tTicket_Price\tAvailable_Ticket");
			while(rs.next()) {
				System.out.println(rs.getInt(1)+"\t\t"+rs.getString(2)+"\t\t"+rs.getString(3)+"\t\t"+rs.getString(4)+"\t\t"+rs.getInt(5)+"\t\t\t"+rs.getInt(6));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void Book_flight_ticket() {
		try {
			System.out.println("Enter source and destination:");
			System.out.println("Enter source: ");
			String a=sc.next();
			
			System.out.println("Enter Destination: ");
			String b=sc.next();
			
			String query="select * from flight_details where source='"+a+"' and destination='"+b+"'";
			ResultSet rs=stmt.executeQuery(query);
			System.out.println("ID\t\tSource\t\tDestination\t\tService_Provider\tTicket_Price\tAvailable_Ticket");
			while(rs.next()) {
				System.out.println(rs.getInt(1)+"\t\t"+rs.getString(2)+"\t\t"+rs.getString(3)+"\t\t"+rs.getString(4)+"\t\t"+rs.getInt(5)+"\t\t\t"+rs.getInt(6));
			}
			
			System.out.println("Enter Service Provider: ");
			String c=sc.next();
			System.out.println("Enter no of ticket book: ");
			int d=sc.nextInt();
			
			String query2="select available_ticket from flight_details where source='"+a+"' and destination='"+b+"' and Service_Provider='"+c+"'";
			ResultSet rs1=stmt.executeQuery(query2);
			while(rs1.next()) {
			if(rs1.getInt(1)>=d ) {
				
			pstmt=con.prepareStatement("update flight_details set Available_Ticket=available_ticket-? where source='"+a+"' and destination='"+b+"' and Service_Provider='"+c+"'");
			pstmt.setInt(1, d);
			
			pstmt.executeUpdate();
			System.out.println("Ticket booked successful: ");
			}
			else
			{
				System.err.println("Ticket Unavailable");
			}
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
