package com.example.senai.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.example.senai.model.transport.AvengerDTO;

/**
 * User AvengerRepository
 *
 */
@Deprecated
public class AvengerDAO {

	Connection connection;

	public AvengerDAO(Connection connection) {
		this.connection = connection;
	}

	public List<String> listOldAvengers() {
		return null;
	}

	public AvengerDTO create(AvengerDTO avenger) throws SQLException {
		try (PreparedStatement pStmt = connection.prepareStatement(
				"INSERT INTO hero (name, secret_indentity, " + "birthday_date, hability, status) VALUES(?, ?, ?, ?, ?)",
				Statement.RETURN_GENERATED_KEYS)) {

			pStmt.setString(1, avenger.getName());
			pStmt.setString(2, avenger.getRealName());
			pStmt.setDate(3, new java.sql.Date(avenger.getBirthdayDate().getTime()));
			pStmt.setString(4, avenger.getSuperPower());
			pStmt.setString(5, avenger.getStatus());
			pStmt.execute();

			ResultSet resultSet = pStmt.getGeneratedKeys();
			while (resultSet.next()) {
				Long id = resultSet.getLong(1);
				avenger.setId(id);
			}
			return avenger;
		}

	}

	public List<String> listAvengersNames() throws SQLException {
		try (Statement stmt = connection.createStatement()) {
			stmt.execute("SELECT name FROM hero");
			ResultSet resultSet = stmt.getResultSet();
			List<String> names = new ArrayList<>();
			while (resultSet.next()) {
				String name = resultSet.getString("name");
				names.add(name);
			}
			return names;
		}
	}

	public List<AvengerDTO> listAvengers() throws SQLException {
		try (Statement stmt = connection.createStatement()) {
			stmt.execute("SELECT * FROM hero");
			ResultSet resultSet = stmt.getResultSet();
			List<AvengerDTO> avengers = new ArrayList<>();
			while (resultSet.next()) {
				AvengerDTO avenger = extractedAvenger(resultSet);
				avengers.add(avenger);
			}
			return avengers;
		}
	}

	public Optional<AvengerDTO> findById(Long id) throws SQLException {
		try (PreparedStatement pStmt = connection.prepareStatement("SELECT * FROM hero WHERE id=?")) {
			pStmt.setLong(1, id);
			pStmt.execute();
			ResultSet resultSet = pStmt.getResultSet();
			AvengerDTO avenger = null;
			while (resultSet.next()) {
				avenger = extractedAvenger(resultSet);
			}
			return Optional.ofNullable(avenger);
		}
	}

	public List<AvengerDTO> getAvengersByFilter(String name) throws SQLException {
		try (PreparedStatement pStmt = 
				connection.prepareStatement("SELECT * FROM hero WHERE name LIKE ?")) {
			pStmt.setString(1, name + "%");
			pStmt.execute();
			ResultSet resultSet = pStmt.getResultSet();
			List<AvengerDTO> avengers = new ArrayList<>();
			while (resultSet.next()) {
				avengers.add(extractedAvenger(resultSet));
			}
			return avengers;
		}
	}

	private AvengerDTO extractedAvenger(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("id");
		String name = resultSet.getString("name");
		String secretIndentity = resultSet.getString("secret_indentity");
		Date birthdayDate = resultSet.getDate("birthday_date");
		String hability = resultSet.getString("hability");
		String status = resultSet.getString("status");

		AvengerDTO avenger = new AvengerDTO(name, secretIndentity, birthdayDate, hability, status);
		avenger.setId(id);
		return avenger;
	}
}
