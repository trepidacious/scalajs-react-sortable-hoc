package org.rebeam.sortable

import japgolly.scalajs.react._

import scala.scalajs.js

object SortableElement {
  case class Props(index: Int,
                   collection: Int = 0,
                   disabled: Boolean = false)

  /**
    * Wrap another component
    * @param wrappedComponent The wrapped component itself
    * @tparam P               The type of Props of the wrapped component
    * @return                 A component wrapping the wrapped component...
    */
  def wrap[P](wrappedComponent: ReactComponentC[P,_,_,_]): Props => P => ReactComponentU_ = {

    val componentFactoryFunction = js.Dynamic.global.SortableElement(wrappedComponent.factory)
    val componentFactory = React.asInstanceOf[js.Dynamic].createFactory(componentFactoryFunction)

    (props) => (wrappedProps) => componentFactory(js.Dynamic.literal(
      "index" -> props.index,
      "collection" -> props.collection,
      "disabled" -> props.disabled,
      "v" -> wrappedProps.asInstanceOf[js.Any]
    )).asInstanceOf[ReactComponentU_]
  }
}
