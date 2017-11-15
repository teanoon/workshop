package com.example.research.schedule;

import org.junit.Assert;
import org.junit.Test;

import java.util.regex.Matcher;

public class WebsiteRankImporterTest {

    @Test
    public void testRegex() {
        Matcher matcher = WebsiteRankImporter.recordPattern.matcher("1234,baidu.com");
        Assert.assertTrue(matcher.find());

        Assert.assertEquals("1234", matcher.group("rank"));
        Assert.assertEquals("baidu.com", matcher.group("url"));
    }

}