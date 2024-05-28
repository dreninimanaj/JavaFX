package com.example.knk_gr23.Services;


import com.example.knk_gr23.Models.User;
import com.example.knk_gr23.Models.dto.CreateClientDto;
import com.example.knk_gr23.Models.dto.LoginUserDto;
import com.example.knk_gr23.Reposirtory.UserRepository;


import java.util.List;

public class UserService {
//    public static boolean signUp(UserDto userData){
//        String password = userData.getPassword();
//        String confirmPassword = userData.getConfirmPassword();
//
//        if(!password.equals(confirmPassword)){
//            return false;
//        }
//
//        String salt = PasswordHasher.generateSalt();
//        String passwordHash = PasswordHasher.generateSaltedHash(
//                password, salt
//        );
//
//        CreateUserDto createUserData = new CreateUserDto(
//                userData.getUsername(),
//                userData.getLastName(),
//                userData.getEmail(),
//                salt,
//                passwordHash
//        );
//
//        return UserRepository.create(createUserData);
//    }

//    public static boolean login(LoginUserDto  loginData){
//        User user = UserRepository.getByEmail(loginData.getUsername());
//        if(user == null){
//            return false;
//        }
//
//        String password = loginData.getPassword();
//        String salt = user.getSalt();
//        String passwordHash = user.getPasswordHash();
//
//        return PasswordHasher.compareSaltedHash(
//                password, salt, passwordHash
//        );
//    }

    public static User login(LoginUserDto loginData) {
        // Log the login attempt with the provided username
        System.out.println("Attempting to log in with username: " + loginData.getUsername());

        // Retrieve the user from the repository
        User user = UserRepository.getByUsername(loginData.getUsername());

        // Check if the user was found
        if (user == null) {
            System.out.println("User not found for username: " + loginData.getUsername());

            // Print all usernames from the database for debugging
            System.out.println("Listing all usernames in the database:");
            List<String> usernames = UserRepository.getAllUsernames();
            for (String username : usernames) {
                System.out.println(username);
            }

            return null;
        }

        // Retrieve and log the entered password
        String enteredPassword = loginData.getPassword();
        System.out.println("Entered password: " + enteredPassword);

        // Check if the user is an admin
        if ("admin".equalsIgnoreCase(user.getRole())) {
            // Admin user, compare passwords directly
            System.out.println("User is admin, comparing password directly.");
            boolean isPasswordMatch = PasswordHasher.comparePassword(user.getPasswordHash(), enteredPassword);
            System.out.println("Password match result: " + isPasswordMatch);
            if (!isPasswordMatch) {
                return null;
            }
        } else {
            String storedPasswordHash = user.getPasswordHash();
            String storedSalt = user.getSalt();
            System.out.println("Stored password hash: " + storedPasswordHash);
            System.out.println("Stored salt: " + storedSalt);

            boolean isPasswordMatch = PasswordHasher.compareSaltedHash(enteredPassword, storedSalt, storedPasswordHash);
            System.out.println("Password match result: " + isPasswordMatch);
            if (!isPasswordMatch) {
                return null;
            }
        }

        return user;
    }

public boolean createClient(CreateClientDto createClientDto) {
        return UserRepository.signUp(createClientDto);
}

}
