package MyPackage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
  private static Connection getConnection() {
    Connection con = null;
    try {
      Class.forName("com.mysql.jdbc.Driver");
      con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tin", "root", "root");

    } catch (Exception e) {
      System.out.println(e);
    }
    return con;
  }

  public static int addUser(UserBean u) {
    int status = 0;
    try {
      Connection con = getConnection();
      PreparedStatement stmt = con.prepareStatement("insert into USER (name,password,email) values (?,?,?)");
      stmt.setString(1, u.getName());
      stmt.setString(2, u.getPassword());
      stmt.setString(3, u.getEmail());
      status = stmt.executeUpdate();
    } catch (Exception e) {
      System.out.println(e);
    }
    return status;
  }

  public static List<UserBean> getUsers() 
  {
    ResultSet resultSet = null;

    try 
    {
      Connection con = getConnection();
      PreparedStatement stmt = con.prepareStatement("select * from USER");
      resultSet = stmt.executeQuery();
    } 
    catch (Exception e) 
    {
      System.out.println(e);
    }
    List<UserBean> li = new ArrayList<>();

    try 
    {
      while (resultSet.next()) 
      {
        UserBean user = new UserBean();
        user.setId(resultSet.getInt(1));
        user.setName(resultSet.getString(2));
        user.setEmail(resultSet.getString(4));
        li.add(user);
      }
    } 
    catch (SQLException e) 
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  return li;
  }
  public static String getUser(String email, String password)
  {
    ResultSet resultSet = null;
    List<UserBean> li = new ArrayList<>();

    try 
    {
      Connection con = getConnection();
      PreparedStatement stmt = con.prepareStatement("select * from USER where email=? and password=? ");
                                                    stmt.setString(1, email);
                                                    stmt.setString(2, password);
      resultSet = stmt.executeQuery();
    } 
    catch (Exception e) 
    {
      System.out.println(e);
    }
    try 
    {
      while (resultSet.next()) 
      {
        UserBean user = new UserBean();
        user.setName(resultSet.getString(2));
        li.add(user);
      }
    }
    catch (SQLException e) 
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    if(li.size() > 0)
    return li.get(0).getName();
    else
    return "";
  }
}
