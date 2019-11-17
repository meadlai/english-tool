package com.kmboot.english.model;

import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RawJson {
	private int result;
	private int Id;
	private RawTitle title;
	private String message;
	private List<RawData> data;
}

@Data
@ToString
class RawTitle {
	private int Category;
	private String CreateTime;
	private String Sound;
	private String Pic;
	private int Flag;
	private String Type;
	private String DescCn;
	private String Title_cn;
	private int series;
	private String CategoryName;
	private int Id;
	private int ReadCount;
}

@Data
@ToString
class RawData {
	private String EndTiming;
	private String ParaId;
	private String IdIndex;
	private String Sentence_cn;
	private String Timing;
	private String Sentence;
}
