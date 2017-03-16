package chat.view;

import javax.swing.JFrame;

import chat.controller.ChatController;

import java.awt.Dimension;

/**
 * Constructs the objects ChatController and ChatPanel.
 * @author Matthew Hachtel
 *
 */
public class ChatFrame extends JFrame
{
	
	private ChatController baseController;
	private ChatPanel basePanel;
	
	public ChatFrame(ChatController baseController)
	{
		super();
		this.baseController = baseController;
		basePanel = new ChatPanel(baseController);
		setupFrame();
	}
	
	/**
	 * Sets up the window with certain conditions.
	 */
	private void setupFrame()
	{
		this.setContentPane(basePanel);
		this.setTitle("Chatbot");
		this.setSize(new Dimension(900, 600));
		this.setResizable(false);
		this.setVisible(true);
	}
}
