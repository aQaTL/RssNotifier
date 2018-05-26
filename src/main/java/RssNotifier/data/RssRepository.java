package RssNotifier.data;

import java.util.List;

public interface RssRepository {
	List<RssSource> findRssSources();
}
