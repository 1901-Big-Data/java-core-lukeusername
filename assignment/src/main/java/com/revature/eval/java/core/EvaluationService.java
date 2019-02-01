package com.revature.eval.java.core;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalField;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Author: Luke Davis

public class EvaluationService {

	/**
	 * 1. Without using the StringBuilder or StringBuffer class, write a method that
	 * reverses a String. Example: reverse("example"); -> "elpmaxe"
	 * 
	 * @param string
	 * @return
	 */
	public String reverse(String string) {
		char[] reversed = new char[string.length()];
		for (int i = reversed.length - 1, j=0; i >= 0; i--, j++) {
			reversed[j] = string.charAt(i);
		}
		return new String(reversed);
	}

	/**
	 * 2. Convert a phrase to its acronym. Techies love their TLA (Three Letter
	 * Acronyms)! Help generate some jargon by writing a program that converts a
	 * long name like Portable Network Graphics to its acronym (PNG).
	 * 
	 * @param phrase
	 * @return
	 */
	
	/* 
	 * ASCII letters: 65-90, 97-122
	 * */
	public String acronym(String phrase) {
		// preprocessing / formatting
		phrase = phrase.toUpperCase();
		phrase = phrase.replaceAll("[^A-Z]", " ");
		phrase = phrase.replaceAll("  ", " ");
		
		String[] words = phrase.split(" ");
		
		StringBuilder sb = new StringBuilder();
		
		// grab first letter of every word
		for (int i = 0; i < words.length; i++) {
			sb.append(words[i].charAt(0));
		}
		
		return sb.toString();
	}

	/**
	 * 3. Determine if a triangle is equilateral, isosceles, or scalene. An
	 * equilateral triangle has all three sides the same length. An isosceles
	 * triangle has at least two sides the same length. (It is sometimes specified
	 * as having exactly two sides the same length, but for the purposes of this
	 * exercise we'll say at least two.) A scalene triangle has all sides of
	 * different lengths.
	 *
	 */
	
	/*
	 * Equilateral: all three sides are equal length
	 * Isosceles: at least two sides are equal length
	 * Scalene: all sides are different lengths
	 */
	
	static class Triangle {
		private double sideOne;
		private double sideTwo;
		private double sideThree;

		public Triangle() {
			super();
		}

		public Triangle(double sideOne, double sideTwo, double sideThree) {
			this();
			this.sideOne = sideOne;
			this.sideTwo = sideTwo;
			this.sideThree = sideThree;
		}

		public double getSideOne() {
			return sideOne;
		}

		public void setSideOne(double sideOne) {
			this.sideOne = sideOne;
		}

		public double getSideTwo() {
			return sideTwo;
		}

		public void setSideTwo(double sideTwo) {
			this.sideTwo = sideTwo;
		}

		public double getSideThree() {
			return sideThree;
		}

		public void setSideThree(double sideThree) {
			this.sideThree = sideThree;
		}

		public boolean isEquilateral() {
			if (sideOne == sideTwo && sideOne == sideThree) {
				return true;
			}
			else
				return false;
		}

		public boolean isIsosceles() {
			if (sideOne == sideTwo || sideOne == sideThree || sideTwo == sideThree)			
				return true;
			else
				return false;
		}

		public boolean isScalene() {
			if (sideOne == sideTwo || sideOne == sideThree || sideTwo == sideThree)	
				return false;
			else
				return true;
		}

	}

