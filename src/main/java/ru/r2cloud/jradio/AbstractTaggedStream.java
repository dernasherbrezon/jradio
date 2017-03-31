package ru.r2cloud.jradio;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractTaggedStream implements TaggedStream {
	
	private final Map<Long, Tag> tags = new HashMap<Long, Tag>(); 

	@Override
	public void addTag(Tag tag) {
		tags.put(tag.getSample(), tag);
	}
	
	@Override
	public Tag getTag(long sampleId) {
		return tags.get(sampleId);
	}
	
	public Map<Long, Tag> getTags() {
		return tags;
	}

}
