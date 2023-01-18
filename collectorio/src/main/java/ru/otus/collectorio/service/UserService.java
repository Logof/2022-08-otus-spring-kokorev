package ru.otus.collectorio.service;

import ru.otus.collectorio.entity.user.User;
import ru.otus.collectorio.payload.response.ApiResponse;
import ru.otus.collectorio.payload.request.InfoRequest;
import ru.otus.collectorio.payload.UserIdentityAvailability;
import ru.otus.collectorio.payload.UserProfile;
import ru.otus.collectorio.payload.UserSummary;
import ru.otus.collectorio.security.UserPrincipal;

public interface UserService {

	UserSummary getCurrentUser(UserPrincipal currentUser);

	UserIdentityAvailability checkUsernameAvailability(String username);

	UserIdentityAvailability checkEmailAvailability(String email);

	UserProfile getUserProfile(String username);

	User addUser(User user);

	User updateUser(User newUser, String username, UserPrincipal currentUser);

	ApiResponse deleteUser(String username, UserPrincipal currentUser);

	ApiResponse giveAdmin(String username);

	ApiResponse removeAdmin(String username);

	UserProfile setOrUpdateInfo(UserPrincipal currentUser, InfoRequest infoRequest);

}