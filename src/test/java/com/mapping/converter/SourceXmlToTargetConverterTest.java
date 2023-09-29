package com.mapping.converter;

import com.mapping.mapper.SourceTargetMapper;
import org.junit.Test;
import java.io.File;

import static uk.org.webcompere.modelassert.json.JsonAssertions.*;

public class SourceXmlToTargetConverterTest {
    SourceXmlToTargetJsonConverter sourceXmlToTargetJsonConverter = new SourceXmlToTargetJsonConverter();

    @Test
    public void givenXmlWhenConvertThenJsonTargetIsCreated() throws Exception {
        //arrange
        File xmlInput = new File(getClass().getResource("/source.xml").getFile());
        File jsonOutput = new File(getClass().getResource("/target.json").getFile());

        //act
        String json = sourceXmlToTargetJsonConverter.convertXmlFileToJson(xmlInput, SourceTargetMapper.INSTANCE);

        //assert
        assertJson(json)
                .where()
                .keysInAnyOrder()
                .arrayInAnyOrder()
                .isEqualTo(jsonOutput);
    }
}
