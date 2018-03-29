package generics;

//interfaces about SuperPower
interface SuperPower {

}

interface xRayVision extends SuperPower {
	void seeByXray();
}

interface SuperHearing extends SuperPower {
	void hearingFromRemote();
}

interface SuperSmell extends SuperPower {
	void semllEverything();
}

interface SuperSmellHearing extends SuperSmell, SuperHearing {

}

// classes about superHero owning superPower.
class SuperHero<T extends SuperPower> {
	T power;

	SuperHero(T power) {
		this.power = power;
	}

	public T getPower() {
		return power;
	}

}

class HeroWithSuperSmell<T extends SuperSmell> extends SuperHero<T> {

	HeroWithSuperSmell(T power) {
		super(power);
	}

	void smell() {
		power.semllEverything();
	};

}

class HeroWithSuperSmellHearing<T extends SuperSmell & SuperHearing> extends SuperHero<T> {

	// can't pass compilation
	// class HeroWithSuperSmellHearing<T> extends SuperHero<T extends
	// SuperSmellHearing > {

	// base-class don't have default constructor.
	HeroWithSuperSmellHearing(T power) {
		super(power);
	}

	void smell() {
		power.semllEverything();
	};

	void hear() {
		power.hearingFromRemote();
	}

	void both() {
		power.semllEverything();
		power.hearingFromRemote();
	}

}

class AnotherHeroWithSuperSmellHearing<T extends SuperSmellHearing> extends SuperHero<T> {

	// base-class don't have default constructor.
	AnotherHeroWithSuperSmellHearing(T power) {
		super(power);
	}

	void smell() {
		power.semllEverything();
	};

	void hear() {
		power.hearingFromRemote();
	}

	void both() {
		power.semllEverything();
		power.hearingFromRemote();
	}

}

class DogBoy extends HeroWithSuperSmellHearing<SuperSmellHearing> {

	DogBoy(SuperSmellHearing power) {
		super(power);
	}

}

class AnotherDogBoy<T extends SuperSmellHearing> extends HeroWithSuperSmellHearing<T> {

	// compile-error
	// AnotherDogBoy(SuperSmellHearing power) {

	AnotherDogBoy(T power) {
		super(power);
	}

}

public class SuperHeroBattle {

	static <T extends SuperHearing> void hearing(SuperHero<T> superHero) {
		superHero.getPower().hearingFromRemote();
	}
	static <T extends SuperSmellHearing> void smellAndHearing(SuperHero<T> superHero) {
		superHero.getPower().semllEverything();
		superHero.getPower().hearingFromRemote();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
