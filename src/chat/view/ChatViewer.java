package chat.view;

import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

/**
 * Constructs the object String and ImageIcon.
 * @author Matthew Hachtel
 *
 */
public class ChatViewer
{
	private String windowMessage;
	private ImageIcon chatIcon;

	public ChatViewer()
	{
		windowMessage = "Chatbot";
		chatIcon = new ImageIcon(getClass().getResource("images/TwitterIcon.png"));
	}
	
	/**
	 * Collects a String
	 * @param question What the bot states as a question.
	 * @return response Returns user input.
	 */
	public String collectResponse(String question)
	{
		String response = "";
		 
		response = JOptionPane.showInputDialog(null, question, windowMessage, JOptionPane.INFORMATION_MESSAGE, chatIcon, null, "Type here please!").toString();
		
		return response;
	}
	
	/**
	 * Collects an integer.
	 * @param question What the bot states as a question.
	 * @return response Returns user option.
	 */
	public int collectUserOption(String question)
	{
		int response = 0;
		 
		response = JOptionPane.showConfirmDialog(null,  question);
		 
		return response;
	}
	
	/**
	 * Displays a message within the popup with supplied message.
	 * @param message The supplied String.
	 */
	public void displayMessage(String message)
	{
		JOptionPane.showMessageDialog(null, message, windowMessage, JOptionPane.PLAIN_MESSAGE, chatIcon);
	}
}