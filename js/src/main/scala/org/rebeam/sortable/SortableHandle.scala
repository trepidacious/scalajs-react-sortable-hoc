package org.rebeam.sortable

import japgolly.scalajs.react._

import scala.scalajs.js

object SortableHandle {
  /**
    * Wrap another component
    * @param wrappedComponent The wrapped component itself
    * @tparam P               The type of Props of the wrapped component
    * @return                 A component wrapping the wrapped component
    */
  def wrap[P](wrappedComponent: ReactComponentC[P,_,_,_]): P => ReactComponentU_ = {

    val componentFactoryFunction = js.Dynamic.global.SortableHandle(wrappedComponent.factory)
    val componentFactory = React.asInstanceOf[js.Dynamic].createFactory(componentFactoryFunction)

    (wrappedProps) => componentFactory(js.Dynamic.literal(
      "v" -> wrappedProps.asInstanceOf[js.Any]
    )).asInstanceOf[ReactComponentU_]
  }
}
