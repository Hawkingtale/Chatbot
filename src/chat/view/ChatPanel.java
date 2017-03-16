package chat.view;

import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
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
	private JScrollPane chatPane;
	
	public ChatPanel(ChatController baseController)
	{
		super();
		this.baseController = baseController;
		baseLayout = new SpringLayout();
		chatDisplay = new JTextArea(20, 65);
		baseLayout.putConstraint(SpringLayout.NORTH, chatDisplay, 181, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.EAST, chatDisplay, -324, SpringLayout.EAST, this);
		chatField = new JTextField(60);
		baseLayout.putConstraint(SpringLayout.NORTH, chatField, 350, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.WEST, chatField, 84, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.SOUTH, chatField, -182, SpringLayout.SOUTH, this);
		baseLayout.putConstraint(SpringLayout.EAST, chatField, -324, SpringLayout.EAST, this);
		chatButton = new JButton("Chat");
		baseLayout.putConstraint(SpringLayout.WEST, chatDisplay, 0, SpringLayout.WEST, chatButton);
		baseLayout.putConstraint(SpringLayout.SOUTH, chatDisplay, -6, SpringLayout.NORTH, chatButton);
		baseLayout.putConstraint(SpringLayout.WEST, chatButton, 0, SpringLayout.WEST, chatField);
		baseLayout.putConstraint(SpringLayout.SOUTH, chatButton, -10, SpringLayout.NORTH, chatField);
		chatLabel = new JLabel("Chatbot");
		baseLayout.putConstraint(SpringLayout.NORTH, chatLabel, 119, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.WEST, chatLabel, 286, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.SOUTH, chatLabel, -26, SpringLayout.NORTH, chatDisplay);
		chatPane = new JScrollPane();
		searchTwitterButton = new JButton("Search Twitter");
		baseLayout.putConstraint(SpringLayout.NORTH, searchTwitterButton, 0, SpringLayout.NORTH, chatButton);
		baseLayout.putConstraint(SpringLayout.EAST, searchTwitterButton, 0, SpringLayout.EAST, chatField);
		sendTwitterButton = new JButton("Send Tweet");
		baseLayout.putConstraint(SpringLayout.NORTH, sendTwitterButton, 0, SpringLayout.NORTH, chatButton);
		baseLayout.putConstraint(SpringLayout.WEST, sendTwitterButton, 6, SpringLayout.EAST, chatButton);
		saveButton = new JButton("Save");
		baseLayout.putConstraint(SpringLayout.EAST, chatLabel, 0, SpringLayout.EAST, saveButton);
		baseLayout.putConstraint(SpringLayout.NORTH, saveButton, 0, SpringLayout.NORTH, chatButton);
		baseLayout.putConstraint(SpringLayout.WEST, saveButton, 6, SpringLayout.EAST, sendTwitterButton);
		loadButton = new JButton("Load");
		baseLayout.putConstraint(SpringLayout.NORTH, loadButton, 0, SpringLayout.NORTH, chatButton);
		baseLayout.putConstraint(SpringLayout.WEST, loadButton, 6, SpringLayout.EAST, saveButton);



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
		chatPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		chatPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	}
	
	/**
	 * Initializes all GUI components and adds them to the Frame.
	 */
	private void setupPanel()
	{
		this.setLayout(baseLayout);
		this.setPreferredSize(new Dimension(900,600));
		this.setBackground(Color.MAGENTA);

		this.add(chatPane);
		this.add(chatButton);
		this.add(chatField);
		this.add(chatLabel);
		this.add(searchTwitterButton);
		this.add(sendTwitterButton);
		this.add(saveButton);
		this.add(loadButton);
		this.add(chatPane);

		add(chatDisplay);
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
						
				
				
				
				
				
				
