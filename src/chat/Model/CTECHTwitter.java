package chat.Model;

import chat.controller.ChatController;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.Status;
import java.util.List;
import java.util.ArrayList;

public class CTECHTwitter
{
	private ChatController baseController;
	private Twitter weebBot;
	private List<Status> searchedTweets;
	private List<String> ignoredWords;
	
	public CTECHTwitter(ChatController baseController)
	{
		this.baseController = baseController;
		searchedTweets = new ArrayList<Status>();
		ignoredWords = new ArrayList<String>();
		weebBot = TwitterFactory.getSingleton();
	}
	
	public void sendTweet(String textToTweet)
	{
		try
		{
			weebBot.updateStatus("I Matthew Hachtel just tweeted from my Java CHatbot program 2017! #APCSRocks @CTECNow "
					+ "thanks @cscheerleader & @codyhenrichsen! @ChatbotCTEC");
		}
		catch(TwitterException tweetError)
		{
			baseController.handleErrors(tweetError);
		}
		catch(Exception OtherError)
		{
			baseController.handleErrors(OtherError);
		}
		
	
	}
	
	private void creatIgnoredWordList()
	{
		
	}
	
	private void collectTweets(String username)
	{
		
	}
	
	public String getMostCommonWord()
	{
		return null;
	}
	
}
