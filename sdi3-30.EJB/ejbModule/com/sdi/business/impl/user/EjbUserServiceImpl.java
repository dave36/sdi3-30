package com.sdi.business.impl.user;

import javax.ejb.Stateless;
import javax.jws.WebService;

import com.sdi.business.exception.BusinessException;
import com.sdi.business.impl.user.command.FindLoggableUSerCommand;
import com.sdi.business.impl.user.command.FindRepeatUserCommand;
import com.sdi.business.impl.user.command.RegisterUserCommand;
import com.sdi.business.impl.user.command.UpdateUserDetailsCommand;
import com.sdi.dto.User;

@Stateless
@WebService(name="UserService")
public class EjbUserServiceImpl implements UserServiceLocal, UserServiceRemote {

	@Override
	public Long registerUser(User user) throws BusinessException {
		return new RegisterUserCommand( user ).execute(); 
	}

	@Override
	public void updateUserDetails(User user) throws BusinessException {
		new UpdateUserDetailsCommand( user ).execute(); 
	}

	@Override
	public User findLoggableUser(final String login, final String password) 
			throws BusinessException {
		return new FindLoggableUSerCommand<User>(login, password).execute();
	}

	@Override
	public User findRepeatUser(String user) throws BusinessException {
		return new FindRepeatUserCommand(user).execute(); 
	}

}
