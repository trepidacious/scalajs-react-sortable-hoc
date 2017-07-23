package org.rebeam.sortable

import japgolly.scalajs.react.raw.ReactElement
import japgolly.scalajs.react.{Callback, Children, GenericComponent, JsComponent, _}

import scala.scalajs.js
import scala.language.higherKinds

object SortableContainer {

  @js.native
  protected trait Permutation extends js.Object {
    val oldIndex: Int = js.native
    val newIndex: Int = js.native
    //Could have collection as well
  }

  @js.native
  trait Props extends js.Object {
    val axis: js.UndefOr[String] = js.native
    val lockAxis: js.UndefOr[String] = js.native
    val helperClass: js.UndefOr[String] = js.native
    val transitionDuration: js.UndefOr[Int] = js.native
    val pressDelay: js.UndefOr[Int] = js.native
    val distance: js.UndefOr[Int] = js.native
    //shouldCancelStart <- undef or a function from event to Boolean
    val useDragHandle: js.UndefOr[Boolean] = js.native
    val useWindowAsScrollContainer: js.UndefOr[Boolean] = js.native
    val hideSortableGhost: js.UndefOr[Boolean] = js.native
    val lockToContainerEdges: js.UndefOr[Boolean] = js.native
    //lockOffset <- really not sure what this is from docs - maybe a string like "50%"?
    //getContainer <- undef or function returning scrollable container element, function(wrappedInstance: React element): DOM element.
    //getHelperDimensions <- undef or function({node, index, collection})
    //Note this function actually gets "{oldIndex, newIndex, collection}, e", but we don't have much use for the other arguments
    val onSortEnd: js.Function1[Permutation, Unit] = js.native
    //onSortStart <- undef or function({node, index, collection}, event)
    //onSortMove <- undef or function(event)
  }

  object Props {
    def apply(axis: js.UndefOr[String] = js.undefined,
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
              onSortEnd: IndexChange => Callback = _ => Callback.empty
              //onSortStart <- undef or function({node, index, collection}, event)
              //onSortMove <- undef or function(event)
             ): Props =
      js.Dynamic.literal(
        axis = axis, lockAxis = lockAxis, helperClass = helperClass, transitionDuration = transitionDuration, pressDelay = pressDelay,
        distance = distance, useDragHandle = useDragHandle, useWindowAsScrollContainer = useWindowAsScrollContainer,
        hideSortableGhost = hideSortableGhost, lockToContainerEdges = lockToContainerEdges,
        onSortEnd = js.defined { p: Permutation => onSortEnd(IndexChange(p.oldIndex, p.newIndex)).runNow() }
      ).asInstanceOf[Props]
  }

  /**
    * Wrap another component
    *
    * @param wrappedComponent The wrapped component itself
    * @tparam P The type of Props of the wrapped component
    * @return A component wrapping the wrapped component...
    */
  def wrap[P, CT[_, _]](wrappedComponent: GenericComponent[P, CT, _]): Props => P => JsComponent.Unmounted[js.Object, Null] = {
    (props) =>
      (wrappedProps) => {
        val reactElement = js.Dynamic.global.Sortable.SortableContainer(wrappedComponent.raw).asInstanceOf[ReactElement]
        val component = JsComponent[js.Object, Children.None, Null](reactElement)
        val mergedProps = js.Dynamic.literal()
        mergedProps.updateDynamic("axis")(props.axis)
        mergedProps.updateDynamic("lockAxis")(props.lockAxis)
        mergedProps.updateDynamic("helperClass")(props.helperClass)
        mergedProps.updateDynamic("transitionDuration")(props.transitionDuration)
        mergedProps.updateDynamic("pressDelay")(props.pressDelay)
        mergedProps.updateDynamic("distance")(props.distance)
        mergedProps.updateDynamic("useDragHandle")(props.useDragHandle)
        mergedProps.updateDynamic("useWindowAsScrollContainer")(props.useWindowAsScrollContainer)
        mergedProps.updateDynamic("hideSortableGhost")(props.hideSortableGhost)
        mergedProps.updateDynamic("lockToContainerEdges")(props.lockToContainerEdges)
        mergedProps.updateDynamic("onSortEnd")(props.onSortEnd)
        mergedProps.updateDynamic("a")(wrappedProps.asInstanceOf[js.Any])
        component(mergedProps.asInstanceOf[js.Object])
      }
  }
}


