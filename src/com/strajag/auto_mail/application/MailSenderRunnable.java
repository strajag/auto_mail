package com.strajag.auto_mail.application;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailSenderRunnable implements Runnable
{
    DataManager dataManager;
    public volatile boolean isRunning = false;
    volatile boolean isStopping = false;

    MailSenderRunnable(DataManager dataManager)
    {
        this.dataManager = dataManager;
    }

    @Override
    public void run()
    {
        isRunning = true;
        dataManager.gui.dataPanel.bottomPanel.startStopButton.setText("STOP");
        dataManager.gui.logPanel.bottomPanel.startStopButton.setText("STOP");

        for (int i = 0; i < dataManager.emailList.size(); i++)
        {
            if (isStopping) break;

            try
            {
                send(dataManager.email, dataManager.password, dataManager.emailList.get(i), dataManager.subject, dataManager. message);
                dataManager.gui.logPanel.append(dataManager.emailList.get(i) + " - SUCCESS");
                if (i >= dataManager.emailList.size() - 1) break;
                Thread.sleep(2000);
            } catch (Exception exception)
            {
                dataManager.gui.logPanel.append(dataManager.emailList.get(i) + " - FAIL" + "\n" + exception.getMessage());
            }
        }

        dataManager.gui.dataPanel.bottomPanel.startStopButton.setText("START");
        dataManager.gui.logPanel.bottomPanel.startStopButton.setText("START");
        dataManager.gui.dataPanelFrame.setEnabled(true);
        isRunning = false;
        isStopping = false;
    }

    public void stop()
    {
        isStopping = true;
    }

    void send(String user, String pass, String to, String sub, String msg) throws Exception
    {

        final String username = user;
        final String password = pass;

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse(to)
        );
        message.setSubject(sub);
        message.setText(msg);

        Transport.send(message);
    }
}
