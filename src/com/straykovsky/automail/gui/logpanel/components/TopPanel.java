package com.straykovsky.automail.gui.logpanel.components;

import com.straykovsky.automail.Settings;
import com.straykovsky.automail.gui.logpanel.LogPanel;
import com.straykovsky.custom_frame.components.Panel;
import com.straykovsky.custom_frame.components.ScrollPane;
import com.straykovsky.custom_frame.components.TextPane;

public class TopPanel extends Panel {

    public LogPanel logPanel;

    public TextPane logTextPane;
    public ScrollPane logScrollPane;

    public TopPanel(LogPanel logPanel) {
        this.logPanel = logPanel;

        logTextPane = new TextPane();
        logScrollPane = new ScrollPane(logTextPane);

        logScrollPane.setBorder(Settings.Borders.getBevelTitleBorder("LOG"));

        add(logScrollPane);
    }
}
