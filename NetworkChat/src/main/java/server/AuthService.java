package server;

import java.sql.*;

public class AuthService {
    private static Connection connection;
    private static Statement stmt;

    static void connection() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:mainDB.db");
            stmt = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setNewUsers(String login, String pass, String nick) {
        connection();
        int hash = pass.hashCode();
        String sql = "INSERT INTO users (login, password, nickname) Values (?, ?, ?)";

        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, login);
            pstmt.setInt(2, hash);
            pstmt.setString(3, nick);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }



    static int getIdByNick(String _nick) {
        String idNick = String.format("SELECT count(id) FROM users where nickname = '%s'", _nick);
        try {
            ResultSet rs = stmt.executeQuery(idNick);

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


    static String getNickByLoginAndPass(String login, String pass) {
        String sql = String.format("SELECT nickname FROM users where login = '%s' and password = '%s'", login, pass);

        try {
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                String str = rs.getString(1);
                return rs.getString(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

        public static void changeNickname(String oldNick, String newNick) throws SQLException {
        connection();

        String sql = "UPDATE users " +
                "  SET nickname = ? " +
                "WHERE nickname = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        try {
            pstmt.setString(1, newNick);
            pstmt.setString(2, oldNick);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
    }


    static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
