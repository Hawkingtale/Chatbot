package chat.Model;

import chat.controller.ChatController;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.Paging;
import twitter4j.Status;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class CTECHTwitter
{
	private ChatController baseController;
	private Twitter weebBot;
	private ArrayList<Status> tweetedWords;
	private ArrayList<String> allTheTweets;
	
	public CTECHTwitter(ChatController baseController)
	{
		this.baseController = baseController;
		tweetedWords = new ArrayList<Status>();
		allTheTweets = new ArrayList<String>();
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
	
	private String [] createIgnoredWordsArray()
	{
		String [] boringWords;
		int wordCount = 0;
		
		Scanner boringWordScanner = new Scanner(this.getClass().getResourceAsStream("commonWords"));
		while(boringWordScanner.hasNextLine())
		{
			wordCount++;
		}
		boringWordScanner.close();
		
		boringWords = new String[wordCount];
		
		boringWordScanner = new Scanner(this.getClass().getResourceAsStream("commonWords.txt"));
		
		for(int index = 0; index < boringWords.length; index++)
		{
			boringWords[index] = boringWordScanner.next();
		}
		boringWordScanner.close();
		
		return boringWords;
	}
	
	private void collectTweets(String username)
	{
		
	}
	
	public String getMostCommonWord()
	{
		gatherTheTweets(username);
		turnTweetsToWords();
		removeBoringWords();
		removeBlankWords();
		
		String information = "The tweetcount is " + allTheTweets.size() + "and the word count after removal is" + tweetedWords.size();
		return "";
	}
	
	public void removeBlankWords()
	{
		for(int index = 0; index < tweetedWords.size(); index++)
		{
			
			if(tweetedWords.get(index).trim().equals(""))
			{
				tweetedWords.remove(index);
				index--;
			}
		}
	}
	
	public void removeBoringWords()
	{
		String [] boringWords = createIgnoredWordsArray();
		
		for(int index = 0; index < tweetedWords.size(); index++)
		{
			for(int boringIndex = 0; index < boringWords.length; boringIndex++)
			{
				if(tweetedWords.get(index).equalsIgnoreCase(boringWords[boringIndex]))
				{
					tweetedWords.remove(index);
					index--;
					boringIndex = boringWords.length;
				}
			}
		}
	}
	private void turnTweetsToWords()
	{
		for(Status currentTweet : allTheTweets)
		{
			String text = currentTweet.getText();
			String [] tweetWords = text.split(" ");
			
		}
	}
	
	public void gatherTheTweets(String user)
	{
		tweetedWords.clear();
		allTheTweets.clear();
		int pageCount = 1;
		
		Paging statusPage = new Paging(1,100);
		
		while(pageCount <= 10)
		{
			try
			{
			allTheTweets.addAll(weebBot.getUserTimeline(user, statusPage));
			}
			catch(TwitterException twitterError)
			{
				baseController.handleErrors(twitterError);
			}
		
			
			pageCount++;
		}
	}
	
	private String calculateTopWord()
	{
		String results = "";
		String topWord = "";
		int mostPopularIndex = 0;
		int popularCount = 0;
		
		for (int index = 0; index < tweetedWords.size(); index++)
		{
			int currentPopularity = 0;
			for(int searched = index + 1; searched < tweetedWords.size(); searched++)
			{
				if(tweetedWords.get(index)equalsIgnoreCase(tweetedWords.get(searched)))
				{
					currentPopularity++;
				}
				
			}
			if(currentPopularity > popularCount)
			{
				popularCount = currentPopularity;
				mostPopularIndex = index;
				topWord = tweetedWords.get(mostPopularIndex);
			}
		}
		results += " the most popular word was " + topWord + ", and it occured " + popularCount + " times";
		results += "\nThat means it has a percent of " +((double)popularCount)/tweetedWords.size() + "%";
		
		return results;
	}
	
	
	
	
	
	
}
