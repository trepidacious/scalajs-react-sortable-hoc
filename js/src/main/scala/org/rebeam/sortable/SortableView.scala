package org.rebeam.sortable

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

object SortableView {

  import japgolly.scalajs.react.vdom.SvgTags._
  import japgolly.scalajs.react.vdom.SvgAttrs._

  private val handleGrip = ScalaComponent.builder[Unit]("HandleGrip")
    .renderStatic(
      <.div(
        ^.className := "react-sortable-handle",
        svg(
          ^.className := "react-sortable-handle-svg",
          viewBox := "0 0 24 24",
          path(d := "M9,8c1.1,0,2-0.9,2-2s-0.9-2-2-2S7,4.9,7,6S7.9,8,9,8z M9,10c-1.1,0-2,0.9-2,2s0.9,2,2,2s2-0.9,2-2S10.1,10,9,10z M9,16c-1.1,0-2,0.9-2,2s0.9,2,2,2s2-0.9,2-2S10.1,16,9,16z"),
          path(d := "M15,8c1.1,0,2-0.9,2-2s-0.9-2-2-2s-2,0.9-2,2S13.9,8,15,8z M15,10c-1.1,0-2,0.9-2,2s0.9,2,2,2s2-0.9,2-2S16.1,10,15,10z M15,16c-1.1,0-2,0.9-2,2s0.9,2,2,2s2-0.9,2-2S16.1,16,15,16z")
        )
      )
    )
    .build

  val handle = SortableHandle.wrap(handleGrip)(())

}
