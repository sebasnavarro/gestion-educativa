package com.azulita.springboot.backend.gestioneducativa.models.services.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.azulita.springboot.backend.gestioneducativa.models.dao.IUserDao;
import com.azulita.springboot.backend.gestioneducativa.models.entity.Alumno;
import com.azulita.springboot.backend.gestioneducativa.models.services.IUserService;


@Service
public class IUserServiceImpl implements IUserService ,UserDetailsService {

	private Logger logger = LoggerFactory.getLogger(IUserServiceImpl.class);
	
	@Autowired
	private IUserDao userDao;
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Alumno user = userDao.findByUsername(username);
		
		if(user == null) {
			logger.error("Error en el login: no existe el usuario '"+username+"' en el sistema!");
		    throw new UsernameNotFoundException("Error en el login: no existe el usuario '"+username+"' en el sistema!");
		}
		
		List<GrantedAuthority> authorities = user.getRoles()
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.getNombre()))
				.peek(authority -> logger.info("Role: " + authority.getAuthority()))
				.collect(Collectors.toList());
		
		return new User(user.getUsername(), user.getPassword(), user.getEnabled(), true, true, true, authorities);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Alumno findByUsername(String username) {
		return userDao.findByUsername(username);
	}

}
