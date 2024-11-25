package listeners;

import apiOperations.*;
import ui.panels.GenImagePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JTextArea;

public class SendDrawRequestButtonListener implements ActionListener {
    private JComboBox<String> modelField;
    private JTextArea userPromptArea, responseArea;
    private GenImagePanel genImagePanel;
    private String apiKey;

    public SendDrawRequestButtonListener(JComboBox<String> modelField, JTextArea userPromptArea,
            JTextArea responseArea, GenImagePanel genImagePanel, String apiKey) {
        this.modelField = modelField;
        this.userPromptArea = userPromptArea;
        this.responseArea = responseArea;
        this.genImagePanel = genImagePanel;
        this.apiKey = apiKey;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String model = (String) modelField.getSelectedItem();
        String prompt = userPromptArea.getText();

        ImageGenerations chatCompletions = new ImageGenerations(apiKey, model, prompt);
        try {
            String responseContent = chatCompletions.execute("https://api.openai.com/v1/images/generations");
            responseArea.setText(responseContent);
            genImagePanel.loadImage(responseContent);
        } catch (Exception ex) {
            responseArea.setText("OpenAI chat response error: " + ex.getMessage());
            
            ex.printStackTrace();
        }
    }
}