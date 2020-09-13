package com.tuan.dictionary.user;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.tuan.dictionary.base.BaseServiceImpl;
import com.tuan.dictionary.exception.ServiceException;
import com.tuan.dictionary.user.type.UserType;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, Long> implements UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEngcoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEngcoder=passwordEncoder;
    }

    @Override
    protected CrudRepository<User, Long> getRepository() {
        return userRepository;
    }

	@Override
	@Transactional(isolation = Isolation.SERIALIZABLE)
	public void addClientUser(User user) throws ServiceException {
		User existingUser=null;
		try {
			existingUser=userRepository.findByPhoneNumber(user.getPhoneNumber()).get(0);
		} catch (IndexOutOfBoundsException  e) {
			System.out.println("Chưa có dòng nào trong database " +e.getMessage());
		}
		try{
			existingUser=userRepository.findByEmail(user.getEmail()).get(0);
		}catch (IndexOutOfBoundsException e){
			System.out.println("Chưa có dòng nào trong database " +e.getMessage());
		}
		if(existingUser!=null) {
			throw new ServiceException("User is already existed!");
		}
		//encode Password
		user.setPassword(passwordEngcoder.encode(user.getPassword()));
		user.setActive(true);
		user.setRegisterDate(LocalDateTime.now());
		user.setUserType(new UserType("CLIENT"));
		userRepository.save(user);
	}
    

}
