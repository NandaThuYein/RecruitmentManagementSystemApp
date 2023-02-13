package com.project.demo.service;

import static com.project.demo.utils.codeGenerator.*;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.demo.entity.Team;
import com.project.demo.repository.TeamRepository;

@Service
public class TeamServiceImpl implements TeamService {

	@Autowired
	TeamRepository teamRepo;

	@Override
	public Team createTeam(Team team) {
		Team t = teamRepo.save(team);
		t = teamRepo.findById(t.getTeamId()).get();
		t.setTeamCode(generateTeamCode(t.getTeamId()));
		return teamRepo.save(t);
	}

	@Override
	public Team updateTeam(Team team) {
		Team t = teamRepo.findById(team.getTeamId()).get();
		t.setTeamName(team.getTeamName());
		t.setDepartment(team.getDepartment());
		return teamRepo.save(t);
	}

	@Override
	public void deleteTeam(Team team) {
		teamRepo.deleteById(team.getTeamId());
	}

	@Override
	public List<Team> getAllTeams() {
		return teamRepo.findAll();
	}

	@Override
	public Team getLastTeam() {
		return teamRepo.findLastTeam();
	}

	@Override
	public List<Team> searchTeam(String code, String name) {
		return teamRepo.searchTeam("%" + code + "%", "%" + name + "%");
	}
}
