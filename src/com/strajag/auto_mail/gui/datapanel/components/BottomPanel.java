package com.strajag.auto_mail.gui.datapanel.components;

import com.strajag.auto_mail.gui.datapanel.DataPanel;
import com.strajag.custom_frame.components.Button;
import com.strajag.custom_frame.components.Panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

public class BottomPanel extends Panel implements ActionListener {

    public DataPanel dataPanel;

    public Button startStopButton;
    public Button saveButton;

    public BottomPanel(DataPanel dataPanel) {
        this.dataPanel = dataPanel;

        startStopButton = new Button("START");
        saveButton = new Button("SAVE");

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        add(startStopButton);
        gbc.gridy = 1;
        add(saveButton, gbc);

        startStopButton.addActionListener(this);
        saveButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        try {
            JButton pressedButton = (JButton) actionEvent.getSource();
            if (pressedButton.equals(startStopButton)) {
                dataPanel.gui.dataManager.start();

                setTexts();

                dataPanel.gui.logPanelFrame.setVisible(true);
                dataPanel.gui.dataPanelFrame.setEnabled(false);
            } else if (pressedButton.equals(saveButton)) {
                setTexts();
                dataPanel.gui.dataManager.writeData();
            }
        } catch (Exception exception) {
            dataPanel.gui.dataPanelFrame.throwException(exception);
        }
    }

    public void setTexts()
    {
        dataPanel.topPanel.email.setText(dataPanel.topPanel.email.getText().trim().replaceAll(" ", ""));
        dataPanel.gui.dataManager.email = dataPanel.topPanel.email.getText();
        dataPanel.gui.dataManager.password = dataPanel.topPanel.password.getText();
        dataPanel.gui.dataManager.subject = dataPanel.topPanel.subject.getText();
        dataPanel.gui.dataManager.message = dataPanel.centerPanel.messageTextArea.getText();

        List<String> emailsList = Arrays.asList(dataPanel.centerPanel.emailsTextArea.getText().split("\n"));
        emailsList.replaceAll(string -> string.trim().replaceAll("[ |\t]", ""));
        dataPanel.gui.dataManager.emailList = emailsList;
        dataPanel.centerPanel.emailsTextArea.setText(String.join("\n", emailsList));

        dataPanel.gui.dataManager.log = dataPanel.gui.logPanel.topPanel.logTextPane.getText();
    }
}
