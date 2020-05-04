package by.tms.storage;

import by.tms.domain.Student;

import java.sql.*;

public class StudentStorage {

	public boolean saveStudent (Student student){
					try {
				Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1987Roll");
				PreparedStatement preparedStatement = connection.prepareStatement("insert into studentbase values (default , ? , ? , ? , ?)");
				preparedStatement.setString ( 2, student.getLogin());
				preparedStatement.setString ( 3, student.getPassword());
				preparedStatement.setString ( 4, student.getFaculty());
				preparedStatement.setString ( 5, student.getGroup());
				preparedStatement.execute();
				System.out.println("Student add");// заменить на метод
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
					return false;
		}

	public Student getStudent (int id) {
		String login = null;
		String password = null;
		String name = null;
		String faculty = null;
		String group = null;
		try {
			Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1987Roll");
			PreparedStatement preparedStatement = connection.prepareStatement("select * from studentbase u where u.id = ?");
			preparedStatement.setInt(1, id);
			if (checkId(id)) {
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			login = resultSet.getString(2);
			password = resultSet.getString(3);
			name = resultSet.getString(4);
			faculty = resultSet.getString(5);
			group = resultSet.getString(6);
			}
			else {
				System.out.println("Incorrect Id");// заменить на метод
			}
			connection.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
			return new Student(login , password , name , faculty , group);
		}

	public void removeStudent ( int id) {
		try {
			Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1987Roll");
			PreparedStatement preparedStatement = connection.prepareStatement("delete from studentbase u where u.id = ?");
			preparedStatement.setInt(1, id);
			if (checkId(id)){
				ResultSet resultSet = preparedStatement.executeQuery();
				resultSet.next();
				String name = resultSet.getString(4);
				System.out.println("Student " + name + " remove");
			}
			else {
				System.out.println("Incorrect Id"); // заменить на метод
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateStudent (int id) {
		try {
			Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1987Roll");
			PreparedStatement preparedStatement = connection.prepareStatement("update studentbase set login = ?, password = ?, name = ?, faculty = ?, group =? where id = ?");
			preparedStatement.setInt(6, id);
			if(checkId(id)) {
				System.out.println(getStudent(id));
//				System.out.println("Update"); // заменить на метод
//				System.out.println("Enter login");// заменить на метод
//				String login = Reader.readerString();// заменить на метод
//				preparedStatement.setString(1, login);
//				Writen.writen("Enter password");
//				String password = Reader.readerString();// заменить на метод
//				preparedStatement.setString(2, password);
//				Writen.writen("Enter name");
//				String name = Reader.readerString();// заменить на метод
//				preparedStatement.setString(3, name);
//				Writen.writen("Enter faculty");
//				String faculty = Reader.readerString();// заменить на метод
//				preparedStatement.setString(4, faculty);
//				Writen.writen("Enter group");
//				String group = Reader.readerString();// заменить на метод
//				preparedStatement.setString(5, group);
//				preparedStatement.execute();
			}
			else {
				System.out.println("Incorrect Id");
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		private boolean checkId(int id) {
			try {
				Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1987Roll");
				PreparedStatement preparedStatement = connection.prepareStatement("select * from usersfromlogin u where u.id = ?");
				preparedStatement.setInt(1, id);
				ResultSet resultSet = preparedStatement.executeQuery();
				if (resultSet.next()){
					return true;
				}
				else {
					return false;
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			return false;
		}








}

