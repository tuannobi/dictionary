package com.tuan.dictionary.user;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.tuan.dictionary.base.BaseServiceImpl;
import com.tuan.dictionary.exception.ServiceException;
import com.tuan.dictionary.partofspeech.PartOfSpeech;
import com.tuan.dictionary.partofspeech.PartOfSpeechRepository;
import com.tuan.dictionary.user.type.UserType;
import com.tuan.dictionary.vocabulary.Vocabulary;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, Long> implements UserService {

	private UserRepository userRepository;
	private PasswordEncoder passwordEngcoder;

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
	public boolean addClientUser(User user) throws ServiceException {
		User existingUser=null;
		try {
			existingUser=userRepository.findByPhoneNumber(user.getPhoneNumber()).get(0);
		} catch (IndexOutOfBoundsException  e) {
			System.out.println("Chưa có dòng nào trong database " +e.getMessage());
		}
		if(existingUser!=null) {
			throw new ServiceException("User with phone number "+user.getPhoneNumber()+" is already existed!");
		}
		//encode Password
		user.setPassword(passwordEngcoder.encode(user.getPassword()));
		user.setActive(true);
		user.setRegisterDate(LocalDateTime.now());
		user.setUserType(new UserType("CLIENT"));
		userRepository.save(user);
		return true;
	}
    

}
