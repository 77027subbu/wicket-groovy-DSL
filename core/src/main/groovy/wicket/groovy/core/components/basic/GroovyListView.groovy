package wicket.groovy.core.components.basic

import org.apache.wicket.markup.html.list.ListItem
import org.apache.wicket.markup.html.list.ListView
import org.apache.wicket.model.IModel
import wicket.groovy.WicketDSL
import wicket.groovy.core.traits.WicketComponentTrait

class GroovyListView<T> extends ListView<T> implements WicketComponentTrait {

    GroovyListView(String id, Map<String, Closure> override = null, Closure closure = null) {
        super(id)
        this.override = override
        this.closure = closure
    }

    GroovyListView(String id, IModel<? extends List<? extends T>> model, Map<String, Closure> override = null, Closure closure = null) {
        super(id, model)
        this.override = override
        this.closure = closure
    }

    GroovyListView(String id, List<? extends T> list, Map<String, Closure> override = null, Closure closure = null) {
        super(id, list)
        this.override = override
        this.closure = closure
    }

    @Override
    protected void populateItem(ListItem<T> item) {
        use(WicketDSL) {
            this.closure?.call(item)
        }
    }
}
