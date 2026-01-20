import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Modelo {
	
	/** 
	 * MISMO CÓDIGO QUE EN LA ACTIVIDAD ANTERIOR 
	 * */

	private Connection connection;
	private final String usuario = "root";
	private final String clave = "abc123.";
	private final String url ="jdbc:mysql://localhost:3306/school";
	
	public Modelo() {
		openConnection();
	}
	
	private void openConnection() {
		try {
			this.connection = DriverManager.getConnection(url, usuario, clave);
		}catch(SQLException sqle) {
			System.out.println("Error al abrir la conexión");
		}
	}
	
	public void closeConnection() {
		try {
			this.connection.close();
		}catch(SQLException sqle) {
			System.out.println("Error al cerrar la conexión");
		}
	}
	
	public boolean addStudent(Student student) {
		try {
			PreparedStatement ps = this.connection.prepareStatement("INSERT INTO student VALUES (?, ?, ?, ?)");
			
			ps.setString(1, student.getId());
			ps.setString(2, student.getName());
			ps.setString(3, student.getSurname());
			ps.setInt(4, student.getAge());
			
			return (ps.executeUpdate() > 0);
			
		} catch (SQLException e) {
			e.printStackTrace();
			closeConnection();
			return false;
		}
		
	}
	
	public Student getStudent(String id) {
		try {
			PreparedStatement ps = this.connection.prepareStatement("SELECT * FROM student WHERE id = ?");
			
			ps.setString(1, id);
			
			ResultSet rs = ps.executeQuery();			
			Student st = null; 
			while(rs.next())
				st = new Student(rs.getString("id"), rs.getString("name"), rs.getString("surname"),rs.getInt("age"));
			
			return st;
			
		} catch (SQLException e) {
			e.printStackTrace();
			closeConnection();
			return new Student();
		}
	}
	
	public boolean deleteStudent(String id) {
		try {
			PreparedStatement ps = this.connection.prepareStatement("DELETE FROM student WHERE id = ?");
			
			ps.setString(1, id);
			
			return (ps.executeUpdate() > 0);
			
		} catch (SQLException e) {
			e.printStackTrace();
			closeConnection();
			return false;
		}
	}
	
	public boolean modifyStudent(Student student) {
		try {
			PreparedStatement ps = this.connection.prepareStatement("UPDATE student set id = ?, name = ?,"
					+ "surname = ?, age = ? where id = ?");
			
			ps.setString(1, student.getId());
			ps.setString(2, student.getName());
			ps.setString(3, student.getSurname());
			ps.setInt(4, student.getAge());
			ps.setString(5, student.getId());
			
			return (ps.executeUpdate() > 0);
			
		} catch (SQLException e) {
			e.printStackTrace();
			closeConnection();
			return false;
		}
	}
	
	public ArrayList<Student> getStudentsList() {
		try {
			PreparedStatement ps = this.connection.prepareStatement("SELECT * FROM student");
			ResultSet rs = ps.executeQuery();
			
			ArrayList<Student> listaEstudiantes = new ArrayList<Student>();
			while(rs.next()) {
				listaEstudiantes.add(new Student(rs.getString("id"), rs.getString("name"),
						rs.getString("surname"),rs.getInt("age")));
			}
			
			return listaEstudiantes;
			
		} catch (SQLException e) {
			e.printStackTrace();
			closeConnection();
			return new ArrayList<Student>();
		}
	}
}
