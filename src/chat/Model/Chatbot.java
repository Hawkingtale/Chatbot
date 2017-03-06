package chat.Model;

import java.util.ArrayList;

public class Chatbot
{
	private ArrayList<String> memesList;
	private ArrayList<String> politicalTopicList;
	private String userName;
	private String content;
		
	/**
	 * Creates an instance of the Chatbot with the supplied username.
	 * @param userName The username for the chatbot.
	 */
	public Chatbot(String userName)
	{
		this.memesList = new ArrayList<String>();
		this.politicalTopicList = new ArrayList<String>();
		this.userName = userName;
		this.content = new String("dogs");
		this.buildMemesList();
		this.buildPoliticalTopicsList();
		}
		
		/**
		 * Adds items to memeslist for use in Chatbot.
		 */
		private void buildMemesList()
		{
			memesList.add("doge");
			memesList.add("cute animals");
			memesList.add("grumpy cat");
			memesList.add("dat boi");
			memesList.add("willy wonka");
			memesList.add("harambe");
			memesList.add("john cena");
			memesList.add("pawn stars");
			memesList.add("pepe");
			memesList.add("ken bone");
			memesList.add("bad luck brian");
			memesList.add("michael phelps");
			memesList.add("spongebob");
			memesList.add("pope");
			memesList.add("arthur");
			memesList.add("danny phantom");
			memesList.add("boi");
			memesList.add("battlefield 1");
			memesList.add("icup");
		}
		
		/**
		 * Adds items to politicalTopicList for use in Chatbot.
		 */
		private void buildPoliticalTopicsList()
		{
			politicalTopicList.add("Democrat");
			politicalTopicList.add("Republican");
			politicalTopicList.add("11/8/16");
			politicalTopicList.add("liberal");
			politicalTopicList.add("conservative");
			politicalTopicList.add("Clinton");
			politicalTopicList.add("Trump");
			politicalTopicList.add("Kaine");
			politicalTopicList.add("Pence");
			politicalTopicList.add("Stein");
			politicalTopicList.add("Johnson");
			politicalTopicList.add("election");
			politicalTopicList.add("Obama");
			politicalTopicList.add("Washington");
			politicalTopicList.add("Lincoln");
			politicalTopicList.add("Jackson");
			politicalTopicList.add("Hillary");
			politicalTopicList.add("McCain");
		}
		
		/**
		 * Checks the length of the supplied string. Returns false if the supplied String is empty or null,
		 * otherwise returns true. 
		 * @param currentInput
		 * @return A true or false based on the length of the supplied String.
		 */
		public boolean lengthChecker(String currentInput)
		{
			boolean hasLength = false;
			
			if (currentInput != null && currentInput.length() > 0)
			{
				hasLength = true;
			}
			
			return hasLength; 
		}
		
		/**
		 * Checks if the supplied String matches the content area for this Chatbot instance.
		 * @param currentInput The supplied String to be checked.
		 * @return Whether it matches the content area.
		 */
		public boolean contentChecker(String currentInput)
		{
			boolean hasContent = false;
			
			String temp = "⎲";
			String temp1 = "⎳";
			System.out.println(temp);
			System.out.println(temp1);
			
			if (currentInput.toLowerCase().contains(content.toLowerCase()))
			{
				hasContent = true;
			}
			
			return hasContent;
		}
		
		/**
		 * Checks if supplied String matches ANY of the topics in the politicalTopicsList. Returns
		 * true if it does find a match and false if it does not match.
		 * @param currentInput The supplied String to be checked.
		 * @return Whether the String is contained in the ArrayList.
		 */
		public boolean politicalTopicChecker(String currentInput)
		{
			boolean hasPoliticalTopic = false;
			int index;
			
			for (index = 0; index < 18; index++)
			{
				if (currentInput.equals(politicalTopicList.get(index)))
				{
					hasPoliticalTopic = true;
				}
			}
			return hasPoliticalTopic;
		}

		/**
		 * Checks to see that the supplied String value is in the current memesList variable.
		 * @param currentInput The supplied String to be checked.
		 * @return Whether the supplied String is a recognized meme.
		 */
		public boolean memeChecker(String currentInput)
		{
			boolean hasMemes = false;
			int index;
			
			for (index = 0; index < 19; index++)
			{
				if (currentInput.equalsIgnoreCase(memesList.get(index)))
				{
					hasMemes = true;
				}
			}
			return hasMemes;
		}
		
		/**
		 * Returns the username of this Chatbot instance.
		 * @return The username of the Chatbot.
		 */
		public String getUserName()
		{
			return userName;
		}
		
		/**
		 * Returns the content area for this Chatbot instance.
		 * @return The content area for this Chatbot instance.
		 */
		public String getContent()
		{
			return content;
		}
		
		/**
		 * Getter method for the memesList object.
		 * @return The reference to the meme list.
		 */
		public ArrayList<String> getMemesList()
		{
			return memesList;
		}
		
