package org.rebeam.sortable

import japgolly.scalajs.react._

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

import scala.language.higherKinds

object SortableHandle {
  @js.native
  @JSImport("react-sortable-hoc", "SortableHandle", "Sortable.SortableHandle")
  private object SortableHandleFacade extends js.Object {
    def apply(wrapped: js.Any): js.Any = js.native
  }

  /**
    * Wrap another component
    *
    * @param wrappedComponent The wrapped component itself
    * @tparam P The type of Props of the wrapped component
    * @return A component wrapping the wrapped component
    */
  def wrap[P, CT[_, _]](wrappedComponent: GenericComponent[P, CT, _]): P => JsComponent.Unmounted[js.Object, Null] = {
    val reactElement = SortableHandleFacade(wrappedComponent.raw)
    val component = JsComponent[js.Object, Children.None, Null](reactElement)
    wrappedProps => {
      val mergedProps = js.Dynamic.literal()
      mergedProps.updateDynamic("a")(wrappedProps.asInstanceOf[js.Any])
      component(mergedProps.asInstanceOf[js.Object])
    }
  }
}
