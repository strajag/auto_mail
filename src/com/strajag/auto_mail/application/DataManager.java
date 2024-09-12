package com.strajag.auto_mail.application;

import com.strajag.auto_mail.gui.GUI;

import java.io.File;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

public class DataManager
{

    File infoFile = new File("./data/info");
    File messageFile = new File("./data/message");
    File emailListFile = new File("./data/emailList");
    File logFile = new File("./data/log");
    public String email;
    public String password;
    public String subject;
    public String message;
    public List<String> emailList;
    public String log;
    public Thread thread = null;
    public MailSenderRunnable mailSenderRunnable = new MailSenderRunnable(this);
    GUI gui;

    public DataManager() throws Exception
    {
        readData();
    }

    private void readData() throws Exception
    {
        List<String> infoLines = Files.readAllLines(infoFile.toPath());
        email = infoLines.get(0);
        password = infoLines.get(1);
        subject = infoLines.get(2);
        message = Files.readString(messageFile.toPath());
        emailList = Files.readAllLines(emailListFile.toPath());
        log = Files.readString(logFile.toPath());
    }

    public void writeData() throws Exception
    {
        List<String> infoLines = Arrays.asList(email, password, subject);
        Files.write(infoFile.toPath(), infoLines);
        Files.writeString(messageFile.toPath(), message);
        Files.write(emailListFile.toPath(), emailList);
        Files.writeString(logFile.toPath(), log);
    }

    public void start()
    {
        if (mailSenderRunnable.isRunning) return;
        thread = new Thread(mailSenderRunnable);
        thread.start();
    }

    public void stop()
    {
        if (!mailSenderRunnable.isRunning) return;
        mailSenderRunnable.stop();
    }

    public void setGUI(GUI gui)
    {
        this.gui = gui;
    }
}
