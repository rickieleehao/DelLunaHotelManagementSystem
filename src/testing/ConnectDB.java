package testing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectDB {
	public static void main(String[] args) {
		String databaseURL = "jdbc:ucanaccess://HotelDatabase.accdb";

		try {
			Connection conn = DriverManager.getConnection(databaseURL);
			System.out.println("Connected to MS Access database.");

//			addClientProfile(conn);
			addPayment(conn);
			addBooking(conn);

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public static void addClientProfile(Connection conn) {

		try {
			String sql = "INSERT INTO ClientProfile (NRIC, firstName, lastName, gender, address) VALUES "
					+ "(?, ?, ?, ?, ?)";

			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, "960325565623");
			pst.setString(2, "Lee");
			pst.setString(3, "Hao");
			pst.setString(4, "Male");
			pst.setString(5, "Ampang");
			int rows = pst.executeUpdate();

			if (rows > 0) {
				System.out.println("A new client profile has been added.");
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public static void addBooking(Connection conn) {
		try {
			String sql = "INSERT INTO Booking (ClientProfileID, checkInDate, checkOutDate, roomID, numOfGuest, paymentID, status) VALUES "
					+ "(?, ?, ?, ?, ?, ?, ?)";

//			String sDate1 = "12/15/2020";
//			Date date1 = new SimpleDateFormat("MM/dd/yyyy").parse(sDate1);
//			String sDate2 = "12/20/2020";
//			Date date2 = new SimpleDateFormat("MM/dd/yyyy").parse(sDate2);

			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, "C-001");
			pst.setString(2, "12/15/2020");
			pst.setString(3, "12/20/2020");
			pst.setString(4, "R001");
			pst.setString(5, "1");
			pst.setString(6, "P-001");
			pst.setString(7, "CheckedOut");
			int rows = pst.executeUpdate();

			if (rows > 0) {
				System.out.println("A new booking has been added.");
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public static void addPayment(Connection conn) {
		try {
			String sql = "INSERT INTO Payment (totalPrice, deposit, paymentMethod, cardNumber) VALUES "
					+ "(?, ?, ?, ?)";

			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, "100");
			pst.setString(2, "15");
			pst.setString(3, "PaymentMethod.Cash");
			pst.setString(4, "1111222233334444");
			int rows = pst.executeUpdate();

			if (rows > 0) {
				System.out.println("A new payment record has been added.");
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
