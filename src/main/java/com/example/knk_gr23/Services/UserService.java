package com.example.knk_gr23.Services;


import com.example.knk_gr23.Models.User;
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

    // Retrieve and log the stored password
    String storedPassword = user.getPasswordHash();
    System.out.println("Stored password: " + storedPassword);

    // Compare the passwords and log the comparison result
    boolean isPasswordMatch = PasswordHasher.comparePassword(enteredPassword, storedPassword);
//    System.out.println("Password match result: " + isPasswordMatch);
    if(!isPasswordMatch){
        return null;
    }

    return user;
}
//    User user = UserRepository.getByUsername(loginData.getUsername());
//    if(user == null){
//        return false;
//    }
//
////    if(user.getRole().equals("admin")){
////
////    }
//    String password = loginData.getPassword();
////            if(password.equals("admin")){
////                return true;
////            }
////            return false;
//    String password = user.getPassword();
//    return PasswordHasher.comaparePassword(
//            password,  password
//    );
////    String password = loginData.getPassword();
////    String salt = user.getSalt();
////    String passwordHash = user.getPasswordHash();
//
////    return PasswordHasher.compareSaltedHash(
////            password, salt, passwordHash
////    );
}

//    public static List<User> filter(UserFilter filter){
//        return UserRepository.getByFilter(filter);
//    }
