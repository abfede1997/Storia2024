package com.example;

import com.example.client.Sweng2024Test;
import com.google.gwt.junit.tools.GWTTestSuite;
import junit.framework.Test;
import junit.framework.TestSuite;

public class Sweng2024Suite extends GWTTestSuite {
  public static Test suite() {
    TestSuite suite = new TestSuite("Tests for Sweng2024");
    suite.addTestSuite(Sweng2024Test.class);
    return suite;
  }
}
