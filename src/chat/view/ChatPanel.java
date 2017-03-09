package chat.view;

import javax.swing.*;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

import chat.controller.ChatController;

/**
 * Constructs the objects ChatController, SpringLayout, JTextArea, JTextField, JButton, and JLabel
 * @author Matthew Hachtel
 *
 */
public class ChatPanel extends JPanel
{
	private ChatController baseController;
	private SpringLayout baseLayout;
	private JTextArea chatDisplay;
	private JTextField chatField;
	private JButton chatButton;
	private JLabel chatLabel;
	
	public ChatPanel(ChatController baseController)
	{
		super();
		this.baseController = baseController;
		baseLayout = new SpringLayout();
		chatDisplay = new JTextArea(5, 25);
		chatField = new JTextField(25);
		chatButton = new JButton("Chat with the bot.");
		chatLabel = new JLabel("Welcome to the bot.");


		setupChatDisplay();
		setupPanel();
		setupLayout();
		setupListeners();
	}
	
	/**
	 * Sets up the JTextField with certain conditions.
	 */
	private void setupChatDisplay()
	{
		chatDisplay.setEditable(false);
		chatDisplay.setEnabled(false);
		chatDisplay.setLineWrap(true);
		chatDisplay.setWrapStyleWord(true);
	}
	
	/**
	 * Initializes all GUI components and adds them to the Frame.
	 */
	private void setupPanel()
	{
		this.setLayout(baseLayout);
		this.setBackground(Color.lightGray);
		this.add(chatDisplay);
		this.add(chatButton);
		this.add(chatField);
		this.add(chatLabel);
	}
	
    /**
	 * Dumps auto-generated code from window builder.
	 */
	private void setupLayout()
	{
		baseLayout.putConstraint(SpringLayout.EAST, chatDisplay, -72, SpringLayout.EAST, this);
		baseLayout.putConstraint(SpringLayout.WEST, chatField, -7, SpringLayout.WEST, chatDisplay);
		baseLayout.putConstraint(SpringLayout.EAST, chatField, -65, SpringLayout.EAST, this);
		baseLayout.putConstraint(SpringLayout.NORTH, chatDisplay, 24, SpringLayout.SOUTH, chatField);
		baseLayout.putConstraint(SpringLayout.NORTH, chatField, 113, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.WEST, chatButton, 33, SpringLayout.WEST, chatDisplay);
		baseLayout.putConstraint(SpringLayout.SOUTH, chatButton, -32, SpringLayout.NORTH, chatField);
		baseLayout.putConstraint(SpringLayout.EAST, chatButton, -104, SpringLayout.EAST, this);
		baseLayout.putConstraint(SpringLayout.NORTH, chatLabel, 10, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.WEST, chatLabel, 0, SpringLayout.WEST, chatDisplay);
	}
	
	/**
	 * Sets actions for buttons when they are pressed.
	 */
	private void setupListeners()
	{
		chatButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				String userWords = chatField.getText();
				String botResponse = baseController.useChatbotCheckers(userWords);
				String currentText = chatDisplay.getText();
				
				chatDisplay.setText("You said: " + userWords + "\n" + "Chatbot said: " + botResponse + "\n" + currentText);
				chatDisplay.setCaretPosition(0);
				chatField.setText("");
			}
		});

			{
			
			}	
			searchTwitterButton.addActionListener(newActionListener()
			{
				public void actionPerformed(ActionEvent click)
				{
					String username = chatField.getText();
					chatDisplay.append(baseController.searchTwitter(username));
				}
			});
				
				
	}

				
				
				
				
				
				
				
				
				
}