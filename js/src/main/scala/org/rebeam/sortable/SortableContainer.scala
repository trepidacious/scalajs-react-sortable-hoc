package org.rebeam.sortable

import japgolly.scalajs.react._

import scala.scalajs.js

object SortableContainer {
  @js.native
  trait PermutationJS extends js.Object {
    val oldIndex: Int                              = js.native
    val newIndex: Int                              = js.native
    //Could have collection as well
  }

  case class Props(
    axis: js.UndefOr[String] = js.undefined,
    lockAxis: js.UndefOr[String] = js.undefined,
    helperClass: js.UndefOr[String] = js.undefined,
    transitionDuration: js.UndefOr[Int] = js.undefined,
    pressDelay: js.UndefOr[Int] = js.undefined,
    distance: js.UndefOr[Int] = js.undefined,
    //shouldCancelStart <- undef or a function from event to Boolean

    useDragHandle: js.UndefOr[Boolean] = js.undefined,
    useWindowAsScrollContainer: js.UndefOr[Boolean] = js.undefined,
    hideSortableGhost: js.UndefOr[Boolean] = js.undefined,
    lockToContainerEdges: js.UndefOr[Boolean] = js.undefined,
    //lockOffset <- really not sure what this is from docs - maybe a string like "50%"?
    //getContainer <- undef or function returning scrollable container element, function(wrappedInstance: React element): DOM element.
    //getHelperDimensions <- undef or function({node, index, collection})

    //Note this function actually gets "{oldIndex, newIndex, collection}, e", but we don't have much use for the other arguments
    onSortEnd: IndexChange => Callback = p => Callback{}
    //onSortStart <- undef or function({node, index, collection}, event)
    //onSortMove <- undef or function(event)
  ) {
    private[sortable] def toJS = {
      val p = js.Dynamic.literal()
      axis.foreach(p.updateDynamic("axis")(_))
      lockAxis.foreach(p.updateDynamic("lockAxis")(_))
      helperClass.foreach(p.updateDynamic("helperClass")(_))
      transitionDuration.foreach(p.updateDynamic("transitionDuration")(_))
      pressDelay.foreach(p.updateDynamic("pressDelay")(_))
      distance.foreach(p.updateDynamic("distance")(_))
      useDragHandle.foreach(p.updateDynamic("useDragHandle")(_))
      useWindowAsScrollContainer.foreach(p.updateDynamic("useWindowAsScrollContainer")(_))
      hideSortableGhost.foreach(p.updateDynamic("hideSortableGhost")(_))
      lockToContainerEdges.foreach(p.updateDynamic("lockToContainerEdges")(_))

      def permutationFromJS(p: PermutationJS): IndexChange = IndexChange(p.oldIndex, p.newIndex)

      val onSortEndJS: (PermutationJS) => Unit = pjs => {
        val p = permutationFromJS(pjs)
        onSortEnd(p).runNow()
      }

      p.updateDynamic("onSortEnd")(onSortEndJS)

      //TODO other callbacks

      p
    }
  }

  /**
    * Wrap another component
    * @param wrappedComponent The wrapped component itself
    * @tparam P               The type of Props of the wrapped component
    * @return                 A component wrapping the wrapped component...
    */
  def wrap[P](wrappedComponent: ReactComponentC[P,_,_,_]): Props => P => ReactComponentU_ = {

    val componentFactoryFunction = js.Dynamic.global.SortableContainer(wrappedComponent.factory)
    val componentFactory = React.asInstanceOf[js.Dynamic].createFactory(componentFactoryFunction)

    (props) => (wrappedProps) => {
      val p = props.toJS
      p.updateDynamic("v")(wrappedProps.asInstanceOf[js.Any])
      componentFactory(p).asInstanceOf[ReactComponentU_]
    }
  }

}


