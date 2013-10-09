import _root_.sbt.IO
import java.lang.management.ManagementFactory
import java.lang.System._
import java.net.URL
import sbt._
import sbt.Configuration
import sbt.Keys._
import scala._
import scala.Predef._
import scala.collection.JavaConversions._

object BuildSettings {
  import Project._
  import Defaults._

  lazy val buildSettings = {
    Seq(
      scalaVersion := "2.10.2",
      libraryDependencies := Seq("org.scalatest" % "scalatest_2.10" % "1.9.2")
    ) ++
      unitTestSettings ++
      integrationTestSettings ++
      functionalTestSettings
  }

  lazy val UnitTests = config("unit") extend(Test)
  lazy val unitTestSettings = createTestSettings("unit", UnitTests)

  lazy val IntegrationTests = config("integration") extend(Test)
  lazy val integrationTestSettings = createTestSettings("integration", IntegrationTests)

  lazy val FunctionalTests = config("functional") extend(Test)
  lazy val functionalTestSettings = createTestSettings("functional", FunctionalTests)

  private def createTestSettings(testType: String, testConfiguration: Configuration) = {
    println("creating settings for " + testType + " : " + testConfiguration)
    inConfig(testConfiguration)(Defaults.testSettings) ++
    (sourceDirectory in testConfiguration <<= baseDirectory(_ / "src" / "test")) ++
    (sources in testConfiguration := ( ((file("src") / "test") ** "*.scala").get).filter(shouldInclude(_, testType))  ) ++
    (classDirectory in testConfiguration <<= crossTarget(_ / "test-classes"))      
    // (testOptions in testConfiguration := Seq(Tests.Filter(name => name contains "."+testType+".")))
    // (testOptions in testConfiguration += Tests.Argument("-oDF"))
  }
    
  def shouldInclude(testFile: File, testType: String) = {
    val path = testFile.toURI.toString
    val result = path.contains("/" + testType + "/") || path.contains("/shared/") || path.contains("ScalatePackage")
    printf("%s: should include %s => %s %n", testType, path, result)
    result
  }  
}

object CasperBuild extends Build {

  import BuildSettings._

  lazy val core = Project("core", file("."))  
    .configs(Test)
    .settings(buildSettings : _*)
}

