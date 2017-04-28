package com.sdi.persistence.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.sdi.dto.User;
import com.sdi.dto.UserTask;
import com.sdi.dto.types.UserStatus;
import com.sdi.persistence.UserDao;
import com.sdi.persistence.util.JdbcTemplate;
import com.sdi.persistence.util.RowMapper;

public class UserDaoJdbcImpl implements UserDao {

	public class UserMapper implements RowMapper<User> {
		@Override
		public User toObject(ResultSet rs) throws SQLException {
			User u = new  User();
				u.setId(  		rs.getLong("id") );
				u.setLogin(  	rs.getString("login") );
				u.setPassword(  	rs.getString("password") );
				u.setEmail(  	rs.getString("email") );
				u.setIsAdmin( 	rs.getBoolean("isAdmin") );
				u.setStatus(  	UserStatus.valueOf( rs.getString("status") ));
				return u;
		}
	}
	
	public class UserWithTasks implements RowMapper<UserTask> {

		@Override
		public UserTask toObject(ResultSet rs) throws SQLException {
			User u = new User();
			u.setId(rs.getLong("id"));
			u.setLogin(rs.getString("login"));
			u.setPassword(rs.getString("password"));
			u.setEmail(rs.getString("email"));
			u.setIsAdmin(rs.getBoolean("isAdmin"));
			u.setStatus(UserStatus.valueOf( rs.getString("status") ));
			
			UserTask ut = new UserTask(u, rs.getInt("completadas"), 
					rs.getInt("completadas_retrasadas"), rs.getInt("planificadas"),
					rs.getInt("sin_planificar"));
			ut.setUser(u);
			ut.setTareasCompletadas(rs.getInt("completadas"));
			ut.setTareasCompletadasRet(rs.getInt("completadas_retrasadas"));
			ut.setTareasPlanificadas(rs.getInt("planificadas"));
			ut.setTareasNoPlanificadas(rs.getInt("sin_planificar"));
			return ut;
		}
		
	}
	
	private	JdbcTemplate jdbcTemplate = new JdbcTemplate();

	@Override
	public Long save(User dto) {
		jdbcTemplate.execute("USER_INSERT", 
				dto.getLogin(), 
				dto.getPassword(), 
				dto.getEmail(),
				dto.getIsAdmin(),
				toStringOrNull( dto.getStatus() )
			);
		return jdbcTemplate.getGeneratedKey();
	}

	private String toStringOrNull(Object obj) {
		return obj == null ? null : obj.toString();
	}

	@Override
	public int update(User dto) {
		return jdbcTemplate.execute("USER_UPDATE", 
				dto.getLogin(), 
				dto.getPassword(), 
				dto.getEmail(),
				dto.getIsAdmin(),
				toStringOrNull( dto.getStatus() ),
				dto.getId()
			);
	}

	@Override
	public int delete(Long id) {
		return jdbcTemplate.execute("USER_DELETE", id);
	}

	@Override
	public User findById(Long id) {
		return jdbcTemplate.queryForObject(
				"USER_FIND_BY_ID", 
				new UserMapper(), 
				id
			);
	}

	@Override
	public List<User> findAll() {
		return jdbcTemplate.queryForList("USER_FIND_ALL", new UserMapper());
	}

	@Override
	public User findByLogin(String login) {
		return jdbcTemplate.queryForObject(
				"USER_FIND_BY_LOGIN", 
				new UserMapper(), 
				login
			);
	}

	@Override
	public User findByLoginAndPassword(String login, String password) {
		return jdbcTemplate.queryForObject(
				"USER_FIND_BY_LOGIN_AND_PASSWORD", 
				new UserMapper(), 
				login, password
			);
	}

	@Override
	public void deleteAllUser() {
		jdbcTemplate.execute("USER_DELETE_ALL");		
	}

	@Override
	public List<UserTask> findAllUsersWithTasks() {
		return jdbcTemplate.queryForList("USER_FIND_ALL_WITH_TASKS", 
				new UserWithTasks());
	}

}
