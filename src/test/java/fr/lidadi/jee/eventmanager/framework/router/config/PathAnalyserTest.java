package fr.lidadi.jee.eventmanager.framework.router.config;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by damien on 11/10/2016.
 */
public class PathAnalyserTest {

    PathAnalyser pathAnalyser;

    @Before
    public void setUp() throws Exception {
        pathAnalyser = new PathAnalyser();
    }

    @Test
    public void analysePathTest_noParam() throws Exception {
        LinkedList<String> oracle = new LinkedList<>();
        oracle.add("/tests");
        assertEquals(oracle, pathAnalyser.analysePath("/tests"));
    }

    @Test
    public void analysePathTest_oneParam() throws Exception {
        LinkedList<String> oracle = new LinkedList<>();
        oracle.add("/tests/");
        oracle.add("{id}");
        assertEquals(oracle, pathAnalyser.analysePath("/tests/{id}"));
    }

    @Test
    public void analysePathTest_twoParams() throws Exception {
        LinkedList<String> oracle = new LinkedList<>();
        oracle.add("/tests/");
        oracle.add("{testId}");
        oracle.add("/entities/");
        oracle.add("{entityId}");
        assertEquals(oracle, pathAnalyser.analysePath("/tests/{testId}/entities/{entityId}"));
    }


    @Test
    public void analyseClassPathTest_params() throws Exception {

        Map<String, AllowedUrlType> params = new HashMap<>();
        params.put("name", AllowedUrlType.STRING);
        params.put("age", AllowedUrlType.INT);
        ClassPath oracle = new ClassPath("com.test.Bonjour", "add", params);

        ClassPath classPath = pathAnalyser.analyseClassPath("com.test.Bonjour.add(STRING name, INT age)");

        assertEquals(oracle, classPath);
    }

    @Test
    public void analyseClassPathTest_noParams() throws Exception {

        Map<String, AllowedUrlType> params = new HashMap<>();
        ClassPath oracle = new ClassPath("com.test.Bonjour", "add", params);

        ClassPath classPath = pathAnalyser.analyseClassPath("com.test.Bonjour.add()");

        assertEquals(oracle, classPath);
    }
}