package RssNotifier.data;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.time.Instant;
import java.util.List;

public class RssSource {
	public String url;
	public Instant lastChecked;
	public List<Subscriber> subscribers;

	public RssSource(String url, Instant lastChecked, List<Subscriber> subscribers) {
		this.url = url;
		this.lastChecked = lastChecked;
		this.subscribers = subscribers;
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, "lastChecked");
	}

	@Override
	public boolean equals(Object other) {
		return EqualsBuilder.reflectionEquals(this, other, "lastChecked");
	}
}
