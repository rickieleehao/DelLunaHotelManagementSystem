package hotel.domain.entity;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import hotel.domain.IEntity.IClientData;

public class ClientProfileList implements IClientData {

	private List<ClientProfile> clientProfileList;
	private String fileName;

	public ClientProfileList(String fileName) {
		this.clientProfileList = new ArrayList<ClientProfile>();
		this.fileName = fileName;
		try {
			Scanner s = new Scanner(new File(fileName));
			s.useDelimiter("(#|\r\n|\r|\n)");
			while (s.hasNext()) {
				this.clientProfileList.add(new ClientProfile(s.next(), s.next(), s.next(), s.next(), s.next()));
			}
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void updateToFile() {
		String fileName = this.fileName;
		try {
			FileWriter $fileEmptier = new FileWriter(fileName);
			FileWriter $writer = new FileWriter(fileName, true);
			$fileEmptier.write("");
			$fileEmptier.close();
			for (ClientProfile c : this.clientProfileList) {
				$writer.write(c.getNRIC() + "#" + c.getFirstName() + "#" + c.getLastName() + "#"
						+ c.getGender().toString() + "#" + c.getAddress() + "\r\n");
			}
			$writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<ClientProfile> getClientProfileList() {
		return this.clientProfileList;
	}

	@Override
	public void addClientProfile(ClientProfile newClientProfile) {
		clientProfileList.add(newClientProfile);
		updateToFile();
	}

	@Override
	public ClientProfile getClientProfile(String NRIC) {
		if (NRIC.length() != 12) {
			throw new IllegalArgumentException("NRIC must have 12 digits!");
		} else if (!(NRIC.matches("[0-9]+"))) {
			throw new IllegalArgumentException("NRIC must consist number only!");
		}

		ClientProfile clientProfile = null;
		for (int i = 0; i < clientProfileList.size(); i++) {
			if (clientProfileList.get(i).getNRIC().equals(NRIC)) {
				clientProfile = clientProfileList.get(i);
				break;
			}
		}
		if (clientProfile == null) {
			throw new NullPointerException("Client profile not found");
		}
		return clientProfile;
	}
}
