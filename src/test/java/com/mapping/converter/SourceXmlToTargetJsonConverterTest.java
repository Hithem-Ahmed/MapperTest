package com.mapping.converter;

import com.mapping.com.mapping.util.TestUtil;
import com.mapping.mapper.SourceTargetMapper;
import org.junit.Test;
import java.io.File;

import static uk.org.webcompere.modelassert.json.JsonAssertions.*;

public class SourceXmlToTargetJsonConverterTest {

    private final SourceXmlToTargetJsonConverter sourceXmlToTargetJsonConverter = new SourceXmlToTargetJsonConverter();
    private final String testsFolderName = "/SourceXmlToTargetJsonConverterTest";

    @Test
    public void givenXmlWhenConvertThenJsonTargetIsCreatedAsExpected() throws Exception {
        //arrange
        File xmlInput = new File(TestUtil.getFileNameFromResourcePath(testsFolderName +"/Source.xml"));
        File jsonOutput = new File(TestUtil.getFileNameFromResourcePath(testsFolderName + "/target.json"));

        //act
        String json = sourceXmlToTargetJsonConverter.convertXmlFileToJson(xmlInput, SourceTargetMapper.INSTANCE);

        //assert
        assertJson(json)
                .where()
                .keysInAnyOrder()
                .arrayInAnyOrder()
                .isEqualTo(jsonOutput);
    }


    @Test
    public void givenXmlWhenConvertThenJsonTargetIsCreatedAsExpectedWithDifferentOrder() throws Exception {
        //arrange
        File xmlInput = new File(TestUtil.getFileNameFromResourcePath(testsFolderName +"/Source.xml"));
        File jsonOutput = new File(TestUtil.getFileNameFromResourcePath(testsFolderName + "/targetWithDifferentOrder.json"));

        //act
        String json = sourceXmlToTargetJsonConverter.convertXmlFileToJson(xmlInput, SourceTargetMapper.INSTANCE);

        //assert
        assertJson(json)
                .where()
                .keysInAnyOrder()
                .arrayInAnyOrder()
                .isEqualTo(jsonOutput);
    }

    @Test
    public void givenXmlWhenConvertThenJsonTargetIsCreatedDifferentThanWrongExpectation() throws Exception {
        //arrange
        File xmlInput = new File(TestUtil.getFileNameFromResourcePath(testsFolderName +"/Source.xml"));
        File jsonOutput = new File(TestUtil.getFileNameFromResourcePath(testsFolderName + "/targetWithWrongValues.json"));

        //act
        String json = sourceXmlToTargetJsonConverter.convertXmlFileToJson(xmlInput, SourceTargetMapper.INSTANCE);

        //assert
        assertJson(json)
                .where()
                .keysInAnyOrder()
                .arrayInAnyOrder()
                .isNotEqualTo(jsonOutput);
    }
}