	/**
	 * 4. Given a word, compute the scrabble score for that word.
	 * 
	 * --Letter Values--
	 * A, E, I, O, U, L, N, R, S, T = 1; 
	 * D, G = 2; 
	 * B, C, M, P = 3; 
	 * F, H, V, W, Y = 4; 
	 * K = 5; 
	 * J, X = 8; 
	 * Q, Z = 10; 
	 * 
	 * Examples "cabbage" should be scored as worth 14 points:
	 * 
	 * 3 points for C, 1 point for A, twice 3 points for B, twice 2 points for G, 1
	 * point for E And to total:
	 * 
	 * 3 + 2*1 + 2*3 + 2 + 1 = 3 + 2 + 6 + 3 = 5 + 9 = 14
	 * 
	 * @param string
	 * @return
	 */
	public int getScrabbleScore(String string) {
		int score = 0;
		
		// force uppercase for simplification
		string = string.toUpperCase();
		
		// create hashmap for quick access to the appropriate value
		Map<Character, Integer> scrabbleMap = new HashMap<Character, Integer>();
		scrabbleMap.put('A', 1);
		scrabbleMap.put('B', 3);
		scrabbleMap.put('C', 3);
		scrabbleMap.put('D', 2);
		scrabbleMap.put('E', 1);
		scrabbleMap.put('F', 4);
		scrabbleMap.put('G', 2);
		scrabbleMap.put('H', 4);
		scrabbleMap.put('I', 1);
		scrabbleMap.put('J', 8);
		scrabbleMap.put('K', 5);
		scrabbleMap.put('L', 1);
		scrabbleMap.put('M', 3);
		scrabbleMap.put('N', 1);
		scrabbleMap.put('O', 1);
		scrabbleMap.put('P', 3);
		scrabbleMap.put('Q', 10);
		scrabbleMap.put('R', 1);
		scrabbleMap.put('S', 1);
		scrabbleMap.put('T', 1);
		scrabbleMap.put('U', 1);
		scrabbleMap.put('V', 4);
		scrabbleMap.put('W', 4);
		scrabbleMap.put('X', 8);
		scrabbleMap.put('Y', 4);
		scrabbleMap.put('Z', 10);
	
		// iterate through string, tabulating score
		for (int i = 0; i < string.length(); i++) {
			score += scrabbleMap.get(string.charAt(i));
		}
		
		return score;
	}

	/**
	 * 5. Clean up user-entered phone numbers so that they can be sent SMS messages.
	 * 
	 * The North American Numbering Plan (NANP) is a telephone numbering system used
	 * by many countries in North America like the United States, Canada or Bermuda.
	 * All NANP-countries share the same international country code: 1.
	 * 
	 * NANP numbers are ten-digit numbers consisting of a three-digit Numbering Plan
	 * Area code, commonly known as area code, followed by a seven-digit local
	 * number. The first three digits of the local number represent the exchange
	 * code, followed by the unique four-digit number which is the subscriber
	 * number.
	 * 
	 * The format is usually represented as
	 * 
	 * 1 (NXX)-NXX-XXXX where N is any digit from 2 through 9 and X is any digit
	 * from 0 through 9.
	 * 
	 * Your task is to clean up differently formatted telephone numbers by removing
	 * punctuation and the country code (1) if present.
	 * 
	 * For example, the inputs
	 * 
	 * +1 (613)-995-0253 613-995-0253 1 613 995 0253 613.995.0253 should all produce
	 * the output
	 * 
	 * 6139950253
	 * 
	 * Note: As this exercise only deals with telephone numbers used in
	 * NANP-countries, only 1 is considered a valid country code.
	 */
	public String cleanPhoneNumber(String string) {
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < string.length(); i++) {
			// if a number, add it to the string
			if (string.charAt(i) >= 48 && string.charAt(i) <= 57) {
				sb.append(string.charAt(i));
			}
			// if its one of these, do nothing (which effectively removes them
			else if (string.charAt(i) == 32 || string.charAt(i) == 40 || string.charAt(i) == 41 || 
					string.charAt(i) == 45 || string.charAt(i) == 46) {
			}
			// if anything else, the input is invalid
			else
				throw new IllegalArgumentException("invalid input: " + string.charAt(i));
		}
		
		if (sb.toString().length() > 11)
			throw new IllegalArgumentException("cannot be more than 11 digits");
		
