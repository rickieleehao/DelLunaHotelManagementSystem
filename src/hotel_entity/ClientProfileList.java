package hotel_entity;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import hotel_interface.IClientData;

public class ClientProfileList implements IClientData {

	List<ClientProfile> clientProfileList;

	public ClientProfileList(String fileName) { // xz -assume using txt.file, delimeter ="#"
		try {
			String filename=fileName+".txt";
			File cpl=new File(filename);
			
			if(cpl.isFile()) {
				Scanner inFile=new Scanner(cpl);
				
				while(inFile.hasNext()) {
					String str=inFile.nextLine();
					String[]tempList=str.split("#");
					String NRIC = tempList[0];
					String firstName=tempList[1];
					String lastName=tempList[2];
					String sGender=tempList[3];
					String address=tempList[4];
					Gender gender=Gender.valueOf(sGender);
					
					ClientProfile clientProfile=new ClientProfile(NRIC,firstName,lastName,gender,address);
					clientProfileList.add(clientProfile);
				}
				inFile.close();
			}
			else {
				throw new IOException();
			}
		}
		catch(IOException ioe){
			System.out.println(ioe);
		}
	}

	@Override
	public void addClientProfile(hotel_entity.ClientProfile newClientProfile) { // xz
		// TODO Auto-generated method stub
		clientProfileList.add(newClientProfile);

	}

	@Override
	public ClientProfile getClientProfile(String NRIC) { // xz
		// TODO Auto-generated method stub
		ClientProfile clientProfile = null;
		
		for(int i=0;i<clientProfileList.size();i++) {
			
			if(clientProfileList.get(i).getNRIC()==NRIC) {
				
				clientProfile=clientProfileList.get(i);
				break;
			}
		}
		return clientProfile;
	}

	@Override
	public boolean searchClientProfile(String NRIC) { // xz
		// TODO Auto-generated method stub
		boolean isFound=false;
		
		for(int i=0;i<clientProfileList.size();i++) {
			
			if(clientProfileList.get(i).getNRIC()==NRIC) {
				isFound=true;
				break;
			}
		}
		return isFound;
	}
}
