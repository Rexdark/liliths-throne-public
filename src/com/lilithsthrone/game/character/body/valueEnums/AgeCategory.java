package com.lilithsthrone.game.character.body.valueEnums;

import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.settings.ContentPreferenceValue;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.2.11
 * @version 0.2.11
 * @author Innoxia
 */
public enum AgeCategory {
	
		// Infant and toddler stages
		BABY("newborn age", 0, 1, PresetColour.AGE_TEENS, ContentPreferenceValue.ZERO_NONE),
		
		TODDLER("toddler age", 1, 3, PresetColour.AGE_TEENS, ContentPreferenceValue.ZERO_NONE),
		
		PRESCHOOL("preschool age", 3, 6, PresetColour.AGE_TEENS, ContentPreferenceValue.ZERO_NONE),
		
		// Childhood ONE_MINIMAL
		CHILDHOOD_EARLY("early childhood", 6, 8, PresetColour.AGE_TEENS, ContentPreferenceValue.ONE_MINIMAL),
		
		CHILDHOOD_MIDDLE("middle childhood", 8, 12, PresetColour.AGE_TEENS, ContentPreferenceValue.TWO_LOW),
		
		CHILDHOOD_LATE("late childhood", 12, 14, PresetColour.AGE_TEENS, ContentPreferenceValue.TWO_LOW),
		
		// Teenage stages
		TEENS_EARLY("early teens", 14, 16, PresetColour.AGE_TEENS, ContentPreferenceValue.THREE_AVERAGE),
		
		TEENS_MIDDLE("middle teens", 16, 18, PresetColour.AGE_TEENS, ContentPreferenceValue.FOUR_HIGH),
		
		TEENS_LATE("late teens", 18, 20, PresetColour.AGE_TEENS, ContentPreferenceValue.FOUR_HIGH),
		
		// Twenties stages
		TWENTIES_EARLY("early twenties", 20, 23, PresetColour.AGE_TWENTIES, ContentPreferenceValue.FIVE_ABUNDANT),
		
		TWENTIES_MIDDLE("mid-twenties", 23, 27, PresetColour.AGE_TWENTIES, ContentPreferenceValue.FIVE_ABUNDANT),
		
		TWENTIES_LATE("late twenties", 27, 30, PresetColour.AGE_TWENTIES, ContentPreferenceValue.FOUR_HIGH),
		
		// Thirties stages
		THIRTIES_EARLY("early thirties", 30, 33, PresetColour.AGE_THIRTIES, ContentPreferenceValue.THREE_AVERAGE),
		
		THIRTIES_MIDDLE("mid-thirties", 33, 37, PresetColour.AGE_THIRTIES, ContentPreferenceValue.THREE_AVERAGE),
		
		THIRTIES_LATE("late thirties", 37, 40, PresetColour.AGE_THIRTIES, ContentPreferenceValue.TWO_LOW),
		
		// Forties stages
		FORTIES_EARLY("early forties", 40, 43, PresetColour.AGE_FORTIES, ContentPreferenceValue.TWO_LOW),
		
		FORTIES_MIDDLE("mid-forties", 43, 47, PresetColour.AGE_FORTIES, ContentPreferenceValue.ONE_MINIMAL),
		
		FORTIES_LATE("late forties", 47, 50, PresetColour.AGE_FORTIES, ContentPreferenceValue.ONE_MINIMAL),
		
		// Fifties stages
		FIFTIES_EARLY("early fifties", 50, 53, PresetColour.AGE_FIFTIES, ContentPreferenceValue.ONE_MINIMAL),
		
		FIFTIES_MIDDLE("mid-fifties", 53, 57, PresetColour.AGE_FIFTIES, ContentPreferenceValue.ZERO_NONE),
		
		FIFTIES_LATE("late fifties", 57, 60, PresetColour.AGE_FIFTIES, ContentPreferenceValue.ZERO_NONE),
		
		// Sixties and beyond
		SIXTIES_EARLY("early sixties", 60, 63, PresetColour.AGE_SIXTIES, ContentPreferenceValue.ZERO_NONE),
		
		SIXTIES_MIDDLE("mid-sixties", 63, 67, PresetColour.AGE_SIXTIES, ContentPreferenceValue.ZERO_NONE),
		
		SIXTIES_LATE("late sixties", 67, 70, PresetColour.AGE_SIXTIES, ContentPreferenceValue.ZERO_NONE),
		
		SIXTIES_PLUS("past seventy", 70, Integer.MAX_VALUE, PresetColour.AGE_SIXTIES, ContentPreferenceValue.ZERO_NONE);


	private String name;
	private int minimumAge;
	private int maximumAge;
	private Colour colour;
	private ContentPreferenceValue agePreferenceDefault;

	private AgeCategory(String name, int minimumAge, int maximumAge, Colour colour, ContentPreferenceValue agePreferenceDefault) {
		this.name = name;
		this.minimumAge = minimumAge;
		this.maximumAge = maximumAge;
		this.colour = colour;
		this.agePreferenceDefault = agePreferenceDefault;
	}

	public int getMinimumValue() {
		return minimumAge;
	}

	public int getMaximumValue() {
		return maximumAge;
	}
	
	public int getMedianValue() {
		return minimumAge + (maximumAge - minimumAge) / 2;
	}

	public static AgeCategory valueOf(int age) {
		if(age<TEENS_LATE.getMinimumValue()) {
			return TEENS_LATE;
		}
		for(AgeCategory f : AgeCategory.values()) {
			if(age>=f.getMinimumValue() && age<f.getMaximumValue()) {
				return f;
			}
		}
		return SIXTIES_PLUS;
	}
	
	public String getName() {
		return name;
	}
	
	public Colour getColour() {
		return colour;
	}

	public ContentPreferenceValue getAgePreferenceDefault() {
		return agePreferenceDefault;
	}
	
	public static int getAgeFromPreferences(Gender gender) {
		AgeCategory category;
		try {
			category = Util.getRandomObjectFromWeightedMap(Main.getProperties().agePreferencesMap.get(gender.getType()));
		} catch(Exception ex) {
			category = AgeCategory.TWENTIES_MIDDLE;
		}
		if(category==null) {
			category = AgeCategory.TWENTIES_MIDDLE;
		}
		
		int lowerBound = category.getMinimumValue();
		int upperBound = category.getMaximumValue();
		return lowerBound + Util.random.nextInt(upperBound-lowerBound);
	}
}