		return sb.toString();
	}

	/**
	 * 6. Given a phrase, count the occurrences of each word in that phrase.
	 * 
	 * For example for the input "olly olly in come free" olly: 2 in: 1 come: 1
	 * free: 1
	 * 
	 * @param string
	 * @return
	 */
	public Map<String, Integer> wordCount(String string) {
		Map<String,Integer> countMap = new HashMap<String,Integer>();
		
		string = string.replaceAll("\n", " ");
		string = string.replaceAll("[^a-zA-Z]", " ");	
		string = string.replaceAll("  ", " ");
		
		String[] words = string.split(" ");
		
		// step through every word
		for (int i = 0; i < words.length; i++) {
			// if the word is already in the hashmap, add one
			if (countMap.containsKey(words[i]))
				countMap.put(words[i], countMap.get(words[i]) + 1);
			// if the word is not in the hashmap, put it there
			else
				countMap.put(words[i], 1);
		}
		
		return countMap;
	}

	/**
	 * 7. Implement a binary search algorithm.
	 * 
	 * Searching a sorted collection is a common task. A dictionary is a sorted list
	 * of word definitions. Given a word, one can find its definition. A telephone
	 * book is a sorted list of people's names, addresses, and telephone numbers.
	 * Knowing someone's name allows one to quickly find their telephone number and
	 * address.
	 * 
	 * If the list to be searched contains more than a few items (a dozen, say) a
	 * binary search will require far fewer comparisons than a linear search, but it
	 * imposes the requirement that the list be sorted.
	 * 
	 * In computer science, a binary search or half-interval search algorithm finds
	 * the position of a specified input value (the search "key") within an array
	 * sorted by key value.
	 * 
	 * In each step, the algorithm compares the search key value with the key value
	 * of the middle element of the array.
	 * 
	 * If the keys match, then a matching element has been found and its index, or
	 * position, is returned.
	 * 
	 * Otherwise, if the search key is less than the middle element's key, then the
	 * algorithm repeats its action on the sub-array to the left of the middle
	 * element or, if the search key is greater, on the sub-array to the right.
	 * 
	 * If the remaining array to be searched is empty, then the key cannot be found
	 * in the array and a special "not found" indication is returned.
	 * 
	 * A binary search halves the number of items to check with each iteration, so
	 * locating an item (or determining its absence) takes logarithmic time. A
	 * binary search is a dichotomic divide and conquer search algorithm.
	 * 
	 */
	static class BinarySearch<T> {
		// whatever type we receive must extend comparable
		private List<? extends Comparable<T>> sortedList;
		
		public int indexOf(T t) {
			int startInd = 0, endInd = sortedList.size()-1;
			
			// keep going until traversal is finished (in which case it isn't there)
			while (startInd <= endInd) {
				int middleInd = (startInd + endInd) / 2;
				
				// if we found it, return now
				if (sortedList.get(middleInd).equals(t))
					return middleInd;
				// if t is bigger, chop off the first half
				else if (sortedList.get(middleInd).compareTo(t) < 0) {
					startInd = middleInd+1;
				}
				// if t is smaller, chop off the second half
				else {
					endInd = middleInd - 1;
				}
			}
							
			// if we reach here, the element is not present
			return -1;
		}

		public BinarySearch(List<? extends Comparable<T>> sortedList) {
			super();
			this.sortedList = sortedList;
		}

		public List<? extends Comparable<T>> getSortedList() {
			return sortedList;
		}

		public void setSortedList(List<? extends Comparable<T>> sortedList) {
			this.sortedList = sortedList;
		}
	}

	/**
	 * 8. Implement a program that translates from English to Pig Latin.
	 * 
	 * Pig Latin is a made-up children's language that's intended to be confusing.
	 * It obeys a few simple rules (below), but when it's spoken quickly it's really
	 * difficult for non-children (and non-native speakers) to understand.
	 * 
	 * Rule 1: If a word begins with a vowel sound, add an "ay" sound to the end of
	 * the word. 
	 * 
	 * Rule 2: If a word begins with a consonant sound, move it to the end
	 * of the word, and then add an "ay" sound to the end of the word. There are a
	 * few more rules for edge cases, and there are regional variants too.
	 * 
	 * See http://en.wikipedia.org/wiki/Pig_latin for more details.
	 * 
	 * @param string
	 * @return
	 */
	
	// a(97), e(101), i(105), o(111), u(117)
	public String toPigLatin(String string) {
		String[] words = string.split(" ");
		
		StringBuilder sb = new StringBuilder();
		
		// find pig latin translation for each individual word
		for (int j = 0; j < words.length; j++) {
			// if it starts with a vowel
			if (words[j].charAt(0) == 97 || words[j].charAt(0) == 101 || words[j].charAt(0) == 105 
					|| words[j].charAt(0) == 111 || words[j].charAt(0) == 117) {
				sb.append(words[j] + "ay");
			}
			// if it starts with a 'q' (which is a special case where 'u' is moved to the back)
			else if (words[j].charAt(0) == 113) {
				sb.append(words[j].substring(2) + "quay");
			}
			// if it doesn't start with a vowel
			else {
				
				int i = 1;
				
				// keep going until you find a vowel
				while (!(words[j].charAt(i) == 97 || words[j].charAt(i) == 101 || words[j].charAt(i) == 105 
						|| words[j].charAt(i) == 111 || words[j].charAt(i) == 117)) {
					i++;
				}
				
				sb.append(words[j].substring(i) + words[j].substring(0,i) + "ay");
			}
			
			// add a space at the end of the word, unless it is the last word
			if (j != words.length-1) {
				sb.append(" ");
			}
		}
		return sb.toString();
	}

	/**
	 * 9. An Armstrong number is a number that is the sum of its own digits each
	 * raised to the power of the number of digits.
	 * 
	 * For example:
	 * 
	 * 9 is an Armstrong number, because 9 = 9^1 = 9 10 is not an Armstrong number,
	 * because 10 != 1^2 + 0^2 = 2 153 is an Armstrong number, because: 153 = 1^3 +
	 * 5^3 + 3^3 = 1 + 125 + 27 = 153 154 is not an Armstrong number, because: 154
	 * != 1^3 + 5^3 + 4^3 = 1 + 125 + 64 = 190 Write some code to determine whether
	 * a number is an Armstrong number.
	 * 
	 * @param input
	 * @return
	 */
	public boolean isArmstrongNumber(int input) {
		String inputStr = input + "";
		int power = inputStr.length();
		int total = 0;
		
		// step through every digit, accumulating each calculation
		for (int i = 0; i < power; i++) {
			total += Math.pow(Character.getNumericValue(inputStr.charAt(i)), power);
		}
		
		// if they match, yes it is armstrong
		return total == input;
	}

	/**
	 * 10. Compute the prime factors of a given natural number.
	 * 
	 * A prime number is only evenly divisible by itself and 1.
	 * 
	 * Note that 1 is not a prime number.
	 * 
	 * @param l
	 * @return
	 */
	public List<Long> calculatePrimeFactorsOf(long target) {
		List<Long> primeFact = new ArrayList<Long>();
		
		// starting point for finding prime factor
		long i = 2;
		while(target != 1) {
			// if its a prime, check to see if it divides the target
			if (isPrime(i)) {
				// keep going until this prime doesn't divide the target anymore
				while((target != 0) && (target % i == 0)) {
					// if it divides, store it and move on
					primeFact.add(i);
					target = target / i;
				}
			}
			i++;
		}
		return primeFact;
	}
	
	public boolean isPrime(long num) {
        // check if any number divides our given number, up to half
        for(long i = 2; i <= num/2; i++)
        {
            // if true, this number is not prime
            if(num % i == 0)
            {
                return false;
            }
        }
        return true;
	}
	

	/**
	 * 11. Create an implementation of the rotational cipher, also sometimes called
	 * the Caesar cipher.
	 * 
	 * The Caesar cipher is a simple shift cipher that relies on transposing all the
	 * letters in the alphabet using an integer key between 0 and 26. Using a key of
	 * 0 or 26 will always yield the same output due to modular arithmetic. The
	 * letter is shifted for as many values as the value of the key.
	 * 
	 * The general notation for rotational ciphers is ROT + <key>. The most commonly
	 * used rotational cipher is ROT13.
	 * 
	 * A ROT13 on the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: nopqrstuvwxyzabcdefghijklm It is
	 * stronger than the Atbash cipher because it has 27 possible keys, and 25
	 * usable keys.
	 * 
	 * Ciphertext is written out in the same formatting as the input including
	 * spaces and punctuation.
	 * 
	 * Examples: ROT5 omg gives trl ROT0 c gives c ROT26 Cool gives Cool ROT13 The
	 * quick brown fox jumps over the lazy dog. gives Gur dhvpx oebja sbk whzcf bire
	 * gur ynml qbt. ROT13 Gur dhvpx oebja sbk whzcf bire gur ynml qbt. gives The
	 * quick brown fox jumps over the lazy dog.
	 */
	static class RotationalCipher {
		private int key;

		public RotationalCipher(int key) {
			super();
			this.key = key;
		}

		public String rotate(String string) {
			StringBuilder answer = new StringBuilder();
			int looped_i;
			
			
			for (int i = 0; i < string.length(); i++) {
				// if it is uppercase
				if (string.charAt(i) >= 65 && string.charAt(i) <= 90) {
					// convert letters to 0-25, do calc, convert back
					looped_i = ((string.charAt(i) - 65 + key) % 26) + 65;
					
					answer.append((char) (looped_i));
				}
				// if it is lowercase
				else if (string.charAt(i) >= 97 && string.charAt(i) <= 122) {
					// convert letters to 0-25, do calc, convert back
					looped_i = ((string.charAt(i) - 97 + key) % 26) + 97;
					
					answer.append((char) (looped_i));
				}
				// if it is something else, keep but don't change
				else
					answer.append(string.charAt(i));
			}
			
			return answer.toString();
		}

	}

	/**
	 * 12. Given a number n, determine what the nth prime is.
	 * 
	 * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see
	 * that the 6th prime is 13.
	 * 
	 * If your language provides methods in the standard library to deal with prime
	 * numbers, pretend they don't exist and implement them yourself.
	 * 
	 * @param i
	 * @return
	 */
	public int calculateNthPrime(int n) {
		// Note: I wrote the "isPrime" method already for problem 10
		
		if (n < 1)
			throw new IllegalArgumentException("input cannot be an undefined prime");
		
		
        int currPrimeCheck = 1;
        
        // keep going until we have found n prime numbers
        while (n != 0) {
        	currPrimeCheck++;
	        if (isPrime(currPrimeCheck)) {
	        	n--;
	        }
        }
        return currPrimeCheck;
	}

	/**
	 * 13 & 14. Create an implementation of the atbash cipher, an ancient encryption
	 * system created in the Middle East.
	 * 
	 * The Atbash cipher is a simple substitution cipher that relies on transposing
	 * all the letters in the alphabet such that the resulting alphabet is
	 * backwards. The first letter is replaced with the last letter, the second with
	 * the second-last, and so on.
	 * 
	 * An Atbash cipher for the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: zyxwvutsrqponmlkjihgfedcba It is a
	 * very weak cipher because it only has one possible key, and it is a simple
	 * monoalphabetic substitution cipher. However, this may not have been an issue
	 * in the cipher's time.
	 * 
	 * Ciphertext is written out in groups of fixed length, the traditional group
	 * size being 5 letters, and punctuation is excluded. This is to make it harder
	 * to guess things based on word boundaries.
	 * 
	 * Examples Encoding test gives gvhg Decoding gvhg gives test Decoding gsvjf
	 * rxpyi ldmul cqfnk hlevi gsvoz abwlt gives thequickbrownfoxjumpsoverthelazydog
	 *
	 */
	static class AtbashCipher {

		/**
		 * Question 13
		 * 
		 * @param string
		 * @return
		 */
		
		// first half: 97-109, second half: 110-122
		public static String encode(String string) {
			string = string.toLowerCase();
			StringBuilder sb = new StringBuilder();
			int offset;
			
			// we only need to keep characters and numbers
			string = string.replaceAll("[^0-9A-Za-z]", "");
			
			// step through each character
			for (int i = 0; i < string.length(); i++) {
				// separate at every 5 characters
				if (i != 0 && i % 5 == 0)
					sb.append(" ");
				
				// if its in the first half of the alphabet
				if (string.charAt(i) <= 109 && string.charAt(i) >= 97) {
					offset = string.charAt(i) - 97;
					sb.append((char)(122 - offset));
				}
				// if its in the second half of the alphabet
				else if (string.charAt(i) <= 122 && string.charAt(i) >= 110) {
					offset = Math.abs(string.charAt(i) - 122);
					sb.append((char)(97 + offset));
				}
				// if its a number, include but don't change
				else if (string.charAt(i) >= 48 && string.charAt(i) <= 57)
					sb.append(string.charAt(i));
			}
			
			return sb.toString();
		}

		/**
		 * Question 14
		 * 
		 * @param string
		 * @return
		 */
		public static String decode(String string) {
			StringBuilder sb = new StringBuilder();
			int offset;
			
			string = string.replaceAll(" ", "");
			
			// step through each character
			for (int i = 0; i < string.length(); i++) {				
				// if its in the first half of the alphabet
				if (string.charAt(i) <= 109 && string.charAt(i) >= 97) {
					offset = string.charAt(i) - 97;
					sb.append((char)(122 - offset));
				}
				// if its in the second half of the alphabet
				else if (string.charAt(i) <= 122 && string.charAt(i) >= 110) {
					offset = Math.abs(string.charAt(i) - 122);
					sb.append((char)(97 + offset));
				}
				// if its a number, include but don't change
				else if (string.charAt(i) >= 48 && string.charAt(i) <= 57)
					sb.append(string.charAt(i));
			}
			
			return sb.toString();
		}
	}

	/**
	 * 15. The ISBN-10 verification process is used to validate book identification
	 * numbers. These normally contain dashes and look like: 3-598-21508-8
	 * 
	 * ISBN The ISBN-10 format is 9 digits (0 to 9) plus one check character (either
	 * a digit or an X only). In the case the check character is an X, this
	 * represents the value '10'. These may be communicated with or without hyphens,
	 * and can be checked for their validity by the following formula:
	 * 
	 * (x1 * 10 + x2 * 9 + x3 * 8 + x4 * 7 + x5 * 6 + x6 * 5 + x7 * 4 + x8 * 3 + x9
	 * * 2 + x10 * 1) mod 11 == 0 If the result is 0, then it is a valid ISBN-10,
	 * otherwise it is invalid.
	 * 
	 * Example Let's take the ISBN-10 3-598-21508-8. We plug it in to the formula,
	 * and get:
	 * 
	 * (3 * 10 + 5 * 9 + 9 * 8 + 8 * 7 + 2 * 6 + 1 * 5 + 5 * 4 + 0 * 3 + 8 * 2 + 8 *
	 * 1) mod 11 == 0 Since the result is 0, this proves that our ISBN is valid.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isValidIsbn(String string) {
		string = string.replaceAll("-", "");
		
		int total = 0;
		int multCount = 10;
		for (int i = 0; i < string.length()-1; i++) {
			// if its a digit
			if (string.charAt(i) >= 48 && string.charAt(i) <= 57) {
				total += (string.charAt(i)-48) * multCount;
				multCount--;
			}
			else
				return false;
		}
		// if the final character is an X
		if (string.charAt(string.length()-1) == 88)
			total += 10;
		else if (string.charAt(string.length()-1) >= 48 && string.charAt(string.length()-1) <= 57)
			total += (string.charAt(string.length()-1)-48) * multCount;
		else
			return false;
		
		return total % 11 == 0;
	}

	/**
	 * 16. Determine if a sentence is a pangram. A pangram (Greek: παν γράμμα, pan
	 * gramma, "every letter") is a sentence using every letter of the alphabet at
	 * least once. The best known English pangram is:
	 * 
	 * The quick brown fox jumps over the lazy dog.
	 * 
	 * The alphabet used consists of ASCII letters a to z, inclusive, and is case
	 * insensitive. Input will not contain non-ASCII symbols.
	 * 
	 * @param string
	 * @return
	 */
	
	// lowercase: 97-109
	public boolean isPangram(String string) {
		string = string.replaceAll(" ", "");
		
		boolean []alphabet = new boolean[26];
		
		string = string.toLowerCase();
		
		int counter = 0;
		
		//
		for (int i = 0; i < string.length(); i++) {
			if(alphabet[string.charAt(i)-97] == false) {
				alphabet[string.charAt(i)-97] = true;
				counter++;
			}
		}
		
		return counter == 26;
	}

	/**
	 * 17. Calculate the moment when someone has lived for 10^9 seconds.
	 * 
	 * A gigasecond is 109 (1,000,000,000) seconds.
	 * 
	 * @param given
	 * @return
	 */
	public Temporal getGigasecondDate(Temporal given) {
		// calculate days, hours, seconds from 1,000,000,000 seconds
		LocalDateTime answer;
		if (given instanceof LocalDate) {
			answer = ((LocalDate) given).atStartOfDay();
		}
		else {
			answer = (LocalDateTime) given;
		}
		answer = answer.plusSeconds(1000000000);
		
		return answer;
	}

	/**
	 * 18. Given a number, find the sum of all the unique multiples of particular
	 * numbers up to but not including that number.
	 * 
	 * If we list all the natural numbers below 20 that are multiples of 3 or 5, we
	 * get 3, 5, 6, 9, 10, 12, 15, and 18.
	 * 
	 * The sum of these multiples is 78.
	 * 
	 * @param i
	 * @param set
	 * @return
	 */
	public int getSumOfMultiples(int target, int[] set) {
		int answer = 0;
		
		// create hashmap for quick access to the appropriate value
		Map<Integer, Boolean> hasEncountered = new HashMap<Integer, Boolean>();
		
		int curr;
		// step through all multiples
		for (int i = 0; i < set.length; i++) {
			// for each multiple, find all instances and log'em
			curr = set[i];
			while (curr < target) {
				if (hasEncountered.get(curr) == null) {
					answer += curr;
					hasEncountered.put(curr, true);
				}
				curr += set[i];
			}
		}
		return answer;
	}

	/**
	 * 19. Given a number determine whether or not it is valid per the Luhn formula.
	 * 
	 * The Luhn algorithm is a simple checksum formula used to validate a variety of
	 * identification numbers, such as credit card numbers and Canadian Social
	 * Insurance Numbers.
	 * 
	 * The task is to check if a given string is valid.
	 * 
	 * Validating a Number Strings of length 1 or less are not valid. Spaces are
	 * allowed in the input, but they should be stripped before checking. All other
	 * non-digit characters are disallowed.
	 * 
	 * Example 1: valid credit card number 1 4539 1488 0343 6467 The first step of
	 * the Luhn algorithm is to double every second digit, starting from the right.
	 * We will be doubling
	 * 
	 * 4_3_ 1_8_ 0_4_ 6_6_ If doubling the number results in a number greater than 9
	 * then subtract 9 from the product. The results of our doubling:
	 * 
	 * 8569 2478 0383 3437 Then sum all of the digits:
	 * 
	 * 8+5+6+9+2+4+7+8+0+3+8+3+3+4+3+7 = 80 If the sum is evenly divisible by 10,
	 * then the number is valid. This number is valid!
	 * 
	 * Example 2: invalid credit card number 1 8273 1232 7352 0569 Double the second
	 * digits, starting from the right
	 * 
	 * 7253 2262 5312 0539 Sum the digits
	 * 
	 * 7+2+5+3+2+2+6+2+5+3+1+2+0+5+3+9 = 57 57 is not evenly divisible by 10, so
	 * this number is not valid.
	 * 
	 * @param string
	 * @return
	 */
	
	/*
	 * sum all digits, but double every other digit
	 * note: if doubled digit is greater than 9, then subtract 9
	 * return result mod 10 is 0
	 * 
	 * '0' = 48
	 */
	public boolean isLuhnValid(String string) {
		string = string.replaceAll(" ", "");
		System.out.println(string);
		int answer = 0;
		
		boolean lock = true;
		// get sum of appropriate value for each digit
		for (int i = 0; i < string.length(); i++) {
			if (string.charAt(i) < 48 || string.charAt(i) > 57)
				return false;
			// we need to double every other digit
			if (lock) {
				answer+=(string.charAt(i)-48);
				lock = !lock;
			}
			else {
				if ((string.charAt(i)-48)*2 > 9)
					answer+=(string.charAt(i)-48)*2-9;
				else
					answer+=(string.charAt(i)-48)*2;
				lock = !lock;
			}
		}
		
		return (answer % 10 == 0);
	}

	/**
	 * 20. Parse and evaluate simple math word problems returning the answer as an
	 * integer.
	 * 
	 * Add two numbers together.
	 * 
	 * What is 5 plus 13?
	 * 
	 * 18
	 * 
	 * Now, perform the other three operations.
	 * 
	 * What is 7 minus 5?
	 * 
	 * 2
	 * 
	 * What is 6 multiplied by 4?
	 * 
	 * 24
	 * 
	 * What is 25 divided by 5?
	 * 
	 * 5
	 * 
	 * @param string
	 * @return
	 */
	/*
	 * 117 == plus
	 * 110 = minus
	 * 118 = divide
	 * 108 = multiply
	 */
	public int solveWordProblem(String string) {
		int number1, number2, operator;
		
		int i = 8;
		
		// grab first number
		while (string.charAt(i) != 32) {
			i++;
		}
		number1 = Integer.parseInt(string.substring(8, i));
	
		// unique operator identifier
		operator = string.charAt(i+3);
		
		// grab second number
		while (!((string.charAt(i) >= 48 && string.charAt(i) <= 57) || string.charAt(i) == 45)) {
			i++;
		}
		number2 = Integer.parseInt(string.substring(i, string.length()-1));

		switch (operator) {
	        case 117:  return number1 + number2;
	        case 110:  return number1 - number2;
	        case 118:  return number1 / number2;
	        case 108:  return number1 * number2;
		}
		return 0;
	}

}
