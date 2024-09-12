package com.strajag.auto_mail.gui.datapanel.components;

import com.strajag.auto_mail.Settings;
import com.strajag.auto_mail.gui.datapanel.DataPanel;
import com.strajag.custom_frame.components.Panel;
import com.strajag.custom_frame.components.TextField;

import java.awt.*;

public class TopPanel extends Panel {

    public DataPanel dataPanel;

    public TextField email;
    public TextField password;
    public TextField subject;

    public TopPanel(DataPanel dataPanel) {
        this.dataPanel = dataPanel;

        email = new TextField();
        password = new TextField();
        subject = new TextField();

        setLayout(new GridLayout(3, 0, 0, 0));
        add(email);
        add(password);
        add(subject);

        password.setForeground(Settings.Colors.backgroundColor);

        email.setBorder(Settings.Borders.getBevelTitleBorder("EMAIL"));
        password.setBorder(Settings.Borders.getBevelTitleBorder("PASSWORD"));
        subject.setBorder(Settings.Borders.getBevelTitleBorder("SUBJECT"));
    }
}
