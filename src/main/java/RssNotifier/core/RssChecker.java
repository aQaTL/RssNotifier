package RssNotifier.core;

import RssNotifier.data.RssRepository;
import RssNotifier.data.RssSource;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

public class RssChecker {
	private RssRepository repository;

	@Autowired
	private Logger coreLogger;

	public RssChecker(RssRepository repository) {
		this.repository = repository;
	}

	public List<RssSource> getNewRssPosts() {
		List<RssSource> rssSources = repository.findRssSources();
		rssSources.removeIf(rssSource -> !rssHasNewPosts(rssSource));
		return rssSources;
	}

	public boolean rssHasNewPosts(RssSource rssSource) {
		SyndFeed feed = fetchRssFeed(rssSource);
		List<SyndEntry> sortedEntries = getSortedEntries(feed);

		Instant lastPostPublishedDate = sortedEntries.get(0).getPublishedDate().toInstant();
		return lastPostPublishedDate.isAfter(rssSource.lastChecked);
	}

	public SyndFeed fetchRssFeed(RssSource rssSource) {
		try {
			return new SyndFeedInput().build(new XmlReader(new URL(rssSource.url)));
		}
		catch (FeedException | IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<SyndEntry> getSortedEntries(SyndFeed feed) {
		List<SyndEntry> entries = feed.getEntries();
		entries.sort(Comparator.comparing(SyndEntry::getPublishedDate));
		return entries;
	}
}
