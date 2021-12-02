package com.example.senai.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Repository;

import com.example.senai.model.transport.AvengerDTO;
import com.example.senai.model.transport.TeamDTO;

@Repository
public class TeamDAO {

	private Connection connection;

	public TeamDAO(Connection connection) {
		this.connection = connection;
	}

	public void addAvenger(TeamDTO team, AvengerDTO avenger) throws SQLException {
		try (PreparedStatement pStmt = connection
				.prepareStatement("INSERT INTO heroes_allows_team (id_hero, id_team)" + " VALUES(?, ?)")) {
			pStmt.setLong(1, avenger.getId());
			pStmt.setLong(2, team.getId());
			pStmt.execute();
		}
	}

	public TeamDTO create(TeamDTO team) throws SQLException {
		try (PreparedStatement pStmt = connection.prepareStatement("INSERT INTO team (name) VALUES(?)",
				Statement.RETURN_GENERATED_KEYS)) {
			pStmt.setString(1, team.getName());
			pStmt.execute();
			ResultSet resultSet = pStmt.getGeneratedKeys();
			while (resultSet.next()) {
				Long id = resultSet.getLong(1);
				team.setId(id);
			}
			return team;
		}
	}
}
