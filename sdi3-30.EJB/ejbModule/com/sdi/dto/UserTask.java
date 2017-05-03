package com.sdi.dto;

import java.io.Serializable;

import com.sdi.dto.types.UserStatus;

public class UserTask implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;
	private Long id;
	private String login;
	private String password;
	private String email;
	private boolean isAdmin;
	private UserStatus status;
	private int tareasCompletadas;
	private int tareasCompletadasRet;
	private int tareasPlanificadas;
	private int tareasNoPlanificadas;
	
	public UserTask() {
	}
	
	public UserTask(User user, int tareasCompletadas, int tareasCompletadasRet,
			int tareasPlanificadas, int tareasNoPlanificadas) {
		super();
		this.id = user.getId();
		this.login = user.getLogin();
		this.password = user.getPassword();
		this.email = user.getEmail();
		this.isAdmin = user.getIsAdmin();
		this.status = user.getStatus();
		this.tareasCompletadas = tareasCompletadas;
		this.tareasCompletadasRet = tareasCompletadasRet;
		this.tareasPlanificadas = tareasPlanificadas;
		this.tareasNoPlanificadas = tareasNoPlanificadas;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public int getTareasCompletadas() {
		return tareasCompletadas;
	}
	public void setTareasCompletadas(int tareasCompletadas) {
		this.tareasCompletadas = tareasCompletadas;
	}
	public int getTareasCompletadasRet() {
		return tareasCompletadasRet;
	}
	public void setTareasCompletadasRet(int tareasCompletadasRet) {
		this.tareasCompletadasRet = tareasCompletadasRet;
	}
	public int getTareasPlanificadas() {
		return tareasPlanificadas;
	}
	public void setTareasPlanificadas(int tareasPlanificadas) {
		this.tareasPlanificadas = tareasPlanificadas;
	}
	public int getTareasNoPlanificadas() {
		return tareasNoPlanificadas;
	}
	public void setTareasNoPlanificadas(int tareasNoPlanificadas) {
		this.tareasNoPlanificadas = tareasNoPlanificadas;
	}
}
