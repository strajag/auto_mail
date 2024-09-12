package com.straykovsky.automail.gui.datapanel.components;

import com.straykovsky.automail.Settings;
import com.straykovsky.automail.gui.datapanel.DataPanel;
import com.straykovsky.custom_frame.components.Panel;
import com.straykovsky.custom_frame.components.ScrollPane;
import com.straykovsky.custom_frame.components.TextArea;

import java.awt.*;

public class CenterPanel extends Panel {

    public DataPanel dataPanel;

    public TextArea messageTextArea;
    public ScrollPane messageScrollPane;
    public TextArea emailsTextArea;
    public ScrollPane emailsScrollPane;

    public CenterPanel(DataPanel dataPanel) {
        this.dataPanel = dataPanel;

        messageTextArea = new TextArea();
        messageScrollPane = new ScrollPane(messageTextArea);
        messageScrollPane.setBorder(Settings.Borders.getBevelTitleBorder("MESSAGE"));
        emailsTextArea = new TextArea();
        emailsScrollPane = new ScrollPane(emailsTextArea);
        emailsScrollPane.setBorder(Settings.Borders.getBevelTitleBorder("EMAILS"));

        setLayout(new GridLayout(2, 0, 0, 0));
        add(messageScrollPane);
        add(emailsScrollPane);
    }
}