		/**
		 * Getter method for the politicalTopicList object.
		 * @return The reference to the political topic list.
		 */
		public ArrayList<String> getPoliticalTopicList()
		{
			return politicalTopicList;
		}
		
		/**
		 * Updates the content area for this Chatbot instance.
		 * @param content The updated value for the content area.
		 */
		public void setContent(String content)
		{
			this.content = content;
		}
		
		/**
		 * Checks to see if currentInput is keyboard mash.
		 * @param currentInput The supplied string to be checked.
		 * @return keyboardMash Returns whether the string is keyboard mashing.
		 */
		public boolean keyboardMashChecker(String currentInput)
		{
			boolean keyboardMash = false;
			
			if (currentInput.equals("sdf"))
			{
				keyboardMash = true;
			}
			
			if (currentInput.equals("dfg"))
			{
				keyboardMash = true;
			}
			
			if (currentInput.equals("cvb"))
			{
				keyboardMash = true;
			}
			
			if (currentInput.equals(",./"))
			{
				keyboardMash = true;
			}
			
			return keyboardMash;
		}
		
		/**
		 * Checks if currentInput is HTML.
		 * @param currentInput The supplied string to be checked.
		 * @return inputHTML Returns if the string is HTML or not.
		 */
		public boolean inputHTMLChecker(String currentInput)
		{
			boolean inputHTML = false;
			
			if(currentInput.contains("<P>"))
			{
				inputHTML = true;
			}
			else if(currentInput.contains("<A HREF=\""))
			{
				int index = currentInput.indexOf("<A HREF=\"") + 9;
				String sub = currentInput.substring(index);
				
				if(sub.contains("\">"))
				{
					int index2 = sub.indexOf("\">");
					String sub2 = sub.substring(index2);
					
					if(sub2.contains("</a>"))
					{
						inputHTML = true;
					}
				}
			}
			else if(currentInput.contains("<"))
			{
				String lower = currentInput.toLowerCase();
				int openIndex1 = lower.indexOf("<") + 1;
				String tag = "";
				
				if(lower.contains(">"))
				{
					 int openIndex2 = lower.indexOf(">");
					 tag = lower.substring(openIndex1, openIndex2);
					 
					 String sub = lower.substring(openIndex2 + 1);
							 
					 if(sub.contains("</" + tag + ">")) 
					 {
						 inputHTML = true;
					 }
				}
			}
			
//			boolean inputHTML = false;
//			
//			if(currentInput.contains("<B>"))
//			{
//				int index = currentInput.indexOf("<B>") + 3;
//				String sub = currentInput.substring(index);
//				
//				if(sub.contains("</B>"))
//				{
//					inputHTML = true;
//				}
//			}
//			
//			else if(currentInput.contains("<I>"))
//			{
//				int index = currentInput.indexOf("<I>") + 3;
//				String sub = currentInput.substring(index);
//				
//				if(sub.contains("</i>"))
//				{
//					inputHTML = true;
//				}
//			}
//			
//			else if(currentInput.contains("<P>"))
//			{
//				inputHTML = true;
//			}
//			
//			else if(currentInput.contains("<A HREF=\""))
//			{
//				int index = currentInput.indexOf("<A HREF=\"") + 9;
//				String sub = currentInput.substring(index);
//				
//				if(sub.contains("\">"))
//				{
//					int index2 = sub.indexOf("\">");
//					String sub2 = sub.substring(index2);
//					
//					if(sub2.contains("</a>"))
//					{
//						inputHTML = true;
//					}
//				}
//			}
			return inputHTML;
		}
		
		/**
		 * Check to see if the currentInput starts with a "#" or "@"
		 * @param currentInput The supplied string to be checked.
		 * @return isTwitter Returns whether currentInput is usable in Twitter.
		 */
		public boolean twitterChecker(String currentInput)
		{
			boolean isTwitter = false;
			
			if(currentInput.contains("#"))
			{
				int index = currentInput.indexOf("#");
				String sub = currentInput.substring(index + 1);
				
				if(index == 0)
				{
					if(sub.length() >= 1)
					{
						isTwitter = true;
					}
				}
			}
			else if(currentInput.contains("@"))
			{
				int index = currentInput.indexOf("@");
				String sub = currentInput.substring(index +1);
				
				if(index == 0)
				{
					if (sub.length() >= 1)
					{
						isTwitter = true;
					}
				}
			}
			
			return isTwitter;
		}
		
		/**
		 * Checks if the currentInput says quit so that it can quit the program 
		 * @param currentInput The supplied string to be checked.
		 * @return quit Returns whether the user wants to quit or not.
		 */
		public boolean quitChecker(String currentInput)
		{
			boolean quit = false;
			
			if (currentInput.equals("quit"))
			{
				quit = true;
			}
			
			return quit;
		}
		}

