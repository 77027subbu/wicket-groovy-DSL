package wicket.groovy.core.traits

import org.apache.wicket.ajax.AjaxRequestTarget
import org.apache.wicket.markup.html.form.Form

/**
 * @author Eugene Kamenev @eugenekamenev
 */
trait WicketFormTrait<T> extends WicketComponentTrait<T> {
    void onSubmit() {
        if (override?.submit) {
            override.submit.call(this)
        } else {
            super.onSubmit()
        }
    }

    void onSubmit(AjaxRequestTarget target, Form<?> form) {
        if (this.override?.submit) {
            if (this.override.submit.maximumNumberOfParameters > 1) {
                this.override.submit.call(target, form)
            } else {
                this.override.submit.call(target)
            }
        } else {
            super.onSubmit(target, form)
        }
    }

    void onError(AjaxRequestTarget target, Form<?> form) {
        if (this.override?.error) {
            if (this.override.error.maximumNumberOfParameters > 1) {
                this.override.error.call(target, form)
            } else {
                this.override.error.call(target)
            }
        } else {
            super.onError(target, form)
        }
    }

    void onValidate() {
        if (override?.validate) {
            this.updateFormComponentModels();
            override.validate.call(this)
        } else {
            super.onValidate()
        }
    }

    void onError() {
        if (override?.error) {
            override.error.call(this)
        } else {
            super.onError()
        }
    }
}
