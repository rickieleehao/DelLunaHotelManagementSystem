package hotel_entity;

public enum Gender {
	Male, Female;

	public Gender selectGender(int option) {
		Gender gender;
		if (option == 1) {
			gender = Gender.Male;
		} else if (option == 2) {
			gender = Gender.Female;
		} else {
			throw new IllegalArgumentException("Invalid selection!");
		}

		return gender;
	}

	public void printGenderOption() {
		System.out.println("1. Male");
		System.out.println("2. Female");
		System.out.print("Pick a option ----> ");
	}

}