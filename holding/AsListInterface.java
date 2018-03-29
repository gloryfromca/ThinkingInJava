package holding;

import java.util.Arrays;
import java.util.List;

class Snow {
}

class Powder extends Snow {
}

class Light extends Powder {
}

class Heavy extends Powder {
}

class Crusty extends Snow {
}

class Slush extends Snow {
}

public class AsListInterface {

	public static void main(String[] args) {
		List<Snow> snows1 = Arrays.asList(new Slush(), new Light(), new Powder());
		List<Snow> snows2 = Arrays.asList(new Heavy(), new Light());
		System.out.println(snows2.get(1));
	}

}
