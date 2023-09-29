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

public class TargetJsonToSourceXmlConverter {

    public String convertJsonFileToXml(File file, SourceTargetMapper mapper)  throws IOException {
        String json = readSFile(file);
        Target target = targetJsonToObject(json);
        Source source = mapper.targetToSource(target);
        return targetToXml(source);
    }

    private String readSFile(File file) throws IOException {
        return FileUtils.readFileToString(file, StandardCharsets.UTF_8);
    }

    private Target targetJsonToObject(String xml) throws JsonProcessingException {
        return new ObjectMapper().readValue(xml, Target.class);
    }

    private String targetToXml(Source source) throws JsonProcessingException {
        return new XmlMapper().writeValueAsString(source);
    }
}
