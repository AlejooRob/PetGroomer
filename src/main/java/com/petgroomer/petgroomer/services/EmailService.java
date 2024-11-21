package com.petgroomer.petgroomer.services;

import com.petgroomer.petgroomer.models.Cita;
import com.petgroomer.petgroomer.models.Cliente;
import com.petgroomer.petgroomer.models.Email;
import jakarta.mail.MessagingException;

public interface EmailService {

    void sendMail(Email email, Cita cita) throws MessagingException;
}
