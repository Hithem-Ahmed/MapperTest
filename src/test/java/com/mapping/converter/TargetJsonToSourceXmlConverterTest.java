package com.mapping.converter;

import com.mapping.mapper.SourceTargetMapper;
import org.junit.Test;
import org.xmlunit.builder.Input;
import org.xmlunit.diff.DefaultNodeMatcher;
import org.xmlunit.diff.DifferenceEvaluators;
import org.xmlunit.diff.ElementSelectors;

import static  org.xmlunit.assertj.XmlAssert.*;

import java.io.File;

public class TargetJsonToSourceXmlConverterTest {

    TargetJsonToSourceXmlConverter targetJsonToSourceXmlConverter = new TargetJsonToSourceXmlConverter();

    @Test
    public void givenJsonWhenConvertThenXmlTargetIsCreated() throws Exception {

        //arrange
        File jsonInput = new File(getClass().getResource("/target.json").getFile());
        File xmlOutput = new File(getClass().getResource("/source.xml").getFile());

        //act
        String xml = targetJsonToSourceXmlConverter.convertJsonFileToXml(jsonInput, SourceTargetMapper.INSTANCE);

        //assert
        assertThat(xml).and(Input.from(xmlOutput))
                .normalizeWhitespace()
                .ignoreComments()
                .withNodeMatcher(new DefaultNodeMatcher(ElementSelectors.byName)) //ignore order
                .areSimilar();
    }
}
