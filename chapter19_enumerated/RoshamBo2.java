package chapter19_enumerated;

import static chapter19_enumerated.Outcome.*;

public enum RoshamBo2 implements Competitor<RoshamBo2> {
	PAPER(DRAW, LOSE, WIN), SCISSORS(WIN, DRAW, LOSE), Rock(LOSE, WIN, DRAW);
	private Outcome vPAPER, vSCISSORS, vRock;

	private RoshamBo2(Outcome vPAPER, Outcome vSCISSORS, Outcome vRock) {
		this.vPAPER = vPAPER;
		this.vSCISSORS = vSCISSORS;
		this.vRock = vRock;
	}

	@Override
	public Outcome compete(RoshamBo2 it) {
		switch (it) {
		case PAPER:
			return vPAPER;
		case SCISSORS:
			return vSCISSORS;
		case Rock:
			return vRock;
		}
		return null;
	}

	public static void main(String[] args) {
		RoShamBo.play(RoshamBo2.class, 20);
	}

}
