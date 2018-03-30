package chapter19_enumerated;

public interface Food {
	enum Appetizer implements Food {
		SALAD, SOUP,
	}
	enum Coffee implements Food{
		TEA, JAVA,
	}

}
