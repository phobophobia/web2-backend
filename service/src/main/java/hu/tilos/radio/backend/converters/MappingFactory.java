package hu.tilos.radio.backend.converters;

import javax.inject.Inject;
import javax.inject.Named;
import java.text.SimpleDateFormat;

/**
 * Factory to create mappers.
 */
@Named
public class MappingFactory {


    public static SimpleDateFormat YYYYMMDD = new SimpleDateFormat("yyyyMMdd");

    public static SimpleDateFormat HHMMSS = new SimpleDateFormat("HHmmss");

    @Inject
    TagUtil tagUtil;

    @Inject
    HTMLSanitizer sanitizer;

    @Inject
    StrictHTMLSanitizer strictSanitizer;

    private String uploadUrl = "http://tilos.hu/upload/";
}
