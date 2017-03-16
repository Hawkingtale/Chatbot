package chat.controller;

import chat.Model.Chatbot;
import chat.view.ChatFrame;
import chat.view.ChatViewer;


import chat.Model.CTECHTwitter;

public class ChatController
{

	private Chatbot stupidBot;
	private ChatViewer chatView;
	private ChatFrame baseFrame;
	public CTECHTwitter weebBot;
		
	public ChatController()
	{
		stupidBot = new Chatbot("Chatbot");
		weebBot = new CTECHTwitter(this);
		chatView = new ChatViewer();
		baseFrame = new ChatFrame(this);
	}
		
		/**
		 * Currently does nothing.
		 */
		public void start()
		{
//			String response = chatView.collectResponse("What do you want to talk about today????");
			
//			while(stupidBot.lengthChecker(response))
//			{
//				chatView.displayMessage(useChatbotCheckers(response));
//				response = chatView.collectResponse("Oh, you are intrested in " + response + "?");
//			}
		}
		
		/**
		 * Responds when the supplied string completes a checker.
		 * @param input The supplied user input.
		 * @return answer Returns the answer the bot will give the user after set checkers is completed.
		 */
		public String useChatbotCheckers(String input)
		{
			String answer = "";
			
			if(!stupidBot.quitChecker(input))
			{
				if(stupidBot.contentChecker(input))
				{
					answer += "\nYou know my special secret\n";
				}
				if(stupidBot.memeChecker(input))
				{
					answer += "\nI can has memes?\n";
				}
				if(stupidBot.politicalTopicChecker(input))
				{
					answer += "\nPolitics blah blah blah.";
				}
				if(!stupidBot.lengthChecker(answer))
				{
					answer += "Sorry, I don't know about " + input + ". ";
				}
				if(stupidBot.inputHTMLChecker(input))
				{
					answer += "Are you on the right program????";
				}
				if(stupidBot.twitterChecker(input))
				{
					answer += "Uh.. wrong program.....";
				}
			
				int canBeRandom = (int) (Math.random() * 7);
				if(canBeRandom % 7 == 0)
				{
					answer += randomTopicGenerator();
				}
			}
			else
			{
				chatView.displayMessage("Thank you for chatting with me :D");
				System.exit(0);
			}
			
			return answer;
		}
		
		/**
		 * Randomly picks a topic whenever the user types anything.
		 * @return randomTopic Returns if and what the randomTopic is or if it is said.
		 */
		private String randomTopicGenerator()
		{
			String randomTopic = "";
			int random = (int) (Math.random() * 7);
			
			switch(random)
			{
				case 0:
					randomTopic = "Did you hear about that one thing?";
					break;
				case 1:
					randomTopic = "How is your day going?";
					break;
				case 2:
					randomTopic = "Oh.. It's you...";
					break;
				case 3:
					randomTopic = "You are a pretty cool dude.";
					break;
				case 4:
					randomTopic = "NEATO";
					break;
				case 5:
					randomTopic = "...";
					break;
				case 6:
					randomTopic = "I don't like this game.";
					break;
				default:
					randomTopic = "NICE";
					break;
			}	
			return randomTopic;		
		}
		
		public Chatbot getChatbot()
		{
			return stupidBot;
		}

		public ChatFrame getBaseFrame()
		{
			return baseFrame;
		}
		
		public void useTwitter(String text)
		{
			weebBot.sendTweet(text);
		}
		
		public ChatViewer getPopup()
		{
			return chatView;
		}
		
		
		
		public void handleErrors(Exception currentException)
		{
			chatView.displayMessage("There is an error next window has details");
			chatView.displayMessage(currentException.getMessage());
		}

		public String searchTwitter(String name)
		{
			String result = weebBot.investigation();
			
			return result;
		}
	}

