package com.example.knk_gr23.Services;

import com.example.knk_gr23.Models.Loan;
import com.example.knk_gr23.Models.User;
import com.example.knk_gr23.Reposirtory.ClientRepository;
import com.example.knk_gr23.Reposirtory.LoanRepository;

import java.sql.SQLException;
import java.util.List;

public class ClientService {
    public static List<User> getAllClients() throws SQLException {
        return ClientRepository.getAllClients();
    }
}
