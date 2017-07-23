package org.rebeam.sortable

import japgolly.scalajs.react._
import japgolly.scalajs.react.raw.ReactElement

import scala.scalajs.js
import scala.language.higherKinds

object SortableHandle {
  /**
    * Wrap another component
    *
    * @param wrappedComponent The wrapped component itself
    * @tparam P The type of Props of the wrapped component
    * @return A component wrapping the wrapped component
    */

  def wrap[P, CT[_, _]](wrappedComponent: GenericComponent[P, CT, _]): P => JsComponent.Unmounted[js.Object, Null] = {
    (wrappedProps) => {
      val reactElement = js.Dynamic.global.Sortable.SortableHandle(wrappedComponent.raw).asInstanceOf[ReactElement]
      val component = JsComponent[js.Object, Children.None, Null](reactElement)
      val mergedProps = js.Dynamic.literal()
      mergedProps.updateDynamic("a")(wrappedProps.asInstanceOf[js.Any])
      component(mergedProps.asInstanceOf[js.Object])
    }
  }
}
