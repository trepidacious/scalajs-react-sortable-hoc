package org.rebeam.sortable

import japgolly.scalajs.react._

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

import scala.language.higherKinds

object SortableElement {

  @js.native
  @JSImport("react-sortable-hoc", "SortableElement", "Sortable.SortableElement")
  private object SortableElementFacade extends js.Object {
    def apply(wrapped: js.Any): js.Any = js.native
  }

  @js.native
  trait Props extends js.Object {
    var index: Int = js.native
    var collection: Int = js.native
    var disabled: Boolean = js.native
    var key: js.UndefOr[Key] = js.native
  }

  object Props {
    def apply(index: Int,
              collection: Int = 0,
              disabled: Boolean = false,
              key: js.UndefOr[Key] = js.undefined): Props =
      js.Dynamic.literal(index = index, collection = collection, disabled = disabled, key = key.asInstanceOf[js.Any]).asInstanceOf[Props]
  }

  /**
    * Wrap another component
    * @param wrappedComponent The wrapped component itself
    * @tparam P               The type of Props of the wrapped component
    * @return                 A component wrapping the wrapped component...
    */
  def wrap[P, CT[_,_]](wrappedComponent: GenericComponent[P, CT, _]): Props => P => JsComponent.Unmounted[js.Object, Null] = {
    val reactElement = SortableElementFacade(wrappedComponent.raw)
    val component = JsComponent[js.Object, Children.None, Null](reactElement)
    props => wrappedProps => {
      val mergedProps = js.Dynamic.literal()
      mergedProps.updateDynamic("index")(props.index)
      mergedProps.updateDynamic("collection")(props.collection)
      mergedProps.updateDynamic("disabled")(props.disabled)
      mergedProps.updateDynamic("key")(props.key.asInstanceOf[js.Any])
      mergedProps.updateDynamic("a")(wrappedProps.asInstanceOf[js.Any])
      component(mergedProps.asInstanceOf[js.Object])
    }
  }
}
