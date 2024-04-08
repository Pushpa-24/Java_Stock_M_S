import java.sql.ResultSet;

class User{
    private static String currentUser;

    public static void loadUser() {
        try {
            Con con = new Con();
            String q = "SELECT Username FROM users";
            ResultSet rs = con.statement.executeQuery(q);
            if (rs.next()) {
                currentUser = rs.getString("Username");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getCurrentUser() {
        return currentUser;
    }
}

