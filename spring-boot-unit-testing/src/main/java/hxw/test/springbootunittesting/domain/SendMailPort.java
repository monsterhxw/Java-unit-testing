package hxw.test.springbootunittesting.domain;

public interface SendMailPort {

    void sendMail(String subject, String text);
}