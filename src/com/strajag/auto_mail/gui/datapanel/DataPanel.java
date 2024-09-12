package com.strajag.auto_mail.gui.datapanel;

import com.strajag.auto_mail.gui.GUI;
import com.strajag.auto_mail.gui.datapanel.components.BottomPanel;
import com.strajag.auto_mail.gui.datapanel.components.CenterPanel;
import com.strajag.auto_mail.gui.datapanel.components.TopPanel;
import com.strajag.custom_frame.components.Panel;

import java.awt.*;

public class DataPanel extends Panel {

    public GUI gui;

    public CenterPanel centerPanel;
    public TopPanel topPanel;
    public BottomPanel bottomPanel;

    public DataPanel(GUI gui) {
        this.gui = gui;

        topPanel = new TopPanel(this);
        centerPanel = new CenterPanel(this);
        bottomPanel = new BottomPanel(this);

        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        gui.dataPanelFrame.titleBarPanel.leftPanel.menuButton.addActionListener(actionEvent -> gui.logPanelFrame.setVisible(true));
        gui.dataPanelFrame.titleBarPanel.rightPanel.exitButton.addActionListener(actionEvent -> { gui.dataPanelFrame.setVisible(false); gui.exit(); });
    }

    public void setData() {
        topPanel.email.setText(gui.dataManager.email);
        topPanel.password.setText(gui.dataManager.password);
        topPanel.subject.setText(gui.dataManager.subject);
        centerPanel.messageTextArea.setText(gui.dataManager.message);
        centerPanel.emailsTextArea.setText(String.join("\n", gui.dataManager.emailList));
        gui.logPanel.topPanel.logTextPane.setText(gui.dataManager.log);
        gui.dataPanelFrame.setVisible(true);
    }
}
