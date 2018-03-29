package enumerated;

import java.util.Iterator;

import com.sun.org.apache.regexp.internal.REUtil;

class Mail {
	enum GeneralDeliverty {
		OK, ERROR1, ERROR2
	}

	enum Scannability {
		OK, ERROR1, ERROR2
	}

	enum Readability {
		OK, ERROR1, ERROR2
	}

	enum FromAddress {
		OK, ERROR1, ERROR2
	}

	enum ToAddress {
		OK, ERROR1, ERROR2
	};

	GeneralDeliverty generalDeliverty;
	Scannability scannability;
	Readability readability;
	FromAddress fromAddress;
	ToAddress toAddress;

	static long counter = 0;
	private long id = counter++;

	@Override
	public String toString() {
		return "Mail " + id;
	}

	public String details() {
		return "generalDeliverty:" + generalDeliverty + ", scannability:" + scannability + ", readability:" + readability
				+ ", fromAddress:" + fromAddress + ", toAddress:" + toAddress;
	}

	public static Mail randomMail() {
		Mail m = new Mail();
		m.generalDeliverty = Enums.random(GeneralDeliverty.class);
		m.scannability = Enums.random(Scannability.class);
		m.readability = Enums.random(Readability.class);
		m.fromAddress = Enums.random(FromAddress.class);
		m.toAddress = Enums.random(ToAddress.class);
		return m;
	}

	public static Iterable<Mail> generator(final int count) {
		return new Iterable<Mail>() {
			int n = count;

			public Iterator<Mail> iterator() {
				return new Iterator<Mail>() {

					@Override
					public boolean hasNext() {
						return n-- > 0;
					}

					@Override
					public Mail next() {
						return randomMail();
					}

				};
			}

		};

	}

}

class PostOffice {
	enum MailHandler {
		GENERAL_DELIVERY {
			boolean handler(Mail m) {
				switch (m.generalDeliverty) {
				case OK:
					System.out.println("generalDeliverty is OK");
					return true;
				default:
					return false;
				}

			}
		},
		MACHINE_SCAN {
			boolean handler(Mail m) {
				switch (m.scannability) {
				case ERROR1:
					System.out.println("scannability failed!");
					return false;
				case ERROR2:
					System.out.println("scannability failed!");
					return false;
				default:
					switch (m.toAddress) {
					case OK:
						System.out.println("toAddress missed!");
						return true;
					default:
						return false;
					}
				}
			}
		},
		RETURN_SENDER {
			boolean handler(Mail m) {
				switch (m.fromAddress) {
				case OK:
					return true;
				default:
					System.out.println("fromAddress is missing!");
					return false;
				}
			}
		};
		abstract boolean handler(Mail m);
	}

}

public class ChainOfResponsibilityPattern {
	static void handle(Mail m) {
		for (PostOffice.MailHandler handler : PostOffice.MailHandler.values()) {
			if (handler.handler(m))
				return;
		}
		System.out.println(m + " is a dead letter");
	}

	public static void main(String[] args) {
		for (Mail mail : Mail.generator(10)) {
			System.out.println(mail.details());
			handle(mail);
			System.out.println("*******");

		}

	}
}
