package li.xmb.document_organizer.title.confidence_factor;

import org.jsoup.nodes.Element;

import li.xmb.document_organizer.title.utils.TitleUtil;
import li.xmb.document_organizer.utils.HtmlUtil;

public class SentenceLengthConfidenceFactor implements IConfidenceFactor
{
	private static final int BEST_LENGTH = 4;
	private static final int EXPONENTIAL_FACTOR = 2;
	private static final int MAX_FACTOR = 100;
	private static final int DEDUCTION_FACTOR = 6;
	
	private Element htmlElement;

	public SentenceLengthConfidenceFactor (Element htmlElement)
	{
		this.htmlElement = htmlElement;
	}

	@Override
	public int getFactor ()
	{
		//TODO: return 100 or 0
		final int sentenceLength = HtmlUtil.removeSpecialChars(TitleUtil.normalizeTitle(htmlElement.text())).split(" ").length;	
		final int factor =(int) (-Math.pow((sentenceLength -BEST_LENGTH),EXPONENTIAL_FACTOR) * DEDUCTION_FACTOR + MAX_FACTOR);
		
		if (factor>0){
			return factor;
		}

		return 0;
	}

}
