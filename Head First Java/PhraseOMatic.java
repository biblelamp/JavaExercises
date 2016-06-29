public class PhraseOMatic {

	public static void main(String[] args) {

	// make three sets of words to choose from. Add your own!
	String[] wordListOne = { "круглосуточный", "трехзвенный", "30000-футовый", "взаимный", "обоюдный выигрыш", "фронтзнд", "на основе веб-технологий", "проникающий", "умный", "шесть сигм", "метод критического пути", "динамичный" };
	String[] wordListTwo = { "уполномоченный", "трудный", "чистый продукт", "ориентированный", "центральный", "распределенный", "кластеризованный", "фирменный", "нестандартный ум", "позиционированный", "сетевой", "сфокусированный", "использованный с выгодой", "выровненный", "нацеленный на", "общий", "совместный", "ускоренный" };
	String[] wordListThree = { "процесс", "пункт разгрузки", "выход из положения", "тип структуры", "талант", "подход", "уровень завоеванного внимания", "портал", "период времени", "обзор", "образец", "пункт следования" };

	// find out how many words are in each list
	int oneLength = wordListOne.length;
	int twoLength = wordListTwo.length;
	int threeLength = wordListThree.length;
	
	// generate three random numbers
	int rand1 = (int) (Math.random() * oneLength);
	int rand2 = (int) (Math.random() * twoLength) ;
	int rand3 = (int) (Math.random() * threeLength);

	// now build a phrase
	String phrase = wordListOne [rand1] + " " + wordListTwo[rand2] + " " + wordListThree[rand3];
	
	// print out the phrase
	System.out.println("What we need is a " + phrase);
	}
}