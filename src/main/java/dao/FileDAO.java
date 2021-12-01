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
	public ArrayList<FileItem> getAllFileOfMe(String userId) {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		ArrayList<FileItem> files = new ArrayList<FileItem>();

		try {
			connection = DBConnection.getConnection();
			String insertSQL = "SELECT * FROM files WHERE userId = ?";
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, userId);
			resultSet = preparedStatement.executeQuery();

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

	public void addFile(ArrayList<String> fileInfo, String userId) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = DBConnection.getConnection();
			String insertSQL = "INSERT INTO files (name, type, size, url, userId)" + "VALUES (?, ?, ?, ?, ?)";
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, fileInfo.get(0));
			preparedStatement.setString(2, fileInfo.get(1));
			preparedStatement.setFloat(3, Integer.parseInt(fileInfo.get(2)));
			preparedStatement.setString(4, fileInfo.get(3));
			preparedStatement.setString(5, userId);

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteFile(int id) {
		Connection connection = null;
		Statement statement = null;

		try {
			connection = DBConnection.getConnection();
			String sql = "DELETE FROM files WHERE id=" + id;
			statement = connection.createStatement();
			statement.executeUpdate(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
