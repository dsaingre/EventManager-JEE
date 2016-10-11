package fr.lidadi.jee.eventmanager.framework.router.data;

import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;

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
}