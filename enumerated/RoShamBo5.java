package enumerated;

import java.util.EnumMap;

import com.sun.javafx.tk.Toolkit.Task;

public enum RoShamBo5 implements Competitor<RoShamBo5> {
	PAPER, SCISSORS, ROCK;
	static EnumMap<RoShamBo5, EnumMap<RoShamBo5, Outcome>> table = new EnumMap<RoShamBo5, EnumMap<RoShamBo5, Outcome>>(
			RoShamBo5.class);

	static void initRow(RoShamBo5 it, Outcome vPAPER, Outcome vSCISSORS, Outcome vROCK) {
		EnumMap<RoShamBo5, Outcome> row = table.get(it);
		row.put(RoShamBo5.PAPER, vPAPER);
		row.put(RoShamBo5.SCISSORS, vSCISSORS);
		row.put(RoShamBo5.ROCK, vROCK);
	}

	static {
		for (RoShamBo5 rsb : values()) {
			table.put(rsb, new EnumMap<RoShamBo5, Outcome>(RoShamBo5.class));
		}
		initRow(PAPER, Outcome.DRAW, Outcome.LOSE, Outcome.WIN);
		initRow(SCISSORS, Outcome.WIN, Outcome.DRAW, Outcome.LOSE);
		initRow(ROCK, Outcome.LOSE, Outcome.WIN, Outcome.DRAW);

	}

	@Override
	public Outcome compete(RoShamBo5 competitor) {
		return table.get(this).get(competitor);
	}

	public static void main(String[] args) {
		RoShamBo.play(RoShamBo5.class, 10);
	}

}
