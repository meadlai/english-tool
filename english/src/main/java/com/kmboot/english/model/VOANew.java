package com.kmboot.english.model;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.kmboot.english.service.EnglishException;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class VOANew {
	private int id;
	@Setter(AccessLevel.NONE)
	private String title;
	@Setter(AccessLevel.NONE)
	private String url;
	@Setter(AccessLevel.NONE)
	private String textCN;
	@Setter(AccessLevel.NONE)
	private String textEN;
	@Setter(AccessLevel.NONE)
	private String json;
	@Setter(AccessLevel.NONE)
	private String create;
	@Setter(AccessLevel.NONE)
	private RawJson raw;

	public VOANew(String url, String title, String json) {
		this.url = url;
		this.title = title;
		this.json = json;
		//
		this.digestText();
	}

	private void digestText() {
		Gson gson = new Gson();
		this.raw = gson.fromJson(json, RawJson.class);
		log.info("Digest the row json:\n\r {}", this.raw);
		if (this.raw == null) {
			throw new EnglishException("##get json null:  " + json);
		}
		this.id = this.raw.getTitle().getId();
		if (StringUtils.isNotBlank(this.raw.getTitle().getTitle_cn())) {
			this.title = this.raw.getTitle().getTitle_cn();
		}

		if (this.raw.getData() == null) {
			throw new EnglishException("##get data null:  " + json);
		}

		this.create = this.raw.getTitle().getCreateTime();

		StringBuilder tcn = new StringBuilder();
		StringBuilder ten = new StringBuilder();
		this.raw.getData().stream().forEach(d -> {
			tcn.append(d.getSentence_cn());
			ten.append(d.getSentence());
		});
		this.textCN = tcn.toString();
		this.textEN = ten.toString();

	}

	public static void main(String[] arg) {
		String raw = "{\"result\":1,\"data\":[{\"EndTiming\":\"9.8\",\"ParaId\":\"1\",\"IdIndex\":\"1\",\"Sentence_cn\":\"对于加利福尼亚天堂镇的居民来说，2018年11月8日的清晨与往常一样，但很快，噩梦就到来了。\",\"Timing\":\"0.1\",\"Sentence\":\"For the residents of California states, Paradise Town, November 8, 2018 began like any other day, but very quickly, things changed.\"},{\"EndTiming\":\"11.0\",\"ParaId\":\"2\",\"IdIndex\":\"1\",\"Sentence_cn\":\"我们根本没收到警告，\",\"Timing\":\"9.9\",\"Sentence\":\"We never got a warning.\"},{\"EndTiming\":\"13.5\",\"ParaId\":\"3\",\"IdIndex\":\"1\",\"Sentence_cn\":\"没有电话也没有短信警报，\",\"Timing\":\"11.1\",\"Sentence\":\"There was no phone call or text message alert,\"},{\"EndTiming\":\"22.5\",\"ParaId\":\"4\",\"IdIndex\":\"1\",\"Sentence_cn\":\"就看到有浓浓的黑烟，我的一位朋友让我们赶紧走，我们觉得她疯了。\",\"Timing\":\"13.6\",\"Sentence\":\"but we just saw black clouds and a friend of us told us we needed to leave, but we thought it was crazy.\"},{\"EndTiming\":\"30.0\",\"ParaId\":\"5\",\"IdIndex\":\"1\",\"Sentence_cn\":\"我知道大事要发生了。我看见橘红色的火光从后面的房屋上冒出。\",\"Timing\":\"22.6\",\"Sentence\":\"I knew something was coming. I saw it glowing red and orange out my back\\u2026out my back\\u2026off of my back deck.\"},{\"EndTiming\":\"40.5\",\"ParaId\":\"6\",\"IdIndex\":\"1\",\"Sentence_cn\":\"从早上6:30开始，这场2018年最致命的大火夺走了84条生命，让数千居民流离失所。\",\"Timing\":\"30.1\",\"Sentence\":\"Starting around 6:30 in the morning, it was reported to be the deadliest wildfire in 2018, taking 84 lives and displacing thousands.\"},{\"EndTiming\":\"45.5\",\"ParaId\":\"7\",\"IdIndex\":\"1\",\"Sentence_cn\":\"这场大火似乎没有尽头，居民丢下一切纷纷逃跑。\",\"Timing\":\"40.6\",\"Sentence\":\"Not having a destination, residents had to flee, leaving everything behind.\"},{\"EndTiming\":\"55.7\",\"ParaId\":\"8\",\"IdIndex\":\"1\",\"Sentence_cn\":\"当时有很多人没买保险，或者保险额度根本不够，而我们的县也根本毫无作为。\",\"Timing\":\"45.6\",\"Sentence\":\"So many people who were uninsured or underinsured and already our county services were stretched thin.\"},{\"EndTiming\":\"61.3\",\"ParaId\":\"9\",\"IdIndex\":\"1\",\"Sentence_cn\":\"在天堂山下坐落着一个叫奇科的小城市。\",\"Timing\":\"55.8\",\"Sentence\":\"Just down from hills of Paradise lies a small city called Chico.\"},{\"EndTiming\":\"67.5\",\"ParaId\":\"10\",\"IdIndex\":\"1\",\"Sentence_cn\":\"鲁比娜·汗，是一位巴基斯坦裔美国人，也是一位奇科居民，她知道必须做些什么来帮忙。\",\"Timing\":\"61.4\",\"Sentence\":\"Rubina Khan, a Pakistani American and a Chico resident knew she had to do something to help.\"},{\"EndTiming\":\"74.1\",\"ParaId\":\"11\",\"IdIndex\":\"1\",\"Sentence_cn\":\"首先我打电话给拉莎说，姐姐，做好准备，我们有很多事要做。\",\"Timing\":\"70.0\",\"Sentence\":\"First of all I called Rasha and said, sister, get ready, we have to do a lot.\"},{\"EndTiming\":\"78.6\",\"ParaId\":\"12\",\"IdIndex\":\"1\",\"Sentence_cn\":\"这是一场严重的灾难，但没有人意识到其严重性。\",\"Timing\":\"74.2\",\"Sentence\":\"It\\u2019s an enormous disaster yet people do not know how serious the matter is.\"},{\"EndTiming\":\"84.1\",\"ParaId\":\"13\",\"IdIndex\":\"1\",\"Sentence_cn\":\"起初姐姐和其他人都认为我夸大其词，认为那时候根本不需要开放避难所。\",\"Timing\":\"78.7\",\"Sentence\":\"Initially, her and everyone else thought I was exaggerating and there was no need to open a shelter for the minute.\"},{\"EndTiming\":\"99.9\",\"ParaId\":\"14\",\"IdIndex\":\"1\",\"Sentence_cn\":\"我们分发衣服，化妆品，食物，礼品卡，最后我们会去帮助那些找到公寓或居所的人。\",\"Timing\":\"84.5\",\"Sentence\":\"We were giving out clothing, toiletries, food, gift cards and right at the end we were helping people whoever found an apartment and a place to stay.\"},{\"EndTiming\":\"108.0\",\"ParaId\":\"15\",\"IdIndex\":\"1\",\"Sentence_cn\":\"大约26500名天堂镇居民在奇科无家可归，其中很多是老年人。\",\"Timing\":\"100.0\",\"Sentence\":\"Approximately 26,500 Paradise residents were displaced in Chico, a large number of them were elderly.\"},{\"EndTiming\":\"122.1\",\"ParaId\":\"16\",\"IdIndex\":\"1\",\"Sentence_cn\":\"鲁比娜在当地穆斯林社区的帮助下，安排了救灾物资的发放，鲁比娜和拉莎在接下来的三个月里继续提供帮助。\",\"Timing\":\"112.1\",\"Sentence\":\"Rubina, with the help of the local Muslim community, arranged distribution of relief goods and both Robina and Rasha continued helping for the next three months.\"},{\"EndTiming\":\"127.6\",\"ParaId\":\"17\",\"IdIndex\":\"1\",\"Sentence_cn\":\"一年后，穆斯林社区的成员相信大火把所有人的距离拉近了。\",\"Timing\":\"122.2\",\"Sentence\":\"A year later, members of the Muslim community believed the fire brought everyone closer together.\"},{\"EndTiming\":\"130.9\",\"ParaId\":\"18\",\"IdIndex\":\"1\",\"Sentence_cn\":\"我们生活的状况发生了很大改变。\",\"Timing\":\"127.7\",\"Sentence\":\"The reality of our life over here has changed.\"},{\"EndTiming\":\"140.5\",\"ParaId\":\"19\",\"IdIndex\":\"1\",\"Sentence_cn\":\"我们从一个封闭的、与天堂镇、马加里亚、康库和被烧毁的其它地区相隔离的社区，\",\"Timing\":\"131.0\",\"Sentence\":\"We have gone from a community that was kind of separate and secluded, in Chico area from Paradise, Magalia, Concow and various areas that got burned,\"},{\"EndTiming\":\"144.7\",\"ParaId\":\"20\",\"IdIndex\":\"1\",\"Sentence_cn\":\"成为了一个拥有共同追求并能够出一份力的社区。\",\"Timing\":\"140.6\",\"Sentence\":\"to a community where everybody feels like part of the same exact thing.\"},{\"EndTiming\":\"150.7\",\"ParaId\":\"21\",\"IdIndex\":\"1\",\"Sentence_cn\":\"由于穆斯林团体在世界以及在这个城镇中的一些政治原因，\",\"Timing\":\"144.8\",\"Sentence\":\"Because of all the political issues we\\u2019ve had in this world and in this town,\"},{\"EndTiming\":\"158.5\",\"ParaId\":\"22\",\"IdIndex\":\"1\",\"Sentence_cn\":\"我决定挺身而出去帮助大家，把我们的城镇建设成一个更好的居住地。\",\"Timing\":\"150.8\",\"Sentence\":\"so I stepping up and helping had made it\\u2026I think had made our town a better place to live in.\"},{\"EndTiming\":\"164.9\",\"ParaId\":\"23\",\"IdIndex\":\"1\",\"Sentence_cn\":\"以往人们会误以为穆斯林就是恐怖分子，\",\"Timing\":\"160.1\",\"Sentence\":\"What came out of that was that people who had the misconception that Muslims are terrorists.\"},{\"EndTiming\":\"170.3\",\"ParaId\":\"24\",\"IdIndex\":\"1\",\"Sentence_cn\":\"但当他们第一次接触我们后，他们发现我们也是普通人，也关心人性，\",\"Timing\":\"165.0\",\"Sentence\":\"They saw for the first time that a Muslim is like any other human being and that they care about humanity.\"},{\"EndTiming\":\"173.0\",\"ParaId\":\"25\",\"IdIndex\":\"1\",\"Sentence_cn\":\"我们把人性置于宗教之前。\",\"Timing\":\"170.4\",\"Sentence\":\"Religion is second, humanity is first.\"},{\"EndTiming\":\"177.4\",\"ParaId\":\"26\",\"IdIndex\":\"1\",\"Sentence_cn\":\"在一场毁灭性的灾难后，这个团结的社区正继续前进。\",\"Timing\":\"173.1\",\"Sentence\":\"In the wake of devastating loss, a united community carries on.\"},{\"EndTiming\":\"181.5\",\"ParaId\":\"27\",\"IdIndex\":\"1\",\"Sentence_cn\":\"努赫巴特·马利克，美国之音新闻，奇科，加利福尼亚州报道。\",\"Timing\":\"177.5\",\"Sentence\":\"Nukhbat Malik, VOA News, Chico, California.\"}],\"Id\":\"11315\",\"title\":{\"Category\":\"101\",\"CreateTime\":\"2019-11-11 05:31:00.0\",\"Title\":\"Community Reflects One Year After Devastating California Fires\",\"Sound\":\"http://static.iyuba.cn/video/voa/11315.mp4\",\"Pic\":\"http://static.iyuba.cn/images/voa/11315.jpg\",\"Flag\":\"2\",\"Type\":\"csvoa\",\"DescCn\":\"对于加利福尼亚天堂镇的居民来说，2018年11月8日的清晨与往常一样，但很快，噩梦就到来了。从早上6:30开始，这场2018年最致命的大火夺走了84条生命，让数千居民流离失所。\",\"Title_cn\":\"加利福尼亚大火一年后的社区反思\",\"series\":\"0\",\"CategoryName\":\"美国\",\"Id\":\"11315\",\"ReadCount\":\"3305\"},\"message\":\"success\"}";
		Gson gson = new Gson();
		RawJson rjson = gson.fromJson(raw, RawJson.class);
		log.info("JSON Object is {}", rjson);
	}

}
