package com.example.senai.model.transport;

import java.util.Date;
import java.util.Objects;

public class AvengerDTO {

	private Long id;

	private String realName;

	private String name;

	private Date birthdayDate;

	private String superPower;

	private String status;

	private TeamDTO team;

	public AvengerDTO() {
	}

	public AvengerDTO(String name, String secretIndentity, Date birthdayDate, String hability, String status) {
		this.realName = secretIndentity;
		this.name = name;
		this.superPower = hability;
		this.status = status;
		this.birthdayDate = birthdayDate;
	}

	public TeamDTO getTeam() {
		return team;
	}

	public void setTeam(TeamDTO team) {
		this.team = team;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthdayDate() {
		return birthdayDate;
	}

	public void setBirthdayDate(Date birthdayDate) {
		this.birthdayDate = birthdayDate;
	}

	public String getSuperPower() {
		return superPower;
	}

	public void setSuperPower(String superPower) {
		this.superPower = superPower;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(realName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AvengerDTO other = (AvengerDTO) obj;
		return Objects.equals(realName, other.realName);
	}

	@Override
	public String toString() {
		return "AvengerDTO [id=" + id + ", realName=" + realName + ", name=" + name + ", birthdayDate=" + birthdayDate
				+ ", superPower=" + superPower + ", status=" + status + "]";
	}

}
