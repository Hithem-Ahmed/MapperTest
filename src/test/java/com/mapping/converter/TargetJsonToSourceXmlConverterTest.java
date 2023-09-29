package com.mapping.converter;

import com.mapping.com.mapping.util.TestUtil;
import com.mapping.mapper.SourceTargetMapper;
import org.junit.Test;
import org.xmlunit.builder.Input;
import org.xmlunit.diff.DefaultNodeMatcher;
import org.xmlunit.diff.ElementSelectors;

import static  org.xmlunit.assertj.XmlAssert.*;

import java.io.File;

public class TargetJsonToSourceXmlConverterTest {

    private final TargetJsonToSourceXmlConverter targetJsonToSourceXmlConverter = new TargetJsonToSourceXmlConverter();
    private final String testsFolderName = "/TargetJsonToSourceXmlConverterTest";

    @Test
    public void givenJsonWhenConvertThenSourceXmlIsCreated() throws Exception {

        //arrange
        File jsonInput = new File(TestUtil.getFileNameFromResourcePath(testsFolderName + "/target.json"));
        File xmlOutput = new File(TestUtil.getFileNameFromResourcePath(testsFolderName + "/source.xml"));

        //act
        String xml = targetJsonToSourceXmlConverter.convertJsonFileToXml(jsonInput, SourceTargetMapper.INSTANCE);

        //assert
        assertThat(xml).and(Input.from(xmlOutput))
                .normalizeWhitespace()
                .ignoreComments()
                .withNodeMatcher(new DefaultNodeMatcher(ElementSelectors.byName)) //ignore order
                .areSimilar();
    }

    @Test
    public void givenJsonWhenConvertThenSourceXmlIsCreatedAsExpectedWithDifferentOrder() throws Exception {

        //arrange
        File jsonInput = new File(TestUtil.getFileNameFromResourcePath(testsFolderName + "/target.json"));
        File xmlOutput = new File(TestUtil.getFileNameFromResourcePath(testsFolderName + "/sourceWithDifferentOrder.xml"));

        //act
        String xml = targetJsonToSourceXmlConverter.convertJsonFileToXml(jsonInput, SourceTargetMapper.INSTANCE);

        //assert
        assertThat(xml).and(Input.from(xmlOutput))
                .normalizeWhitespace()
                .ignoreComments()
                .withNodeMatcher(new DefaultNodeMatcher(ElementSelectors.byName)) //ignore order
                .areSimilar();
    }

    @Test
    public void givenJsonWhenConvertThenSourceXmlIsCreatedDifferentThanExpectedWithWrongValues() throws Exception {

        //arrange
        File jsonInput = new File(TestUtil.getFileNameFromResourcePath(testsFolderName + "/target.json"));
        File xmlOutput = new File(TestUtil.getFileNameFromResourcePath(testsFolderName + "/sourceWithDifferentValues.xml"));

        //act
        String xml = targetJsonToSourceXmlConverter.convertJsonFileToXml(jsonInput, SourceTargetMapper.INSTANCE);

        //assert
        assertThat(xml).and(Input.from(xmlOutput))
                .normalizeWhitespace()
                .ignoreComments()
                .withNodeMatcher(new DefaultNodeMatcher(ElementSelectors.byName)) //ignore order
                .areNotSimilar();
    }
}
