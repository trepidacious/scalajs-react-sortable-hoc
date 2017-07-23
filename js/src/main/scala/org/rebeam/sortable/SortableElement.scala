package org.rebeam.sortable

import japgolly.scalajs.react.raw.ReactElement
import japgolly.scalajs.react.{Children, GenericComponent, JsComponent, _}

import scala.scalajs.js
import scala.language.higherKinds

object SortableElement {

  @js.native
  trait Props extends js.Object {
    var index: Int = js.native
    var collection: Int = js.native
    var disabled: Boolean = js.native
  }

  object Props {
    def apply(index: Int,
              collection: Int = 0,
              disabled: Boolean = false): Props =
      js.Dynamic.literal(index = index, collection = collection, disabled = disabled).asInstanceOf[Props]
  }

  /**
    * Wrap another component
    * @param wrappedComponent The wrapped component itself
    * @tparam P               The type of Props of the wrapped component
    * @return                 A component wrapping the wrapped component...
    */
  def wrap[P, CT[_,_]](wrappedComponent: GenericComponent[P, CT, _]): Props => P => JsComponent.Unmounted[js.Object, Null] = {
    (props) => (wrappedProps) => {
      val reactElement = js.Dynamic.global.Sortable.SortableElement(wrappedComponent.raw).asInstanceOf[ReactElement]
      val component = JsComponent[js.Object, Children.None, Null](reactElement)
      val mergedProps = js.Dynamic.literal()
      mergedProps.updateDynamic("index")(props.index)
      mergedProps.updateDynamic("collection")(props.collection)
      mergedProps.updateDynamic("disabled")(props.disabled)
      mergedProps.updateDynamic("a")(wrappedProps.asInstanceOf[js.Any])
      component(mergedProps.asInstanceOf[js.Object])
    }
  }
}
