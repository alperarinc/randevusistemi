package com.aarinc.randevusistemi.service;

import com.aarinc.randevusistemi.model.Appointment;
import com.aarinc.randevusistemi.model.Business;
import com.aarinc.randevusistemi.model.User; // Doğru import
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendAppointmentCreatedEmail(User user, Business business, Appointment appointment) {
        String subject = "Randevu Oluşturuldu";
        String content = "Sayın " + user.getUsername() + ",\n\n"
                + "Randevunuz başarıyla alındı. İşletme: " + business.getName() + ". Randevu Tarihi: " + appointment.getDate() + ".\n"
                + "Randevunuz admin tarafından onaylandığında bilgilendirileceksiniz.\n\nTeşekkürler!";

        sendSimpleEmail(user.getEmail(), subject, content);

        for (User owner : business.getUsers()) {
            if (owner.getRoles().contains("ADMIN")) {
                sendSimpleEmail(owner.getEmail(), subject, content);
            }
        }
    }

    public void sendAppointmentApprovedEmail(User user, Business business, Appointment appointment) {
        String subject = "Randevu Onaylandı";
        String content = "Sayın " + user.getUsername() + ",\n\n"
                + "Randevunuz onaylandı. İşletme: " + business.getName() + ". Randevu Tarihi: " + appointment.getDate() + ".\n"
                + "İşletme ile belirtilen tarih ve saatte görüşmek üzere!\n\nTeşekkürler!";

        sendSimpleEmail(user.getEmail(), subject, content);

        for (User owner : business.getUsers()) {
            if (owner.getRoles().contains("ADMIN")) {
                sendSimpleEmail(owner.getEmail(), subject, content);
            }
        }
    }

    private void sendSimpleEmail(String toAddress, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toAddress);
        message.setSubject(subject);
        message.setText(content);
        mailSender.send(message);
    }
}
