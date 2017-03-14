package chat.view;

import javax.swing.*;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

import chat.controller.ChatController;
import chat.controller.FileController;

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
	private JButton searchTwitterButton;
	private JButton sendTwitterButton;
	private JButton saveButton;
	private JButton loadButton;
	
	public ChatPanel(ChatController baseController)
	{
		super();
		this.baseController = baseController;
		baseLayout = new SpringLayout();
		chatDisplay = new JTextArea(5, 25);
		chatField = new JTextField(25);
		baseLayout.putConstraint(SpringLayout.NORTH, chatDisplay, 6, SpringLayout.SOUTH, chatField);
		baseLayout.putConstraint(SpringLayout.WEST, chatDisplay, 0, SpringLayout.WEST, chatField);
		baseLayout.putConstraint(SpringLayout.WEST, chatField, 71, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.EAST, chatField, -79, SpringLayout.EAST, this);
		chatButton = new JButton("Chat with the bot.");
		baseLayout.putConstraint(SpringLayout.NORTH, chatField, 6, SpringLayout.SOUTH, chatButton);
		baseLayout.putConstraint(SpringLayout.WEST, chatButton, 106, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.EAST, chatButton, -109, SpringLayout.EAST, this);
		chatLabel = new JLabel("Welcome to the bot.");
		baseLayout.putConstraint(SpringLayout.SOUTH, chatLabel, -253, SpringLayout.SOUTH, this);
		baseLayout.putConstraint(SpringLayout.NORTH, chatButton, 6, SpringLayout.SOUTH, chatLabel);
		baseLayout.putConstraint(SpringLayout.WEST, chatLabel, 134, SpringLayout.WEST, this);
		searchTwitterButton = new JButton("SEARCH TWITTER");
		baseLayout.putConstraint(SpringLayout.NORTH, searchTwitterButton, 6, SpringLayout.SOUTH, chatDisplay);
		baseLayout.putConstraint(SpringLayout.WEST, searchTwitterButton, 71, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.SOUTH, searchTwitterButton, -65, SpringLayout.SOUTH, this);
		baseLayout.putConstraint(SpringLayout.EAST, searchTwitterButton, -247, SpringLayout.EAST, this);
		sendTwitterButton = new JButton("Send Tweet");
		baseLayout.putConstraint(SpringLayout.NORTH, sendTwitterButton, 0, SpringLayout.NORTH, searchTwitterButton);
		baseLayout.putConstraint(SpringLayout.WEST, sendTwitterButton, 6, SpringLayout.EAST, searchTwitterButton);
		saveButton = new JButton("save");
		loadButton = new JButton("load");
		baseLayout.putConstraint(SpringLayout.NORTH, saveButton, 0, SpringLayout.NORTH, loadButton);
		baseLayout.putConstraint(SpringLayout.WEST, saveButton, 6, SpringLayout.EAST, loadButton);
		baseLayout.putConstraint(SpringLayout.NORTH, loadButton, 6, SpringLayout.SOUTH, searchTwitterButton);
		baseLayout.putConstraint(SpringLayout.EAST, loadButton, 0, SpringLayout.EAST, searchTwitterButton);

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
		this.setBackground(Color.MAGENTA);
		this.add(chatDisplay);
		this.add(chatButton);
		this.add(chatField);
		this.add(chatLabel);
		this.add(searchTwitterButton);
		this.add(sendTwitterButton);
		this.add(saveButton);
		this.add(loadButton);
	}
	
    /**
	 * Dumps auto-generated code from window builder.
	 */
	private void setupLayout()
	{
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

	
				
	

				
				
		searchTwitterButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				String username = chatField.getText();
				chatDisplay.append(baseController.searchTwitter(username));
			}
		});
		
		sendTwitterButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				baseController.useTwitter(chatField.getText());
			}
		});
		
		saveButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				String fileName = chatField.getText();
				
				FileController.saveFile(baseController, fileName.trim(), chatDisplay.getText());
			}
		});
		
		loadButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				String fileName = chatField.getText() + ".txt";
				String saved = FileController.readFile(baseController, fileName);
				chatDisplay.setText(saved);
			}
		});
		
		chatField.addActionListener(new ActionListener()	
		{
			public void actionPerformed(ActionEvent enterPress)
			{
				scrollTextUp();
			}
		});
	}
	
	private void scrollTextUp()
	{
		String personWords = chatField.getText();
		String chatbotResponse = baseController.useChatbotCheckers(personWords);
		String currentText = chatDisplay.getText();
		
		chatDisplay.setText("You said: " + personWords+ "\n" + "Chatbot says: " + chatbotResponse + "\n" + currentText);
		chatDisplay.setCaretPosition(0);
		
		chatField.setText("");
	}
	
	private void scrollTextDown()
	{
		String personWords = chatField.getText();
		String chatbotResponse = baseController.useChatbotCheckers(personWords);
		String currentText = chatDisplay.getText();
		
		chatDisplay.setText("You said: " + personWords+ "\n" + "Chatbot says: " + chatbotResponse + "\n" + currentText);
		chatDisplay.setCaretPosition(chatDisplay.getCaretPosition());
		
		chatField.setText("");
	}
}
						
				
				
				
				
				
				
