package ru.r2cloud.jradio;


public interface TaggedStream {

	void addTag(Tag tag);
	
	Tag getTag(long sampleId);
	
}