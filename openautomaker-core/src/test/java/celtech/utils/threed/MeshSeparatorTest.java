package celtech.utils.threed;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.io.File;
import java.net.URL;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openautomaker.test_library.GuiceExtension;
import org.openautomaker.ui.inject.importer.STLImporterFactory;

import celtech.utils.threed.importers.stl.STLFileParsingException;
import jakarta.inject.Inject;
import javafx.scene.shape.TriangleMesh;

@ExtendWith(GuiceExtension.class)
public class MeshSeparatorTest {

	@Inject
	private STLImporterFactory stlImporterFactory;

	@Test
	public void testMeshOfOneObject() throws STLFileParsingException {
		URL stlURL = this.getClass().getResource("/pyramid1.stl");
		File singleObjectSTLFile = new File(stlURL.getFile());
		TriangleMesh mesh = stlImporterFactory.create().processBinarySTLData(singleObjectSTLFile);
		List<TriangleMesh> meshes = MeshSeparator.separate(mesh);
		assertEquals(1, meshes.size());
		assertSame(mesh, meshes.get(0));
	}

	@Test
	public void testMeshOfTwoObjects() throws STLFileParsingException {
		URL stlURL = this.getClass().getResource("/twodiscs.stl");
		File singleObjectSTLFile = new File(stlURL.getFile());
		TriangleMesh mesh = stlImporterFactory.create().processBinarySTLData(singleObjectSTLFile);
		List<TriangleMesh> meshes = MeshSeparator.separate(mesh);
		assertEquals(2, meshes.size());
		// each sub mesh should have 176 faces
		assertEquals(176, meshes.get(0).getFaces().size() / 6);
		assertEquals(176, meshes.get(1).getFaces().size() / 6);
	}

}
