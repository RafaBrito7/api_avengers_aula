package com.example.senai.controllers.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.senai.config.JDBCConfig;
import com.example.senai.dao.AvengerDAO;
import com.example.senai.dao.TeamDAO;
import com.example.senai.exceptions.AvengersNotFoundExcetion;
import com.example.senai.model.transport.AvengerDTO;
import com.example.senai.model.transport.TeamDTO;

@Service
public class AvengerService {

	private AvengerDAO avengerDAO;
	private TeamDAO teamDAO;

	public AvengerService(AvengerDAO avengerDAO, TeamDAO teamDAO) {
		this.avengerDAO = avengerDAO;
		this.teamDAO = teamDAO;
	}

	public List<String> listOldAvengers() throws AvengersNotFoundExcetion {
		List<String> listOldAvengers = this.avengerDAO.listOldAvengers();

		if (listOldAvengers.isEmpty()) {
			System.out.println("Não foram encontrados Vingadores no Banco!");
			throw new AvengersNotFoundExcetion();
		}
		return listOldAvengers;
	}

	public AvengerDTO create(AvengerDTO avenger) throws SQLException {
		if (avenger == null) {
			throw new IllegalArgumentException("O Avenger está nulo!");
		}
		try (Connection connection = new JDBCConfig().getConnection()) {
			try {
				connection.setAutoCommit(false);
				AvengerDTO avengerCreated = new AvengerDAO(connection).create(avenger);
				TeamDAO teamDAO = new TeamDAO(connection);
				TeamDTO teamCreated = teamDAO.create(avenger.getTeam());
				teamDAO.addAvenger(teamCreated, avengerCreated);
				connection.commit();
				return avengerCreated;
			} catch (SQLException e) {
				connection.rollback();
				throw e;
			}
		}

	}

	public List<String> listAvengers() throws SQLException {
		return this.avengerDAO.listAvengersNames();
	}

	public AvengerDTO getById(Long id) throws SQLException {
		if (id == null) {
			throw new IllegalArgumentException("O Id não pode ser Nulo!");
		}
		Optional<AvengerDTO> avenger = avengerDAO.findById(id);

		if (avenger.isPresent()) {
			return avenger.get();
		}
		return null;
	}

	public List<AvengerDTO> getAvengersByFilter(String name) throws SQLException {
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("O nome não pode ser Vazio!");
		}

		List<AvengerDTO> avengersFilted = new ArrayList<>();
		avengersFilted = this.avengerDAO.getAvengersByFilter(name);
		if (!avengersFilted.isEmpty()) {
			return avengersFilted;
		}

		return avengersFilted;
	}
}
