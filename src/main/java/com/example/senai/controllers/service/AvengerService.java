package com.example.senai.controllers.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.senai.dao.TeamDAO;
import com.example.senai.exceptions.AvengersNotFoundExcetion;
import com.example.senai.model.Avenger;
import com.example.senai.model.transport.AvengerDTO;
import com.example.senai.model.transport.AvengerNameProjection;
import com.example.senai.repository.AvengerRepository;

@Service
public class AvengerService {

	private AvengerRepository avengerRepository;

	private TeamDAO teamDAO;

	public AvengerService(AvengerRepository avengerRepository, TeamDAO teamDAO) {
		this.avengerRepository = avengerRepository;
		this.teamDAO = teamDAO;
	}

	public List<String> listOldAvengers() throws AvengersNotFoundExcetion {
		List<String> listOldAvengers = null;

		if (listOldAvengers.isEmpty()) {
			System.out.println("Não foram encontrados Vingadores no Banco!");
			throw new AvengersNotFoundExcetion();
		}
		return listOldAvengers;
	}

	@Transactional(readOnly = false)
	public AvengerDTO create(AvengerDTO avenger) throws SQLException {
		if (avenger == null) {
			throw new IllegalArgumentException("O Avenger está nulo!");
		}
		Avenger savedAvanger = avengerRepository.save(new Avenger(avenger));

//		try (Connection connection = new JDBCConfig().getConnection()) {
//			try {
//				connection.setAutoCommit(false);
//				AvengerDTO avengerCreated = new AvengerDAO(connection).create(avenger);
//				TeamDAO teamDAO = new TeamDAO(connection);
//				TeamDTO teamCreated = teamDAO.create(avenger.getTeam());
//				teamDAO.addAvenger(teamCreated, avengerCreated);
//				connection.commit();
//			} catch (SQLException e) {
//				connection.rollback();
//				throw e;
//			}
//		}

		return new AvengerDTO(savedAvanger);
	}

	public List<AvengerNameProjection> listAvengers() throws SQLException {
		return this.avengerRepository.findAllName();
	}

	public AvengerDTO getById(Long id) throws SQLException {
		if (id == null) {
			throw new IllegalArgumentException("O Id não pode ser Nulo!");
		}
		Optional<Avenger> avenger = avengerRepository.findById(id);

		if (avenger.isPresent()) {
			return new AvengerDTO(avenger.get());
		}
		return null;
	}

	public List<AvengerDTO> getAvengersByFilter(String name) throws SQLException {
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("O nome não pode ser Vazio!");
		}

		List<AvengerDTO> avengers = avengerRepository.findDTOByName(name);
		if (!avengers.isEmpty()) {
			return avengers;
		}

		return new ArrayList<>();
	}
}
