package com.paypal.library.entities;

import javax.persistence.Embeddable;


/**
 * @author Sandip
 *
 */

@Embeddable
public class BookRatings {

	private Integer storyRating;
	private Integer printQualityRating;
	private Integer languageAccuracy;
	
	public BookRatings() {
	}
	
	public BookRatings(Integer storyRating, Integer  printRating, Integer languageAccuracy) {
		this.storyRating=storyRating;
		this.printQualityRating=printRating;
		this.languageAccuracy=languageAccuracy;
	}

	public Integer getStoryRating() {
		return storyRating;
	}

	public Integer getPrintQualityRating() {
		return printQualityRating;
	}

	public Integer getLanguageAccuracy() {
		return languageAccuracy;
	}

	public void setStoryRating(Integer storyRating) {
		this.storyRating = storyRating;
	}

	public void setPrintQualityRating(Integer printQualityRating) {
		this.printQualityRating = printQualityRating;
	}

	public void setLanguageAccuracy(Integer languageAccuracy) {
		this.languageAccuracy = languageAccuracy;
	}
	
}
