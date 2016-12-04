name := "scalajs-react-sortable-hoc root project"

version in ThisBuild := "0.1-SNAPSHOT"

organization in ThisBuild := "org.rebeam"

scalaVersion in ThisBuild := "2.11.8"

scalacOptions in ThisBuild ++= Seq(
  "-feature",
  "-deprecation",
  "-encoding", "UTF-8",
  "-unchecked",
  "-Xfatal-warnings",
  "-Xlint"
)

val scalajsReactVersion = "0.11.3"

lazy val root = project.in(file(".")).
  aggregate(scalaJSReactSortableHOCJS, scalaJSReactSortableHOCJVM).
  settings(
    publish := {},
    publishLocal := {}
  )

lazy val scalaJSReactSortableHOC = crossProject.in(file(".")).

  //Settings for all projects
  settings(
    name := "tree-material-ui",
    version := "0.1-SNAPSHOT",
    libraryDependencies ++= Seq()

  //Settings specific to JVM
  ).jvmSettings(
    libraryDependencies ++= Seq()

  //Settings specific to JS
  ).jsSettings(
    //Scalajs dependencies that are used on the client only
    libraryDependencies ++= Seq(      
    	"com.github.japgolly.scalajs-react" %%% "core" % scalajsReactVersion,
      "com.github.japgolly.scalajs-react" %%% "extra" % scalajsReactVersion
		)
  )

lazy val scalaJSReactSortableHOCJVM = scalaJSReactSortableHOC.jvm
lazy val scalaJSReactSortableHOCJS = scalaJSReactSortableHOC.js