package jwt.authorization.controller;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jwt.authorization.exception.InvalidCredException;
import jwt.authorization.config.*;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

	// CREATING AN OBJECT FOR LOGGING

	Logger logger = LoggerFactory.getLogger(JwtAuthenticationController.class);

	// AUTOWIRING OBJECT FOR AUTHENTICATION MANAGER

	@Autowired
	private AuthenticationManager authenticationManager;

	// AUTOWIRING OBJECT FOR JWTTOKENUTIL

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	// AUTOWIRING OBJECT FOR USERDETAILSSERVICE

	@Autowired
	private UserDetailsService jwtInMemoryUserDetailsService;

	// GET JWT TOKEN

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestParam("username") String username,
			@RequestParam("password") String password) throws Exception {

		logger.trace(username);
		logger.trace("auth");
		authenticate(username, password);
		logger.trace("after auth");

		final UserDetails userDetails = jwtInMemoryUserDetailsService.loadUserByUsername(username);
		System.out.println(userDetails);

		final String token = jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(new String(token));
	}

	// AUTHORIZE LOGIN USING CREDENTIALS

	private void authenticate(String username, String password) throws Exception {
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (InvalidCredException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
