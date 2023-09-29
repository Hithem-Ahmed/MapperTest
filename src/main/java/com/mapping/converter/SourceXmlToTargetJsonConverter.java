package com.mapping.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.mapping.dto.Source;
import com.mapping.dto.Target;
import com.mapping.mapper.SourceTargetMapper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class SourceXmlToTargetJsonConverter {

    public String convertXmlFileToJson(File file, SourceTargetMapper mapper)  throws IOException {
        String xml = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        Source source = sourceXmlToObject(xml);
        Target target = mapper.sourceToTarget(source);
        return targetToJson(target);
    }

    private Source sourceXmlToObject(String xml) throws JsonProcessingException {
        return new XmlMapper().readValue(xml, Source.class);
    }

    private String targetToJson(Target target) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(target);
    }

}
