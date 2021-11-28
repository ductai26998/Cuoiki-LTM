package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bo.FileItem;
import dbconnection.DBConnection;

public class FileDAO {
	public ArrayList<FileItem> getAllFile() {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		ArrayList<FileItem> files = new ArrayList<FileItem>();

		try {
			connection = DBConnection.getConnection();
			String sql = "SELECT * FROM files";
			statement = connection.createStatement();

			resultSet = statement.executeQuery(sql);

			// todo
			while (resultSet.next()) {
				files.add(ConvertToFile(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return files;
	}

	private static FileItem ConvertToFile(ResultSet rs) throws SQLException {
		FileItem file = new FileItem();

		file.setId(rs.getInt(1));
		file.setName(rs.getString(2));
		file.setType(rs.getString(3));
		file.setSize(rs.getFloat(4));
		file.setUrl(rs.getString(5));
		return file;
	}

	public void addFile(ArrayList<String> fileInfo) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = DBConnection.getConnection();
			String insertSQL = "INSERT INTO files (name, type, size, url)" + "VALUES (?, ?, ?, ?)";
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, fileInfo.get(0));
			preparedStatement.setString(2, fileInfo.get(1));
			preparedStatement.setFloat(3, Integer.parseInt(fileInfo.get(2)));
			preparedStatement.setString(4, fileInfo.get(3));

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
