import sbtcrossproject.CrossPlugin.autoImport.crossProject

name := "scalajs-react-sortable-hoc root"

version in ThisBuild := "0.0.2"

organization in ThisBuild := "org.rebeam"

scalaVersion in ThisBuild := "2.12.6"

crossScalaVersions in ThisBuild := Seq("2.11.12", "2.12.6")

scalacOptions in ThisBuild ++= Seq(
  "-feature",
  "-deprecation",
  "-encoding", "UTF-8",
  "-unchecked",
  "-Xfatal-warnings",
  "-Xlint"
)

val scalajsReactVersion = "1.2.3"

lazy val root = project.in(file(".")).
  aggregate(scalaJSReactSortableHOCJS, scalaJSReactSortableHOCJVM).
  settings(
    publish := {},
    publishLocal := {}
  )

lazy val scalaJSReactSortableHOC = crossProject(JSPlatform, JVMPlatform).in(file(".")).

  //Settings for all projects
  settings(
    name := "scalajs-react-sortable-hoc"
  ).jsSettings(
    //Scalajs dependencies that are used on the client only
    libraryDependencies ++= Seq(
    	"com.github.japgolly.scalajs-react" %%% "core" % scalajsReactVersion,
      "com.github.japgolly.scalajs-react" %%% "extra" % scalajsReactVersion
		)
  )

lazy val scalaJSReactSortableHOCJVM = scalaJSReactSortableHOC.jvm
lazy val scalaJSReactSortableHOCJS = scalaJSReactSortableHOC.js