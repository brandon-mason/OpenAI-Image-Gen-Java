package ui;

import javax.swing.*;

import apiOperations.ApiKeyLoader;
import ui.tabs.ChatTab;
import ui.tabs.ChatTabComponents;
import ui.tabs.DrawTab;

public class OpenAIapiUI extends JFrame {
    private String apiKey;

    public OpenAIapiUI() {
        // Load API key from .env file
        apiKey = ApiKeyLoader.loadApiKey();

        // Set up UI
        setTitle("OpenAI Chat Example");
        setSize(800, 1200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create tabbed pane
        JTabbedPane tabbedPane = new JTabbedPane();

        // Create ChatTab components
        ChatTabComponents chatComponents = new ChatTabComponents();

        // Add "Chat" tab
        ChatTab chatTab = new ChatTab(
                chatComponents.getModelField(),
                chatComponents.getMaxTokensField(),
                chatComponents.getSystemPersonalityArea(),
                chatComponents.getUserPromptArea(),
                chatComponents.getResponseArea(),
                apiKey);
        tabbedPane.addTab("Chat", chatTab.createTab());

        // Add "Draw" tab
        DrawTab drawTab = new DrawTab();
        tabbedPane.addTab("Draw", drawTab.createTab());

        // Add the tabbed pane to the frame
        add(tabbedPane);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(OpenAIapiUI::new);
    }
}
