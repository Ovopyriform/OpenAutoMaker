package org.openautomaker.base.configuration.datafileaccessors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.File;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openautomaker.base.configuration.Filament;
import org.openautomaker.environment.preference.printer.FilamentsPathPreference;
import org.openautomaker.test_library.GuiceExtension;

import jakarta.inject.Inject;
import javafx.collections.ObservableList;

@ExtendWith(GuiceExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FilamentContainerTest {

	@Inject
	private FilamentsPathPreference filamentsPathPreference;

	@Inject
	private FilamentContainer filamentContainer;

	@BeforeAll
	public void clearUserFilamentDir() {
		Path userFilamentDir = filamentsPathPreference.getUserValue();
		File[] files = userFilamentDir.toFile().listFiles();
		if (files != null && files.length > 0) {
			for (File filamentFile : files) {
				System.out.println("Delete file " + filamentFile);
				filamentFile.delete();
			}
		}
	}

	@Test
	public void testCreateNewFilamentAndDelete() {
		String NEW_ID = "U1234567";
		filamentContainer.reload();
		ObservableList<Filament> userFilaments = filamentContainer.getUserFilamentList();
		ObservableList<Filament> completeFilaments = filamentContainer.getCompleteFilamentList();
		int numFilaments = completeFilaments.size();
		Filament greenABSFilament = filamentContainer.getFilamentByID("RBX-ABS-GR499");

		Filament filamentCopy = greenABSFilament.clone();
		filamentCopy.setFilamentID(NEW_ID);
		filamentCopy.setFriendlyFilamentName("GREEN COPY");

		filamentContainer.saveFilament(filamentCopy);
		assertEquals(numFilaments + 1, completeFilaments.size());
		assertEquals(1, userFilaments.size());

		filamentContainer.deleteFilament(filamentCopy);
		assertEquals(0, userFilaments.size());
		assertEquals(numFilaments, completeFilaments.size());
		Filament retrievedFilament = filamentContainer.getFilamentByID(NEW_ID);
		assertNull(retrievedFilament);
	}

	@Test
	public void testCreateNewFilamentAndChangeAndSave() {
		String NEW_ID = "U1234568";
		filamentContainer.reload();
		ObservableList<Filament> userFilaments = filamentContainer.getUserFilamentList();
		ObservableList<Filament> completeFilaments = filamentContainer.getCompleteFilamentList();
		int numFilaments = completeFilaments.size();
		Filament greenABSFilament = filamentContainer.getFilamentByID("RBX-ABS-GR499");

		Filament filamentCopy = greenABSFilament.clone();
		filamentCopy.setFilamentID(NEW_ID);
		filamentCopy.setFriendlyFilamentName("GREEN COPY");

		filamentContainer.saveFilament(filamentCopy);
		assertEquals(numFilaments + 1, completeFilaments.size());
		assertEquals(1, userFilaments.size());

		filamentCopy.setBedTemperature(67);
		filamentContainer.saveFilament(filamentCopy);
		filamentContainer.reload();

		Filament editedFilament = filamentContainer.getFilamentByID(NEW_ID);
		assertEquals(67, editedFilament.getBedTemperature());
		assertNotSame(filamentCopy, editedFilament);

		filamentContainer.deleteFilament(filamentCopy);

	}

	@Test
	public void testCreateNewFilamentAndChangeNameAndSave() {
		String NEW_ID = "U1234569";
		filamentContainer.reload();
		ObservableList<Filament> userFilaments = filamentContainer.getUserFilamentList();
		ObservableList<Filament> completeFilaments = filamentContainer.getCompleteFilamentList();
		int numFilaments = completeFilaments.size();
		Filament greenABSFilament = filamentContainer.getFilamentByID("RBX-ABS-GR499");

		Filament filamentCopy = greenABSFilament.clone();
		filamentCopy.setFilamentID(NEW_ID);
		filamentCopy.setFriendlyFilamentName("GREEN COPY");

		filamentContainer.saveFilament(filamentCopy);
		assertEquals(numFilaments + 1, completeFilaments.size());
		assertEquals(1, userFilaments.size());

		String newName = "GREEN COPY 3";
		filamentCopy.setFriendlyFilamentName(newName);
		filamentContainer.saveFilament(filamentCopy);
		assertEquals(numFilaments + 1, completeFilaments.size());
		assertEquals(1, userFilaments.size());

		filamentContainer.reload();

		Filament editedFilament = filamentContainer.getFilamentByID(NEW_ID);
		assertEquals(newName, editedFilament.getFriendlyFilamentName());
		assertNotSame(filamentCopy, editedFilament);

		assertEquals(numFilaments + 1, completeFilaments.size());
		assertEquals(1, userFilaments.size());

		filamentContainer.deleteFilament(filamentCopy);
	}
}
