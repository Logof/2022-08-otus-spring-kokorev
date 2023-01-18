package ru.otus.collectorio.service.impl;

import ru.otus.collectorio.exception.AccessDeniedException;
import ru.otus.collectorio.exception.AppException;
import ru.otus.collectorio.exception.BadRequestException;
import ru.otus.collectorio.exception.ResourceNotFoundException;
import ru.otus.collectorio.exception.UnauthorizedException;
import ru.otus.collectorio.entity.role.Role;
import ru.otus.collectorio.entity.role.RoleName;
import ru.otus.collectorio.entity.user.User;
import ru.otus.collectorio.payload.response.ApiResponse;
import ru.otus.collectorio.payload.request.InfoRequest;
import ru.otus.collectorio.payload.UserIdentityAvailability;
import ru.otus.collectorio.payload.UserProfile;
import ru.otus.collectorio.payload.UserSummary;
import ru.otus.collectorio.repository.RoleRepository;
import ru.otus.collectorio.repository.UserRepository;
import ru.otus.collectorio.security.UserPrincipal;
import ru.otus.collectorio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
//TODO разобрать комменты
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserSummary getCurrentUser(UserPrincipal currentUser) {
		return new UserSummary(currentUser.getId(), currentUser.getUsername(), currentUser.getFirstName(),
				currentUser.getLastName());
	}

	@Override
	public UserIdentityAvailability checkUsernameAvailability(String username) {
		Boolean isAvailable = !userRepository.existsByUsername(username);
		return new UserIdentityAvailability(isAvailable);
	}

	@Override
	public UserIdentityAvailability checkEmailAvailability(String email) {
		Boolean isAvailable = !userRepository.existsByEmail(email);
		return new UserIdentityAvailability(isAvailable);
	}

	@Override
	public UserProfile getUserProfile(String username) {
		User user = userRepository.getUserByName(username);

		return new UserProfile(user.getId(), user.getUsername(), user.getFirstName(), user.getLastName(),
				user.getCreatedAt(), user.getEmail(), user.getPhone(), user.getWebsite());
	}

	@Override
	public User addUser(User user) {
		if (userRepository.existsByUsername(user.getUsername())) {
			ApiResponse apiResponse = ApiResponse.error("Username is already taken");
			throw new BadRequestException(apiResponse);
		}

		if (userRepository.existsByEmail(user.getEmail())) {
			ApiResponse apiResponse =ApiResponse.error("Email is already taken");
			throw new BadRequestException(apiResponse);
		}

		List<Role> roles = new ArrayList<>();
		roles.add(
				roleRepository.findByName(RoleName.ROLE_USER).orElseThrow(() -> new AppException("User role not set")));
		user.setRoles(roles);

		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public User updateUser(User newUser, String username, UserPrincipal currentUser) {
		User user = userRepository.getUserByName(username);
		if (user.getId().equals(currentUser.getId())
				|| currentUser.getAuthorities().contains(new SimpleGrantedAuthority(RoleName.ROLE_ADMIN.toString()))) {
			user.setFirstName(newUser.getFirstName());
			user.setLastName(newUser.getLastName());
			user.setPassword(passwordEncoder.encode(newUser.getPassword()));
			user.setPhone(newUser.getPhone());
			user.setWebsite(newUser.getWebsite());
			return userRepository.save(user);

		}

		ApiResponse apiResponse = ApiResponse.error("You don't have permission to update profile of: " + username);
		throw new UnauthorizedException(apiResponse);

	}

	@Override
	public ApiResponse deleteUser(String username, UserPrincipal currentUser) {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", username));
		if (!user.getId().equals(currentUser.getId()) || !currentUser.getAuthorities()
				.contains(new SimpleGrantedAuthority(RoleName.ROLE_ADMIN.toString()))) {
			ApiResponse apiResponse = ApiResponse.error("You don't have permission to delete profile of: " + username);
			throw new AccessDeniedException(apiResponse);
		}

		userRepository.deleteById(user.getId());

		return ApiResponse.success(); //new ApiResponse(Boolean.TRUE, "You successfully deleted profile of: " + username);
	}

	@Override
	public ApiResponse giveAdmin(String username) {
		User user = userRepository.getUserByName(username);
		List<Role> roles = new ArrayList<>();
		roles.add(roleRepository.findByName(RoleName.ROLE_ADMIN)
				.orElseThrow(() -> new AppException("User role not set")));
		roles.add(
				roleRepository.findByName(RoleName.ROLE_USER).orElseThrow(() -> new AppException("User role not set")));
		user.setRoles(roles);
		userRepository.save(user);
		return ApiResponse.success(); //(Boolean.TRUE, "You gave ADMIN role to user: " + username);
	}

	@Override
	public ApiResponse removeAdmin(String username) {
		User user = userRepository.getUserByName(username);
		List<Role> roles = new ArrayList<>();
		roles.add(
				roleRepository.findByName(RoleName.ROLE_USER).orElseThrow(() -> new AppException("User role not set")));
		user.setRoles(roles);
		userRepository.save(user);
		return ApiResponse.success();//(Boolean.TRUE, "You took ADMIN role from user: " + username);
	}

	@Override
	public UserProfile setOrUpdateInfo(UserPrincipal currentUser, InfoRequest infoRequest) {
		User user = userRepository.findByUsername(currentUser.getUsername())
				.orElseThrow(() -> new ResourceNotFoundException("User", "username", currentUser.getUsername()));

		if (user.getId().equals(currentUser.getId())
				|| currentUser.getAuthorities().contains(new SimpleGrantedAuthority(RoleName.ROLE_ADMIN.toString()))) {
			user.setWebsite(infoRequest.getWebsite());
			user.setPhone(infoRequest.getPhone());
			User updatedUser = userRepository.save(user);

			return new UserProfile(updatedUser.getId(), updatedUser.getUsername(),
					updatedUser.getFirstName(), updatedUser.getLastName(), updatedUser.getCreatedAt(),
					updatedUser.getEmail(), updatedUser.getPhone(), updatedUser.getWebsite());
		}

		ApiResponse apiResponse = ApiResponse.error("You don't have permission to update users profile"); //, HttpStatus.FORBIDDEN
		throw new AccessDeniedException(apiResponse);
	}
}
