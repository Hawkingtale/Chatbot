package chat.Model;

import chat.controller.ChatController;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.Paging;
import twitter4j.Status;
import java.util.List;
import java.util.Scanner;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class CTECHTwitter
{
	private ChatController baseController;
	private Twitter weebBot;
	private ArrayList<String> tweetedWords;
	private ArrayList<Status> allTheTweets;
	
	public CTECHTwitter(ChatController baseController)
	{
		this.baseController = baseController;
		tweetedWords = new ArrayList<String>();
		allTheTweets = new ArrayList<Status>();
		weebBot = TwitterFactory.getSingleton();
	}
	
	
	
	public void sendTweet(String textToTweet)
	{
		try
		{
			weebBot.updateStatus("I Matthew Hachtel just tweeted from my Java Chatbot program 2017! #APCSRocks @CTECNow "
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
		tweetedWords.clear();
		allTheTweets.clear();
		
		Paging statusPage = new Paging(1,100);
		int pageCount = 1;
		
		while(pageCount <= 10)
		{
			try
			{
				allTheTweets.addAll(weebBot.getUserTimeline(username, statusPage));
			}
			catch(TwitterException twitterError)
			{
				baseController.handleErrors(twitterError);
			}
			pageCount++;
		}
	}
	
	public String getMostCommonWord()
	{
	String results = "";
	collectTweets(results);
	turnStatusToWords();
	
	removeBlankWords();
	removeBoringWords();
	
	results += " from " + results + calculateTopWordAndCount();
	return results;
	}
	
	public void removeBlankWords()
	{
		for(int index = 0; index < allTheTweets.size(); index++)
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
		
		for(int index = 0; index < allTheTweets.size(); index++)
		{
			for(int boringIndex = 0; index < boringWords.length; boringIndex++)
			{
				if(tweetedWords.get(index).equalsIgnoreCase(boringWords[boringIndex]))
				{
					allTheTweets.remove(index);
					index--;
					boringIndex = boringWords.length;
				}
			}
		}
	}
	
	private void turnStatusToWords()
	{
		for(Status currentTweet : allTheTweets)
		{
			String tweetText = currentTweet.getText();
			String [] tweetWords = tweetText.split(" ");
			for(String word : tweetWords)
			{
				tweetedWords.add(word);
			}
			
		}
	}
	
	public void gatherTheTweets(String user)
	{
		allTheTweets.clear();
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
	
	private String calculateTopWordAndCount()
	{
		String results = "";
		String topWord = "";
		int mostPopularIndex = 0;
		int popularCount = 0;
		
		for (int index = 0; index < allTheTweets.size(); index++)
		{
			int currentPopularity = 0;
			for(int searched = index + 1; searched < tweetedWords.size(); searched++)
			{
				if(tweetedWords.get(index).equalsIgnoreCase(tweetedWords.get(searched)) && allTheTweets.get(index).equals(topWord))
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
		results += " the most popular word was " + topWord + ", and it occured " + popularCount + " timesout of " + 
		allTheTweets.size() + ", AKA " + (DecimalFormat.getPercentInstance().formatToCharacterIterator(((double) popularCount)/allTheTweets.size()));
	
		return results;
	}
	
	private String removePunctuation(String currentString)
	{
		String punctuation = ".,':;\"(){}[]<>-";
		
		String scrubbedString = "";
		for(int i = 0; i < currentString.length(); i++)
		{
			if(punctuation.indexOf(currentString.charAt(i)) == -1)
			{
				scrubbedString += currentString.charAt(i);
			}
		}
		return scrubbedString;

	}
	
	
	
	
	
	
}
