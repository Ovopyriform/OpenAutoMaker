package celtech.coreUI.components;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openautomaker.test_library.GuiceExtension;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import javafx.scene.control.Hyperlink;
import javafx.scene.text.Text;
import javafx.stage.Stage;

@ExtendWith({ GuiceExtension.class, ApplicationExtension.class })
public class HyperlinkedLabelTest {

	@Start
	public void start(Stage stage) {

	}

	@Test
	public void testReplaceText_plaintextOnly() {
		String newText = "Some plain text";
		HyperlinkedLabel instance = new HyperlinkedLabel();
		instance.replaceText(newText);

		assertEquals(1, instance.getChildren().size());
		assertTrue(instance.getChildren().get(0) instanceof Text);
		assertEquals(((Text) instance.getChildren().get(0)).getText(), newText);
	}

	@Test
	public void testReplaceText_plaintextAndHyperlink() {
		String newText = "Robox firmware update <a href=\"https://robox.freshdesk.com/solution/categories/1000090870/folders/1000214277/articles/1000180224-the-filament-isn-t-moving-as-expected\">Other article</a>";
		String expectedTextContent = "Robox firmware update ";
		String expectedHyperlinkContent = "Other article";
		HyperlinkedLabel instance = new HyperlinkedLabel();
		instance.replaceText(newText);

		assertEquals(2, instance.getChildren().size());
		assertTrue(instance.getChildren().get(0) instanceof Text);
		assertTrue(instance.getChildren().get(1) instanceof Hyperlink);
		assertEquals(((Text) instance.getChildren().get(0)).getText(), expectedTextContent);
		assertEquals(((Hyperlink) instance.getChildren().get(1)).getText(), expectedHyperlinkContent);
	}

	@Test
	public void testReplaceText_plaintextAndTwoHyperlinks() {
		String newText = "Robox firmware update <a href=\"https://robox.freshdesk.com/support/home\">Robox solutions</a>more text<a href=\"https://robox.freshdesk.com/solution/categories/1000090870/folders/1000214277/articles/1000180224-the-filament-isn-t-moving-as-expected\">Other article</a>";

		String expectedTextContent1 = "Robox firmware update ";
		String expectedTextContent2 = "more text";
		String expectedHyperlinkContent1 = "Robox solutions";
		String expectedHyperlinkContent2 = "Other article";
		HyperlinkedLabel instance = new HyperlinkedLabel();
		instance.replaceText(newText);

		assertEquals(4, instance.getChildren().size());
		assertTrue(instance.getChildren().get(0) instanceof Text);
		assertTrue(instance.getChildren().get(1) instanceof Hyperlink);
		assertTrue(instance.getChildren().get(2) instanceof Text);
		assertTrue(instance.getChildren().get(3) instanceof Hyperlink);
		assertEquals(((Text) instance.getChildren().get(0)).getText(), expectedTextContent1);
		assertEquals(((Hyperlink) instance.getChildren().get(1)).getText(), expectedHyperlinkContent1);
		assertEquals(((Text) instance.getChildren().get(2)).getText(), expectedTextContent2);
		assertEquals(((Hyperlink) instance.getChildren().get(3)).getText(), expectedHyperlinkContent2);
	}

	@Test
	public void testReplaceText_HyperlinkOnly() {
		String newText = "<a href=\"https://robox.freshdesk.com/solution/categories/1000090870/folders/1000214277/articles/1000180224-the-filament-isn-t-moving-as-expected\">Other article</a>";
		String expectedHyperlinkContent = "Other article";
		HyperlinkedLabel instance = new HyperlinkedLabel();
		instance.replaceText(newText);

		assertEquals(1, instance.getChildren().size());
		assertTrue(instance.getChildren().get(0) instanceof Hyperlink);
		assertEquals(((Hyperlink) instance.getChildren().get(0)).getText(), expectedHyperlinkContent);
	}

	@Test
	public void testReplaceText_HyperlinkInMiddleOfText() {
		final String NEW_TEXT = "PRECEDING TEXT <a href=\"https://example.web.page/home\">LINK</a> FOLLOWING TEXT";

		final String EXPECTED_TEXT_CONTENT_1 = "PRECEDING TEXT ";
		final String EXPECTED_TEXT_CONTENT_2 = " FOLLOWING TEXT";
		final String EXPECTED_HYPERLINK_CONTENT = "LINK";

		HyperlinkedLabel hyperLinkLabel = new HyperlinkedLabel();
		hyperLinkLabel.replaceText(NEW_TEXT);

		assertEquals(3, hyperLinkLabel.getChildren().size());
		assertTrue(hyperLinkLabel.getChildren().get(0) instanceof Text, "The first element of the TextFlow should be plain text");
		assertTrue(hyperLinkLabel.getChildren().get(1) instanceof Hyperlink, "The second element of the TextFlow should be a hyperlink");
		assertTrue(hyperLinkLabel.getChildren().get(2) instanceof Text, "The third element of the TextFlow should be plain text");

		assertEquals(((Text) hyperLinkLabel.getChildren().get(0)).getText(), EXPECTED_TEXT_CONTENT_1);
		assertEquals(((Hyperlink) hyperLinkLabel.getChildren().get(1)).getText(), EXPECTED_HYPERLINK_CONTENT);
		assertEquals(((Text) hyperLinkLabel.getChildren().get(2)).getText(), EXPECTED_TEXT_CONTENT_2);
	}
}
